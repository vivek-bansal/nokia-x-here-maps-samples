/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.example.android.apis.view;

import android.os.Bundle;

import com.example.android.maps.apis.R;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

/**
 * Demonstrates a MapView created programatically containing the
 * user's position indicator
 */
public class MapViewDemo_v1 extends MapActivity {

    private MapView mMapView;
    private MyLocationOverlay mMyLocationOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = new MapView(this, getString(R.string.maps_api_key));
        setContentView(mMapView);

        mMyLocationOverlay = new MyLocationOverlay(this, mMapView);
        mMyLocationOverlay.runOnFirstFix(new Runnable() { 
        	public void run() {
        		mMapView.getController().animateTo(mMyLocationOverlay.getMyLocation());
        	}
        });
        mMapView.getOverlays().add(mMyLocationOverlay);
        mMapView.getController().setZoom(18);
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyLocationOverlay.enableMyLocation();
    }

    @Override
    protected void onStop() {
        mMyLocationOverlay.disableMyLocation();
        super.onStop();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
