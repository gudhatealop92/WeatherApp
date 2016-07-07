package com.demo.alopgudhate.weatherapp.valueobjects;

import android.location.Location;

import com.demo.alopgudhate.weatherapp.system.Constants;

/**
 * Created by Alop Gudhate on 1/7/16.
 */
public class QueryConstructor {
    public String getQuery(Location location) {
        StringBuffer query = new StringBuffer();
        query = query.append(Constants.QUERY_FIRST_PART)
                .append("lat=").append(location.getLatitude())
                .append("&lon=").append(location.getLongitude())
                .append("&cnt=").append(Constants.COUNT)
                .append("&units=").append(Constants.UNIT)
                .append("&APPID=").append(Constants.APP_ID);
        return query.toString();
    }

    public String getQuery(String CityName) {
        StringBuffer query = new StringBuffer();
        query = query.append(Constants.QUERY_FIRST_PART)
                .append("q=").append(CityName.replaceAll(" ", "%20"))
                .append("&cnt=").append(Constants.COUNT)
                .append("&units=").append(Constants.UNIT)
                .append("&APPID=").append(Constants.APP_ID);
        return query.toString();
    }
}
