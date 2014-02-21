/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.nokia.example.mapsv2oneapk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private static final String HERE_LIBRARY = "com.here.android";
    /**
     * String array of meta-data keys in AndroidManifest.xml we validate on launch. These keys need to be defined for the application to actually work.
     */
    private static final String[] REQUIRED_KEYS_ARRAY = {"com.here.android.maps.appid", "com.here.android.maps.apptoken", "com.google.android.maps.v2.API_KEY"};
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (validateMetaData()) {
            Fragment fragment;
            if (hasHere()) {
                fragment = new MapsFragmentHere();
            } else {
                fragment = new MapsFragmentGoogle();
            }
            getSupportFragmentManager().beginTransaction().add(R.id.map, fragment).commit();
        } else {
            Toast.makeText(this, R.string.missing_metadata, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

    }

    /**
     * Validate that AndroidManifest.xml defines keys this application requires
     *
     * @return
     */
    private boolean validateMetaData() {
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            for (String key : REQUIRED_KEYS_ARRAY) {
                if (TextUtils.isEmpty(bundle.getString(key))) {
                    Log.d(TAG, "missing meta-data from AndroidManifest.xml. Undefined key " + key);
                    return false;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return true;
    }

    /**
     * Checks if HERE library (com.here.android) is available on the device
     *
     * @return true if HERE library is found
     */
    private boolean hasHere() {
        String[] systemSharedLibraryNames = getPackageManager().getSystemSharedLibraryNames();
        for (String library : systemSharedLibraryNames) {
            if (HERE_LIBRARY.equals(library))
                return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
