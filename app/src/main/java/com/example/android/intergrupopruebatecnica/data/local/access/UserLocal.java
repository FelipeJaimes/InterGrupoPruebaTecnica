package com.example.android.intergrupopruebatecnica.data.local.access;

import com.example.android.intergrupopruebatecnica.data.local.Database;
import com.example.android.intergrupopruebatecnica.data.local.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;


public class UserLocal extends BaseLocalAccess {

    public UserLocal(Database database) {
        super(database);
    }

    public Completable refreshUser(User user) {
        return toCompletable(() -> {
                    database.userDAO().deleteAll();
                    database.userDAO().insertUser(user); });
    }

    public Completable clearUser() {
        return toCompletable(() -> database.userDAO().deleteAll());
    }

}
