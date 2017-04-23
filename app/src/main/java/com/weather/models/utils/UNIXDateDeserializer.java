package com.weather.models.utils;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class UNIXDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // convert unix timestamp to date
        return new Date(Long.parseLong(parser.getText()) * 1000L);
    }
}
