/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.nokia.example.mapsv1oneapk;

import android.os.Bundle;
import com.nokia.android.maps.MapActivity;

/**
 *
 */
public class MapViewActivityHere extends MapActivity {

    /**
     * Resource ID to Google Maps version of layout
     */
    public static final int ACTIVITY_LAYOUT = R.layout.activity_mapview_here;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ACTIVITY_LAYOUT);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
