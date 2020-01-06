package com.iflytek.librarystudy.rxjava.operator.observer;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: cyli8
 * @date: 2019-10-08 16:35
 */
public class BaseAndroidObserver<T> implements Observer<T> {
    private String TAG;

    public BaseAndroidObserver(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe");
    }

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
