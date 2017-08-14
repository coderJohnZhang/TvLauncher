package com.gotech.tv.launcher.util;

import android.content.Context;

/**
 * @version 1.0
 * @author: john
 * @date： 2015/11/25
 * @description: Screen adaptation, resolution conversion
 */
public class DensityUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}