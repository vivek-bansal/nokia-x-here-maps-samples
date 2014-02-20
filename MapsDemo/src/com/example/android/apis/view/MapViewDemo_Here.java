/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.example.android.apis.view;

import android.os.Bundle;

import com.here.android.common.GeoPosition;
import com.here.android.common.LocationMethod;
import com.here.android.common.LocationStatus;
import com.here.android.common.PositionListener;
import com.here.android.mapping.InitError;
import com.here.android.mapping.Map;
import com.here.android.mapping.MapActivity;
import com.here.android.mapping.MapAnimation;
import com.here.android.mapping.MapFactory;
import com.here.android.mapping.MapView;

/**
 * Demonstrates a MapView created programatically containing the
 * user's position indicator
 */
public class MapViewDemo_Here extends MapActivity {

    private MapView mMapView;
    private Map mMap;

    private PositionListener mPositionListener = new PositionListener() {
    	
    	public void onPositionUpdated(LocationMethod method, GeoPosition position) {
    		mMap.setCenter(position.getCoordinate(),MapAnimation.BOW);
    	}
    	
    	public void onPositionFixChanged(LocationMethod method, LocationStatus status) {
    	}
    };
 
    
    @Override
    protected void onInitialised(InitError error) {
    	
    	if (error == InitError.NONE) {
            mMap = MapFactory.createMap();
            mMapView.setMap(mMap);
            
            MapFactory.getPositioningManager().addPositionListener(mPositionListener);
        	MapFactory.getPositioningManager().start(LocationMethod.GPS_NETWORK);
            
            mMap.getPositionIndicator().setVisible(true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = new MapView(this); 
        setContentView(mMapView);   
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMapView.onResume();
        
        if (MapFactory.getMapEngine() != null) {
        	MapFactory.getPositioningManager().addPositionListener(mPositionListener);
        	MapFactory.getPositioningManager().start(LocationMethod.GPS_NETWORK);
        }
    }
    
    @Override
    protected void onPause() {
    	super.onPause();

    	mMapView.onPause();
    	
    	if (MapFactory.getMapEngine() != null) {
    		MapFactory.getPositioningManager().stop();
    		MapFactory.getPositioningManager().removePositionListener(mPositionListener);
    	}
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
        mMap = null;
    }
    
}
