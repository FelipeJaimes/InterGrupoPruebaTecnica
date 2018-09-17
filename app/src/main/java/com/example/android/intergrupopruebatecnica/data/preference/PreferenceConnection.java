package com.example.android.intergrupopruebatecnica.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceConnection {

    private SharedPreferences preferences;

    public PreferenceConnection(@NonNull Context context, @NonNull final String name) {
        this.preferences = context.getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
    }

    public void put(@NonNull final String key, @Nullable final String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void put(@NonNull String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public void put(@NonNull String key, float value) {
        preferences.edit().putFloat(key, value).apply();
    }

    public void put(@NonNull String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void put(@NonNull String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    public void put(@NonNull String key, Set<String> values) {
        preferences.edit().putStringSet(key, values).apply();
    }

    public String getString(@NonNull String key) {
        return preferences.getString(key, null);
    }

    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public float getFloat(@NonNull String key) {
        return preferences.getFloat(key, 0);
    }

    public int getInt(@NonNull String key) {
        return preferences.getInt(key, 0);
    }

    public long getLong(@NonNull String key) {
        return preferences.getLong(key, 0);
    }

    public Set<String> getStringSet(@NonNull String key) {
        return preferences.getStringSet(key, null);
    }

    public void remove(@NonNull String key) {
        preferences.edit().remove(key).apply();
    }
}
