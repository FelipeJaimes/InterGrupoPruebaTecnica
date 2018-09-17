package com.example.android.intergrupopruebatecnica.data.remote.access;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.remote.api.ProspectsAPI;
import com.example.android.intergrupopruebatecnica.data.remote.deserializer.ProspectResponseDeserializer;
import com.example.android.intergrupopruebatecnica.data.remote.response.DefaultResponse;
import com.example.android.intergrupopruebatecnica.data.remote.response.ProspectResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ProspectsRemote extends BaseRemoteAccess<ProspectsAPI> {

    public ProspectsRemote(ProspectsAPI prospectsAPI) {
        super(prospectsAPI);
    }

    public Observable<List<ProspectResponse>> getProspects(){
        return getAPI().getProspects()
                .map(ProspectResponseDeserializer::deserialize)
                .flatMap(Observable::just);
    }

    public Observable<ProspectResponse> getProspect(int id){
        return getAPI().getProspect(id);
    }

    public Observable<ProspectResponse> addProspect(Prospect prospect) {
        return Observable.empty();
    }

    public Observable<ProspectResponse> editProspect(Prospect prospect) {
        return Observable.empty();
    }

    public Observable<ProspectResponse> removeProspect(Prospect prospect) {
        return Observable.empty();
    }
}
