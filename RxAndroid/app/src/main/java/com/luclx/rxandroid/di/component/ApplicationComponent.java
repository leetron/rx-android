package com.luclx.rxandroid.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.luclx.rxandroid.data.service.ColorService;
import com.luclx.rxandroid.di.module.ApplicationModule;
import com.luclx.rxandroid.di.module.RepositoryModule;
import com.luclx.rxandroid.di.module.ServiceModule;
import com.luclx.rxandroid.repository.ColorRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LucLX on 11/19/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class
        , ServiceModule.class
        , RepositoryModule.class
})
public interface ApplicationComponent {
    ColorService getColorService();

    ColorRepository getColorRepository();

    Context exposeContext();

    SharedPreferences exposeSharedPreferences();
}
