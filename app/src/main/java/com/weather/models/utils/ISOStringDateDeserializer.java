package com.weather.models.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class ISOStringDateDeserializer extends JsonDeserializer<Date> {
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(ISO_DATE_FORMAT);

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // example: 2017-04-24 03:00:00
        String date = parser.getText();
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse date from string", e);
        }
    }
}
