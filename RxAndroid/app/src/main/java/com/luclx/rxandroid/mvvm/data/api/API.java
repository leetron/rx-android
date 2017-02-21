package com.luclx.rxandroid.mvvm.data.api;

import com.luclx.rxandroid.mvvm.data.photo.Photo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.luclx.rxandroid.mvvm.data.config.APIConfig.API_CLIENT_ID;
import static com.luclx.rxandroid.mvvm.data.config.APIConfig.API_HEADER_ACCEPT_VERSION;
import static com.luclx.rxandroid.mvvm.data.config.APIConfig.API_HEADER_CLIENT_ID;

/**
 * Created by lucle on 2/21/17.
 */

public interface API {

    @Headers({API_HEADER_ACCEPT_VERSION, API_HEADER_CLIENT_ID + " " + API_CLIENT_ID})
    @GET("photos")
    Observable<Response<List<Photo>>> getPhotoList();

    @Headers({API_HEADER_ACCEPT_VERSION, API_HEADER_CLIENT_ID + " " + API_CLIENT_ID})
    @GET("photos/:{id}")
    Observable<Response<Photo>> getPhoto(@Path("id") String photoId);
}
