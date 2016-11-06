package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PosterView extends FrameLayout
{
	private ImageView mPosterIcon;
	private TextView mPosterText;
	private View mPosterBackground;

	public PosterView(Context context)
	{
		this(context, null);
	}

	public PosterView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	@SuppressWarnings("deprecation")
	public PosterView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		initView();
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.poster);

		if (mPosterIcon != null)
		{

			Drawable drawable = typedArray.getDrawable(R.styleable.poster_icon);

			if (drawable != null)
			{
				mPosterIcon.setImageDrawable(drawable);
			}

			int width = typedArray.getDimensionPixelOffset(R.styleable.poster_iconW, 100);
			int height = typedArray.getDimensionPixelOffset(R.styleable.poster_iconH, 100);
			ViewGroup.LayoutParams lp = mPosterIcon.getLayoutParams();
			lp.width = width;
			lp.height = height;
			mPosterIcon.setLayoutParams(lp);

		}

		if (mPosterText != null)
		{

			String text = typedArray.getString(R.styleable.poster_text);

			if (text != null)
			{
				mPosterText.setText(text);
			}

			int color = typedArray.getColor(R.styleable.poster_textColor, Color.WHITE);
			mPosterText.setTextColor(color);
			float size = typedArray.getDimensionPixelOffset(R.styleable.poster_textSize, 24);
			mPosterText.setTextSize(size);

		}

		if (mPosterBackground != null)
		{
			Drawable drawable = typedArray.getDrawable(R.styleable.poster_background);

			if (drawable != null)
			{
				mPosterBackground.setBackgroundDrawable(drawable);
			}

		}

		typedArray.recycle();

	}

	private void initView()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.poster_view_layout, this, true);
		mPosterBackground = findViewById(R.id.poster);
		mPosterIcon = (ImageView) findViewById(R.id.imageView);
		mPosterText = (TextView) findViewById(R.id.textView);
	}

	public void setImageDrawable(Drawable drawable)
	{
		if (mPosterIcon != null)
		{
			mPosterIcon.setImageDrawable(drawable);
		}
	}

	public void setImageDrawable(int resId)
	{
		if (mPosterIcon != null)
		{
			mPosterIcon.setImageResource(resId);
		}
	}

	public void setText(CharSequence text)
	{
		if (mPosterText != null)
		{
			mPosterText.setText(text);
		}
	}

	public void setText(int resId)
	{
		if (mPosterText != null)
		{
			mPosterText.setText(resId);
		}
	}

	public ImageView getIconImageView()
	{
		return mPosterIcon;
	}

	public TextView getTextView()
	{
		return mPosterText;
	}

	public void setTextVisibility(int visibility)
	{
		if (mPosterText != null)
		{
			mPosterText.setVisibility(visibility);
		}
	}

	public void setIconVisibility(int visibility)
	{
		if (mPosterIcon != null)
		{
			mPosterIcon.setVisibility(visibility);
		}
	}

	@SuppressWarnings("deprecation")
	public void setPosterBackground(Drawable drawable)
	{
		if (mPosterBackground != null)
		{
			mPosterBackground.setBackgroundDrawable(drawable);
		}
	}

	public void setPosterBackground(int resId)
	{
		if (mPosterBackground != null)
		{
			mPosterBackground.setBackgroundResource(resId);
		}
	}
}
