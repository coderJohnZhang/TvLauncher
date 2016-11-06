package com.gotech.tv.launcher.page;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.gotech.tv.launcher.activity.MainActivity;
import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.anim.ScaleAnimEffect;
import com.gotech.tv.launcher.view.MainLayout;
import com.gotech.tv.launcher.view.FlyBorderView;
import com.gotech.tv.launcher.view.ReflectView;

/**
 * 首页 Home
 * 
 * @author john
 * 
 */
public class PageHome extends BasePage implements OnFocusChangeListener, View.OnClickListener
{
	ScaleAnimEffect animEffect = new ScaleAnimEffect();
	private FlyBorderView mFlyBorderView = null;
	private MainLayout mMainLayout = null;
	private int[] mRefFrameId = { R.id.frame_tv, R.id.frame_game, R.id.frame_search, R.id.frame_about };
	private int[] mImageViewId = { R.id.reflect_tv, R.id.reflect_game, R.id.reflect_search, R.id.reflect_about };

	public PageHome(MainActivity activity)
	{
		super(activity);
		initView();

	}

	@Override
	public int getLayoutViewId()
	{

		return R.layout.index_home;

	}

	private void initView()
	{
		mFlyBorderView = (FlyBorderView) getParentView().findViewById(R.id.flyBorder_view);
		mMainLayout = (MainLayout) getParentView().findViewById(R.id.main_layout);
		for (int index = 0; index < mMainLayout.getChildCount(); index++)
		{
			mMainLayout.getChildAt(index).setOnFocusChangeListener(this);
			mMainLayout.getChildAt(index).setOnClickListener(this);
		}

		reflectImageView();

	}

	private void reflectImageView()
	{
		ImageView[] mImageViews = new ImageView[mImageViewId.length];
		View[] mRefFrames = new View[mRefFrameId.length];
		for (int i = 0; i < mImageViewId.length; i++)
		{

			mImageViews[i] = (ImageView) getParentView().findViewById(mImageViewId[i]);
			mRefFrames[i] = getParentView().findViewById(mRefFrameId[i]);
			ReflectView.reflectImage(mImageViews[i], mRefFrames[i]);
			mRefFrames[i].setNextFocusDownId(R.id.home_button);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		if (hasFocus)
		{

			mFlyBorderView.setVisibility(View.VISIBLE);
			mFlyBorderView.setTvScreen(true);
			if (v.getId() == R.id.frame_tv)
			{
				mFlyBorderView.setFocusView(v, 1.15f);
				showOnFocusAnimation(v, 1.15f);
			}
			else
			{
				mFlyBorderView.setFocusView(v, 1.20f);
				showOnFocusAnimation(v, 1.20f);
			}

		}
		else
		{
			mFlyBorderView.setVisibility(View.INVISIBLE);
			if (v.getId() == R.id.frame_tv)
			{
				showLoseFocusAnimation(v, 1.15f);
			}
			else
			{
				showLoseFocusAnimation(v, 1.20f);
			}

		}

	}

	private void showOnFocusAnimation(View v, float scale)
	{
		animEffect.setAttributs(1.0f, scale, 1.0f, scale, 100);
		Animation anim = animEffect.createAnimation();
		v.startAnimation(anim);
		v.bringToFront();
	}

	private void showLoseFocusAnimation(View v, float scale)
	{
		animEffect.setAttributs(scale, 1.0f, scale, 1.0f, 100);
		Animation anim = animEffect.createAnimation();
		v.startAnimation(anim);
	}

	@Override
	protected int getItemCount()
	{
		return 0;
	}

	@Override
	protected void GetItemTextAndIconsRes()
	{

	}

	@Override
	protected void setItemTextAndIcons(int i, View viewCurentItem)
	{

	}

	@Override
	public void onClick(View view)
	{
		Uri uri;
		switch (view.getId())
		{

		case R.id.frame_tv:
			try
			{
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.dvb.dtv.home", "com.dvb.dtv.home.InitActivity"));
				thisActivity.startActivity(intent);

			}
			catch (Exception e)
			{
				showToast(getResources().getString(R.string.main_app_open_error));
				e.printStackTrace();

			}
			break;

		case R.id.frame_edu:
			// http://www.chinaedu.edu.cn
			uri = Uri.parse("http://www.chinaedu.edu.cn");
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			thisActivity.startActivity(it);
			break;

		case R.id.frame_movie:
			openApp("com.mxtech.videoplayer.pro");
			break;

		case R.id.frame_update:
			showToast(getResources().getString(R.string.main_app_wait_develop));
			break;

		case R.id.frame_game:
			showToast(getResources().getString(R.string.main_app_wait_develop));
			break;

		case R.id.frame_search:
			showToast(getResources().getString(R.string.main_app_wait_develop));
			break;

		case R.id.frame_about:
			openApp("com.skype.rover");
			break;

		}
	}
}