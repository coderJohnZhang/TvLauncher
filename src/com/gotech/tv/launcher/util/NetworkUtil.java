package com.gotech.tv.launcher.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;

import com.gotech.tv.launcher.service.ContextManager;
import com.gotech.tv.launcher.service.NetStateTracker;
import com.mt.util.plat.PlatformManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;
import android.util.Patterns;

public class NetworkUtil {
    final static private String TAG = "NetworkUtil";

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                Log.e(TAG, "isNetworkAvailable: Unavailable");
                return false;
            } else {
                return connectivity.getActiveNetworkInfo().isConnected();
            }
        } catch (Exception e) {
            Log.e(TAG, "isNetworkAvailable Exception:" + e.getMessage());
        }

        return false;
    }

    public static boolean isWifiAvailable(Context context) {
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mWiFiNetworkInfo != null) {
                    return mWiFiNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
                Log.e(TAG, "isWifiAvailable Exception:" + e.getMessage());
            }

        }
        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mMobileNetworkInfo != null) {
                    return mMobileNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
                Log.e(TAG, "isWifiAvailable Exception:" + e.getMessage());
            }
        }
        return false;
    }

    public static int getNetworkType(Context context) {
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (PlatformManager.CUR_PLATFORM == PlatformManager.PLAT_HISI) {
                    return getHisiActiveNetwork(mConnectivityManager);
                } else {
                    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                    if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                        return mNetworkInfo.getType();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "isWifiAvailable Exception:" + e.getMessage());
            }

        }
        return ConnectivityManager.TYPE_WIFI;
    }

    /**
     * CN：取有线网的MAC地址
     *
     * @param context
     * @return
     */
    public static final String getEth0Addr(Context context) {

        String ethernetMac = "";

        try {

            StringBuffer fileData = new StringBuffer(1000);
            BufferedReader reader = new BufferedReader(new FileReader("/sys/class/net/eth0/address"));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            ethernetMac = fileData.toString();
            ethernetMac = ethernetMac.toUpperCase().substring(0, 17);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ethernetMac.equals("") && ethernetMac.length() < 10) {
            ethernetMac = getWifiMac(context);
            ;
        }
        return ethernetMac;

    }

    /**
     * CN：获取Wi-Fi的MAC地址
     *
     * @param context
     * @returnd
     */
    public static final String getWifiMac(Context context) {
        String fileName = "mac.txt";
        String macConfigFile = ContextManager.LOCAL_CONFIG_PATH + fileName;
        String savedMacAddr = "";
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File path1 = new File(macConfigFile);
                if (path1.exists()) {
                    savedMacAddr = FileUtils.getInstance().readTxtFile(macConfigFile);
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        if (savedMacAddr.equals("")) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            if (info == null || info.getMacAddress() == null || info.getMacAddress().isEmpty()) {
                savedMacAddr = getEth0Addr(context);
            } else {
                savedMacAddr = info.getMacAddress();
            }
            savedMacAddr = savedMacAddr.toLowerCase(Locale.US);

            try {
                FileUtils.getInstance().writeTxtFile(savedMacAddr, new File(macConfigFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 随机生机一个
        if (savedMacAddr.equals("")) {
            Random random = new Random();
            int temp1 = random.nextInt();
            int temp2 = random.nextInt();
            if (temp1 < 0)
                temp1 = -temp1;
            if (temp2 < 0)
                temp2 = -temp2;

            savedMacAddr = "44:37:e6:a7:" + Integer.toHexString(temp1 % 256).toLowerCase(Locale.US) + ":" + Integer.toHexString(temp2 % 256).toLowerCase(Locale.US);
            try {
                FileUtils.getInstance().writeTxtFile(savedMacAddr, new File(macConfigFile));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Log.e(TAG, "savedMacAddr: " + savedMacAddr);
        return savedMacAddr;

    }

    /**
     * 获取wifi MAC地址
     *
     * @param context
     * @return
     */
    public static final String getWIFIMac(Context context) {
        String wifiAdress = "";
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            if (info == null || info.getMacAddress() == null || info.getMacAddress().isEmpty()) {
                wifiAdress = "";
            } else {
                wifiAdress = info.getMacAddress();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        Log.e(TAG, "wifiAdress: " + wifiAdress);
        return wifiAdress;

    }

    public static final String getIp(Context context) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                Log.e(TAG, "new intf.getName(): " + intf.getName());
                if (intf.getName().toLowerCase(Locale.US).equals("eth0") || intf.getName().toLowerCase(Locale.US).equals("wlan0")) {

                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        Log.e(TAG, "inetAddress.isLoopbackAddress(): " + inetAddress.isLoopbackAddress());

                        if (inetAddress.isLoopbackAddress() == false) {
                            Log.e(TAG, "inetAddress.isLoopbackAddress(): " + inetAddress.isLoopbackAddress());
                            String ipaddress = inetAddress.getHostAddress().toString();
                            Log.e(TAG, "ipaddress: " + ipaddress);
                            if (ipaddress != null && ipaddress.contains(":") == false) {
                                return ipaddress;
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    private static final String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    /**
     * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap网络3：net网络
     *
     * @param context
     * @return
     */
    public static int getAPNType(Context context) {
        int netType = NetStateTracker.NET_STATE_NO_CMNET;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        // CN:因为hisi平台做了wifi伪装，会把有线伪装成wifi，目的是防止一些app检测不到wifi网络而不工作
        if (PlatformManager.CUR_PLATFORM == PlatformManager.PLAT_HISI) {
            nType = getHisiActiveNetwork(connMgr);
        }
        if (nType == ConnectivityManager.TYPE_MOBILE || nType == ConnectivityManager.TYPE_ETHERNET) {
            // 已连接
            if (networkInfo.isConnected()) {
                netType = NetStateTracker.NET_STATE_CMNET;
            } else {
                netType = NetStateTracker.NET_STATE_NO_CMNET;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {

            if (networkInfo.isConnected()) {
                netType = NetStateTracker.NET_STATE_WIFI;
            } else {
                netType = NetStateTracker.NET_STATE_NO_WIFI;
            }
        }
        return netType;
    }

    public static int getHisiActiveNetwork(ConnectivityManager connMgr) {
        try {
            Method method = ConnectivityManager.class.getDeclaredMethod("getActiveDefaultNetwork");
            method.setAccessible(true);
            return (Integer) method.invoke(connMgr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isReachable(String ipOrHost) {
        try {
            InetAddress address = InetAddress.getByName(ipOrHost);
            return address.isReachable(5000);
        } catch (IOException e) {
            System.err.println("Unable to reach " + ipOrHost);
        }
        return false;
    }

    /**
     * Convert int IP to string.<br>
     */
    public final static String int2Ip(int ip) {
        StringBuffer ipAddressBuff = new StringBuffer();
        ipAddressBuff.append((ip & 0xFF));
        ipAddressBuff.append(".");
        ipAddressBuff.append(((ip >> 8) & 0xFF));
        ipAddressBuff.append(".");
        ipAddressBuff.append(((ip >> 16) & 0xFF));
        ipAddressBuff.append(".");
        ipAddressBuff.append(((ip >> 24) & 0xFF));
        return ipAddressBuff.toString();
    }

    public static int Ip2Int(String strIp) {
        if (strIp == null) {
            return 0;
        }
        String[] ss = strIp.split("\\.");
        if (ss.length != 4) {
            return 0;
        }
        byte[] bytes = new byte[ss.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(ss[i]);
        }
        return byte2Int(bytes);
    }

    private static int byte2Int(byte[] bytes) {
        if (bytes == null) {
            return 0;
        }
        int n = bytes[0] & 0xFF;
        n |= ((bytes[1] << 8) & 0xFF00);
        n |= ((bytes[2] << 16) & 0xFF0000);
        n |= ((bytes[3] << 24) & 0xFF000000);
        return n;
    }

    /**
     * validate string is according to ip format.
     *
     * @param ip the input string.
     * @return true the string is according to ip format,otherwise return false.
     */
    public static boolean isValidIp(String ip) {
        return ip != null && Patterns.IP_ADDRESS.matcher(ip).matches();
    }
}
