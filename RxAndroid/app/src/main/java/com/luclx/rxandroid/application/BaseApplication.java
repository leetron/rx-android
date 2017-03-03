package com.luclx.rxandroid.application;

import android.app.Application;

import com.luclx.rxandroid.di.component.ApplicationComponent;
import com.luclx.rxandroid.di.component.DaggerApplicationComponent;
import com.luclx.rxandroid.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by LucLX on 11/19/16.
 */

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;


    private static BaseApplication mInstance;

    private synchronized void initInstance() {
        mInstance = this;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initInstance();
        LeakCanary.install(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(BaseApplication.getInstance()))
                .build();

    }
}
