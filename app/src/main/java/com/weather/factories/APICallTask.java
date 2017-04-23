package com.weather.factories;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class APICallTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        if (strings == null || strings.length == 0) return null;

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            String response;
            try {
                response = getStringFromInputStream(httpURLConnection.getInputStream());
            } finally {
                httpURLConnection.disconnect();
            }

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getStringFromInputStream(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

}
