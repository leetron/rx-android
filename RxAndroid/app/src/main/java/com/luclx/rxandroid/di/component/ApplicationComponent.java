package com.luclx.rxandroid.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.luclx.rxandroid.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by LucLX on 11/19/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Retrofit exposeRetrofit();

    SharedPreferences exposeSharedPreferences();
}
