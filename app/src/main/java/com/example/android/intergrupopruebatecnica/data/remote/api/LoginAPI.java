package com.example.android.intergrupopruebatecnica.data.remote.api;

import com.example.android.intergrupopruebatecnica.data.remote.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface LoginAPI {

    String BASE_URL = "http://directotesting.igapps.co";

    @GET("/application/login")
    Observable<LoginResponse> getLogin(@Query("email") String email,
                                       @Query("password") String password);

}
