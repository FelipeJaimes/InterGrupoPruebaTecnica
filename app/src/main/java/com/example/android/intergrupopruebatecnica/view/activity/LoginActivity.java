package com.example.android.intergrupopruebatecnica.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.data.local.access.UserLocal;
import com.example.android.intergrupopruebatecnica.data.preference.access.UserPreferences;
import com.example.android.intergrupopruebatecnica.model.LoginModel;
import com.example.android.intergrupopruebatecnica.presenter.LoginPresenter;
import com.example.android.intergrupopruebatecnica.view.base.BaseActivity;
import com.example.android.intergrupopruebatecnica.view.contract.LoginView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.login_inputLayoutEmail) TextInputLayout inputLayoutEmail;
    @BindView(R.id.login_editMail) TextInputEditText editMail;
    @BindView(R.id.login_editPassword) EditText editPassword;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.login_progress) ProgressBar progressLoading;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(
                new LoginModel(getApiConnection().getLoginRemote(),
                        new UserPreferences(this),
                        new UserLocal(getDatabase())),
                this
        );
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.login_button)
    void onLoginButtonClick() {
        progressLoading.setVisibility(View.VISIBLE);
        presenter.doLogin(editMail.getText().toString(), editPassword.getText().toString());
    }

    @Override
    public void showLoginError() {
        progressLoading.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Wrong mail or password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMenu() {
        progressLoading.setVisibility(View.GONE);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @OnFocusChange(R.id.login_editMail)
    void onEmailFocusChange(boolean hasFocus) {
        presenter.onEmailFocusChanged(editMail.getText().toString(), hasFocus);
    }

    @Override
    public void showEmailError() {
        inputLayoutEmail.setError("email is wrong");
        inputLayoutEmail.setErrorEnabled(true);
        loginButton.setEnabled(false);
    }

    @Override
    public void hideEmailError() {
        inputLayoutEmail.setErrorEnabled(false);
        loginButton.setEnabled(true);
    }

}
