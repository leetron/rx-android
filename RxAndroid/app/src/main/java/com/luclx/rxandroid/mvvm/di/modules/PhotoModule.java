package com.luclx.rxandroid.mvvm.di.modules;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tai.nguyen on 11/11/16.
 */

@Module
public class PhotoModule {
    String id;

    public PhotoModule() {
        this.id = "";
    }

    public PhotoModule(String id) {
        this.id = id;
    }

    @Provides
//    @AScope
    @Named("id")
    public String getUserId() {
        return id;
    }
}
