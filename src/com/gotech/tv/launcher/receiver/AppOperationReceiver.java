package com.gotech.tv.launcher.receiver;

import com.gotech.tv.launcher.activity.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class AppOperationReceiver extends WeakBroadcastReceiver<MainActivity>
{

	public AppOperationReceiver(MainActivity owner)
	{
		super(owner);
		Log.d(TAG, "============创建广播接收器===================owner---" + owner);
	}

	public void register(Context ctx)
	{
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
		filter.addDataScheme("package");
		ctx.registerReceiver(this, filter);
		Log.d(TAG, "============注册广播===================owner---" + ctx);
	}

	public void unRegister(Context ctx)
	{
		ctx.unregisterReceiver(this);
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.d(TAG, "============匹配广播,执行相应操作===================");
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))
		{
			Log.d(TAG, "============接收应用卸载广播===================");
			MainActivity mainActivity = getOwner();
			String packageName = intent.getDataString();
			if (!packageName.equals("package:" + context.getPackageName()))
			{

				mainActivity.getAppPage().refresh(true);

			}

		}
	}
}
