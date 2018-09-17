package com.example.android.intergrupopruebatecnica.data.remote;

import com.example.android.intergrupopruebatecnica.data.remote.access.LoginRemote;
import com.example.android.intergrupopruebatecnica.data.remote.access.ProspectsRemote;
import com.example.android.intergrupopruebatecnica.data.remote.api.LoginAPI;
import com.example.android.intergrupopruebatecnica.data.remote.api.ProspectsAPI;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {

    private OkHttpClient okHttpClient;
    private Retrofit retrofitApiLogin;
    private Retrofit retrofitApiProspect;

    private ProspectsRemote prospectsRemote;
    private LoginRemote loginRemote;

    public ApiConnection() {
        okHttpClient = initOkHttpClient();
        retrofitApiLogin = initRetrofitApiLogin();
        retrofitApiProspect = initRetrofitApiProspect();
        prospectsRemote = initProspectRemote();
        loginRemote = initLoginRemote();
    }

    private OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                /*
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Auth","token")
                            .build();
                    return chain.proceed(request);
                })
                */
                .build();
    }

    private ProspectsRemote initProspectRemote() {
        return new ProspectsRemote(retrofitApiProspect.create(ProspectsAPI.class));
    }

    private LoginRemote initLoginRemote() {
        return new LoginRemote(retrofitApiLogin.create(LoginAPI.class));
    }

    private Retrofit initRetrofitApiLogin() {
        return retrofitDefaultConfig()
                .baseUrl(LoginAPI.BASE_URL)
                .build();
    }

    private Retrofit initRetrofitApiProspect() {
        return retrofitDefaultConfig()
                .baseUrl(ProspectsAPI.BASE_URL)
                .build();
    }

    private Retrofit.Builder retrofitDefaultConfig() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
    public ProspectsRemote getProspectRemote() {
        return prospectsRemote;
    }

    public LoginRemote getLoginRemote() {
        return loginRemote;
    }

}
