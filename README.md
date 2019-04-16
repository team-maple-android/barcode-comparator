# AVN Barcode Comparator
Compare barcode inputs using external USB Barcode Scanner.

## Getting Started

These instructions will get you a copy of the project from a source repository and run on your physical device or virtual device using your local machine. See [Functionality](#functionality) section for a detailed information on how the application runs from the start.

### Prerequisites

What are things you need to install for the application:

* [Android Studio](https://developer.android.com/studio)
* [Git](https://git-scm.com/downloads)
* [Subversion](https://subversion.apache.org/download.cgi?update=201708081800)

### Check out project

#### Using Git
* Android Studio
    1. Open your Android Studio.
        * If you have no recent project opened in Android Studio, a *Welcome to Android Studio* window will appear. Then click *Check out project from Version Control* and select *Git*.
        * Otherwise, Android studio will load your recent project. From the menu above, select *File* > *New* > *Project from Version Control* > *Git*.
    2. Paste this to the *URL* field
        ```
        https://github.com/team-maple-android/barcode-comparator.git
        ```        
    3. Click the **Clone** button.


* Terminal *(Command Prompt)*
    1. Open terminal then __cd__ to your *Android Studio Projects* directory. For instance,
        
        ```
        cd ~/AndroidStudioProjects
        ```

    2. Check out a working copy from a repository.
        ```
        git clone https://github.com/team-maple-android/barcode-comparator.git maple-barcode-comparator
        ```
        After check out,
        ```
        cd maple-barcode-comparator
        ```
    3. Open your project in Android Studio.

#### Using SVN
Check out project using Subversion.
* Android Studio
    1. Open your Android Studio. From the menu above, select *File* > *New* > *Project from Version Control* > *Subversion*.
    2. In *Check out from Subversion* window, click <code>+</code> sign to add repository location. Then enter this *Repository URL* and click *OK*.
        ```
        svn://172.18.254.66/QADOCSL/trunk
        ```
    3. Select the <code>svn://172.18.254.66/QADOCSL/trunk</code> folder and click *Checkout*.
    4. From the *Destination Directory* window. Select *AndroidStudioProjects* directory and click *OK*.
    5. From the *SVN Checkout Options* window. Select your *desired destination* and click *OK*.
    6. From the *Subversion Working Copy Format* window. Select the default format and click *OK*.    

## Testing
Setup the development target.
* [Physical Device](https://developer.android.com/studio/run/device)
    You can use your mobile device to run the application.
* [Virtual Device (Emulator)](https://developer.android.com/studio/run/emulator)


## Run the app
1. In your Android Studio, select *Run* > *Run 'app'*.
2. Select Deployment Target from availabe **Physical Connected Devices** and **Virtual Devices**.
3. Click **'OK'**.

## Functionality
When the application launches, it will look for the <code>AndroidManifest.xml</code> file.
The application has two activities, **SplashActivity** and **MainActivity**. It will first look up the *SplashActivity* because of the <code>intent-filter</code> 
``` java
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```
In <code>SplashActivity.java</code>'s *onCreate* method is to start another activity <code>MainActivity.java</code> then closes thereafter by calling <code>finish()</code>.

**MainActivity.java**
Open the <code>MainActivity.java</code> file and look for each methods:
* **onCreate()**
    This method will set the corresponding layout and initialize elements from the view and define listeners for <code>master input</code>, <code>slave input</code>, and <code>clear button</code>.
* **compareBarcodes()**
    This method will be called when the <code>slave input</code> receives an <code>Enter</code> key. From the name itself implies comparing <code>master</code> to <code>slave</code> barcode inputs, opens a *Dialog* and plays a *beep sound* depending on the condition.
* **showOkDialog()**
    This method will build a dialog from a layout view defined in the resources <code>R.layout.ok_custom_dialog</code> and sets a timer to close itself after <code>300 milliseconds</code>, and plays a *successful beep* indicating an equal <code>master</code> and <code>slave</code> comparison.
* **timerDelayRemoveDialog()**
    This method will be called after <code>okDialog.show()</code>, closes the dialog and resetting the latest comparison.
* **showNgDialog()**
    This method will build a dialog from a layout view defined in the resources <code>R.layout.ng_custom_dialog</code> and plays a repetitive *error beep* indicating a mismatched <code>master</code> and <code>slave</code> comparison. By clicking the *OK* button, it will stop the repititive *error beep*, closes the dialog, and resets the latest comparison
* **reset()**
    This method will reset data from the <code>master</code> and <code>slave</code> inputs, and adds focus to <code>master</code> input.

## Built With

* [Android (Java)](https://developer.android.com/)

## Contributors
* [Jason Daro](mailto:j.daro@maple.muramoto.com)
* [Giezel Cabal](mailto:g.cabal@maple.muramoto.com)

## Authors
* [Jason Daro](mailto:j.daro@maple.muramoto.com)
