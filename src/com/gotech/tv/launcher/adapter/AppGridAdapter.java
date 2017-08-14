package com.gotech.tv.launcher.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.vo.AppInfoVo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppGridAdapter extends BaseAdapter {
    private int mPage = 0;
    private int mPageSize = 0;
    private List<AppInfoVo> mData = null;
    private Context mContext = null;
    private List<AppInfoVo> appList = null;

    public AppGridAdapter(int page, int pageSize, List<AppInfoVo> data, Context context) {

        mPage = page;
        mPageSize = pageSize;

        mData = data;
        mContext = context;

        appList = new ArrayList<AppInfoVo>();
        int i = mPage * mPageSize;
        int iEnd = i + mPageSize;

        while ((i < mData.size()) && (i < iEnd)) {
            appList.add(mData.get(i));
            i++;
        }

    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public AppInfoVo getItem(int position) {

        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.app_grid_item, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mAppColor.setImageResource((getItem(position).mAppPanelId[position % mPageSize]));
        holder.mAppIcon.setImageDrawable((getItem(position).mAppIcon));
        holder.mAppName.setText(getItem(position).mAppName);

        return convertView;

    }

    class ViewHolder {

        ImageView mAppIcon;
        ImageView mAppColor;
        TextView mAppName;

        public ViewHolder(View v) {

            mAppIcon = (ImageView) v.findViewById(R.id.app_icon);
            mAppName = (TextView) v.findViewById(R.id.app_title);
            mAppColor = (ImageView) v.findViewById(R.id.app_color);

        }
    }

}
