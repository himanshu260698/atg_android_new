package com.ATG.World.application;

import android.app.Application;

/**
 * Created by Chetan on 22-12-2017.
 */

public class AtgApplication extends Application {

    // Region static variable
    private static AtgApplication currentApplication = null;

    // Region Lifecycle Method

    @Override
    public void onCreate() {
        super.onCreate();
        currentApplication = this;
    }

    // Region Helper Method
    public static AtgApplication getInstance(){
        return currentApplication;
    }
}
