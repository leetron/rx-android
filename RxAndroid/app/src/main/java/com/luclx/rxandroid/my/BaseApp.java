package com.luclx.rxandroid.my;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by LucLX on 1/10/17.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
