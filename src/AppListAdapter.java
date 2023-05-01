package com.applefritter.rheuma;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<AppInfo> mAppInfos;
    private Drawable mDefaultAppIcon;

    public AppListAdapter(Context context, ArrayList<AppInfo> appInfos, Drawable defaultAppIcon) {
        mContext = context;
        mAppInfos = appInfos;
        mDefaultAppIcon = defaultAppIcon;
    }

    @Override
    public int getCount() {
        return mAppInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_app, parent, false);
        }

        AppInfo appInfo = mAppInfos.get(position);

        TextView appNameTextView = convertView.findViewById(R.id.app_name_text_view);
        ImageView appIconImageView = convertView.findViewById(R.id.app_icon_image_view);

        appNameTextView.setText(appInfo.getName());
        appIconImageView.setImageDrawable(appInfo.getIcon() != null ? appInfo.getIcon() : mDefaultAppIcon);

        return convertView;
    }

}
