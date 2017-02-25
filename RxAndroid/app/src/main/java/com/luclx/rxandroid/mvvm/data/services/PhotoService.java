package com.luclx.rxandroid.mvvm.data.services;


import com.luclx.rxandroid.mvvm.data.entities.Photo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by LucLX on 2/25/17.
 */

public interface PhotoService {

    Observable<Photo> getUser(String photoId);

    Observable<List<Photo>> getUserList();
}
