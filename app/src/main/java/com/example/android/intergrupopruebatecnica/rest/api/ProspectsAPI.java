package com.example.android.intergrupopruebatecnica.rest.api;

import com.example.android.intergrupopruebatecnica.rest.response.ProspectsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProspectsAPI {

    String baseURL = "http://demo5663271.mockable.io";

    @GET("/prospects")
    Call<ProspectsResponse> getProspects();

}