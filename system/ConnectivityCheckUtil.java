package com.demo.alopgudhate.weatherapp.system;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Alop Gudhate on 07-07-2016.
 */
public class ConnectivityCheckUtil {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void notifyConnectivityError(Context context) {
            AlertDialog.Builder noConnectivityDilog = new AlertDialog.Builder(context);
            noConnectivityDilog.setTitle("Connectivity Check");
            noConnectivityDilog.setMessage("No internet connectivity.\nPlease check internet connection.");
            noConnectivityDilog.setPositiveButton("OK", null);
            noConnectivityDilog.show();
    }
}
