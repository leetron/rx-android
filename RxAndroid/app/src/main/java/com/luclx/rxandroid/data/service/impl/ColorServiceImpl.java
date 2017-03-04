package com.luclx.rxandroid.data.service.impl;

import com.luclx.rxandroid.data.entity.Color;
import com.luclx.rxandroid.data.net.API;
import com.luclx.rxandroid.data.service.BaseService;
import com.luclx.rxandroid.data.service.ColorService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lucle on 2/27/17.
 */

public class ColorServiceImpl
        extends BaseService
        implements ColorService {
    private API mClient;

    @Inject
    public ColorServiceImpl(API api) {
        this.mClient = api;
    }

    @Override
    public Observable<List<Color>> getColorList() {
        return mClient.getMyColorRx()
                .flatMap(this::catchError)
                .subscribeOn(Schedulers.io());
    }
}
