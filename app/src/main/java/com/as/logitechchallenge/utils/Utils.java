package com.as.logitechchallenge.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Suresh on 09-02-2016.
 */
public class Utils {
    private static Utils instance;
    public static Context context;


    public static  Utils getInstance(final Context ctx){
        if(instance==null){
            instance=new Utils();
            // instance.IsDeviceHasInternet=checkForNetwork();
        }
        return instance;
    }


    public static  boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo!=null && netInfo.isConnectedOrConnecting());
    }

}
