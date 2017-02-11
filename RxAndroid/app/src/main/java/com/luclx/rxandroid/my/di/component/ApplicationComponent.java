package com.luclx.rxandroid.my.di.component;

import android.content.Context;

import com.luclx.rxandroid.my.di.module.ApplicationModule;

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

    Context exposeContext();
}
