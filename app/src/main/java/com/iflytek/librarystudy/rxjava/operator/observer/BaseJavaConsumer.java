package com.iflytek.librarystudy.rxjava.operator.observer;

import io.reactivex.functions.Consumer;

/**
 * @author: cyli8
 * @date: 2019-12-11 14:33
 */
public class BaseJavaConsumer<T> implements Consumer<T> {

    private String TAG;

    public BaseJavaConsumer(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void accept(T t) throws Exception {
        System.out.println(TAG + " accept=" + t);
    }
}
