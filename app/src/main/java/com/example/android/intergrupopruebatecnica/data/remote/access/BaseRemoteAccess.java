package com.example.android.intergrupopruebatecnica.data.remote.access;

abstract class BaseRemoteAccess<Api> {
    private Api api;

    BaseRemoteAccess(Api api) {
        this.api = api;
    }

    public Api getAPI() {
        return api;
    }
}