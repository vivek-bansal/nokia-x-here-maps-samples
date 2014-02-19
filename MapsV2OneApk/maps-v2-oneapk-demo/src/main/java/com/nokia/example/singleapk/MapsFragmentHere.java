/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 *  See the license text file delivered with this project for more information.
 */

package com.nokia.example.singleapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nokia.android.gms.maps.GoogleMap;
import com.nokia.android.gms.maps.SupportMapFragment;
import com.nokia.android.gms.maps.model.LatLng;
import com.nokia.android.gms.maps.model.MarkerOptions;

/**
 * TODO: Oneliner about class
 * <p/>
 * TODO: Description of the class
 * {@author} riku salkia
 * {@version} 1.0.0
 * {@since} 17.2.2014
 */
public class MapsFragmentHere extends Fragment {

    public static final int FRAGMENT_LAYOUT = R.layout.fragment_map_here;
    public static final int FRAGMENT_ID = R.id.fragment_map_here;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(FRAGMENT_LAYOUT, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(FRAGMENT_ID);
        GoogleMap map = mapFragment.getMap();
        MarkerOptions marker = new MarkerOptions().position(new LatLng(0, 0)).title("Marker");
        map.addMarker(marker);
    }
}
