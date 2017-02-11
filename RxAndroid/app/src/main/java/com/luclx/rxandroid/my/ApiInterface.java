package com.luclx.rxandroid.my;

/**
 * Created by LucLX on 11/19/16.
 */


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("5821f8e5e4b0a828bd208d4f")
    Call<ColorResponse> getMyColor();

    @GET("5821f8e5e4b0a828bd208d4f")
    Observable<ColorResponse> getMyColorRx();
}
