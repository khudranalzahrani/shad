package com.example.lap_shop.shahad_3asal.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ahmed Salamony on 6/7/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}