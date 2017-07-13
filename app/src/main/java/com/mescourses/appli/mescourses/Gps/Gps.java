package com.mescourses.appli.mescourses.Gps;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by student on 13-07-17.
 */

public class Gps {
    private static final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context ;
    Gps(Context context)
    {
        this.context = context ;
    }
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission((Activity) context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }




}
