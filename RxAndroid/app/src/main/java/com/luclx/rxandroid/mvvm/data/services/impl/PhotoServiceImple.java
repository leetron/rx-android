package com.luclx.rxandroid.mvvm.data.services.impl;

import com.luclx.rxandroid.mvvm.data.api.API;
import com.luclx.rxandroid.mvvm.data.entities.Photo;
import com.luclx.rxandroid.mvvm.data.services.PhotoService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LucLX on 2/25/17.
 */

public class PhotoServiceImple
        extends BaseService
        implements PhotoService {

    private API mClient;

    @Inject
    public PhotoServiceImple(API api) {
        this.mClient = api;
    }

    @Override
    public Observable<Photo> getUser(String photoId) {
        return mClient.getPhoto(photoId)
                .flatMap(this::catchError)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Photo>> getUserList() {
        return mClient.getPhotoList()
                .flatMap(this::catchError)
                .subscribeOn(Schedulers.io());
    }
}
