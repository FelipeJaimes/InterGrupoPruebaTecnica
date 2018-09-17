package com.example.android.intergrupopruebatecnica.presenter;

import com.example.android.intergrupopruebatecnica.model.MenuModel;
import com.example.android.intergrupopruebatecnica.view.contract.MenuView;

import io.reactivex.observers.DisposableCompletableObserver;

public class MenuPresenter extends BasePresenter<MenuView> {

    private MenuModel model;

    public MenuPresenter(MenuView menuView, MenuModel model) {
        super(menuView);
        this.model = model;
    }

    @Override
    public void init() {

    }

    public void logout() {
        addSubscription(model.clearUserSession().subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                doLogout();
            }

            @Override
            public void onError(Throwable e) {
                doLogout();
            }
        }));
    }

    private void doLogout() {
        model.setIsUserLogged(false);
        view.goToLogin();
    }
}
