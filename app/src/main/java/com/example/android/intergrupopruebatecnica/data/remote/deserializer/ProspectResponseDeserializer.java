package com.example.android.intergrupopruebatecnica.data.remote.deserializer;

import com.example.android.intergrupopruebatecnica.data.remote.response.ProspectResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ProspectResponseDeserializer {

    private ProspectResponseDeserializer() {

    }

    public static List<ProspectResponse> deserialize(Map<String, ProspectResponse> response) {
        List<ProspectResponse> responseList = new ArrayList<>(response.values());
        List<String> keySet = new ArrayList<>(response.keySet());
        for (int i = 0; i < responseList.size(); i++) {
            responseList.get(i).setObjectId(keySet.get(i));
        }
        return responseList;
    }
}
