package com.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class Rain {
    @JsonProperty("3h")
    private double threeHours;
}
