package com.example.android.intergrupopruebatecnica.model;

import com.example.android.intergrupopruebatecnica.data.local.access.UserLocal;
import com.example.android.intergrupopruebatecnica.data.local.entity.User;
import com.example.android.intergrupopruebatecnica.data.preference.access.UserPreferences;
import com.example.android.intergrupopruebatecnica.data.remote.access.LoginRemote;
import com.example.android.intergrupopruebatecnica.data.remote.response.LoginResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel {
    private LoginRemote loginRemote;
    private UserPreferences userPreferences;
    private UserLocal userLocal;

    public LoginModel(LoginRemote loginRemote, UserPreferences userPreferences, UserLocal userLocal) {
        this.loginRemote = loginRemote;
        this.userPreferences = userPreferences;
        this.userLocal = userLocal;
    }

    public void setIsLogged(boolean isLogged) {
        userPreferences.setIsLogged(isLogged);

    }

    public boolean isUserLogged() {
        return userPreferences.isUserLogged();
    }

    public Observable<LoginResponse> doLogin(String mail, String password) {
        return loginRemote.doLogin(mail, password)
                .subscribeOn(Schedulers.io());
    }

    public Completable saveUser(User user) {
        return userLocal.refreshUser(user);
    }
}
