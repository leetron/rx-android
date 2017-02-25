package com.luclx.rxandroid.mvvm.reponsitory;

import com.luclx.rxandroid.mvvm.model.Photo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by LucLX on 2/25/17.
 */

public interface PhotoReponsitory {
    Observable<Photo> getUser(String photoId);

    Observable<List<Photo>> getUserList();
}
