package com.example.javanmeaexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class NmeaModule {


    private final LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public NmeaModule() {

        this.locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    public void start() {
        LocationListener locationListener = new NmeaLocationListener();
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.1f, locationListener);
        this.locationManager.addNmeaListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void stop() {
        this.locationManager.removeNmeaListener(listener);
    }

    private final OnNmeaMessageListener listener = new OnNmeaMessageListener(){

        @Override
        public void onNmeaMessage(String message, long timestamp) {
            System.out.println(message);

        };
    };
}
