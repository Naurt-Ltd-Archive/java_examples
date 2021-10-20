package com.example.javanmeaexample;

import android.location.GpsStatus.NmeaListener;


import javax.annotation.Nullable;

public class NmeaMessageListener implements NmeaListener {


    public NmeaMessageListener() {
    }

    public void onNmeaReceived(long timestamp, String message) {
        WritableMap params = Arguments.createMap();
        params.putString("message", message);
        params.putDouble("timestamp", timestamp);

    }

}