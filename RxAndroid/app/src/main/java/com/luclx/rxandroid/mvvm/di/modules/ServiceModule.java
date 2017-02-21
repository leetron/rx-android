package com.luclx.rxandroid.mvvm.di.modules;


import com.luclx.rxandroid.mvvm.data.api.API;
import com.luclx.rxandroid.mvvm.data.net.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tai.nguyen on 10/6/16.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    public API getGitHubClient() {
        return  ServiceGenerator.createService(API.class);
    }

//    @Singleton
//    @Provides
//    public PhotoService getGitHubService(PhotoServiceImpl service) {
//        return service;
//    }
}
