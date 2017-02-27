package com.luclx.rxandroid.data.net;

/**
 * Created by LucLX on 11/19/16.
 */


import com.luclx.rxandroid.data.entity.Color;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface API {

    @GET("5821f8e5e4b0a828bd208d4f")
    Observable<Response<List<Color>>> getMyColorRx();
}
