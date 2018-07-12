package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.UserHomeActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.CallMethods;

public class UserLocationFragment extends Fragment implements CallMethods {
    private static UserLocationFragment INSTANCE = null;
    SupportMapFragment supportMapFragment;
    GoogleMap map;

    Location currentLocation;
    Location alarmLocation;
    private boolean alarmLocationRecieved = false;
    private boolean currentLocationRecieved = false;
    public void setAlarmLocation(Location alarmLocation){
        this.alarmLocation = alarmLocation;
        alarmLocationRecieved = true;
        Log.d("Location","alarm Location recieved");
    }

    public void setCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
        currentLocationRecieved = true;
        Log.d("Location","current Location recieved");
    }

    public static UserLocationFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocationFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_location, container, false);
        supportMapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);
                //if(currentLocationRecieved){
                currentLocation = UserHomeActivity.getInstance().mCurrentLocation;
                LatLng currLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                map.moveCamera(CameraUpdateFactory.newLatLng(currLatLng));
            //}
                //map.moveCamera();
            }
        });
        return v;
    }

    public View onResume(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_user_location, container, false);
        supportMapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);
                //if(currentLocationRecieved){
                currentLocation = UserHomeActivity.getInstance().mCurrentLocation;
                LatLng currLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                map.moveCamera(CameraUpdateFactory.newLatLng(currLatLng));
                //}
                //map.moveCamera();
            }
        });
        return v;
    }


}
