package com.luclx.rxandroid.mvvm.reponsitory.impl;

import com.luclx.rxandroid.mvvm.data.services.PhotoService;
import com.luclx.rxandroid.mvvm.model.Photo;
import com.luclx.rxandroid.mvvm.model.mapper.PhotoMapper;
import com.luclx.rxandroid.mvvm.reponsitory.PhotoReponsitory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by LucLX on 2/25/17.
 */

public class PhotoReponsitoryImpl implements PhotoReponsitory {
    private PhotoService mService;
    private PhotoMapper<com.luclx.rxandroid.mvvm.data.entities.Photo, Photo> mMapper;
    private List<Photo> mList;

    @Inject
    public PhotoReponsitoryImpl(PhotoService service, PhotoMapper mapper) {
        mService = service;
        mMapper = mapper;
    }

    @Override
    public Observable<Photo> getUser(String photoId) {
        return mService.getUser(photoId)
                .map(mMapper::transform);
    }

    @Override
    public Observable<List<Photo>> getUserList() {
        return Observable.concat(mList != null ?
                        Observable.just(mList) : Observable.empty(),
                mService.getUserList()
                        .map(mMapper::transform)
                        .doOnNext(photos -> mList = photos));
    }
}
