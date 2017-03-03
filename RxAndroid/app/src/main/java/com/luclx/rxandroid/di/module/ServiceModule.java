package com.luclx.rxandroid.di.module;

import com.luclx.rxandroid.data.net.API;
import com.luclx.rxandroid.data.net.ServiceGenerator;
import com.luclx.rxandroid.data.service.ColorService;
import com.luclx.rxandroid.data.service.impl.ColorServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lucle on 2/27/17.
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    public API getAPIClient() {
        return ServiceGenerator.createService(API.class);
    }

    @Singleton
    @Provides
    public ColorService getColorService(ColorServiceImpl service) {
        return service;
    }
}
