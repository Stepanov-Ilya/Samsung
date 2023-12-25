package com.example.weather24;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;


import javax.net.ssl.HttpsURLConnection;

public class Geocoder extends Service {
    Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String response = (String) msg.obj;

                Intent intent = new Intent("Geocoder");
                intent.putExtra("GeoInfo", response);

                sendBroadcast(intent);
            }
        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String query = intent.getStringExtra("QUERY");


        try {
            Thread geoThread = new Thread(new HttpsRequest(handler,
                    "https://geocode.search.hereapi.com/v1/geocode?",
                    "apiKey=APIKEY",
                    "&q=" + query));
            geoThread.start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
