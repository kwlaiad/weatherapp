package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weather.models.utils.ISOStringDateDeserializer;

import java.util.Date;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class Forecast extends AbstractWeather {
    @JsonDeserialize(using = ISOStringDateDeserializer.class)
    @JsonProperty("dt_txt")
    Date date;

    @Override
    public Date getDate() {
        return date;
    }
}
