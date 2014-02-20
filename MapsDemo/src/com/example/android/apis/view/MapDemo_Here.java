/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.example.android.apis.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.android.maps.apis.R;
import com.here.android.mapping.FragmentInitListener;
import com.here.android.mapping.InitError;
import com.here.android.mapping.MapFragment;
//import com.here.android.mapping.Map;

/**
 * Demonstrates a map defined in XML
 */
public class MapDemo_Here extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_here);
        
        final MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapfragment);
		// initialize the Map Fragment to create a map and
		// attached to the fragment
		mapFragment.init(new FragmentInitListener() {
			@Override
			public void onFragmentInitializationCompleted(InitError error) {
				if (error == InitError.NONE) {
					//  Map map = (Map) mapFragment.getMap();
				}
			}
		});
    }

}
