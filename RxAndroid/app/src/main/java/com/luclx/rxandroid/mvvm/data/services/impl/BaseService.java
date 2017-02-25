package com.luclx.rxandroid.mvvm.data.services.impl;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by LucLX on 2/25/17.
 */

public abstract class BaseService {
    protected <T> Observable<T> catchError(final Response<T> response) {
        return Observable.create(emitter -> {
            if (response.isSuccessful()) {
                emitter.onNext(response.body());
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable(response.message()));
            }
        });
    }
}
