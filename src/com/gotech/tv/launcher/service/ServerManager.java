package com.gotech.tv.launcher.service;

public class ServerManager {
    private static final String TAG = ServerManager.class.getSimpleName();
    private static final String DEFAULT_SERVER = "11.11.11.11";
    private static final int DEFAULT_PORT = 8660;

    /**
     * 基本路径
     */
    private String mBaseUrl = DEFAULT_SERVER;

    /**
     * 3A端口
     **/
    private int AAAport = DEFAULT_PORT;// 8660 8080

    /**
     * MAC地址注册请求
     **/
    public String macRegisterUrl;

    public String mAAALoginUrl;

    public String mAuthCodeUrl;

    public String mLoginReqUrl;

    /**
     * 登录挑战字(随机数)获取
     **/
    public String mLoginRandomCodeUrl;

    /**
     * 检测用户是否注册 aaa/setting/checkRegInfoByUserId?userid=zj20140422133029798
     * {"result":0},0表示未注册 1表示已注册
     **/
    public String mCheckUserIsRegUrl;

    /**
     * 根据MAC地址找回userId访问地址
     * aaa/setting/retrieveUserInfoByMac?mac=1e:aa:c8:10:e1:75
     **/
    public String mGetUserIdAndPwdUrl;

    /**
     * hotel 获取下载视频 列表地址
     */
    public String Hotel_GEt_VIDEO_REQURL;

    public String checkAndroidVersionUrl;

    public String mParserLibUrl;

    public String ftpUploadUrl;

    public String mToken = "";

    public long mLastTokenTime = 0;

    private static ServerManager instance = null;

    private ServerManager() {
        setBaseServer(DEFAULT_SERVER);
        setAAAPort(DEFAULT_PORT);
        resetUrl();
    }

    public synchronized static ServerManager obtain() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }

    private void resetUrl() {
        macRegisterUrl = "http://" + mBaseUrl + ":8186" + "/accountserver/register";

        mAAALoginUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/ott/login?";

        mAuthCodeUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/getAuthCode";

        mLoginReqUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/login";

        mLoginRandomCodeUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/ott/getLoginCode?";

        mCheckUserIsRegUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/checkRegInfoByUserId?userid=";

        mGetUserIdAndPwdUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/retrieveUserInfoByMac?";

        Hotel_GEt_VIDEO_REQURL = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/getNewsVidio";

        checkAndroidVersionUrl = "http://" + mBaseUrl + ":8081/upgrade/version.xml";

        mParserLibUrl = "http://" + mBaseUrl + ":8081/upgrade/parser_lib.jar";

        ftpUploadUrl = "ftp://stb:stb123@" + mBaseUrl + "/log";

    }

    public void setBaseServer(String ip) {
        if (!mBaseUrl.equals(ip)) {
            this.mBaseUrl = ip;
            resetUrl();
        }
    }

    public String getBaseServer() {
        return mBaseUrl;
    }

    public void setAAAPort(int port) {
        if (AAAport != port) {
            this.AAAport = port;
            resetUrl();
        }
    }
}
