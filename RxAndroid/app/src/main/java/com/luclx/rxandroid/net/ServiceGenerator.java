package com.luclx.rxandroid.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.luclx.rxandroid.mvvm.data.config.APIConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucle on 2/21/17.
 */

public class ServiceGenerator {
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(APIConfig.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(getClient(getLogInterceptor()))
                .build();

        return retrofit.create(serviceClass);
    }

    protected static OkHttpClient getClient(Interceptor... interceptors) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        for (final Interceptor interceptor : interceptors) {
            httpClient.addInterceptor(interceptor);
        }

        httpClient.connectTimeout(APIConfig.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        return httpClient.build();
    }

    protected static Interceptor getLogInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }
}
