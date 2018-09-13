package com.example.android.intergrupopruebatecnica.rest.response;

import com.example.android.intergrupopruebatecnica.model.Prospect;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ProspectsResponse {

    @Expose
    private List<Prospect> prospects;

    public List<Prospect> getProspects() {
        return prospects;
    }

    public void setProspects(List<Prospect> prospects) {
        this.prospects = prospects;
    }
}