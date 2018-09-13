package com.example.android.intergrupopruebatecnica.rest.service;

import com.example.android.intergrupopruebatecnica.rest.api.ProspectsAPI;
import com.example.android.intergrupopruebatecnica.rest.response.ProspectsResponse;

import retrofit2.Callback;

public class ProspectsService extends BaseService <ProspectsAPI> {

    public ProspectsService() {
        super(ProspectsAPI.baseURL, ProspectsAPI.class);
    }

    public void getProspects(String token, Callback<ProspectsResponse> ProspectsResponseCallback){
        getAPI().getProspects().enqueue(ProspectsResponseCallback);
    }
}
