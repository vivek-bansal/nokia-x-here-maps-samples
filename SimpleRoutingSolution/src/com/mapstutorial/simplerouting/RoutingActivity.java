/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.mapstutorial.simplerouting;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import java.util.EnumSet;
import java.util.List;

import android.view.View;
import android.widget.TextView;
import com.here.android.common.*;
import com.here.android.routing.*;
import com.here.android.mapping.*;

public class RoutingActivity extends Activity {

    // map embedded in the map fragment
    private Map map = null;

    // map fragment embedded in this activity
    private MapFragment mapFragment = null;

    // TextView for displaying the current map scheme
    private TextView textViewResult = null;

    // MapRoute for this activity
    private static MapRoute mapRoute = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search for the map fragment to finish setup by calling init().
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new FragmentInitListener() {
            @Override
            public void onFragmentInitializationCompleted(InitError error) {
                if (error == InitError.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    // Set the map center coordinate to the Vancouver region (no animation)
                    map.setCenter(MapFactory.createGeoCoordinate(49.196261, -123.004773, 0.0), MapAnimation.NONE);
                    // Set the map zoom level to the average between min and max (no animation)
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });

        textViewResult = (TextView) findViewById(R.id.title);
        textViewResult.setText(R.string.textview_routecoordinates_2waypoints);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    // Functionality for taps of the "Get Directions" button
    public void getDirections(View view) {
        // 1. clear previous results
        textViewResult.setText("");
        if (map != null && mapRoute != null) {
            map.removeMapObject(mapRoute);
            mapRoute = null;
        }

        // 2. Initialize RouteManager
        RouteManager routeManager = MapFactory.getRouteManager();

        // 3. Select routing options via RoutingMode
        RoutingMode routingMode = MapFactory.createRoutingMode();
        routingMode.setRoutingOptions(EnumSet.noneOf(RoutingOptions.class));
        routingMode.setTransportMode(TransportMode.CAR);
        routingMode.setRoutingType(RoutingType.FASTEST);

        // 4. Select Waypoints for your routes via RouteManagerEventListener
        WaypointParameterList waypointParameterList = MapFactory.createWaypointParameterList();
        // START: Nokia, Burnaby
        waypointParameterList.addCoordinate(MapFactory.createGeoCoordinate(49.1966286, -123.0053635));
        // END: Airport, YVR
        waypointParameterList.addCoordinate(MapFactory.createGeoCoordinate(49.1947289, -123.1762924));

        // 5. Retrieve Routing information via Route
        routeManager.calculateRouteAsync(waypointParameterList, routingMode, routeManagerEventListener);
    };

    private RouteManagerEventListener routeManagerEventListener = new RouteManagerEventListener()
    {
        public void onCalculateRouteFinished(RouterError errorCode, List<RoutingResult> result) {

            if (errorCode == RouterError.NONE && result.get(0).getRoute() != null) {
                // create a map route object and place it on the map
                mapRoute = MapFactory.createMapRoute(result.get(0).getRoute());
                map.addMapObject(mapRoute);

                // Get the bounding box containing the route and zoom in (no animation)
                GeoBoundingBox gbb = result.get(0).getRoute().getBoundingBox();
                map.zoomTo(gbb, MapAnimation.NONE, Map.MOVE_PRESERVE_ORIENTATION);

                textViewResult.setText(String.format("Route calculated with %d maneuvers.", result.get(0).getRoute().getManeuvers().size()));
            } else {
                textViewResult.setText(String.format("Route calculation failed: %s", errorCode.toString()));
            }
        }

        public void onProgress(int percentage) {
            textViewResult.setText(String.format("... %d percent done ...", percentage));
        }
    };
}
