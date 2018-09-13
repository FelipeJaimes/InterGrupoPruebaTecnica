package com.example.android.intergrupopruebatecnica.rest.api;

import com.example.android.intergrupopruebatecnica.rest.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface LoginAPI {

    String baseURL = "http://directotesting.igapps.co";

    @GET("/application/login")

    Call<LoginResponse> getLogin(@Query("email") String email,
                                 @Query("password") String password);

}
