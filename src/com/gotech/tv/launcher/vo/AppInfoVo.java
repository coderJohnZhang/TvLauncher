package com.gotech.tv.launcher.vo;

import com.gotech.tv.launcher.R;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class AppInfoVo
{

	private String mAppName = null;
	private String mAppPackageName = null;
	private Drawable mAppIcon = null;
	private Intent mAppIntent = null;
	private Integer[] mAppPanelId = { R.drawable.color_01, R.drawable.color_02, R.drawable.color_03, R.drawable.color_04, R.drawable.color_05, R.drawable.color_06, R.drawable.color_07,
			R.drawable.color_08, R.drawable.color_09, R.drawable.color_10, R.drawable.color_11, R.drawable.color_12 };
	private boolean isSystemApps = false;

	public String getAppName()
	{
		return mAppName;
	}

	public void setAppName(String mAppName)
	{
		this.mAppName = mAppName;
	}

	public String getAppPackageName()
	{
		return mAppPackageName;
	}

	public void setAppPackageName(String mAppPackageName)
	{
		this.mAppPackageName = mAppPackageName;
	}

	public Drawable getAppIcon()
	{
		return mAppIcon;
	}

	public void setAppIcon(Drawable mAppIcon)
	{
		this.mAppIcon = mAppIcon;
	}

	public Integer[] getAppPanelId()
	{
		return mAppPanelId;
	}

	public void setAppPanelId(Integer[] mAppPanelId)
	{
		this.mAppPanelId = mAppPanelId;
	}

	public Intent getAppIntent()
	{
		return mAppIntent;
	}

	public void setAppIntent(Intent mAppIntent)
	{
		this.mAppIntent = mAppIntent;
	}

	public boolean isSystemApps()
	{
		return isSystemApps;
	}

	public void setIsSystemApps(boolean isSystemApps)
	{
		this.isSystemApps = isSystemApps;
	}

}
