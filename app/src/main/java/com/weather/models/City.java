package com.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ken Lai on 23/4/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty("coord")
    private Coordinate coordinate;
    @JsonProperty
    private String country;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }
}
