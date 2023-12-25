package com.example.weather24;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


import javax.net.ssl.HttpsURLConnection;

public class HttpsRequest implements Runnable {


    URL url;
    Handler handler;

    public HttpsRequest(Handler handler, String GEOCODING_RESOURCE, String API_KEY, String query) throws MalformedURLException {
        this.handler = handler;

        url = new URL(GEOCODING_RESOURCE + API_KEY + query);
        Log.e("RESULT", url.toString());
    }

    @Override
    public void run() {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());

            StringBuilder response = new StringBuilder();

            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }

            scanner.close();
            connection.disconnect();
            Message message = Message.obtain();
            message.obj = response.toString();
            handler.sendMessage(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
