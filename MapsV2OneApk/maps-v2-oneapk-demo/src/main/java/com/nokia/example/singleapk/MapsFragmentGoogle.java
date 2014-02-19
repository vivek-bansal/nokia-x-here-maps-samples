package com.nokia.example.singleapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * TODO: Oneliner about class
 * <p/>
 * TODO: Description of the class
 * {@author} riku salkia
 * {@version} 1.0.0
 * {@since} 17.2.2014
 */
public class MapsFragmentGoogle extends Fragment {

    public static final int FRAGMENT_LAYOUT = R.layout.fragment_map_google;
    public static final int FRAGMENT_ID = R.id.fragment_map_google;

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
    }}
