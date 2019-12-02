package com.iflytek.librarystudy.rxjava.operator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaCreateActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaCreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        /**
         * 一、create操作符：完整创建1个被观察者对象（Observable）
         */
        //1、创建被观察者Observable对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //2、通过emitter对象产生事件，并通知观察者
                emitter.onNext(1);
                emitter.onError(new NullPointerException("异常了"));
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        //3、创建观察者Observer
        final Observer<Integer> observer = new Observer<Integer>() {
            //可以使用Disposable切断观察者和被观察者之间的连接
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: 开始采用subscribe连接");
//                d.dispose();
                Log.d(TAG, "onSubscribe: 是否切断了连接" + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        //4、通过订阅subscribe连接观察者和被观察者
        //表示观察者对被观察者发送的任何事件都作出响应
//        observable.subscribe(new BaseObservable<Integer>() {
//            @Override
//            public void onNext(Integer integer) {
//                super.onNext(integer);
//                Log.e(TAG, "onNext: ");
//            }
//        });

        BaseObservable<Integer> baseObservable = observable.subscribeWith(new BaseObservable<Integer>() {
            @Override
            public void onNext(Integer integer) {
                super.onNext(integer);
                Log.e(TAG, "onNext: " + integer);
            }
        });
        baseObservable.onNext(3);

        List<Wrapper<Integer>> wrappers = new ArrayList<>();
        wrappers.add(new Wrapper<Integer>(1));
        wrappers.add(new Wrapper<Integer>(2));
        wrappers.add(new Wrapper<Integer>(3));
        wrappers.add(new Wrapper<Integer>(4));
        wrappers.add(new Wrapper<Integer>(5));
        Observable iterable = Observable.fromIterable(wrappers);
        iterable.flatMap(new Function<Wrapper<Integer>, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(final Wrapper<Integer> integerWrapper) throws Exception {
                Log.e(TAG, "apply: " + Thread.currentThread().getName());
                int delay = 0;
                if (integerWrapper.data == 2) {
                    delay = 500;
                }
                return Observable.just(integerWrapper.data + "sss").delay(delay, TimeUnit.MICROSECONDS);
            }
        })
                .compose(RxJavaUtil.rxScheduler(Schedulers.io()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept: " + Thread.currentThread().getName());
                        Log.e(TAG, "accept: flatMap = " + s);
                    }
                });

        iterable.concatMap(new Function<Wrapper<Integer>, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(final Wrapper<Integer> integerWrapper) throws Exception {
                int delay = 0;
                if (integerWrapper.data == 2) {
                    delay = 2000;
                }
                return Observable.just(integerWrapper.data + "sss").delay(delay, TimeUnit.MICROSECONDS);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("concatMap= " + s);
                    }
                });

        //表示观察者只对被观察者发送的Next事件作出响应
//        Disposable subscribe = observable.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d(TAG, "accept: " + integer);
//            }
//        });
//
//        //简洁的链式调用
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("apple");
//                emitter.onNext("banana");
//                emitter.onError(new IllegalArgumentException("抛出异常"));
//                emitter.onComplete();
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG, "onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });
//
//        /**
//         * 二、just操作符：
//         * 快速创建1个被观察者对象（Observable）
//         * 发送事件的特点：直接发送 传入的事件
//         */
//        Disposable disposable = Observable.just(4, 5, 6).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d(TAG, "just accept: " + integer);
//            }
//        });
//
//        /**
//         * empty：创建一个什么都不做直接通知完成的Observable
//         */
//        Observable.empty().subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "empty onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.d(TAG, "empty onNext: " + o);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "empty onComplete: ");
//            }
//        });
//
//        /**
//         * error：创建一个什么都不做直接通知错误的Observable
//         */
//        Observable.error(new IllegalArgumentException("直接通知错误")).subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "error onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.d(TAG, "error onNext: " + o);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "error onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "error onComplete: ");
//            }
//        });
//
//        /**
//         * never： 创建一个什么都不做的Observable
//         */
//        Observable.never().subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "never onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.d(TAG, "never onNext: " + o);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "never onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "never onComplete: ");
//            }
//        });
//
//        /**
//         * 三、fromArray操作符
//         * 快速创建1个被观察者对象（Observable）
//         * 发送事件的特点：直接发送 传入的数组数据
//         */
//        Integer[] intArray = new Integer[]{7, 8, 9};
//        Disposable disposable1 = Observable.fromArray(intArray).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d(TAG, "accept: " + integer);
//            }
//        });
//
//        /**
//         * 四、fromIterable操作符
//         * 快速创建1个被观察者对象（Observable）
//         * 发送事件的特点：直接发送 传入的集合List数据
//         */
//        List<Integer> intList = new ArrayList<>();
//        intList.add(10);
//        intList.add(11);
//        intList.add(12);
//        Disposable disposable2 = Observable.fromIterable(intList).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d(TAG, "accept: " + integer);
//            }
//        });
//
//        /**
//         * interval操作符：发送事件的特点：每隔指定时间就发送事件
//         * 延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
//         */
//        Observable.interval(3, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                Log.d(TAG, "onNext: " + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });
//
//        /**
//         * intervalRange
//         * 1. 从3开始，一共发送10个事件；
//         * 2. 第1次延迟2s发送，之后每隔1秒产生1个数字
//         */
//        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                Log.d(TAG, "onNext: " + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });
//
//        /**
//         * range操作符
//         */
//        Observable.range(3, 10).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext: " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });
//
//        /**
//         * timer：创建一个在给定的延时之后
//         */
        Disposable disposable3 = Observable.interval(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "timer accept: " + aLong);
            }
        });
//
//        Disposable disposable4 = Observable.defer(new Callable<ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> call() throws Exception {
//                return Observable.just(1);
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.d(TAG, "defer accept: " + o);
//            }
//        });
    }
}
