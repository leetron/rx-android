package com.luclx.rxandroid.mvvm.data.api;

import com.luclx.rxandroid.mvvm.data.entities.Photo;

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
 * map()
 * tre ve mot object kieu <T>
 * la mot Operator dung de chuyen doi data trong mapper
 * co the co nhieu map trong 1 Observable
 * trinh tu thuc hien map tu tren xuong duoi
 * VD: PhotoReponsitoryImpl
 * https://viblo.asia/haint.fit12/posts/mrDkMrpzvzL
 */

/**
 * flatMap()
 * thay the duoc cho map() va linh hoat hon map
 * tra ve mot Observable<T>
 * VD: PhotoServiceImple
 */

/**
 * concat
 * noi cac item phat ra cua 2 Observable ma khong dan xen
 * VD: {a} phat ra {1,7}, {b} phat ra {9,2}
 * concat(a,b) => phat ra {1,7,9,2}
 * VD: PhotoReponsitoryImpl
 * http://reactivex.io/documentation/operators/concat.html
 */

public interface API {

    @Headers({API_HEADER_ACCEPT_VERSION, API_HEADER_CLIENT_ID + " " + API_CLIENT_ID})
    @GET("photos")
    Observable<Response<List<Photo>>> getPhotoList();

    @Headers({API_HEADER_ACCEPT_VERSION, API_HEADER_CLIENT_ID + " " + API_CLIENT_ID})
    @GET("photos/:{id}")
    Observable<Response<Photo>> getPhoto(@Path("id") String photoId);
}
