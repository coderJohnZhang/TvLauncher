package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.util.Constant;
import com.gotech.tv.launcher.util.DensityUtil;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class FlyBorderView extends View
{

	private View mFocusView;
	private View mSelectView;
	private boolean isTvScreen = false;

	public FlyBorderView(Context context)
	{
		super(context, null, 0);
		init(context);
	}

	public FlyBorderView(Context context, AttributeSet attrs)
	{
		super(context, attrs, 0);
		init(context);
	}

	public FlyBorderView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context)
	{

	}

	public boolean isTvScreen()
	{
		return isTvScreen;
	}

	public void setTvScreen(boolean isTvScreen)
	{
		this.isTvScreen = isTvScreen;
		invalidate();
	}

	/**
	 * 设置焦点框的移动.
	 */
	public void setFocusView(View view, float scale)
	{
		if (mFocusView != view)
		{
			mFocusView = view;
			runTranslateAnimation(mFocusView, scale, scale);
		}
	}

	public void setSelectView(View view)
	{
		if (mSelectView != view)
		{
			mSelectView = view;
		
			runTranslateAnimation(mSelectView);
		}
	}

	private void runTranslateAnimation(View toView)
	{
		Rect fromRect = findLocationWithView(this);
		Rect toRect = findLocationWithView(toView);
		int x = toRect.left - fromRect.left;
		int y = toRect.top - fromRect.top;

		int deltaX = (toView.getWidth() - this.getWidth()) / 2;
		int deltaY = (toView.getHeight() - this.getHeight()) / 2;
		// tv
		if (isTvScreen)
		{
			x = DensityUtil.dip2px(this.getContext(), x + deltaX);
			y = DensityUtil.dip2px(this.getContext(), y + deltaY);
		}
		else
		{
			x = x + deltaX;
			y = y + deltaY;
		}
		flyWhiteBorder(x, y);

	}

	private void flyWhiteBorder(float x, float y)
	{
		
		animate().translationX(x).translationY(y).setDuration(Constant.TRAN_DUR_ANIM).setInterpolator(new DecelerateInterpolator()).start();

	}

	public void runTranslateAnimation(View toView, float scaleX, float scaleY)
	{
		Rect fromRect = findLocationWithView(this);
		Rect toRect = findLocationWithView(toView);

		int x = toRect.left - fromRect.left;
		int y = toRect.top - fromRect.top;

		int deltaX = (toView.getWidth() - this.getWidth()) / 2;
		int deltaY = (toView.getHeight() - this.getHeight()) / 2;
		// tv
		if (isTvScreen)
		{
			x = DensityUtil.dip2px(this.getContext(), x + deltaX);
			y = DensityUtil.dip2px(this.getContext(), y + deltaY);
		}
		else
		{
			x = x + deltaX;
			y = y + deltaY;
		}
		float toWidth = toView.getWidth() * scaleX;
		float toHeight = toView.getHeight() * scaleY;
		int width = (int) (toWidth);
		int height = (int) (toHeight);

		flyWhiteBorder(width, height, x, y);
	}

	private void flyWhiteBorder(int width, int height, float x, float y)
	{
		int mWidth = this.getWidth();
		int mHeight = this.getHeight();

		float scaleX = (float) width / (float) mWidth;
		float scaleY = (float) height / (float) mHeight;

		animate().translationX(x).translationY(y).setDuration(Constant.TRAN_DUR_ANIM).scaleX(scaleX).scaleY(scaleY).setInterpolator(new DecelerateInterpolator()).start();
	}

	public Rect findLocationWithView(View view)
	{
		ViewGroup root = (ViewGroup) this.getParent();
		Rect rect = new Rect();
		root.offsetDescendantRectToMyCoords(view, rect);
		return rect;
	}

}
