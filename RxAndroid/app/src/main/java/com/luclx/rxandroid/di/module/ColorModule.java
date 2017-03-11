package com.luclx.rxandroid.di.module;

import com.luclx.rxandroid.di.scope.ActivityScope;
import com.luclx.rxandroid.mvp.view.interfaces.MainView;

import dagger.Module;
import dagger.Provides;

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
    MainView provideView() {
        return this.mainView;
    }
}
