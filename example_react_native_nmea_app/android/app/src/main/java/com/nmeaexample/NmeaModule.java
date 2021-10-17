package com.nmeaexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class NmeaModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private final LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public NmeaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.locationManager = (LocationManager) reactContext.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    }

    @NonNull
    @Override
    public String getName() {
        return "Nmea";
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    @ReactMethod
    public void start() {
        LocationListener locationListener = new NmeaLocationListener();
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.1f, locationListener);
        this.locationManager.addNmeaListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @ReactMethod
    public void stop() {
        this.locationManager.removeNmeaListener(listener);
    }

    private final OnNmeaMessageListener listener = new OnNmeaMessageListener(){

        @Override
        public void onNmeaMessage(String message, long timestamp) {
            sendEvent(reactContext, "onNmeaReceive", message);

        };
    };

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable String params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
