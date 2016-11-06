package com.gotech.tv.launcher.service;

import com.gotech.tv.launcher.util.LogTool;
import com.gotech.tv.launcher.util.NetworkUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * 检测网络
 * 
 * @author meto
 * 
 */
public class NetStateTracker implements Runnable
{
	private static final String TAG = NetStateTracker.class.getSimpleName();
	public static final String TVLAUNCHER_NETWORK_STATE_CHANGE = "tvlauncher.network.state.change";

	/** CN:网络状态_有线网已连接但不能上网 **/
	public static final int NET_STATE_CMNET_UNABLE = 6;
	/** CN:网络状态_有线网已连接且能上网 **/
	public static final int NET_STATE_CMNET = 4;
	/** CN:网络状态_无线网已连接但不能上网 **/
	public static final int NET_STATE_WIFI_UNABLE = 5;
	/** CN:网络状态_无线网已连接且能上网 **/
	public static final int NET_STATE_WIFI = 3;
	/** CN:网络状态_有线网连接失败 **/
	public static final int NET_STATE_NO_CMNET = 2;
	/** CN:网络状态_无线网连接失败 **/
	public static final int NET_STATE_NO_WIFI = 1;

	private static NetStateTracker mInstance;
	private int mCurState = NET_STATE_CMNET_UNABLE;
	private Context mCtx;
	private Thread mChecker;

	private NetStateTracker(Context ctx)
	{
		mCtx = ctx.getApplicationContext();
		initState();
	}

	private void initState()
	{
		setCurNetState(NetworkUtil.getAPNType(mCtx));
	}

	public synchronized static NetStateTracker getTracker(Context ctx)
	{
		if (mInstance == null)
		{
			mInstance = new NetStateTracker(ctx);
		}
		return mInstance;
	}

	public synchronized void startTrack()
	{
		if (mChecker == null || !mChecker.isAlive())
		{
			mChecker = new Thread(this, "NetworkChecker");
			mChecker.start();
		}
	}

	public synchronized void stopCheck()
	{
		if (mChecker != null && mChecker.isAlive())
		{
			mChecker.interrupt();
		}
	}

	public synchronized int getCurNetState()
	{
		return mCurState;
	}

	public synchronized void setCurNetState(int mCurState)
	{
		if (this.mCurState != mCurState)
		{
			this.mCurState = mCurState;
			mCtx.sendBroadcast(new Intent(TVLAUNCHER_NETWORK_STATE_CHANGE).putExtra("state", mCurState));
		}
	}

	public boolean isNetworkAvailable()
	{
		int state = getCurNetState();
		if (state == NET_STATE_CMNET || state == NET_STATE_WIFI)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void run()
	{
		while (!Thread.currentThread().isInterrupted())
		{
			boolean isReached = false;
			if (NetworkUtil.isNetworkAvailable(mCtx))
			{
				isReached = NetworkUtil.isReachable(ServerManager.obtain().getBaseServer());// HttpTool.obtain().isReachedUrl(Constant.getProvinceCityNameUrl);
				LogTool.v(TAG, ServerManager.obtain().getBaseServer() + " " + (isReached ? "can reached" : "can not reached"));
				if (isReached)
				{
					switch (NetworkUtil.getNetworkType(mCtx))
					{
					case ConnectivityManager.TYPE_ETHERNET:
					case ConnectivityManager.TYPE_MOBILE:
						setCurNetState(NET_STATE_CMNET);
						break;
					case ConnectivityManager.TYPE_WIFI:
						setCurNetState(NET_STATE_WIFI);
						break;
					}
				}
				else
				{
					switch (NetworkUtil.getNetworkType(mCtx))
					{
					case ConnectivityManager.TYPE_ETHERNET:
					case ConnectivityManager.TYPE_MOBILE:
						setCurNetState(NET_STATE_CMNET_UNABLE);
						break;
					case ConnectivityManager.TYPE_WIFI:
						setCurNetState(NET_STATE_WIFI_UNABLE);
						break;
					}
				}
			}
			else
			{
				LogTool.v(TAG, " is not NetworkAvailable");
				setCurNetState(NetworkUtil.getAPNType(mCtx));
			}

			try
			{
				Thread.sleep(isReached ? 50000 : 10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}

	public static interface NetworkStateCheckBack
	{
		public void onNetworkStaeCheckBack(int state);
	}
}
