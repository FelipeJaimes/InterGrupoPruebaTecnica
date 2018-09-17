package com.example.android.intergrupopruebatecnica.data.repository;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ProspectDataSource {
    Observable<Prospect> addProspect(Prospect prospect);
    Observable<Prospect> getProspectById(int id);
    Observable<List<Prospect>> getProspects();
    Observable<Prospect> editProspect(Prospect prospect);
    Observable<Prospect> removeProspect(Prospect prospect);
}
