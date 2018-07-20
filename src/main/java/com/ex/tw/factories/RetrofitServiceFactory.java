package com.ex.tw.factories;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceFactory {

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitServiceFactory.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl("http://api.geonames.org/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return instance;
    }
}
