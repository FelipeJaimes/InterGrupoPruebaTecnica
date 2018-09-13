package com.example.android.intergrupopruebatecnica.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.dispose();
    }
}