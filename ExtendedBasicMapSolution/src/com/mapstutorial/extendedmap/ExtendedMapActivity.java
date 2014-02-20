/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.mapstutorial.extendedmap;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.here.android.mapping.FragmentInitListener;
import com.here.android.mapping.InitError;
import com.here.android.mapping.Map;
import com.here.android.mapping.MapAnimation;
import com.here.android.mapping.MapFactory;
import com.here.android.mapping.MapFragment;

public class ExtendedMapActivity extends Activity {

    // map for this activity's map fragment
    private Map map = null;
    // map fragment of this activity
    private MapFragment mapFragment = null;
    // Initial map scheme, initialized in onCreate() and accessed in goHome()
    private static String initial_scheme = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search for the map fragment to finish setup by calling init().
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new FragmentInitListener() {
            @Override
            public void onFragmentInitializationCompleted(InitError error) {
                if (error == InitError.NONE) {
                    onMapFragmentInitializationCompleted();
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment.");
                }
            }
        });
    }

    private void onMapFragmentInitializationCompleted() {
        // retrieve a reference of the map from the map fragment
        map = mapFragment.getMap();
        // Set the map center coordinate to the Vancouver region (no animation)
        map.setCenter(MapFactory.createGeoCoordinate(49.196261, -123.004773, 0.0),
                MapAnimation.NONE);
        // Set the map zoom level to the average between min and max (no
        // animation)
        map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    // Functionality for taps of the "Go Home" button
    public void goHome(View view) {
        if (map != null) {
            // Change map view to "home" coordinate and zoom level, plus
            // eliminate any rotation or tilt
            map.setCenter(MapFactory.createGeoCoordinate(49.196261, -123.004773, 0.0),
                    MapAnimation.NONE);
            map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
            map.setOrientation(0);
            map.setTilt(0);

            // Reset the map scheme to the initial scheme and display a message
            // to users
            map.setMapScheme(initial_scheme);
        }
    }

    // Functionality for taps of the "Change Map Scheme" button
    public void changeScheme(View view) {
        if (map != null) {
            // Local variable representing the current map scheme
            String current = map.getMapScheme();
            if (initial_scheme == null || initial_scheme.isEmpty()) {
                // Initialize the value for initial_scheme with the original map scheme on display
                initial_scheme = current;
            }

            // Local array containing string values of available map schemes
            List<String> schemes = map.getMapSchemes();
            // Local variable representing the number of available map schemes
            int total = map.getMapSchemes().size();

            // If the current scheme is the last element in the array, reset to
            // the scheme at array index 0
            if (schemes.get(total - 1).equals(current))
                map.setMapScheme(schemes.get(0));
            else {
                // If the current scheme is any other element, set to the next
                // scheme in the array
                for (int count = 0; count < total - 1; count++) {
                    if (schemes.get(count).equals(current))
                        map.setMapScheme(schemes.get(count + 1));
                }
            }
        }
    }
}
