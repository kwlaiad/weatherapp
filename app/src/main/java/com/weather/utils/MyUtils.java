package com.weather.utils;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class MyUtils {
    public static double getDegreesCelciusFromKelvin(double kelvin){
        return (kelvin - 273.15f);
    }
    public static double getDegreesFahrenheitFromKelvin(double kelvin){
        return (kelvin - 273.15f) * 9 / 5 + 32;
    }
}
