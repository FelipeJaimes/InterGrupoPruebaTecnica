package com.example.android.intergrupopruebatecnica.presenter;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.model.ProspectsModel;
import com.example.android.intergrupopruebatecnica.view.contract.ProspectsView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ProspectsPresenter extends BasePresenter<ProspectsView>{

    private ProspectsModel model;

    public ProspectsPresenter(ProspectsView prospectsView, ProspectsModel model) {
        super(prospectsView);
        this.model = model;
    }

    @Override
    public void init() {
        showProspects();
    }

    private void showProspects() {
        addSubscription(model.getProspects()
                .subscribeWith(new DisposableObserver<List<Prospect>>() {
            @Override
            public void onNext(List<Prospect> prospects) {
                view.showItemsOnList(prospects);
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();

            }

            @Override
            public void onComplete() {

            }
        }));

    }

    public void addNew(){
        view.goToAdd();
    }
}
