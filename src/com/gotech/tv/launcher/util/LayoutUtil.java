package com.gotech.tv.launcher.util;

import com.gotech.tv.launcher.R;

import android.view.View;

public class LayoutUtil
{
	//private final static String TAG = LayoutUtil.class.getSimpleName();

	public static View getMainMenuCommonView(int index, View parant)
	{
		View myView = null;
		switch (index)
		{
		case 0:
			myView = (View) parant.findViewById(R.id.myview1);
			break;
		case 1:
			myView = (View) parant.findViewById(R.id.myview2);
			break;
		case 2:
			myView = (View) parant.findViewById(R.id.myview3);
			break;
		case 3:
			myView = (View) parant.findViewById(R.id.myview4);
			break;
		case 4:
			myView = (View) parant.findViewById(R.id.myview5);
			break;
		case 5:
			myView = (View) parant.findViewById(R.id.myview6);
			break;
		case 6:
			//myView = (View) parant.findViewById(R.id.myview7);
			break;
		case 7:
			// myView = (View) parant.findViewById(R.id.myview8);
			break;
		case 8:
			// myView = (View) parant.findViewById(R.id.myview9);
			break;
		case 9:
			// myView = (View) parant.findViewById(R.id.myview10);
			break;
		}
		return myView;
	}

}
