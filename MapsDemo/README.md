HERE Maps API sample: MapsDemo
==============================

This Nokia sample illustrates how to implement Google Maps v1, v2, and HERE Maps
offering in a single APK, and how to select the maps implementation to be used
run-time. This sample is especially useful should you already have an
application using either Google Maps v1 or v2 in terms of demonstrating how to
add HERE Maps alongside your original implementation.


Instructions
--------------------------------------------------------------------------------

1. Import the project to your Eclipse workspace by clicking File > Import and
   then selecting "Existing Android Code into Workspace".
2. In the same manner, import the Google Play Services reference library into
   Eclipse. (See the Android Developers website for more details of the Google
   Play Services library project.)
3. Right-click on the sample project, open the Properties dialog and select the
   "Android" option from the left column. Ensure that the Google Play Services
   library is added to the project as a Project Library.
4. From the same Properties dialog, ensure that the Project Build Target is se
   to "Google APIs".
5. Next, select the "Java Build Path" option from the left column. Select the
   "Libraries" tab and use the "Add External JARs" button to add
   `com.here.android.sdk.jar` into the project. The JAR file is located at
   `<your SDK directory>/sdk/extras/nokia/nokia_x_services/libs/here`.
6. Click on the "Order and Export" tab and make sure that
   `com.here.android.sdk.jar` is deselected. Click OK to close the Properties
   dialog.
7. Open `AndroidManifest.xml` and add the following within the `application`
   tags and outside the `activity` tag:
   
        <meta-data
            android:name="com.google.android.gms.version" 
            android:value="@integer/google_play_services_version" />
        <meta-data
            android:name="com.here.android.maps.appid" 
            android:value="(Your ID)" />
        <meta-data
            android:name="com.here.android.maps.apptoken" 
            android:value="(Your token)"/>

7. To test the Google Maps run the app in an Android device or emulator with
   Google API level 19. When run, you can choose the Google Maps version to
   test. Please note that the requirements include having a valid Google Maps v2
   API key.
8. To test HERE Maps on Nokia X, use either a Nokia X device or Nokia X
   emulator. You need to obtain the HERE Maps ID and token to successfully run
   the application. See HERE Maps API documentation for further instructions.


Implementation
--------------------------------------------------------------------------------

Descriptions of the classes:

* Package `com.example.android.apis`:
    * `MapsDemo`: Checks the availability of Google Maps v1, v2, and HERE Maps
       and displays the result in a list view
* Package `com.example.android.apis.view`:
    * `MapDemo_HERE`: Creates the map fragment for HERE Maps
    * `MapDemo_v1`: Creates the view for Google Maps v1
    * `MapDemo_v2`: Creates the view for Google Maps v2
    * `MapViewDemo_HERE`: Creates the map component for HERE Maps
    * `MapViewDemo_v1`: Creates the map component for Google Maps v1
    * `MapViewDemo_v2`: Creates the map component for Google Maps v2
    
For more information, see the HERE Maps API developer documentation.


Known issues
--------------------------------------------------------------------------------

The Google Maps v1 specific code will not work with Google API level 17.


License
--------------------------------------------------------------------------------

See the separate license file provided with this project.
