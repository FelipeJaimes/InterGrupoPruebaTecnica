package com.example.android.intergrupopruebatecnica.data.repository;


import com.example.android.intergrupopruebatecnica.data.local.access.ProspectsLocal;
import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.mapper.ProspectMapper;
import com.example.android.intergrupopruebatecnica.data.remote.access.ProspectsRemote;

import java.util.List;

import io.reactivex.Observable;

public class ProspectRepository implements ProspectDataSource{
    private ProspectsLocal prospectsLocal;
    private ProspectsRemote prospectsRemote;
    private ProspectMapper mapper = new ProspectMapper();

    public ProspectRepository(ProspectsLocal prospectsLocal, ProspectsRemote prospectRemote) {
        this.prospectsLocal = prospectsLocal;
        this.prospectsRemote = prospectRemote;
    }


    public Observable<Prospect> addProspect(Prospect prospect) {
        return prospectsRemote.addProspect(prospect)
                .flatMap(response -> prospectsLocal.addProspect(prospect));
    }

    public Observable<Prospect> getProspectById(int id) {
        return prospectsRemote.getProspect(id)
                .map(mapper::transform)
                .flatMap(Observable::just)
                .onErrorResumeNext(prospectsLocal.getProspectById(id));
    }

    @Override
    public Observable<List<Prospect>> getProspects() {
        return prospectsRemote.getProspects()
                .map(mapper::transform)
                .flatMap(prospectsLocal::refreshAllProspects)
                .onErrorResumeNext(prospectsLocal.getProspects());
    }

    @Override
    public Observable<Prospect> editProspect(Prospect prospect) {
        return prospectsRemote.editProspect(prospect)
                .map(mapper::transform)
                .flatMap(prospectsLocal::editProspect);
    }

    @Override
    public Observable<Prospect> removeProspect(Prospect prospect) {
        return prospectsRemote.removeProspect(prospect)
                .map(mapper::transform)
                .flatMap(prospectsLocal::removeProspect);
    }

}
