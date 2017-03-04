package com.luclx.rxandroid.data.service;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by lucle on 2/27/17.
 */

public abstract class BaseService {
    protected <T> Observable<T> catchError(Response<T> response) {
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
