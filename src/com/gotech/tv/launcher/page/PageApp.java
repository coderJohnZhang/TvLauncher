package com.gotech.tv.launcher.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;

import com.gotech.tv.launcher.anim.CubeOutTransformer;
import com.gotech.tv.launcher.util.Constant;
import com.gotech.tv.launcher.view.CustomDialog;
import com.gotech.tv.launcher.view.FlyBorderView;
import com.gotech.tv.launcher.view.ImageGridView;
import com.gotech.tv.launcher.view.ImageGridView.OnImageActionListener;
import com.gotech.tv.launcher.view.PageIndicator;
import com.gotech.tv.launcher.vo.AppInfoVo;
import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.activity.MainActivity;
import com.gotech.tv.launcher.adapter.AppGridAdapter;
import com.gotech.tv.launcher.adapter.MyViewPagerAdapter;
import com.gotech.tv.launcher.anim.ControlViewPagerSpeed;
import com.gotech.tv.launcher.anim.DepthPageTransformer;

/**
 * @author: john
 * @date: Nov 26, 2015 5:16:07 PM
 * @version 1.0
 * @description: manage app
 */
public class PageApp extends BasePage
{
	private ViewPager mViewPager = null;
	private PackageManager mPackManager = null;
	private List<AppInfoVo> mAppInfos = null;
	private List<View> mListViews = null;
	private int move = 0;
	private CustomDialog mCustomDialog = null;
	private AppInfoVo mSelectAppInfoVo = null;
	private int mPageCount = 0;
	private int mCurrentPageNum = 0;
	private int lastPosition = 0;
	private int currentPosition = 0;
	private PageIndicator mPageIndicator = null;

	public PageApp(MainActivity activity)
	{
		super(activity);
		initViews();
	}

	@Override
	public int getLayoutViewId()
	{

		return R.layout.index_apps;

	}

	private void initViews()
	{

		mViewPager = (ViewPager) getParentView().findViewById(R.id.viewpager_app);

		mAppInfos = sortAppsByName();

		testSortResult();
		Log.d(TAG, "=======================匹配SharedPreferences中存储位置========================" + System.getProperty("line.separator") + " ");
		sortAppsByUser(mAppInfos);

		testMoveResult();

		int appCount = mAppInfos.size();
		mPageCount = (int) Math.ceil(appCount / Constant.APP_PAGE_SIZE);
		initOnePageData();

		MyViewPagerAdapter mViewPagerAdapter = new MyViewPagerAdapter(mListViews);
		mViewPager.setAdapter(mViewPagerAdapter);
		mPageIndicator = (PageIndicator) getParentView().findViewById(R.id.indicator);
		mPageIndicator.setViewPager(mViewPager);
		mViewPager.setPageTransformer(true, new CubeOutTransformer());
		ControlViewPagerSpeed mViewPagerSpeed = new ControlViewPagerSpeed(mViewPager);
		mViewPagerSpeed.controlSpeed();
		/**
		 * This method will be invoked when the current page is scrolled, either
		 * as part of a programmatically initiated smooth scroll or a user
		 * initiated touch scroll.
		 * 
		 * @param position
		 *            Position index of the first page currently being
		 *            displayed. Page position+1 will be visible if
		 *            positionOffset is nonzero.
		 * @param positionOffset
		 *            Value from [0, 1) indicating the offset from the page at
		 *            position.
		 * @param positionOffsetPixels
		 *            Value in pixels indicating the offset from position.
		 */
		// public void onPageScrolled(int position, float positionOffset, int
		// positionOffsetPixels);

		/**
		 * This method will be invoked when a new page becomes selected.
		 * Animation is not necessarily complete.
		 * 
		 * @param position
		 *            Position index of the new selected page.
		 */
		// public void onPageSelected(int position);

		/**
		 * Called when the scroll state changes. Useful for discovering when the
		 * user begins dragging, when the pager is automatically settling to the
		 * current page, or when it is fully stopped/idle.
		 * 
		 * @param state
		 *            The new scroll state.
		 * @see ViewPager#SCROLL_STATE_IDLE
		 * @see ViewPager#SCROLL_STATE_DRAGGING
		 * @see ViewPager#SCROLL_STATE_SETTLING
		 */
		// public void onPageScrollStateChanged(int state);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int arg0)
			{

				mCurrentPageNum = arg0;
				mPageIndicator.updateIndicator(arg0);

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
		manageApp();

	}

	public void initOnePageData()
	{

		mListViews = new ArrayList<View>();
		for (int i = 0; i < mPageCount; i++)
		{
			AppGridAdapter mAppGridAdapter = new AppGridAdapter(i, (int) Constant.APP_PAGE_SIZE, mAppInfos, thisActivity);

			ImageGridView appPage = new ImageGridView(thisActivity, mAppGridAdapter, Constant.APP_PAGE_COLUMNS, new OnImageActionListener()
			{

				@Override
				public void onImageItemClick(AdapterView<?> parent, int position)
				{

					mSelectAppInfoVo = (AppInfoVo) parent.getItemAtPosition(position);
					currentPosition = (int) (position + Constant.APP_PAGE_SIZE * mCurrentPageNum);
					Log.d(TAG, "***********将要被移动的位置*********" + currentPosition);
					switch (move)
					{

					case Constant.MOVE:
						move = 0;
						moveApp(mAppInfos, lastPosition, currentPosition);
						refresh(false);
						saveAppMoveStatus();

						break;

					default:
						lastPosition = currentPosition;
						Log.d(TAG, "***********当前应用的位置*********" + lastPosition);
						mCustomDialog.show();

						break;
					}

				}

				@Override
				public void OnImageItemSelected(View selectView, FlyBorderView flyBorderView)
				{
					flyBorderView.setTvScreen(true);
					flyBorderView.setSelectView(selectView);

				}

				@Override
				public void OnImageFocusChange(boolean hasFocus, FlyBorderView flyBorderView)
				{
					if (hasFocus)
					{
						flyBorderView.setVisibility(View.VISIBLE);
					}
					else
					{
						flyBorderView.setVisibility(View.INVISIBLE);
					}

				}

			});

			mListViews.add(appPage);

		}
	}

	public void refresh(boolean bDelete)
	{
		int pageNum = mViewPager.getCurrentItem();
		mViewPager.removeAllViews();
		if (bDelete)
		{
			mAppInfos.remove(currentPosition);
			mPageCount = (int) Math.ceil(mAppInfos.size() / Constant.APP_PAGE_SIZE);
		}

		initOnePageData();
		mViewPager.setAdapter(new MyViewPagerAdapter(mListViews));

		if (pageNum == mPageCount)// one page has deleted all data
		{
			pageNum--;
		}

		mViewPager.setCurrentItem(pageNum);

	}

	private void manageApp()
	{
		OnClickListener myClickListener = new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				switch (v.getId())
				{

				case R.id.button_open:
					openApps(mSelectAppInfoVo.getAppPackageName());
					break;
				case R.id.button_move:
					move = 1;
					break;

				case R.id.button_uninstall:
					uninstallApps(mSelectAppInfoVo.getAppPackageName(), mSelectAppInfoVo);
					break;
				}
				mCustomDialog.dismiss();

			}

		};

		mCustomDialog = new CustomDialog(thisActivity, R.style.custom_dialog, myClickListener);

	}

	public void uninstallApps(String packageName, AppInfoVo mAppInfo)
	{

		if (mAppInfo.isSystemApps())
		{

			Toast.makeText(thisActivity, "you must get system apps uninstall permission!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Uri packageUri = Uri.parse("package:" + packageName);
			Intent deleteIntent = new Intent(Intent.ACTION_DELETE, packageUri);
			thisActivity.startActivity(deleteIntent);
		}
	}

	public void openApps(String packageName)
	{
		Intent mainIntent = thisActivity.getPackageManager().getLaunchIntentForPackage(packageName);
		mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try
		{
			// launcher the package
			thisActivity.startActivity(mainIntent);

		}
		catch (ActivityNotFoundException noFound)
		{
			Toast.makeText(thisActivity, "Package not found!", Toast.LENGTH_SHORT).show();
		}
	}

	public List<AppInfoVo> sortAppsByName()
	{
		mPackManager = thisActivity.getPackageManager();
		Intent mainiIntent = new Intent(Intent.ACTION_MAIN, null);
		mainiIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfos = mPackManager.queryIntentActivities(mainiIntent, 0);
		Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(mPackManager));
		List<AppInfoVo> data = new ArrayList<AppInfoVo>();
		data.clear();
		for (ResolveInfo app : resolveInfos)
		{
			data.add(getApp(app));
		}
		return data;
	}

	public AppInfoVo getApp(ResolveInfo app)
	{
		AppInfoVo mAppInfo = new AppInfoVo();
		mAppInfo.setAppName((String) app.loadLabel(mPackManager));
		mAppInfo.setAppPackageName(app.activityInfo.packageName);
		mAppInfo.setAppIcon(app.loadIcon(mPackManager));

		if ((app.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
		{
			mAppInfo.setIsSystemApps(true);
		}
		else
		{
			mAppInfo.setIsSystemApps(false);
		}

		Intent intent = new Intent();
		intent.setComponent(new ComponentName(app.activityInfo.packageName, app.activityInfo.name));
		mAppInfo.setAppIntent(intent);

		return mAppInfo;
	}

	public void moveApp(List<AppInfoVo> appInfos, int from, int to)
	{

		// if (from < to)
		// {
		// for (int i = from; i < to; i++)
		// {
		// Collections.swap(appInfos, i, i + 1);
		// }
		// }
		// else
		// {
		// for (int i = from; i > to; i--)
		// {
		// Collections.swap(appInfos, i, i - 1);
		// }
		// }
		if (0 <= from && from < appInfos.size() && 0 <= to && to < appInfos.size())
		{
			Collections.swap(appInfos, from, to);
		}

	}

	public void saveAppMoveStatus()
	{

		SharedPreferences mSharedPreferences = thisActivity.getSharedPreferences("app_order", Context.MODE_PRIVATE);
		Editor editor = mSharedPreferences.edit();

		editor.clear();

		for (int i = 0; i < mAppInfos.size(); i++)
		{

			editor.putInt(mAppInfos.get(i).getAppName(), i);

		}

		editor.apply();

	}

	public void sortAppsByUser(List<AppInfoVo> appInfoVos)
	{
		int appLocation;
		int count;
		SharedPreferences mSharedPreferences = thisActivity.getSharedPreferences("app_order", Context.MODE_PRIVATE);

		if (0 == mSharedPreferences.getAll().size())
		{
			return;
		}
		else
		{
			count = appInfoVos.size();
			appLocation = -1;
			List<AppInfoVo> dataList = new ArrayList<AppInfoVo>();
			for (int i = 0; i < count; i++)
			{

				String appName = appInfoVos.get(i).getAppName();
				/**
				 * int getInt(String key, int defValue);
				 * 
				 * Retrieve an int value from the preferences.
				 * 
				 * @param key
				 *            The name of the preference to retrieve.
				 * @param defValue
				 *            Value to return if this preference does not exist.
				 * 
				 * @return Returns the preference value if it exists, or
				 *         defValue. Throws ClassCastException if there is a
				 *         preference with this name that is not an int.
				 * 
				 * @throws ClassCastException
				 */

				dataList.add(appInfoVos.get(i));
				appLocation = mSharedPreferences.getInt(appName, -1);

				moveApp(dataList, i, appLocation);

			}
			mAppInfos = dataList;
		}
	}

	public void testSortResult()
	{
		for (int i = 0; i < mAppInfos.size(); i++)
		{

			String logAppName = mAppInfos.get(i).getAppName();
			Log.d(TAG, "*************按字典排序后*****************" + i + "---" + logAppName);
		}
		Log.d(TAG, " ");
	}

	public void testMoveResult()
	{
		for (int i = 0; i < mAppInfos.size(); i++)
		{

			String logAppName = mAppInfos.get(i).getAppName();
			Log.d(TAG, "*************按数据库排序后*****************" + i + "---" + logAppName);
		}
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

}
