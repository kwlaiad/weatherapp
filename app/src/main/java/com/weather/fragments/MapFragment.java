package com.weather.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weather.R;
import com.weather.factories.GPSTracker;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private MapView mMapView;
    private GoogleMap mMap;
    private GPSTracker mTracker;

    private double lat;
    private double lng;

    OnLocationSelectedListener mCallback;

    public MapFragment() {
        // Required empty public constructor
    }

    public interface OnLocationSelectedListener {
        void onLocationSelected(LatLng point);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnLocationSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnLocationSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) mView.findViewById(R.id.mapView);

        mTracker = new GPSTracker(getContext());
        lat = getArguments().getDouble("lat", mTracker.getLatitude());
        lng = getArguments().getDouble("lng", mTracker.getLongitude());

        mMapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        return mView;
    }

    @Override
    public void onMapClick(LatLng point) {
        lat = point.latitude;
        lng = point.longitude;
        Toast.makeText(getContext(), String.format("Lat:%s, Lng:%s", lat, lng), Toast.LENGTH_LONG).show();
        mMap.addMarker(new MarkerOptions().position(point));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 14.0f));
        mCallback.onLocationSelected(point);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        LatLng mapVenue = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(mapVenue));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapVenue, 14.0f));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}
