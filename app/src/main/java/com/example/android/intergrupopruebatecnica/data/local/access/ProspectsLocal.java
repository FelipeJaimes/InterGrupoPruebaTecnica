package com.example.android.intergrupopruebatecnica.data.local.access;

import com.example.android.intergrupopruebatecnica.data.local.Database;
import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.repository.ProspectDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

public class ProspectsLocal extends BaseLocalAccess implements ProspectDataSource {

    public ProspectsLocal(Database database) {
        super(database);
    }

    @Override
    public Observable<Prospect> addProspect(Prospect prospect) {
        return toObservable(prospect, () -> database.prospectDAO().insertProspect(prospect));
    }

    @Override
    public Observable<Prospect> getProspectById(int id) {
        return toObservable(empty -> database.prospectDAO().fetchOneProspectByProspectId(id));
    }

    @Override
    public Observable<List<Prospect>> getProspects() {
        return toObservable(empty -> database.prospectDAO().fetchAllProspects());
    }

    @Override
    public Observable<Prospect> editProspect(Prospect prospect) {
        return toObservable(prospect, () -> database.prospectDAO().updateProspect(prospect));
    }

    @Override
    public Observable<Prospect> removeProspect(Prospect prospect) {
        return toObservable(prospect, () -> database.prospectDAO().deleteProspect(prospect));
    }

    public Observable<List<Prospect>> refreshAllProspects(List<Prospect> prospects) {
        return toObservable(prospects, () -> {
            database.prospectDAO().deleteAll();
            database.prospectDAO().insertAllProspects(prospects);
        });

    }
}
