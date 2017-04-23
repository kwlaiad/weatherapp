package com.weather.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.weather.fragments.CurrentWeatherFragment;
import com.weather.fragments.WeeklyWeatherFragment;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    private static final int ITEM_COUNT = 2;
    private Bundle _args;

    public WeatherPagerAdapter(FragmentManager fragmentManager, Bundle args) {
        super(fragmentManager);
        _args = args;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                currentWeatherFragment.setArguments(_args);
                return currentWeatherFragment;
            case 1:
                WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();
                weeklyWeatherFragment.setArguments(_args);
                return weeklyWeatherFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
                return null;
        }
    }
}
