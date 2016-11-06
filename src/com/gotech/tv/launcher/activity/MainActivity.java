package com.gotech.tv.launcher.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnFocusChangeListener;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.adapter.MyViewPagerAdapter;
import com.gotech.tv.launcher.anim.ControlViewPagerSpeed;
import com.gotech.tv.launcher.anim.DepthPageTransformer;
import com.gotech.tv.launcher.anim.RippleLayout;
import com.gotech.tv.launcher.page.BasePage;
import com.gotech.tv.launcher.page.PageApp;
import com.gotech.tv.launcher.page.PageHome;
import com.gotech.tv.launcher.page.PageSetting;
import com.gotech.tv.launcher.receiver.AppOperationReceiver;
import com.gotech.tv.launcher.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author john
 *
 */
public class MainActivity extends TvBaseActivity implements OnFocusChangeListener
{

	private int[] mRippleLayoutId = { R.id.ripple_layout_home, R.id.ripple_layout_app, R.id.ripple_layout_info };
	private ViewPager mViewPager = null;
	private PageApp mPageApp = null;
	private View[] views = null;
	private int viewId[] = { R.id.home_button, R.id.app_button, R.id.setting_button };
	private AppOperationReceiver mAppOperationReceiver = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAppOperationReceiver = new AppOperationReceiver(this);
		initView();

	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		if (mAppOperationReceiver != null)
		{
			mAppOperationReceiver.register(this);
		}

	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		if (mAppOperationReceiver != null)
		{
			mAppOperationReceiver.unRegister(this);
		}

	}

	/**
	 * 初始首页
	 */
	public void initView()
	{

		mViewPager = (ViewPager) findViewById(R.id.my_pager);
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
		ControlViewPagerSpeed mViewPagerSpeed = new ControlViewPagerSpeed(mViewPager);
		mViewPagerSpeed.controlSpeed();
		PageHome mPageHome = new PageHome(this);
		mPageApp = new PageApp(this);
		PageSetting mPageSetting = new PageSetting(this);
		List<BasePage> mBasePageList = new ArrayList<BasePage>();
		List<View> mList = new ArrayList<View>();
		mBasePageList.add(mPageHome);
		mBasePageList.add(mPageApp);
		mBasePageList.add(mPageSetting);
		for (int i = 0; i < mBasePageList.size(); i++)
		{
			View currentView = mBasePageList.get(i).getParentView();
			mList.add(currentView);
		}
		MyViewPagerAdapter mViewPagerAdapter = new MyViewPagerAdapter(mList);
		mViewPager.setAdapter(mViewPagerAdapter);

		setCurrentPage(Constant.MENU_HOME);
		views = new View[viewId.length];
		for (int i = 0; i < viewId.length; i++)
		{
			views[i] = findViewById(viewId[i]);
			views[i].setOnFocusChangeListener(this);
		}
		views[0].setBackgroundResource(R.drawable.icon_homebtn_checked);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int arg0)
			{

				InitMenuBackGround();

				switch (arg0)
				{
				case Constant.MENU_HOME:
					views[0].setBackgroundResource(R.drawable.icon_homebtn_checked);
					break;

				case Constant.MENU_APP:
					views[1].setBackgroundResource(R.drawable.icon_appbtn_checked);
					break;

				case Constant.MENU_SETTING:
					views[2].setBackgroundResource(R.drawable.icon_setbtn_checked);
					break;

				default:
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{

			}
		});

	}

	protected void setCurrentPage(int iMenu)
	{
		mViewPager.setCurrentItem(iMenu);
	}

	public PageApp getAppPage()
	{
		return mPageApp;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		int position = -1;
		if (hasFocus)
		{
			switch (v.getId())
			{
			case R.id.home_button:
				position = 0;
				break;
			case R.id.app_button:
				position = 1;
				break;
			case R.id.setting_button:
				position = 2;
				break;

			default:
				break;

			}
			setCurrentPage(position);
			rippleAnimation(position);
		}
		else
		{
			stopAllRippleAnimation();
		}

	}

	public void rippleAnimation(int position)
	{

		RippleLayout mLayout = (RippleLayout) findViewById(mRippleLayoutId[position]);
		if (mLayout.isRippleAnimationRunning())
		{
			mLayout.stopRippleAnimation();
		}
		else
		{
			mLayout.startRippleAnimation();
		}

	}

	public void stopAllRippleAnimation()
	{

		for (int aMRippleLayoutId : mRippleLayoutId)
		{
			RippleLayout mLayout = (RippleLayout) findViewById(aMRippleLayoutId);
			mLayout.stopRippleAnimation();
		}

	}

	public void InitMenuBackGround()
	{

		views[0].setBackgroundResource(R.drawable.icon_homebtn_normal);
		views[1].setBackgroundResource(R.drawable.icon_appbtn_normal);
		views[2].setBackgroundResource(R.drawable.icon_setbtn_normal);

	}

}