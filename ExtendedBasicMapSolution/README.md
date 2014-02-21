HERE API sample: ExtendedBasicMapSolution
=========================================

This Nokia sample application uses the HERE library to show various map schemes
supported by HERE API. The app will display a map around Vancouver region in
Canada and a button to change schemes.


Instructions
--------------------------------------------------------------------------------

1. Import the project to your Eclipse workspace by clicking File > Import and
   then selecting "Existing Android Code into Workspace".
2. Right-click on the sample project, open the Properties dialog, and select the
   "Android" option from the left column.
3. Add HERE library to build path: 
    * Open Properties dialog, navigate to Java Build Path -> Libraries and click
      Add External JARs... button.
    * Navigate to
      `<Android SDK installation path>/extras/nokia/nokia_x_services/libs/here`,
      select `com.here.android.sdk.jar` and click Open.
    * Make sure that the library is not exported to JAR, i.e. the check box is
      not checked in Order and Export tab.
    * Click on OK button to close the Properties dialog.
4. Open `AndroidManifest.xml` and add the HERE Maps app ID and the app code
   within the `application` tags.
    * For instructions on obtaining these credentials, please check the HERE
      Maps API documentation.
5. Launch the sample application in an environment that satisfies the following
   requirements:
    * The environment target is Nokia X services.
    * If the app is run in an emulator, the emulator must have an SD card
      defined. This is required for maps data storage.
    * The environment must support Open GL ES 2.0. (With an emulator, ensure
      that the "Use Host GPU" check box in the Android virtual device manager is
      checked.)


Implementation
--------------------------------------------------------------------------------

The `ExtendedMapActivity` class in package `com.mapstutorial.extendedmap` is the
main UI class of the application. The most important methods in the class are:

* `goHome()`: Resets the map view to "home" coordinate and zoom level, plus
  eliminates any rotation or tilt.
* `changeScheme()`: Changes various schemes supported by HERE.

For more information, see the HERE Maps API developer documentation.


Known issues
--------------------------------------------------------------------------------

None.


License
--------------------------------------------------------------------------------

See the separate license file provided with this project.
