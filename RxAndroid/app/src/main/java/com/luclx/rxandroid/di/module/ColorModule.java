package com.luclx.rxandroid.di.module;

import com.luclx.rxandroid.api.ColorApiService;
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
    ColorApiService provideColorApiService(Retrofit retrofit) {
        return retrofit.create(ColorApiService.class);
    }

    @ActivityScope
    @Provides
    MainView provideView() {
        return mainView;
    }
}
