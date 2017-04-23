package com.weather.factories;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by Ken Lai on 23/4/2017.
 */

public class OpenWeatherMapCall {
    private static final String CURRENT_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String WEEKLY_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String API_KEY = "4333e9cff8602c1af3885fe0598a6f40";

    public static String getCurrentWeather(LatLng point) {
        return String.format("%s?lat=%s&lon=%s&appid=%s", CURRENT_URL, point.latitude, point.longitude, API_KEY);
    }

    public static String getForecast(LatLng point) {
        return String.format("%s?lat=%s&lon=%s&appid=%s", WEEKLY_URL, point.latitude, point.longitude, API_KEY);
    }
}
