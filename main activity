import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mAppList;
    private PackageManager mPackageManager;
    private List<ResolveInfo> mActivities;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppList = new ListView(this);
        mPackageManager = getPackageManager();

        loadApps();
        setupListView();

        setContentView(mAppList);
    }

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mActivities = mPackageManager.queryIntentActivities(mainIntent, 0);
        Collections.sort(mActivities, new ResolveInfo.DisplayNameComparator(mPackageManager));
    }

    private void setupListView() {
        ArrayList<String> appNames = new ArrayList<>();
        for (ResolveInfo activity : mActivities) {
            appNames.add(activity.loadLabel(mPackageManager).toString());
        }

        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, appNames);
        mAppList.setAdapter(mAdapter);

        mAppList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo activity = mActivities.get(position);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName(activity.activityInfo.applicationInfo.packageName,
                        activity.activityInfo.name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
