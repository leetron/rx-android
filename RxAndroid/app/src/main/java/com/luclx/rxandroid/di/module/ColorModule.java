package com.luclx.rxandroid.di.module;

import com.luclx.rxandroid.net.API;
import com.luclx.rxandroid.di.scope.ActivityScope;
import com.luclx.rxandroid.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by LucLX on 11/19/16.
 */

@Module
public class ColorModule {
    private MainView mainView;

    public ColorModule(MainView mainView) {
        this.mainView = mainView;
    }

    @ActivityScope
    @Provides
    API provideApiService(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @ActivityScope
    @Provides
    MainView provideView() {
        return mainView;
    }
}
