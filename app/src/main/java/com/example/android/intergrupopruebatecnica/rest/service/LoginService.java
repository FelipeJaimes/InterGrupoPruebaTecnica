package com.example.android.intergrupopruebatecnica.rest.service;

import com.example.android.intergrupopruebatecnica.rest.api.LoginAPI;
import com.example.android.intergrupopruebatecnica.rest.response.LoginResponse;

import retrofit2.Callback;

public class LoginService extends BaseService<LoginAPI>{

    public LoginService() {
        super(LoginAPI.baseURL, LoginAPI.class);
    }

    public void getLogin(String email, String password, Callback<LoginResponse> loginResponseCallback){
        getAPI().getLogin(email, password).enqueue(loginResponseCallback);
    }

}