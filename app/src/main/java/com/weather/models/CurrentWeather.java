package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.util.Date;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class CurrentWeather extends AbstractWeather {
    @JsonProperty("coord")
    private Coordinate coordinate;
    @JsonProperty("name")
    private String regionName;
    @JsonProperty("id")
    private long regionId;
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("sys")
    private SystemInformation systemInformation;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCod() {
        return cod;
    }

    public String getVisibility() {
        return visibility;
    }

    public long getRegionId() {
        return regionId;
    }

    public SystemInformation getSystemInformation() {
        return systemInformation;
    }
}
