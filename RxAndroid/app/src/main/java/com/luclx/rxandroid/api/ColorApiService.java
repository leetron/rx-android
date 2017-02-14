package com.luclx.rxandroid.api;

/**
 * Created by LucLX on 11/19/16.
 */


import com.luclx.rxandroid.mvp.model.ColorResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ColorApiService {

    @GET("5821f8e5e4b0a828bd208d4f")
    Call<ColorResponse> getMyColor();

    @GET("5821f8e5e4b0a828bd208d4f")
    Observable<ColorResponse> getMyColorRx();
}
