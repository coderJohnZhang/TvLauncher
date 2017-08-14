package com.gotech.tv.launcher.page;

import java.lang.ref.WeakReference;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.activity.MainActivity;
import com.gotech.tv.launcher.anim.AnimationMagnify;
import com.gotech.tv.launcher.util.LayoutUtil;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnFocusChangeListener;

public abstract class BasePage {
    protected String TAG = this.getClass().getSimpleName();
    protected WeakReference<MainActivity> mActivity;
    protected MainActivity thisActivity = null;

    protected View viewParent = null;
    protected View viewCurentItem = null;
    protected String[] astrItemTexts = null;

    public BasePage(MainActivity activity) {
        int resId = getLayoutViewId();
        mActivity = new WeakReference<MainActivity>(activity);
        thisActivity = mActivity.get();
        viewParent = thisActivity.getLayoutInflater().inflate(resId, null);
        initViews();
    }

    /**
     * @param
     * @return int
     * @throw
     */
    public abstract int getLayoutViewId();

    /**
     * @param
     * @return View
     * @throw
     */
    public View getParentView() {
        return viewParent;
    }

    private void initViews() {
        GetItemTextAndIconsRes();
        for (int i = 0; i < getItemCount(); i++) {
            initItemView(i);
        }
    }

    protected abstract int getItemCount();

    protected abstract void GetItemTextAndIconsRes();

    protected abstract void setItemTextAndIcons(int i, View viewCurentItem);

    protected void initItemView(int i) {
        viewCurentItem = LayoutUtil.getMainMenuCommonView(i, viewParent);
        viewCurentItem.setClickable(true);
        viewCurentItem.setFocusable(true);
        viewCurentItem.setFocusableInTouchMode(true);
        viewCurentItem.setOnFocusChangeListener(new HotImageOnFocusChangeListener());

        setItemTextAndIcons(i, viewCurentItem);
    }

    protected Resources getResources() {
        return thisActivity.getResources();
    }

    protected class HotImageOnFocusChangeListener implements OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (hasFocus) {
                if ((v.getId() == R.id.myview1) || (v.getHeight() > 300)) {
                    AnimationMagnify.Magnify(v, 1.10f);
                } else {
                    AnimationMagnify.Magnify(v, 1.20f);
                }
                v.bringToFront();

            } else {
                AnimationMagnify.backView(v);

            }
        }
    }

    protected void openApp(String appPackage) {
        thisActivity.openApp(appPackage);
    }

    protected void showToast(String msg) {
        thisActivity.showToast(msg);
    }

}