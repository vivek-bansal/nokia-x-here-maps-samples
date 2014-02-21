/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.mapstutorial.basicmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.here.android.mapping.*;
import com.mapstutorial.basicmap.R;

public class BasicMapActivity extends Activity {

    // map embedded in the map fragment
    private Map map = null;

    // map fragment embedded in this activity
    private MapFragment mapFragment = null;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
