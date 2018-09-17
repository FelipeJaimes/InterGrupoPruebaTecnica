package com.example.android.intergrupopruebatecnica.data.local.access;

import com.example.android.intergrupopruebatecnica.data.local.Database;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

public class BaseLocalAccess {

    Database database;

    BaseLocalAccess(Database database) {
        this.database = database;
    }


    <Return> Observable<Return> toObservable(Return toReturn, Action action) {
        return Observable.create(emitter -> {
            try {
                action.run();
                emitter.onNext(toReturn);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    Completable toCompletable(Action action) {
        return Completable.create(emitter -> {
            try {
                action.run();
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    <T> Observable<T> toObservable(Function<Void, T> function) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(function.apply(null));
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


}
