HERE Maps API sample: SimpleRoutingSolution
===========================================

This Nokia sample application uses the HERE library to show various map schemes
supported by HERE. The app will show a map around Vancouver region in Canada
and display a route between two preselected locations in the area.


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
6. Tap "Get Directions" and a route will be displayed.


Implementation
--------------------------------------------------------------------------------

The `RoutingActivity` in package `com.mapstutorial.simplerouting` is the main
class of the application. The most important methods in the class are:

* `getDirections()`: Selects the routing mode and retrieves the routing information
* `routeManagerEventListener()`: Creates the route object and places it on the map

For more information, see the HERE Maps API developer documentation.


Known issues
--------------------------------------------------------------------------------

None.


License
--------------------------------------------------------------------------------

See the separate license file provided with this project.
