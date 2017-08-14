package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View.OnFocusChangeListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * @version 1.0
 * @author:john
 * @date：Nov 30, 2015 2:00:04 PM
 * @description: gridview +viewpager
 */
public class ImageGridView extends FrameLayout implements OnItemClickListener, OnItemSelectedListener, OnFocusChangeListener {
    //private static final String TAG = ImageGridView.class.getSimpleName();
    private OnImageActionListener mActionListener = null;
    private FlyBorderView mFlyBorderView = null;

    public ImageGridView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ImageGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null, 0);
    }

    public ImageGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null, 0);
    }

    public ImageGridView(Context context, ListAdapter adapter, int numCol, OnImageActionListener listener) {
        super(context);
        mActionListener = listener;
        init(context, adapter, numCol);
    }

    private void init(Context context, ListAdapter adapter, int numCol) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_grid, this, true);
        GridView gridView = (GridView) view.findViewById(R.id.app_gridview);
        mFlyBorderView = (FlyBorderView) view.findViewById(R.id.flyBorder_view);
        gridView.setNumColumns(numCol);
        gridView.setNextFocusDownId(R.id.app_button);
        gridView.setOnItemClickListener(this);
        gridView.setOnFocusChangeListener(this);
        gridView.setOnItemSelectedListener(this);
        setGridAdapter(gridView, adapter);

    }

    private void setGridAdapter(GridView gv, ListAdapter adapter) {
        gv.setAdapter(adapter);
    }

    public interface OnImageActionListener {
        void onImageItemClick(AdapterView<?> parent, int position);

        void OnImageItemSelected(View selectView, FlyBorderView flyBorderView);

        void OnImageFocusChange(boolean hasFocus, FlyBorderView flyBorderView);

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        mActionListener.onImageItemClick(arg0, arg2);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        mActionListener.OnImageItemSelected(arg1, mFlyBorderView);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        mActionListener.OnImageFocusChange(hasFocus, mFlyBorderView);

    }

}
