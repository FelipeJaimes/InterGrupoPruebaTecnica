package com.example.android.intergrupopruebatecnica.data.preference.access;

import android.content.Context;
import android.support.annotation.NonNull;

import static com.example.android.intergrupopruebatecnica.data.preference.model.UserPrefsModel.IS_LOGGED;

public class UserPreferences extends BasePreferenceAccess {

    private static final String USER_PREFS = "user_prefs";

    public UserPreferences(@NonNull Context context) {
        super(context, USER_PREFS);
    }

    public boolean isUserLogged() {
        return preferenceConnection.getBoolean(IS_LOGGED);
    }

    public void setIsLogged(boolean isLogged) {
        preferenceConnection.put(IS_LOGGED, isLogged);
    }
}
