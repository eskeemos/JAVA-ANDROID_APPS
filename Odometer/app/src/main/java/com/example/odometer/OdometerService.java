package com.example.odometer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class OdometerService extends Service {

    private static Location lastLocation = null;
    private static double distance;
    private final IBinder binder = new OdometerBinder();

    public OdometerService() {
    }

    public  class OdometerBinder extends Binder {
        OdometerService getOdometer() { return  OdometerService.this;}
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    @Override
    public void onCreate() {
        LocationListener locationL = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distance += (lastLocation.distanceTo(location));
                lastLocation = location;
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationL);
    }

    public double getDistance() {
        return distance;
    }
}