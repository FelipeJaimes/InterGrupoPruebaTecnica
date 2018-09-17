package com.example.android.intergrupopruebatecnica.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.intergrupopruebatecnica.Application;
import com.example.android.intergrupopruebatecnica.data.local.Database;
import com.example.android.intergrupopruebatecnica.data.remote.ApiConnection;
import com.example.android.intergrupopruebatecnica.presenter.BasePresenter;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {
    protected Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        presenter = createPresenter();
        presenter.init();
    }

    @Override
    protected void onPause() {
        presenter.disposeSubscriptions();
        super.onPause();
    }


    public Database getDatabase() {
        return ((Application)getApplication()).getDatabase();
    }

    public ApiConnection getApiConnection() {
        return ((Application)getApplication()).getApiConnection();
    }

    public abstract Presenter createPresenter();

    public abstract int getLayout();
}