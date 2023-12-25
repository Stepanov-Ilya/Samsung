package com.example.weather24;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class ResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_response);

        TextView textViewPlace = findViewById(R.id.Place);


        String place = getIntent().getStringExtra("TITLE");
        double lat = getIntent().getDoubleExtra("LAT", 0);
        double lng = getIntent().getDoubleExtra("LNG", 0);

        textViewPlace.setText("Forecast for " + place);
        textViewPlace.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textViewPlace.setGravity(Gravity.CENTER);

        registerReceiver(broadcastReceiver, new IntentFilter("WeatherForecast"), RECEIVER_EXPORTED);

        Intent intent = new Intent(ResponseActivity.this, WeatherForecast.class);
        intent.putExtra("LAT", lat);
        intent.putExtra("LNG", lng);
        startService(intent);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.getStringExtra("Forecast");
            Log.e("RESULT", str);
            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONObject data = jsonObject.getJSONObject("daily");

                JSONArray arrJson = data.getJSONArray("time");
                JSONArray tempMaxJson = data.getJSONArray("temperature_2m_max");
                JSONArray tempMinJson = data.getJSONArray("temperature_2m_min");

                String[] time = new String[arrJson.length()];
                double[] tempMax = new double[tempMaxJson.length()];
                double[] tempMin = new double[tempMinJson.length()];

                for (int i = 0; i < arrJson.length(); i++) {
                    time[i] = arrJson.getString(i);
                    tempMax[i] = tempMaxJson.getDouble(i);
                    tempMin[i] = tempMinJson.getDouble(i);
                }


                Log.e("RESULT", Arrays.toString(time));
                Log.e("RESULT", Arrays.toString(tempMax));
                Log.e("RESULT", Arrays.toString(tempMin));

                Day day;

                for (int i = 0; i < 7; i++) {
                    Date date = Day.parseToDate(time[i]);
                    day = new Day(date, tempMin[i], tempMax[i]);
                    displayForecast(i + 1, day);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    };

    private void displayForecast(int dayOfWeekNumber,Day day) {
        // Construct the TextView resource ID using the day of the week as a suffix
        String textViewResourceName = "day" + dayOfWeekNumber + "WeatherTextView";
        int textViewResourceId = getResources().getIdentifier(textViewResourceName, "id", getPackageName());

        // Check if the TextView resource ID is valid
        if (textViewResourceId != 0) {
            TextView dayTextView = findViewById(textViewResourceId);

            // Check if the TextView is found
            if (dayTextView != null) {
                Log.e("RESULT",day.toString());
                dayTextView.setText(day.toString());

            } else {
                Log.e("TextView Error", "TextView is null for resource ID: " + textViewResourceId);
            }
        } else {
            Log.e("Resource ID Error", "Invalid resource ID for TextView: " + textViewResourceName);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(ResponseActivity.this, WeatherForecast.class);
        startService(intent);
        unregisterReceiver(broadcastReceiver);
    }


}
