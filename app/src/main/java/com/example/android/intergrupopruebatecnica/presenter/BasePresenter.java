package com.example.android.intergrupopruebatecnica.presenter;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<View> {

    View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    
    public BasePresenter(View view) {
        this.view = view;
    }

    public abstract void init();

    public void disposeSubscriptions() {
        compositeDisposable.clear();
    }

    public void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
