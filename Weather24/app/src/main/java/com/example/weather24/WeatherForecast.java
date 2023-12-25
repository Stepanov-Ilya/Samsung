package com.example.weather24;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.MalformedURLException;

public class WeatherForecast extends Service {

    Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String response = (String) msg.obj;
                Intent intent = new Intent("WeatherForecast");
                intent.putExtra("Forecast", response);

                sendBroadcast(intent);
            }
        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        double lat = intent.getDoubleExtra("LAT", 0);
        double lng = intent.getDoubleExtra("LNG", 0);


        try {
            Thread meteoThread = new Thread(new HttpsRequest(handler,
                    "https://api.open-meteo.com/v1/forecast?",
                    "",
                    "&latitude=" + lat + "&longitude=" + lng +
                            "&daily=temperature_2m_max,temperature_2m_min&timezone=Europe%2FMoscow"
            ));
            meteoThread.start();
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
