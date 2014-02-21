/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.example.android.apis.view;

import com.example.android.maps.apis.R;
import com.google.android.maps.MapActivity;

import android.os.Bundle;

/**
 * Demonstrates a map defined in XML
 */
public class MapDemo_v1 extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_v1);
    }

    @Override
    protected boolean isRouteDisplayed() { 
    	return false; 
    }
}
