package com.gotech.tv.launcher.activity;

import java.util.Date;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.util.StringUtil;

public class TvBaseActivity extends Activity
{

	public static String TAG = "TvBaseActivity";

	@Override
	protected void onDestroy()
	{

		super.onDestroy();

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{

		return super.dispatchKeyEvent(event);

	}

	/**
	 * 重定向页面
	 * 
	 * @param locationClass
	 *            目标
	 * @param mReqParams
	 *            附带参数 key ->value haspMap
	 */
	public void locationPage(Class<?> locationClass, LinkedHashMap<String, Object> mReqParams, boolean _target)
	{

		Intent intent = new Intent(this, locationClass);
		if (mReqParams != null && mReqParams.size() > 0)
		{

			Bundle bundle = new Bundle();
			for (String key : mReqParams.keySet())
			{
				Object param = mReqParams.get(key);

				if (param instanceof Integer)
				{
					bundle.putInt(key, Integer.parseInt(param.toString()));
				}
				else if (param instanceof String)
				{
					bundle.putString(key, param.toString());
				}
				else if (param instanceof Double)
				{
				}
				else if (param instanceof Float)
				{
				}
				else if (param instanceof Long)
				{
				}
				else if (param instanceof Boolean)
				{
				}
				else if (param instanceof Date)
				{
				}
			}
			intent.putExtras(bundle);
		}
		if (_target)
		{
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		else
		{
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		this.startActivity(intent);

	}

	private long lastKeyTime = 0;

	public boolean moreThanLastKeyTime(long value)
	{
		long curTime = System.currentTimeMillis();
		if ((curTime - lastKeyTime) < value)
		{
			if (lastKeyTime > curTime)
			{
				lastKeyTime = curTime;
			}
			return false;
		}
		lastKeyTime = curTime;
		return true;
	}

	/**
	 * 打开软件
	 */
	public void openApp(String appPackage)
	{
		try
		{
			Log.i(TAG, "Try to open app: " + appPackage);
			Intent localIntent = getPackageManager().getLaunchIntentForPackage(appPackage);
			startActivity(localIntent);
		}
		catch (Exception e)
		{
			showToast(getResources().getString(R.string.main_app_open_error));
		}
	}

	/**
	 * 显示提示信息
	 *
	 * @param msg
	 */
	public void showToast(String msg)
	{

		StringUtil.showToast(this, msg, 1);

	}

}
