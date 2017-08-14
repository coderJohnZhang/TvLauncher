package com.gotech.tv.launcher.vo;

import com.gotech.tv.launcher.R;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class AppInfoVo {

    public String mAppName;
    public String mAppPackageName;
    public Drawable mAppIcon;
    public Intent mAppIntent;
    public Integer[] mAppPanelId = {R.drawable.color_01, R.drawable.color_02, R.drawable.color_03, R.drawable.color_04, R.drawable.color_05, R.drawable.color_06, R.drawable.color_07,
            R.drawable.color_08, R.drawable.color_09, R.drawable.color_10, R.drawable.color_11, R.drawable.color_12};
    public boolean isSystemApps = false;

}
