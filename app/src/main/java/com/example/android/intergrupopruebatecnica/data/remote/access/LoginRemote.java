package com.example.android.intergrupopruebatecnica.data.remote.access;

import com.example.android.intergrupopruebatecnica.data.remote.api.LoginAPI;
import com.example.android.intergrupopruebatecnica.data.remote.response.LoginResponse;

import io.reactivex.Observable;

public class LoginRemote extends BaseRemoteAccess<LoginAPI> {

    public LoginRemote(LoginAPI loginAPI) {
        super(loginAPI);
    }

    public Observable<LoginResponse> doLogin(String email, String password){
        return getAPI().getLogin(email, password);
    }

}