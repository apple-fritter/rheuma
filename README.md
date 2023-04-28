# rheuma launcher
rheuma is a simple launcher app for Android that displays a vertically scrollable list of installed apps. It allows users to quickly launch their favorite apps without the clutter of a full home screen.

## incomplete project
This project is far from completion, and I hope to have it done by summer's time. If you would like to assist, please do! Right now, it is of a lower priority, so no promises!

## Features
```
Simple and fast launcher for Android devices
Displays a vertically scrollable list of installed apps
Uses the system font and theme
No search bar or app drawer for a streamlined experience
Dark mode by default
```
## How it works

The `MainActivity` is the entry point for the app and sets up the views and variables for the app list. The `PackageManager` is used to query the device for all activities that have a category of `Intent.CATEGORY_LAUNCHER`. These are the apps that appear in the app list. The app list is sorted alphabetically using a custom Comparator. Each app's label, package name, and icon are extracted and stored in an `AppInfo` object. The `AppListAdapter` is a custom adapter that extends `ArrayAdapter` and is used to populate the `ListView` with the `AppInfo` objects.

When an app in the list is clicked, the package name is used to retrieve the app's launch intent from the `PackageManager` and the app is launched with `startActivity()`. The launcher supports dark mode by setting the `android:forceDarkAllowed` attribute to true in the manifest file.

The UI of the launcher is intentionally minimalistic and doesn't use any custom styling or theming. The list of apps is displayed in a simple vertical scrollable `ListView` with no dividers or search bar. The app icons are the only visual element that differentiate the apps from one another.

## Permissions
This launcher does not require any special permissions. It only uses the standard permissions that are automatically granted to all Android applications, such as access to the internet and access to the device's storage.

## Installation
* Clone or download the repository
* Open the project in Android Studio
* Build and run the app on an emulator or physical device

## Usage
* Launch the app to display a list of installed apps
* Scroll up or down to browse the list
* Tap an app icon to launch the app

## [Disclaimer](DISCLAIMER)
**This software is provided "as is" and without warranty of any kind**, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.

**The authors do not endorse or support any harmful or malicious activities** that may be carried out with the software. It is the user's responsibility to ensure that their use of the software complies with all applicable laws and regulations.

## License

These files released under the [MIT License](LICENSE).
