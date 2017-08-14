package com.gotech.tv.launcher.util;

public class Constant
{

	public static final int VIEW_PAGER_DURATION =500;
	public static final int MENU_HOME = 0;
	public static final int MENU_APP = 1;
	public static final int MENU_SETTING = 2;
	public static final int TRAN_DUR_ANIM = 500;
	public static final float APP_PAGE_SIZE = 12.0f;
	public static final int APP_PAGE_COLUMNS = 4;
	public static final int MOVE = 1;
	public static final int PAGE_ITEM_NUM = 6;
	public static final int DIALOG_AUTO_DISMISS = 0;
	public static final int TIME_OUT = 5000;

	/**
	 * 加（解）密钥匙
	 */
	public static final String DesEncryptKey = "TvLauncher";
	
	/** 存取用户IP和软件类型 **/
	public static final String UserServerConfigTxt = "serverconfig.txt";
	
	/** 存用户名和密码文件 **/
	public static final String UserDataTxt = "userpwd.txt";
	
	/**
	 * 软件类型
	 */
	public static String SOFTWARE_TYPE = "TvLauncher";
	
	/**
	 * 默认分组号
	 * 默认：创维 999
	 */
	public static String MAC_REGISTER_DEFAULT_CODE= "009"; 
	
	/** JSON数据格式错误 **/
	public static int ParseData_FormatWrong = 2;
	
	/** 解析成功 **/
	public static int ParseData_Success = 0;
	
	/** 用户访问超时 或 未登录 **/
	public static int ParseData_TimeOut = 1;

}
