package com.example.mylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PackageManager mPackageManager;
    private List<ResolveInfo> mActivities;
    private ListView mAppList;
    private TextView mEmptyText;
    private Drawable mDefaultAppIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPackageManager = getPackageManager();
        mAppList = findViewById(R.id.app_list);
        mEmptyText = findViewById(R.id.empty_text);
        mDefaultAppIcon = ContextCompat.getDrawable(this, R.drawable.default_app_icon);

        loadApps();
        setupListView();
    }

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mActivities = mPackageManager.queryIntentActivities(mainIntent, 0);
        Collections.sort(mActivities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                return o1.loadLabel(mPackageManager).toString().compareToIgnoreCase(o2.loadLabel(mPackageManager).toString());
            }
        });
    }

    private void setupListView() {
        ArrayList<AppInfo> appInfos = new ArrayList<>();
        for (ResolveInfo activity : mActivities) {
            appInfos.add(new AppInfo(activity.loadLabel(mPackageManager).toString(), activity.activityInfo.packageName, activity.loadIcon(mPackageManager)));
        }
        if (appInfos.isEmpty()) {
            mEmptyText.setVisibility(View.VISIBLE);
            mAppList.setVisibility(View.GONE);
        } else {
            mEmptyText.setVisibility(View.GONE);
            mAppList.setVisibility(View.VISIBLE);
            AppListAdapter adapter = new AppListAdapter(this, appInfos, mDefaultAppIcon);
            mAppList.setAdapter(adapter);
            mAppList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent launchIntent = mPackageManager.getLaunchIntentForPackage(appInfos.get(position).packageName);
                    startActivity(launchIntent);
                }
            });
        }
    }
}
