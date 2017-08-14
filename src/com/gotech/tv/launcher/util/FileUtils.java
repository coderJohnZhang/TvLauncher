package com.gotech.tv.launcher.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.gotech.tv.launcher.service.ContextManager;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {

    public static String TAG = "FileUtils";

    private static FileUtils instance;

    /**
     * singleton
     *
     * @return
     */
    public static FileUtils getInstance() {
        if (instance == null)
            instance = new FileUtils();
        return instance;
    }

    /**
     * 获取注册文件内容
     *
     * @param fileName
     * @param c
     * @return
     */
    public String getRegFile(String fileName, Context c) {
        String str = "";
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            fileName = ContextManager.LOCAL_CONFIG_PATH + fileName;
            File f = new File(fileName);
            if (f.exists()) {

                inStream = new FileInputStream(fileName);
                outStream = new ByteArrayOutputStream();// 输出到内存

                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);//
                }
                byte[] content_byte = outStream.toByteArray();
                String content = new String(content_byte);
                str = content;
            }

        } catch (Exception e) {
            Log.e(TAG, "获取用户名和密码存取文件失败:error" + e.getMessage());
        } finally {

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!str.equals("")) {
            // 解密
            if (!str.contains("|^|")) {
                DesEncryptUtil des = new DesEncryptUtil();// 实例化一个对像

                str = des.decode(str, Constant.DesEncryptKey);
            }

        }
        return str;
    }

    /**
     * 写入注册文件
     */
    public void writeRegFile(String mess, String fileName, Context c) {

        writeRegFile(mess, fileName);

    }

    /**
     * 写入注册文件
     */
    public void writeRegFile(String mess, String fileName) {

        if (!mess.equals("")) {
            // 加密
            DesEncryptUtil des = new DesEncryptUtil();// 实例化一个对像
            mess = des.encrypt(mess, Constant.DesEncryptKey);

        }
        String myfileName = ContextManager.LOCAL_CONFIG_PATH + fileName;
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(myfileName);
            outStream.write(mess.getBytes());
            outStream.flush();
            // 调用两次sync操作
            outStream.getFD().sync();
            outStream.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "写入文件内容:error" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "写入文件内容:error" + e.getMessage());
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 写入服务器IP配置文件
     */
    public void writeServerIP(String mess, String fileName) {
        if (!mess.equals("")) {
            // 加密
            DesEncryptUtil des = new DesEncryptUtil();// 实例化一个对像
            mess = des.encrypt(mess, Constant.DesEncryptKey);

        }
        fileName = ContextManager.LOCAL_CONFIG_PATH + fileName;
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(fileName);
            outStream.write(mess.getBytes());
            outStream.flush();
            // 调用两次sync操作
            outStream.getFD().sync();
            outStream.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(TAG, "写入服务器IP配置文件内容:error" + e.getMessage());
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 获取注册文件内容
     *
     * @return
     */
    public String getServerIP() {
        String str = "";
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            String fileName = ContextManager.LOCAL_CONFIG_PATH + Constant.UserServerConfigTxt;
            File serverConfigFile = new File(fileName);
            if (serverConfigFile.exists()) {

                inStream = new FileInputStream(fileName);
                outStream = new ByteArrayOutputStream();// 输出到内存
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);//
                }
                byte[] content_byte = outStream.toByteArray();
                String content = new String(content_byte);
                str = content;
            }

        } catch (Exception e) {
            Log.e(TAG, "获取服务器IP配置文件:error" + e.getMessage());
        } finally {

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!str.equals("")) {
            // 解密
            if (!str.contains("|")) {
                DesEncryptUtil des = new DesEncryptUtil();// 实例化一个对像

                str = des.decode(str.toString(), Constant.DesEncryptKey);
            }

        }
        return str;
    }

    /**
     * 获取系统默认目录 文件内容
     *
     * @param fileName
     * @param c
     * @return
     */
    public String getTxt(String fileName, Context c) {
        String str = "";
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            inStream = c.openFileInput(fileName);// 只需传文件名
            outStream = new ByteArrayOutputStream();// 输出到内存

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);//
            }

            byte[] content_byte = outStream.toByteArray();

            str = new String(content_byte);

        } catch (Exception e) {
            Log.e(TAG, "获取用户名和密码存取文件失败:error" + e.getMessage());
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return str;
    }

    /**
     * 写入系统默认目录 文件内容
     *
     * @param mess
     * @param fileName
     * @param c
     */
    public void witeTxt(String mess, String fileName, Context c) {
        FileOutputStream outStream = null;
        try {
            outStream = c.openFileOutput(fileName, Context.MODE_PRIVATE);
            outStream.write(mess.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(TAG, "写入文件内容:error" + e.getMessage());
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     */
    public String readTxtFile(String filePath) {

        String str = "";
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    str += lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 写入文件内容
     *
     * @param content
     * @param fileName
     * @return
     * @throws Exception
     */
    public boolean writeTxtFile(String content, File fileName) throws Exception {
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("GBK"));
            o.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert mm != null;
            mm.close();
        }
        return flag;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int byteSum = 0;
            int byteRead = 0;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; // 字节数 文件大小
                    System.out.println(byteSum);
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                fs.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()));
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return byte[]
     * @throws Exception
     */
    public byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return InputStream
     * @throws Exception
     */
    public InputStream getImageStream(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return conn.getInputStream();
        }
        return null;
    }

    /**
     * Get data from stream
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName, String filePath) throws IOException {
        File dirFile = new File(filePath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(filePath + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.PNG, 80, bos);
        bos.flush();
        bos.close();
    }

    /**
     * 读取本地文件
     *
     * @param pathString
     * @return
     * @deprecated 请用getDiskBitmap(String pathString, int minSideLength, int
     *maxNumOfPixels)
     */
    public Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapUtil.tryGetBitmap(pathString, 720, 1280 * 720);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 读取本地文件
     *
     * @param pathString
     * @param parentView for FrameLayout
     * @return
     */
    public Bitmap getDiskBitmap(String pathString, View parentView) {
        Bitmap bitmap = null;
        try {
            android.view.ViewGroup.LayoutParams x = parentView.getLayoutParams();
            int width = x.width;
            int height = x.height;
            int minValue = 0;
            if (width > height) {
                minValue = height;
            } else {
                minValue = width;
            }

            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapUtil.tryGetBitmap(pathString, minValue, width * height);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 读取本地文件
     *
     * @param pathString
     * @return
     */
    public Bitmap getDiskBitmap(String pathString, int minSideLength, int maxNumOfPixels) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapUtil.tryGetBitmap(pathString, minSideLength, maxNumOfPixels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取加载图标
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public Drawable getLoadingBG(String path) {
        Drawable mDrawable = null;

        File f = new File(path);

        try {
            // 文件存在马上显示
            if (!path.equals("")) {
                if (f.exists()) {
                    if (!path.equals("")) {
                        Bitmap myB = FileUtils.getInstance().getDiskBitmap(path, 720, 1280 * 720);
                        mDrawable = new BitmapDrawable(myB);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mDrawable;

    }

    /**
     * 获取加载图标
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public Drawable getLoadingBG(String path, int width, int height) {
        Drawable mDrawable = null;

        File f = new File(path);

        try {
            // 文件存在马上显示
            if (!path.equals("")) {
                if (f.exists()) {
                    if (!path.equals("")) {
                        Bitmap myB = FileUtils.getInstance().getDiskBitmap(path, height, width * height);
                        mDrawable = new BitmapDrawable(myB);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mDrawable;

    }

    /**
     * 删除文件
     *
     * @param path
     */
    public void deleteFile(String path) {

        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
    }

    /**
     * 检查并创建文件路径
     *
     * @param filepath
     */
    public void checkFilePath(String filepath) {
        File dir = new File(filepath);
        if (!dir.exists()) {
            int i = filepath.lastIndexOf("/");
            String path2 = filepath.substring(0, i);
            File dir2 = new File(path2);
            if (!dir2.exists()) {
                dir2.mkdir();
            }
            dir.mkdir();
        }
    }

}
