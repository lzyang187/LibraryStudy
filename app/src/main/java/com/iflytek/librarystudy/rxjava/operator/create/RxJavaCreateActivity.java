package com.iflytek.librarystudy.rxjava.operator.create;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.rxjava.operator.observer.BaseAndroidObserver;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

public class RxJavaCreateActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaCreateActivity";
    private int defer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        //简洁的链式调用
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("apple");
                emitter.onNext("banana");
                int a = 3 / 0;
                emitter.onError(new IllegalArgumentException("抛出异常"));
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        /**
         * interval操作符：发送事件的特点：每隔指定时间就发送事件
         * 延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
         */
        Observable.interval(2, 1, TimeUnit.SECONDS).subscribe(new BaseAndroidObserver<>("interval符"));

        /**
         * intervalRange
         * 1. 从3开始，一共发送2个事件；
         * 2. 第1次延迟2s发送，之后每隔1秒产生1个数字
         */
        Observable.intervalRange(3, 2, 2, 1, TimeUnit.SECONDS).subscribe(new BaseAndroidObserver<>("intervalRange"));

        /**
         * range操作符：从3开始，一共发送2个事件
         */
        Observable.range(3, 2).subscribe(new BaseAndroidObserver<>("range"));

        /**
         * timer：延迟指定时间，发送一个0，一般用于检测
         */
        Observable.timer(3, TimeUnit.SECONDS).subscribe(new BaseAndroidObserver<>("timer符"));

        /**
         * defer:直到有观察者（Observer）订阅时，才动态创建被观察者对象（Observable） & 发送事件
         */
        Observable<Integer> deferObservable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() {
                return Observable.just(RxJavaCreateActivity.this.defer);
            }
        });
        this.defer = 2;
        //此时收到的值是2
        deferObservable.subscribe(new BaseAndroidObserver<Integer>("defer符"));

    }
}
