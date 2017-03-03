package com.luclx.rxandroid.di.module;

import com.luclx.rxandroid.mvp.model.mapper.ColorMapper;
import com.luclx.rxandroid.mvp.model.mapper.impl.ColorMapperImpl;
import com.luclx.rxandroid.repository.ColorRepository;
import com.luclx.rxandroid.repository.impl.ColorRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lucle on 2/27/17.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public ColorRepository getColorRepository(ColorRepositoryImpl colorRepository) {
        return colorRepository;
    }

    @Singleton
    @Provides
    public ColorMapper getColorMapper(ColorMapperImpl colorMapper) {
        return colorMapper;
    }

}
