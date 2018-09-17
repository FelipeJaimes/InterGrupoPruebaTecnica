package com.example.android.intergrupopruebatecnica.model;

import com.example.android.intergrupopruebatecnica.data.local.access.UserLocal;
import com.example.android.intergrupopruebatecnica.data.preference.access.UserPreferences;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MenuModel {
    UserPreferences userPreferences;
    UserLocal userLocal;

    public MenuModel(UserPreferences userPreferences, UserLocal userLocal) {
        this.userPreferences = userPreferences;
        this.userLocal = userLocal;
    }

    public void setIsUserLogged(boolean isUserLogged) {
        userPreferences.setIsLogged(isUserLogged);
    }
    public Completable clearUserSession() {
        return userLocal.clearUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
