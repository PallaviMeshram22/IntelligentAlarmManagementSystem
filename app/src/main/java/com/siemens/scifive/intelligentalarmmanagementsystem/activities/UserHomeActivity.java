package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.siemens.scifive.intelligentalarmmanagementsystem.Manifest;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.AlarmDetailsFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.HistoryFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserHomeFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserLocationFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.CallMethods;

import java.text.DateFormat;
import java.util.Date;

public class UserHomeActivity extends AppCompatActivity {
    private static UserHomeActivity INSTANCE = null;
    FrameLayout fLAUHFragmentContainer;
    TabLayout tLAUHTabs;
    FragmentManager fragmentManager;

    private CallMethods listener ;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    public Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    private String mLastUpdateTime;
    private SettingsClient mSettingsClient;
    public static UserHomeActivity getInstance() {
        return INSTANCE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        INSTANCE = this;

        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        // Kick off the process of building the LocationCallback, LocationRequest, and
        // LocationSettingsRequest objects.

        fLAUHFragmentContainer = findViewById(R.id.fLAUHFragmentContainer);
        tLAUHTabs = findViewById(R.id.tLAUHTabs);

        fragmentManager = getSupportFragmentManager();

        setupTabs();

        changeFragment(UserHomeFragment.getInstance(), true);
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        startLocationUpdates();
    }

    private void changeFragment(Fragment fragment, boolean isFirst) {
        if (isFirst) {
            //DONT ADD TO BACKSTACK
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fLAUHFragmentContainer, fragment)
                    .commit();
        } else {
            //ADD TO BACKSTACK
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fLAUHFragmentContainer, fragment)
                    .addToBackStack("Yes")
                    .commit();
        }
    }

    private void setupTabs() {
        TabLayout.Tab homeTab = tLAUHTabs.newTab();//POSITION 0
        homeTab.setText("Home");
        homeTab.setIcon(R.drawable.ic_home);

        TabLayout.Tab locationTab = tLAUHTabs.newTab();//POSITION 1
        locationTab.setText("Location");
        locationTab.setIcon(R.drawable.ic_location);

        TabLayout.Tab alarmDetailsTab = tLAUHTabs.newTab();//POSITION 2
        alarmDetailsTab.setText("Alarm Details");
        alarmDetailsTab.setIcon(R.drawable.ic_alarm_details);

        TabLayout.Tab historyTab = tLAUHTabs.newTab();//POSITION 3
        historyTab.setText("History");
        historyTab.setIcon(R.drawable.ic_history);


        tLAUHTabs.addTab(homeTab);
        tLAUHTabs.addTab(locationTab);
        tLAUHTabs.addTab(alarmDetailsTab);
        tLAUHTabs.addTab(historyTab);

        tLAUHTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //showToast("Home selected");
                        changeFragment(UserHomeFragment.getInstance(), false);
                        break;
                    case 1:
                        //showToast("Location selected");
                        changeFragment(UserLocationFragment.getInstance(), false);
                        break;
                    case 2:
                        //showToast("Alarm d selected");
                        changeFragment(AlarmDetailsFragment.getInstance(), false);
                        break;
                    case 3:
                        //showToast("history selected");
                        changeFragment(HistoryFragment.getInstance(), false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(UserHomeActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();
                //listener.setCurrentLocation(mCurrentLocation);
                Log.d("Location", "Location update recieved");
                //listener.setAlarmLocation();
                //Fragment locFrag = UserLocationFragment.getInstance();
                //locFrag.
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                //updateLocationUI();
            }
        };
    }

    public void setListener(CallMethods listener)
    {
        this.listener = listener ;
    }

    private void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("Location", "All location settings are satisfied.");

                        //noinspection MissingPermission

                        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Check Permissions Now
                            ActivityCompat.requestPermissions(UserHomeActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    99);
                        } else {
                            // permission has been granted, continue as usual
                            //Location myLocation =
                            //        LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                    mLocationCallback, Looper.myLooper());
                        }

                        //mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                        //        mLocationCallback, Looper.myLooper());

                        //updateUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i("Location", "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(UserHomeActivity.this, 99);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i("Location", "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e("Location", errorMessage);
                                Toast.makeText(UserHomeActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                                mRequestingLocationUpdates = false;
                        }

                        //updateUI();
                    }
                });
    }
}
