package com.gotech.tv.launcher.service;

import java.io.File;

import com.gotech.tv.launcher.util.Constant;
import com.gotech.tv.launcher.util.FileUtils;
import com.gotech.tv.launcher.vo.UserInfoVo;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

/**
 * CN:上下文管理者，管理软件相关基本信息，提供基本接口
 * 
 */
public class ContextManager
{
	//private static final String TAG = ContextManager.class.getSimpleName();

	private static ContextManager instance = null;

	public static String ACTION_SN_ACTIVATE_RESULT = "action.sn.activate.result";
	public static String ACTION_USER_REGIST_RESULT = "action.user.regist.result";

	public static String ACTION_LOAD_HOME_DATA_FINISH = "action.load.home.data.finish";

	public static String LOCAL_INSTALL_PATH = Environment.getExternalStorageDirectory() + "/TvLauncher";
	public static String LOCAL_CACHE_PATH = LOCAL_INSTALL_PATH + "/download/";
	public static String LOCAL_CONFIG_PATH = LOCAL_INSTALL_PATH + "/config/";
	public static String LOCAL_FILE_PATH = LOCAL_INSTALL_PATH + "/files/";
	static
	{
		File file = new File(LOCAL_CACHE_PATH);
		if (!file.exists())
		{
			file.mkdirs();
		}
		file = new File(LOCAL_CONFIG_PATH);
		if (!file.exists())
		{
			file.mkdirs();
		}
		file = new File(LOCAL_FILE_PATH);
		if (!file.exists())
		{
			file.mkdirs();
		}
	}

	/**
	 * CN:最好使用baseContext
	 */
	private Context mCtx;
	private UserInfoVo mUserInfo;

	private ContextManager(Context ctx)
	{
		mCtx = ctx;
	}

	public static synchronized ContextManager obtain(Context ctx)
	{
		if (instance == null)
		{
			instance = new ContextManager(ctx.getApplicationContext());
		}
		return instance;
	}
	
	/**
	 * CN:校验用户信息
	 * 
	 * @return
	 */
	public boolean checkUserInfoExist()
	{
		UserInfoVo info = getUserInfo();
		return (info != null && !TextUtils.isEmpty(info.getUserName()));
	}

	public UserInfoVo getUserInfo()
	{
		if(mUserInfo == null)
		{
			String userInfoStr = FileUtils.getInstance().getRegFile(Constant.UserDataTxt, mCtx);
			if (!TextUtils.isEmpty(userInfoStr))
			{
				mUserInfo = new UserInfoVo();
				String[] info = userInfoStr.split("\\|\\^\\|");
				mUserInfo.setUserName(info[0]);
				mUserInfo.setPassword(info[1]);
			}
		}
		return mUserInfo;
	}
	
	public String getUserName()
	{
		return mUserInfo == null ? "":mUserInfo.getUserName();
	}

}
