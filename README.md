# author_idas
A clean and streamlined experience for a more organized home screen and an Android launcher for my tired old hands. A simple and efficient way to organize and access apps on an Android device. With a clean and minimalistic design, it is easy to view and launch apps quickly. The app list is organized alphabetically and is easily scrollable for quick access.

## Here's how it works
The app list in this launcher app has been designed to replace the main screen of the Android device. This means that when the launcher app is set as the default launcher, the user will be presented with a scrollable list of all installed apps as the first screen they see when they unlock their device, instead of the usual home screen with widgets, shortcuts, and app icons.

The app list is presented in a clean and organized way, allowing users to easily find the app they're looking for by scrolling through the list or by typing the name of the app in the search bar. This design is meant to create a minimalist and distraction-free interface, which can help users focus on the apps they need to use, without being distracted by notifications, widgets, or other elements that are typically present on the home screen.

The app consists of a single activity, MainActivity, which extends the AppCompatActivity class.
```
public class MainActivity extends AppCompatActivity {
    ...
}
```
When the activity is created, the onCreate() method is called. This method sets the content view to the activity_main layout file, which contains a ListView element to display the list of apps.
```@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ...
}
```
The loadApps() method is called to load the list of apps from the device. This method creates an Intent with the ACTION_MAIN action and CATEGORY_LAUNCHER category, and queries the PackageManager for all activities that can handle the intent. The ResolveInfo objects returned by the query are stored in the mActivities list and sorted alphabetically by name.
```private void loadApps() {
    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    mActivities = mPackageManager.queryIntentActivities(mainIntent, 0);
    Collections.sort(mActivities, new ResolveInfo.DisplayNameComparator(mPackageManager));
}
```
The setupListView() method is called to set up the ListView to display the list of apps. This method creates an ArrayAdapter with the app names and sets it as the adapter for the ListView. When the user clicks on an app in the list, the onItemClick() method is called, which launches the selected app. Here's an example:
```private void setupListView() {
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

```
The app uses the ```Android PackageManager``` and ```ListView``` classes to display a sorted list of installed apps and launch them when selected.
## This app requires
* Minimum SDK version of 15 (Android 4.0.3, Ice Cream Sandwich) to run.
* It specifies a target SDK version of 31 (Android 12) in the app's manifest file, indicating that it has been tested and optimized for that version of Android. 

However, the app should still be compatible with devices running earlier versions of Android, down to the minimum SDK version.

## Android Permissions
* android.permission.READ_EXTERNAL_STORAGE


