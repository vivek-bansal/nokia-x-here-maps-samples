/**
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.example.android.apis;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsDemo extends ListActivity {
	
	/**
	 * Determines if the G* Android Maps v1 platform is supported
	 * on this device.
	 */
	private boolean isV1MapsAvailable() {
		boolean available = false ;
		try {
		     Class.forName("com.google.android.maps.MapView");
		     available = true ;
		} catch (ClassNotFoundException e) {
			
		}  
		return available;
	}
	
	/**
	 * Determines if the G* Android Maps v2 platform is supported
	 * on this device.
	 */
	private boolean isV2MapsAvailable() {
		int r = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		return r == ConnectionResult.SUCCESS;
	}
	
	/**
	 * Determines if the Here platform is supported
	 * on this device.
	 */
	public static boolean isHereMapsAvailable() {
		boolean available = false;
		try {
			Class.forName("com.here.android.mapping.Map");
			available = true;
		} catch (ClassNotFoundException e) {
			
		}
		return available;
	}
	
	protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        int len = list.size();
 
        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);

            CharSequence labelSeq = info.loadLabel(pm);

            if ("com.example.android.maps.apis".equals(info.activityInfo.applicationInfo.packageName)) {
            	
            	/**
            	 * Only include activities that are compatible with the supported
            	 * maps API.
            	 */
            	boolean addItem = false;
            	
            	if (isV1MapsAvailable() && info.activityInfo.name.endsWith("v1")) {
            		addItem = true;
            	}
            	
            	if (isV2MapsAvailable() && info.activityInfo.name.endsWith("v2")) {
            		addItem = true;	
            	}
            	
            	if (isHereMapsAvailable() && info.activityInfo.name.endsWith("Here")) {
            		addItem = true;
            	}
            		
            	if (addItem) {
	                addItem(myData, labelSeq.toString(), activityIntent(
	                        info.activityInfo.applicationInfo.packageName,
	                        info.activityInfo.name));
            	}
            }
        }

        Collections.sort(myData, sDisplayNameComparator);
        
        return myData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }


    private final static Comparator<Map<String, Object>> sDisplayNameComparator = 
    			new Comparator<Map<String, Object>>() {
    	
        private final Collator collator = Collator.getInstance();

        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            return collator.compare(map1.get("title"), map2.get("title"));
        }
    };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }
    
    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
