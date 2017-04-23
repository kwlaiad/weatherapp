package com.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weather.models.utils.UNIXDateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Created by Ken Lai on 23/4/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractWeather {
    @JsonProperty("main")
    private MainInformation mainInformation;
    @JsonProperty
    private Wind wind;
    @JsonProperty
    private Clouds clouds;
    @JsonProperty
    private Rain rain;
    @JsonProperty
    private Snow snow;
    @JsonDeserialize(using = UNIXDateDeserializer.class)
    @JsonProperty("dt")
    private Date date;
    @JsonProperty("weather")
    private List<Weather> weather;

    public MainInformation getMainInformation() {
        return mainInformation;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public Date getDate() {
        return date;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
