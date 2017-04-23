package com.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;
import com.weather.R;
import com.weather.adapters.ForecastAdapter;
import com.weather.factories.APICallTask;
import com.weather.factories.GPSTracker;
import com.weather.factories.OpenWeatherMapCall;
import com.weather.models.Forecast;
import com.weather.models.ForecastInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class WeeklyWeatherFragment extends Fragment {
    private View mView;
    private RecyclerView weathers;

    private LinearLayoutManager layoutManager;

    private ForecastInfo forecastInfo;
    private GPSTracker mTracker;

    private double lat;
    private double lng;

    private APICallTask mTask;

    public WeeklyWeatherFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_weekly_weather, container, false);
        weathers = (RecyclerView) mView.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        weathers.setLayoutManager(layoutManager);

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
                    forecastInfo = new ObjectMapper().readValue(s, new TypeReference<ForecastInfo>() {});
                } catch (IOException e) {
                    throw new RuntimeException("Cannot parse json", e);
                }
                List<Forecast> forecastList = forecastInfo.getForecasts();
                ForecastAdapter adapter = new ForecastAdapter(forecastList);
                weathers.setAdapter(adapter);
            }
        };
        mTask.execute(OpenWeatherMapCall.getForecast(new LatLng(lat, lng)));
    }
}
