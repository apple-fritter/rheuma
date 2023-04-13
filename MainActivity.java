public class MainActivity extends AppCompatActivity {

    private PackageManager mPackageManager;
    private ListView mAppList;
    private List<ResolveInfo> mActivities;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPackageManager = getPackageManager();
        mAppList = findViewById(R.id.app_list);
        loadApps();
        setupListView();
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
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appNames);
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
