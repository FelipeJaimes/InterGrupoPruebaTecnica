package com.example.android.intergrupopruebatecnica.rest.service;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class BaseService<API> {

    private Retrofit mRetrofit;
    private API api;

    BaseService(String baseUrl, final Class<API> apiClass) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = mRetrofit.create(apiClass);
    }

    API getAPI() {
        return api;
    }


}