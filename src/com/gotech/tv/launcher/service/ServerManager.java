package com.gotech.tv.launcher.service;

import com.loopj.android.http.RequestParams;
import com.gotech.tv.launcher.parser.ParserSet.*;

public class ServerManager
{
	private static final String TAG = ServerManager.class.getSimpleName();
	private static final String DEFAULT_SERVER = "11.11.11.11";
	private static final int DEFAULT_PORT = 8660;

	/** 基本路径 */
	private String mBaseUrl = DEFAULT_SERVER;

	/** 3A端口 **/
	private int AAAport = DEFAULT_PORT;// 8660 8080

	/** MAC地址注册请求 **/
	private String macRegisterUrl;

	private String mAAALoginUrl;

	private String mAuthCodeUrl;

	private String mLoginReqUrl;

	/** 登录挑战字(随机数)获取 **/
	private String mLoginRadomCodeUrl;

	/**
	 * 检测用户是否注册 aaa/setting/checkRegInfoByUserId?userid=zj20140422133029798
	 * {"result":0},0表示未注册 1表示已注册
	 **/
	private String mcheckUserIsRegUrl;

	/**
	 * 根据MAC地址找回userId访问地址
	 * aaa/setting/retrieveUserInfoByMac?mac=1e:aa:c8:10:e1:75
	 **/
	private String mGetUserIdAndPwdUrl;

	/**
	 * hotel 获取下载视频 列表地址
	 */
	private String Hotel_GEt_VIDEO_REQURL;

	private String checkAndroidVersionUrl;

	private String mParserLibUrl;

	private String ftpUploadUrl;

	private String mToken = "";

	private long mLastTokenTime = 0;

	private static ServerManager instance = null;

	private ServerManager()
	{
		setBaseServer(DEFAULT_SERVER);
		setAAAPort(DEFAULT_PORT);
		resetUrl();
	}

	public synchronized static ServerManager obtain()
	{
		if (instance == null)
		{
			instance = new ServerManager();
		}
		return instance;
	}

	private void resetUrl()
	{
		macRegisterUrl = "http://" + mBaseUrl + ":8186" + "/accountserver/register";

		mAAALoginUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/ott/login?";

		mAuthCodeUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/getAuthCode";

		mLoginReqUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/login";

		mLoginRadomCodeUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/ott/getLoginCode?";

		mcheckUserIsRegUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/checkRegInfoByUserId?userid=";

		mGetUserIdAndPwdUrl = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/retrieveUserInfoByMac?";

		Hotel_GEt_VIDEO_REQURL = "http://" + mBaseUrl + ":" + AAAport + "/aaa/setting/getNewsVidio";

		checkAndroidVersionUrl = "http://" + mBaseUrl + ":8081/upgrade/version.xml";

		mParserLibUrl = "http://" + mBaseUrl + ":8081/upgrade/parser_lib.jar";

		setFtpUploadUrl("ftp://stb:stb123@" + mBaseUrl + "/log");

	}

	public void setBaseServer(String ip)
	{
		if (!mBaseUrl.equals(ip))
		{
			this.mBaseUrl = ip;
			resetUrl();
		}
	}

	public String getBaseServer()
	{
		return mBaseUrl;
	}

	public void setAAAPort(int port)
	{
		if (AAAport != port)
		{
			this.AAAport = port;
			resetUrl();
		}
	}

	public String getMacRegisterUrl()
	{
		return macRegisterUrl;
	}

	public String getAAALoginUrl()
	{
		return mAAALoginUrl;
	}

	public String getAuthCodeUrl()
	{
		return mAuthCodeUrl;
	}

	public String getLoginReqUrl()
	{
		return mLoginReqUrl;
	}

	public String getLoginRadomCodeUrl()
	{
		return mLoginRadomCodeUrl;
	}

	public String getCheckUserRegUrl()
	{
		return mcheckUserIsRegUrl;
	}

	public String getUserIdAndPwdUrl()
	{
		return mGetUserIdAndPwdUrl;
	}

	public String getHotelVideoReqUrl()
	{
		return Hotel_GEt_VIDEO_REQURL;
	}

	public String getVersionCheckUrl()
	{
		return checkAndroidVersionUrl;
	}

	public void setVersionCheckUrl(String url)
	{
		checkAndroidVersionUrl = url;
	}

	public String getUrlOfParserLib()
	{
		return mParserLibUrl;
	}

	public synchronized String getToken()
	{
		return mToken;
	}

	public void setToken(String token)
	{
		this.mToken = token;
	}

	public long getLastTokenTime()
	{
		return mLastTokenTime;
	}

	public void setLastTokenTime(long lastTokenTime)
	{
		this.mLastTokenTime = lastTokenTime;
	}

	public String getFtpUploadUrl()
	{
		return ftpUploadUrl;
	}

	public void setFtpUploadUrl(String ftpUploadUrl)
	{
		this.ftpUploadUrl = ftpUploadUrl;
	}

}
