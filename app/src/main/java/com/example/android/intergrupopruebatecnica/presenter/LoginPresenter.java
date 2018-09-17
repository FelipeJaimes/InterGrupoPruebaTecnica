package com.example.android.intergrupopruebatecnica.presenter;

import android.util.Patterns;

import com.example.android.intergrupopruebatecnica.data.local.entity.User;
import com.example.android.intergrupopruebatecnica.model.LoginModel;
import com.example.android.intergrupopruebatecnica.utils.AESCrypt;
import com.example.android.intergrupopruebatecnica.view.contract.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel model;

    public LoginPresenter(LoginModel model, LoginView view) {
        super(view);
        this.model = model;
    }

    @Override
    public void init() {
        checkAutoLogin();
    }

    private void checkAutoLogin() {
        if (model.isUserLogged()) {
            view.goToMenu();
        }
    }

    public void doLogin(String mail, String password) {
        addSubscription(model.doLogin(mail, password)
                .flatMapCompletable(loginResponse -> {
                    User user = getUser(mail, password, loginResponse.getAuthToken());
                    model.setIsLogged(true);
                    return model.saveUser(user);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.goToMenu();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showLoginError();
                    }
                }));
    }

    private User getUser(String mail, String password, String token) throws Exception {
        User user = new User();
        user.setEmail(AESCrypt.encrypt(mail));
        user.setPassword(AESCrypt.encrypt(password));
        user.setToken(AESCrypt.encrypt(token));
        return user;
    }

    public void onEmailFocusChanged(String text, boolean hasFocus) {
        boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(text).matches();
        if (hasFocus || isValidEmail) {
            view.hideEmailError();
        } else {
            view.showEmailError();
        }
    }

}
