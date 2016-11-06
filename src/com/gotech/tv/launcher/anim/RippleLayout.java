package com.gotech.tv.launcher.anim;

import java.util.ArrayList;

import com.gotech.tv.launcher.R;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * This is a ripple effect of the layout, the default round completely
 * invisiable view to add the layout, start the animation will be graphics zoom,
 * color gradient, causing a ripple effect.
 * 
 */
public class RippleLayout extends RelativeLayout
{
	/**
	 * static final fields
	 */
	private static final int DEFAULT_RIPPLE_COUNT = 6;
	private static final int DEFAULT_DURATION_TIME = 2000;
	private static final float DEFAULT_SCALE = 1.2f;
	private static final int DEFAULT_RIPPLE_COLOR = Color.rgb(255, 140, 0);
	private static final int DEFAULT_STROKE_WIDTH = 0;
	private static final int DEFAULT_RADIUS = 30;
	/**
	 * Private member variables of basic data types
	 */
	private int mRippleColor = DEFAULT_RIPPLE_COLOR;
	private float mStrokeWidth = DEFAULT_STROKE_WIDTH;
	private float mRippleRadius = DEFAULT_RADIUS;
	private int mAnimDuration = DEFAULT_DURATION_TIME;
	private int mRippleViewNums = DEFAULT_RIPPLE_COUNT;
	private int mAnimDelay = 0;
	private float mRippleScale = DEFAULT_SCALE;
	private boolean mAnimationRunning = false;
	/**
	 * Private member variable reference data types
	 */
	private Paint mPaint = new Paint();
	// Perform zoom
	private AnimatorSet mAnimatorSet = new AnimatorSet();
	// Save animation
	private ArrayList<Animator> mAnimatorList = new ArrayList<Animator>();
	private LayoutParams mRippleViewParams = null;

	public RippleLayout(Context context)
	{
		super(context);
		init(context, null);
	}

	public RippleLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(final Context context, final AttributeSet attrs)
	{
		if (isInEditMode())
		{
			return;
		}

		if (null != attrs)
		{
			initTypedArray(context, attrs);
		}

		initPaint();
		initRippleViewLayoutParams();
		generateRippleViews();

	}

	/**
	 * Initialize the custom attribute container
	 * 
	 * @param context
	 * @param attrs
	 */
	private void initTypedArray(Context context, AttributeSet attrs)
	{
		final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.rippleLayout);

		mRippleColor = typedArray.getColor(R.styleable.rippleLayout_color, DEFAULT_RIPPLE_COLOR);
		mStrokeWidth = typedArray.getDimension(R.styleable.rippleLayout_strokeWidth, DEFAULT_STROKE_WIDTH);
		mRippleRadius = typedArray.getDimension(R.styleable.rippleLayout_radius, DEFAULT_RADIUS);
		mAnimDuration = typedArray.getInt(R.styleable.rippleLayout_duration, DEFAULT_DURATION_TIME);
		mRippleViewNums = typedArray.getInt(R.styleable.rippleLayout_rippleNums, DEFAULT_RIPPLE_COUNT);
		mRippleScale = typedArray.getFloat(R.styleable.rippleLayout_scale, DEFAULT_SCALE);

		/**
		 * recycle the typedArray
		 */
		typedArray.recycle();
	}

	/**
	 * create a brush and initialization
	 */
	private void initPaint()
	{
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mStrokeWidth = 2.0f;
		mPaint.setStyle(Paint.Style.STROKE);// Hollow effect
		mPaint.setColor(mRippleColor);
		mPaint.setStrokeWidth(mStrokeWidth);// set the line width
	}

	private void initRippleViewLayoutParams()
	{
		// Ripple view size is two times the width of the (radius + pen)
		int rippleSide = (int) (2 * (mRippleRadius + mStrokeWidth));
		mRippleViewParams = new LayoutParams(rippleSide, rippleSide);
		// The settings shown in the position of the parent control
		mRippleViewParams.addRule(CENTER_IN_PARENT, TRUE);
	}

	private void calculateAnimDelay()
	{
		mAnimDelay = mAnimDuration / mRippleViewNums;
	}

	private void generateRippleViews()
	{

		calculateAnimDelay();
		initAnimSet();
		// add RippleView
		for (int i = 0; i < mRippleViewNums; i++)
		{
			RippleView rippleView = new RippleView(getContext());
			addView(rippleView, mRippleViewParams);
			// add animation
			addAnimToRippleView(rippleView, i);
		}

		mAnimatorSet.playTogether(mAnimatorList);

	}

	private void initAnimSet()
	{
		mAnimatorSet.setDuration(mAnimDuration);
		mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
	}

	private void addAnimToRippleView(RippleView rippleView, int i)
	{

		// The X axis zoom animation
		final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleView, "scaleX", 1.0f, mRippleScale);
		scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
		scaleXAnimator.setStartDelay(i * mAnimDelay);
		scaleXAnimator.setDuration(mAnimDuration);
		mAnimatorList.add(scaleXAnimator);

		// The Y axis zoom animation
		final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleView, "scaleY", 1.0f, mRippleScale);
		scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
		scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		scaleYAnimator.setStartDelay(i * mAnimDelay);
		scaleYAnimator.setDuration(mAnimDuration);
		mAnimatorList.add(scaleYAnimator);

		/**
		 * The color of alpha animation
		 */
		final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleView, "alpha", 1.0f, 0f);
		alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
		alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		alphaAnimator.setDuration(mAnimDuration);
		alphaAnimator.setStartDelay(i * mAnimDelay);
		mAnimatorList.add(alphaAnimator);
	}

	public void startRippleAnimation()
	{
		if (!isRippleAnimationRunning())
		{
			makeRippleViewsVisible();
			mAnimatorSet.start();
			mAnimationRunning = true;
		}
	}

	// make the child view visible
	private void makeRippleViewsVisible()
	{
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			View childView = this.getChildAt(i);
			if (childView instanceof RippleView)
			{
				childView.setVisibility(VISIBLE);
			}
		}
	}

	// make the child view invisible
	private void makeRippleViewsInVisible()
	{
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			View childView = this.getChildAt(i);
			if (childView instanceof RippleView)
			{
				childView.setVisibility(INVISIBLE);
			}
		}
	}

	public void stopRippleAnimation()
	{
		if (isRippleAnimationRunning())
		{

			mAnimatorSet.end();
			mAnimationRunning = false;
			makeRippleViewsInVisible();

		}
	}

	public boolean isRippleAnimationRunning()
	{
		return mAnimationRunning;
	}

	private class RippleView extends View
	{

		public RippleView(Context context)
		{
			super(context);
			this.setVisibility(View.INVISIBLE);
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			int radius = (Math.min(getWidth(), getHeight())) / 2;
			// Draw a circle
			/**
			 * Cx: the X coordinates.
			 * 
			 * Cy: the Y coordinates.
			 * 
			 * Radius: the radius of the circle.
			 * 
			 * Paint: used when drawing brush.
			 */
			canvas.drawCircle(radius, radius, radius - mStrokeWidth, mPaint);
		}
	}
}
