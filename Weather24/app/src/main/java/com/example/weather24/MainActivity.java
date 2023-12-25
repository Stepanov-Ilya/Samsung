package com.example.weather24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        EditText editTextPlace = findViewById(R.id.EditTextPlace);
        errorText = findViewById(R.id.ErrorText);
        Button button = findViewById(R.id.SearchButton);

        registerReceiver(broadcastReceiver, new IntentFilter("Geocoder"), RECEIVER_EXPORTED);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = editTextPlace.getText().toString();
                if (place.trim().isEmpty()) {
                    errorText.setText("Please enter a place");
                } else {
                    Intent intent = new Intent(MainActivity.this, Geocoder.class);
                    intent.putExtra("QUERY", place);
                    startService(intent);
                }
            }
        });
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("RESULT", intent.getStringExtra("GeoInfo"));

            String str = intent.getStringExtra("GeoInfo");
            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONArray items = jsonObject.getJSONArray("items");
                if (items.length() == 0) {
                    errorText.setText("Address not found");
                } else if (items.length() > 1) {
                    errorText.setText("Please provide a more precise address");
                } else {
                    errorText.setText("");
                    JSONObject item = items.getJSONObject(0);
                    String title = item.getString("title");
                    JSONObject pos = item.getJSONObject("position");
                    double lat = pos.getDouble("lat");
                    double lng = pos.getDouble("lng");

                    Intent responseIntent = new Intent(MainActivity.this, ResponseActivity.class);

                    responseIntent.putExtra("TITLE", title);
                    responseIntent.putExtra("LAT", lat);
                    responseIntent.putExtra("LNG", lng);

                    startActivity(responseIntent);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MainActivity.this, Geocoder.class);
        stopService(intent);
        unregisterReceiver(broadcastReceiver);
    }


}