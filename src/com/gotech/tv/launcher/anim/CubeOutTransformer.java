package com.gotech.tv.launcher.anim;

import android.view.View;

/**
 * Created by john on 12/15/15.
 * 
 */

public class CubeOutTransformer extends ABaseTransformer
{

	@Override
	protected void onTransform(View view, float position)
	{
		view.setPivotX(position < 0f ? view.getWidth() : 0f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setRotationY(90f * position);
	}

	@Override
	public boolean isPagingEnabled()
	{
		return true;
	}

}
