package com.luclx.rxandroid;

/**
 * Created by LucLX on 11/19/16.
 */


import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import rx.Subscription;

public interface ApiInterface {

    @GET("5821f8e5e4b0a828bd208d4f")
    Call<ColorResponse> getMyColor();

    @GET("5821f8e5e4b0a828bd208d4f")
    Observable<ColorResponse> getMyColorRx();
}
