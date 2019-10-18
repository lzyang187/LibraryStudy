package com.iflytek.librarystudy.rxjava.operator;

import android.util.Log;

import io.reactivex.observers.ResourceObserver;

/**
 * @author: cyli8
 * @date: 2019-10-08 16:35
 */
public class BaseObservable<T> extends ResourceObserver<T> {
    private static final String TAG = "BaseObservable";
    @Override
    public void onNext(T t) {
        Log.e(TAG, "onNext: " + t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ");
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }
}
