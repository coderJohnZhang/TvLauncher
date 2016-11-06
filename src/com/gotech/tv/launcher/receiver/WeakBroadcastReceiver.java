package com.gotech.tv.launcher.receiver;

import java.lang.ref.WeakReference;

import android.content.BroadcastReceiver;

public abstract class WeakBroadcastReceiver<T> extends BroadcastReceiver
{
	private WeakReference<T> mOwner;
	protected String TAG = this.getClass().getSimpleName();

	public WeakBroadcastReceiver(T owner)
	{
		setOwner(owner);
	}

	public T getOwner()
	{
		return mOwner.get();
	}

	public void setOwner(T owner)
	{
		this.mOwner = new WeakReference<T>(owner);
	}
}
