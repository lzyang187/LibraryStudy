package com.iflytek.librarystudy.rxjava.operator.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: cyli8
 * @date: 2019-12-11 14:08
 */
public class BaseJavaObserver<T> implements Observer<T> {

    private String TAG;

    public BaseJavaObserver(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println(TAG + " onSubscribe");
    }

    @Override
    public void onNext(T t) {
        System.out.println(TAG + " onNext=" + t);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println(TAG + " onError=" + e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(TAG + " onComplete");
    }
}
