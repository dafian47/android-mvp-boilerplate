package com.dafian.android.androidmvpboilerplate.data.api;

import android.support.annotation.NonNull;

import com.dafian.android.androidmvpboilerplate.BuildConfig;
import com.dafian.android.androidmvpboilerplate.config.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Dafian on 13/03/18
 */
public class RestFactory {

    public static RestService create() {
        OkHttpClient client = makeClientService(makeLoggingInterceptor());
        return makeRestService(client, makeGson());
    }

    private static RestService makeRestService(OkHttpClient okHttp, Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_REST)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RestService.class);
    }

    @NonNull
    private static OkHttpClient makeClientService(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder ongoing = chain.request().newBuilder();
                    ongoing.addHeader("Content-Type", "application/json");
                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
    }

    @NonNull
    private static Gson makeGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }
}
