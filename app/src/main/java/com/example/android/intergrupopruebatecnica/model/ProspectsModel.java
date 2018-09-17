package com.example.android.intergrupopruebatecnica.model;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.repository.ProspectRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProspectsModel {

    ProspectRepository prospectRepository;

    public ProspectsModel(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    public Observable<List<Prospect>> getProspects() {
        return prospectRepository.getProspects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
