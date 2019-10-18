package com.iflytek.librarystudy.rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author: cyli8
 * @date: 2019-10-08 15:20
 */
public class RxJavaUtil {

    public static <T> ObservableTransformer<T, T> rxScheduler(final Scheduler scheduler) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
