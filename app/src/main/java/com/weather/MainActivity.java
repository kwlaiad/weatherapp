package com.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.model.LatLng;
import com.weather.adapters.WeatherPagerAdapter;
import com.weather.fragments.MapFragment;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class MainActivity extends AppCompatActivity implements MapFragment.OnLocationSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private WeatherPagerAdapter adapter;

    private Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            args = getIntent().getExtras();
        } else {
            args = new Bundle();
        }
        setup();
    }

    private void setup() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new WeatherPagerAdapter(getSupportFragmentManager(), args);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_edit_location) {
            MapFragment mapFragment = new MapFragment();
            mapFragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_bottom, 0, 0, R.anim.exit_from_top);
            ft.replace(R.id.map_container, mapFragment);
            ft.addToBackStack(null);
            ft.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationSelected(LatLng point) {
        args.putDouble("lat", point.latitude);
        args.putDouble("lng", point.longitude);
        getSupportFragmentManager().popBackStack();
        refresh();
    }

    private void refresh() {
        adapter = new WeatherPagerAdapter(getSupportFragmentManager(), args);
        viewPager.setAdapter(adapter);
    }
}
