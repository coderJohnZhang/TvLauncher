package com.gotech.tv.launcher.page;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.activity.MainActivity;
import com.gotech.tv.launcher.util.Constant;
import com.gotech.tv.launcher.view.PosterView;

/**
 * @version 1.0
 * @author: john
 * @date：Nov 26, 2015 6:01:05 PM
 * @description: setting
 */
public class PageSetting extends BasePage {

    public PageSetting(MainActivity activity) {
        super(activity);
    }

    @Override
    public int getLayoutViewId() {

        return R.layout.index_setting;

    }

    @Override
    protected int getItemCount() {
        return Constant.PAGE_ITEM_NUM;
    }

    @Override
    protected void GetItemTextAndIconsRes() {
        astrItemTexts = getResources().getStringArray(R.array.sub_menu_setting);
    }

    @Override
    protected void setItemTextAndIcons(int i, View viewCurrentItem) {
        if (null != astrItemTexts && astrItemTexts.length >= i) {
            ((PosterView) viewCurentItem).setText(astrItemTexts[i]);
        }
    }

    @Override
    protected void initItemView(int i) {
        super.initItemView(i);
        super.viewCurentItem.setOnClickListener(new SettingOnClickListener());
    }

    private class SettingOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.myview1: {// network
                    //thisActivity.locationPage(com.gotech.tv.launcher.activity.SettingNetActivity.class, null, true);
                    break;
                }

                case R.id.myview2: {// update
                    //thisActivity.locationPage(com.gotech.tv.launcher.activity.SettingSoftUpdateActivity.class, null, true);
                    break;
                }

                case R.id.myview3: {// advance
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
                    thisActivity.startActivity(intent);
                    break;
                }

                case R.id.myview4: {// common
                    //thisActivity.locationPage(com.gotech.tv.launcher.activity.SettingCommonActivity.class, null, true);
                    break;
                }

                case R.id.myview5: {// information
                    //thisActivity.locationPage(com.gotech.tv.launcher.activity.SettingSysInfoActivity.class, null, true);
                    break;
                }

                case R.id.myview6: {// quick setup
                    //thisActivity.locationPage(com.gotech.tv.launcher.activity.UserGuideActivity.class, null, true);
                    break;
                }

            }
        }
    }

}
