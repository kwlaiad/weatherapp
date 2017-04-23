package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class ForecastInfo {
    @JsonProperty("cod")
    private String code;
    @JsonProperty
    private String message;
    @JsonProperty("cnt")
    private int count;
    @JsonProperty
    private City city;
    @JsonProperty("list")
    private List<Forecast> forecasts;

    public City getCity() {
        return city;
    }

    public int getCount() {
        return count;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
