package com.luclx.rxandroid.my.di.module;

import com.luclx.rxandroid.my.api.ColorApiService;
import com.luclx.rxandroid.my.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by LucLX on 11/19/16.
 */

@Module
public class ColorModule {
    @ActivityScope
    @Provides
    ColorApiService provideColorApiService(Retrofit retrofit) {
        return retrofit.create(ColorApiService.class);
    }
}
