package com.example.android.intergrupopruebatecnica.data.preference.access;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android.intergrupopruebatecnica.data.preference.PreferenceConnection;

public class BasePreferenceAccess {

    PreferenceConnection preferenceConnection;

    public BasePreferenceAccess(@NonNull Context context, @NonNull String name) {
        preferenceConnection = new PreferenceConnection(context, name);
    }


}
