package com.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;
import com.weather.R;
import com.weather.factories.APICallTask;
import com.weather.factories.GPSTracker;
import com.weather.factories.OpenWeatherMapCall;
import com.weather.models.CurrentWeather;
import com.weather.utils.MyUtils;

import java.io.IOException;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class CurrentWeatherFragment extends Fragment {
    private View mView;
    private TextView cityName, description, temperature, maxTemp, minTemp, sunrise, sunset, humidity, pressure;

    private CurrentWeather currentWeather;
    private GPSTracker mTracker;

    private double lat;
    private double lng;

    private APICallTask mTask;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_current_weather, container, false);
        cityName = (TextView) mView.findViewById(R.id.current_city_name);
        description = (TextView) mView.findViewById(R.id.current_description);
        temperature = (TextView) mView.findViewById(R.id.current_temp);
        maxTemp = (TextView) mView.findViewById(R.id.current_max_temp);
        minTemp = (TextView) mView.findViewById(R.id.current_min_temp);
        sunrise = (TextView) mView.findViewById(R.id.current_sunrise);
        sunset = (TextView) mView.findViewById(R.id.current_sunset);
        humidity = (TextView) mView.findViewById(R.id.current_humidity);
        pressure = (TextView) mView.findViewById(R.id.current_pressure);

        mTracker = new GPSTracker(getContext());
        lat = getArguments().getDouble("lat", mTracker.getLatitude());
        lng = getArguments().getDouble("lng", mTracker.getLongitude());

        getWeather();

        return mView;
    }

    private void getWeather() {
        mTask = new APICallTask() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    Log.i("json: ", s);
                    currentWeather = new ObjectMapper().readValue(s, new TypeReference<CurrentWeather>() {});
                } catch (IOException e) {
                    throw new RuntimeException("Cannot parse json", e);
                }
                cityName.setText(currentWeather.getRegionName());
                description.setText(currentWeather.getWeather().get(0).getDescription());
                temperature.setText(String.format("%.1f", MyUtils.getDegreesCelciusFromKelvin(currentWeather.getMainInformation().getTemperature())));
                maxTemp.append(String.format("%.1f°C", MyUtils.getDegreesCelciusFromKelvin(currentWeather.getMainInformation().getMaxTemperature())));
                minTemp.append(String.format("%.1f°C", MyUtils.getDegreesCelciusFromKelvin(currentWeather.getMainInformation().getMinTemperature())));
                sunrise.append(currentWeather.getSystemInformation().getSunrise().toString());
                sunset.append(currentWeather.getSystemInformation().getSunset().toString());
                humidity.append(currentWeather.getMainInformation().getHumidity() + "%");
                pressure.append(currentWeather.getMainInformation().getPressure() + " hPa");
            }
        };
        mTask.execute(OpenWeatherMapCall.getCurrentWeather(new LatLng(lat, lng)));
    }
}
