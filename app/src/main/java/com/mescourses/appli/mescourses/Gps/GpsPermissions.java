package com.mescourses.appli.mescourses.Gps;

/**
 * Created by student on 14-07-17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class GpsPermissions {
    private static final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context ;
    GpsPermissions(Context context)
    {
        this.context = context ;
    }
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission((Activity) context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

}
