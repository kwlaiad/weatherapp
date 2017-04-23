package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weather.models.utils.UNIXDateDeserializer;

import java.util.Date;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class SystemInformation {
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("message")
    private String message;
    @JsonProperty("country")
    private String country;
    @JsonDeserialize(using = UNIXDateDeserializer.class)
    @JsonProperty("sunrise")
    private Date sunrise;
    @JsonDeserialize(using = UNIXDateDeserializer.class)
    @JsonProperty("sunset")
    private Date sunset;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }
}
