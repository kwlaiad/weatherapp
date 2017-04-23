package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class Coordinate {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
