package com.gotech.tv.launcher.util;

import android.content.Context;

/**
 * @author: john
 * @date： 2015/11/25
 * @version 1.0
 * @description: Screen adaptation, resolution conversion
 */
public class DensityUtil
{

	/*
	 * deprecated public static int getScreenHeight(Activity activity) { return
	 * activity.getWindowManager().getDefaultDisplay().getHeight(); }
	 * 
	 * public static int getScreenWidth(Activity activity) { return
	 * activity.getWindowManager().getDefaultDisplay().getWidth(); }
	 */

	/*
	 * theme as decorate (TitleBar) public void getSize(Point outSize) {
	 * synchronized (this) { updateDisplayInfoLocked();
	 * mDisplayInfo.getAppMetrics(mTempMetrics, mDisplayAdjustments); outSize.x
	 * = mTempMetrics.widthPixels; outSize.y = mTempMetrics.heightPixels; } }
	 * 
	 * public void getRealSize(Point outSize) { synchronized (this) {
	 * updateDisplayInfoLocked(); outSize.x = mDisplayInfo.logicalWidth;
	 * outSize.y = mDisplayInfo.logicalHeight; } }
	 */

	public static int dip2px(Context context, float dpValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
}