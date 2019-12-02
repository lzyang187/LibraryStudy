package com.iflytek.librarystudy.rxjava.operator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: cyli8
 * @date: 2019-09-17 14:41
 */
public class RxJavaCreate {
    private static final String TAG = "RxJavaCreate";

    public static void main(String[] args) {
        Observable createObervable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
//                emitter.onComplete();
//                emitter.onNext(3);
//                emitter.onError(new NullPointerException("空指针异常"));
            }
        });
//        createObervable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe：" + d.isDisposed());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("onNext：" + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError：" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });
//        createObervable.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println("accept：" + o);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                System.out.println("accept onError：" + throwable.getMessage());
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                System.out.println("action：run");
//            }
//        });
//
//        Observable arrayObservable = Observable.fromArray(new String[]{"a", "b", "c"});
//        arrayObservable.subscribe(new Consumer() {
//            @Override
//            public void accept(Object s) throws Exception {
//                System.out.println("accept：" + s);
//            }
//        });

        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("timer onNext" + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("timer onComplete");
            }
        });

    }

}
