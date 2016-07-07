package com.demo.alopgudhate.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.alopgudhate.weatherapp.system.ConnectivityCheckUtil;
import com.demo.alopgudhate.weatherapp.system.Constants;
import com.demo.alopgudhate.weatherapp.system.RequestHandlerTask;
import com.demo.alopgudhate.weatherapp.valueobjects.QueryConstructor;

public class CurrentCityForecastActivity extends AppCompatActivity implements LocationListener {

    private ListView lv;
    protected LocationManager locationManager;
    protected boolean gps_enabled, network_enabled;
    private QueryConstructor queryConstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_city_forecast_activity);
        Location location;
        location = getCurrentLocation();
        if (location == null) {
            location = new Location(LocationManager.NETWORK_PROVIDER);
            location.setLatitude(17);
            location.setLongitude(73);
        }
        queryConstructor = new QueryConstructor();
        String query = queryConstructor.getQuery(location);
        // call AsynTask to perform network operation on separate thread
        lv = (ListView) findViewById(R.id.listView);
        if (ConnectivityCheckUtil.isNetworkAvailable(this)) {
            new RequestHandlerTask(CurrentCityForecastActivity.this, lv, null).execute(query);
        } else {
            ConnectivityCheckUtil.notifyConnectivityError(this);
        }
        Button searchButton = (Button) findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.txtEnterCity);
                if (tv.getText().length() == 0) {
                    Toast.makeText(CurrentCityForecastActivity.this, Constants.NO_CITIES_ERROR, Toast.LENGTH_LONG).show();
                } else {
                    String[] cities = tv.getText().toString().split(",");
                    if (ConnectivityCheckUtil.isNetworkAvailable(getContext())) {
                        Intent intent = new Intent(CurrentCityForecastActivity.this, MultipleCitiesForecastActivity.class);
                        intent.putExtra(Constants.CITIES, cities);
                        startActivity(intent);
                    } else {
                        ConnectivityCheckUtil.notifyConnectivityError(getContext());
                    }
                }
            }
        });
    }

    private Context getContext() {
        return this;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    public Location getCurrentLocation() {
        Location location = null;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return location;

        }
        return location;
    }
}
