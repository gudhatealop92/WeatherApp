package com.demo.alopgudhate.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.astuetz.PagerSlidingTabStrip;
import com.demo.alopgudhate.weatherapp.adapters.MultipleCitiesFragmentPagerAdapter;
import com.demo.alopgudhate.weatherapp.system.ConnectivityCheckUtil;
import com.demo.alopgudhate.weatherapp.system.Constants;

/**
 * Created by Alop Gudhate on 4/7/16.
 */
public class MultipleCitiesForecastActivity extends AppCompatActivity {
    private String[] cities;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabsStrip;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cities = getIntent().getStringArrayExtra(Constants.CITIES);
        setContentView(R.layout.multiple_cities_forecast_activity);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MultipleCitiesFragmentPagerAdapter(getSupportFragmentManager(), cities));

        // Give the PagerSlidingTabStrip the ViewPager
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        final EditText cityNameSearchView = (EditText) findViewById(R.id.txtEnterCity);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (cityNameSearchView.getText().toString().trim() != null
                        && !cityNameSearchView.getText().toString().trim().isEmpty()) {
                    String[] cities = cityNameSearchView.getText().toString().split(",");
                    if (ConnectivityCheckUtil.isNetworkAvailable(getContext())) {
                        Intent intent = new Intent(MultipleCitiesForecastActivity.this, MultipleCitiesForecastActivity.class);
                        intent.putExtra(Constants.CITIES, cities);
                        startActivity(intent);
                        finish();
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
    protected void onResume() {
        super.onResume();

    }
}

