package com.reshadi.nima.kidocodeimagetranslator.Util;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.google.gson.GsonBuilder;

/**
 * Created by n.reshadi on 4/12/2015.
 */
public class Initializer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
    }







}
