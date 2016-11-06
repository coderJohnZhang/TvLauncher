package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextImageView extends LinearLayout implements OnClickListener
{
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	private LinearLayout layoutMain = null;
	private ImageView image = null;
	private TextView text = null;
	private Context mContext = null;
	private View view;
	private OnClickListener clickListener = null;

	public TextImageView(Context context)
	{

		super(context);
		this.mContext = context;
		initView();
	}

	public String getText()
	{
		return (String) text.getText();
	}

	public TextImageView(Context context, AttributeSet attrs)
	{

		super(context, attrs);
		this.mContext = context;
		initView();
	}

	private void initView()
	{
		view = LayoutInflater.from(mContext).inflate(R.layout.text_image_view, this, true);
		layoutMain = (LinearLayout) view.findViewById(R.id.layoutAll);
		image = (ImageView) view.findViewById(R.id.imageViewImage);
		text = (TextView) view.findViewById(R.id.textViewText);
		image.setOnClickListener(this);
		text.setOnClickListener(this);
	}

	public void setOrientation(int orientation)
	{
		layoutMain.setOrientation(orientation);
	}

	public void initTitle(int imageResId, int textResId, float textSize, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom)
	{
		setImageResource(imageResId);
		setText(textResId);
		setTextSize(textSize);
		setTextPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}

	public void initTitle(int imageResId, String text, float textSize, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom)
	{
		setImageResource(imageResId);
		setText(text);
		setTextSize(textSize);
		setTextPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}

	public void initTitle(int imageResId, int textResId)
	{
		initTitle(imageResId, textResId, mContext.getResources().getDimension(R.dimen.text_size_large), 30, 0, 0, 0);
	}

	public void initTitle(int imageResId, String text)
	{
		initTitle(imageResId, text, mContext.getResources().getDimension(R.dimen.text_size_large), 30, 0, 0, 0);
	}

	public void initColorHelp(int imageResId, int textResId)
	{
		setImageResource(imageResId);
		setText(textResId);
	}

	public void initColorHelp(int imageResId, String text)
	{
		setImageResource(imageResId);
		setText(text);
	}

	public void setImageWidthHeight(int iWidth, int iHeight)
	{
		image.setLayoutParams(new LayoutParams(iWidth, iHeight));
	}

	public void setTextWidthHeight(int iWidth, int iHeight)
	{
		text.setLayoutParams(new LayoutParams(iWidth, iHeight));
	}

	public void setText(String str)
	{
		text.setText(str);
	}

	public void setText(int id)
	{
		text.setText(mContext.getResources().getString(id));
	}

	public void setTextSize(int id)
	{
		text.setTextSize(mContext.getResources().getDimension(id));
	}

	public void setImageResource(int resId)
	{
		image.setImageResource(resId);
	}

	public void setImageTextPadding(int imagetop, int left, int top, int right, int bottom)
	{
		image.setPadding(0, imagetop, 0, 0);
		text.setPadding(left, top, right, bottom);
	}

	public void setTextPadding(int left, int top, int right, int bottom)
	{
		text.setPadding(left, top, right, bottom);
	}

	public void setTextSize(float size)
	{
		text.setTextSize(size);
	}

	public void setTextColor(int color)
	{
		text.setTextColor(color);
	}

	public interface OnClickListener
	{
		public void onClick(View v);
	}

	public void setOnClickListener(OnClickListener listener)
	{
		this.clickListener = listener;
	}

	@Override
	public void onClick(View v)
	{

		if (clickListener != null)
		{
			clickListener.onClick(this);
		}
	}

}
