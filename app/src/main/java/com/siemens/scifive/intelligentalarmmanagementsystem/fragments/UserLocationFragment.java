package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.UserHomeActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.DirectionsJSONParser;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyProgressDialog;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserLocationFragment extends Fragment {
    private static UserLocationFragment INSTANCE = null;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    Context mCtx;
    Location myCurrentLocation;
    LatLng destinationLatLng=null;

    public static UserLocationFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocationFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserHomeActivity.getInstance().tLAUHTabs.getTabAt(1).select();
        mCtx = container.getContext();

        View v = inflater.inflate(R.layout.fragment_user_location, container, false);
        supportMapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                displayMyLocationDetail();

                map.setMyLocationEnabled(true);



            }
        });
        return v;
    }

    @SuppressLint("MissingPermission")
    private void displayMyLocationDetail() {
        LocationManager lm = (LocationManager) mCtx.getSystemService(Context.LOCATION_SERVICE);
        lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myCurrentLocation = location;
                gotoMyLocation();
                if (destinationLatLng!=null){
                    map.clear();
                    map.addMarker(new MarkerOptions().position(destinationLatLng));

                    // Getting URL to the Google Directions API
                    LatLng origin = new LatLng(myCurrentLocation.getLatitude(), myCurrentLocation.getLongitude());

                    String url = getDirectionsUrl(origin, destinationLatLng);

                    MyProgressDialog.show(mCtx, "Please wait, getting direction data", false);
                    Ion.with(mCtx)
                            .load(url)
                            .asString()
                            .setCallback(new FutureCallback<String>() {
                                @Override
                                public void onCompleted(Exception e, String result) {
                                    MyProgressDialog.dismiss();
                                    if (e != null) {
                                        e.printStackTrace();
                                        return;
                                    }

                                    //LongLog.d(TAG, result);
                                    if (result.toLowerCase().contains("error")){
                                        Toast.makeText(mCtx, "Quota exeeded", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    ParserTask parserTask = new ParserTask();
                                    parserTask.execute(result);
                                }
                            });

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        }, Looper.getMainLooper());
    }

    private void gotoMyLocation() {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myCurrentLocation.getLatitude(), myCurrentLocation.getLongitude()), 15));
    }

    public void setDestinationLatLng(LatLng latLng) {
        destinationLatLng = latLng;
    }

    public void switchFragmentShowPath() {
        if (destinationLatLng!=null){
            UserHomeActivity.getInstance().changeFragment(this, false);


        }
    }



    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String key = "AIzaSyAjLWtECbFLfPX1alKlEBSQsOMcRT2cN4g";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    public static final String TAG="ShraX";

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            Log.d(TAG, "DaTa: " + data);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();


            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(true);

            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                map.addPolyline(lineOptions);
            }
        }
    }
}
