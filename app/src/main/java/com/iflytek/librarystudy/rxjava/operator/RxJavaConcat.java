package com.iflytek.librarystudy.rxjava.operator;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * 合并类操作符
 *
 * @author: cyli8
 * @date: 2019/2/18 10:46
 */
public class RxJavaConcat {

    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
        Observable<Float> observable2 = Observable.just(3.f, 4.f);
        //concat按顺序连接多个Observables
        Observable.concat(observable1, observable2).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                System.out.println("onNext:" + serializable);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        //startWith： 在数据序列的开头增加一项数据。startWith的内部也是调用了concat
        Disposable disposable = Observable.just(1, 2, 3).startWith(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("startWith accept：" + integer);
            }
        });

        //merge：将多个Observable合并为一个。不同于concat，merge不是按照添加顺序连接，而是按照时间线来连接
        //其中mergeDelayError将异常延迟到其它没有错误的Observable发送完毕后才发射。而merge则是一遇到异常将停止发射数据，发送onError通知。
        Disposable disposable1 = Observable.concat(observable1, observable2).subscribe(new Consumer<Number>() {
            @Override
            public void accept(Number number) throws Exception {
                System.out.println("merge accept: " + number);
            }
        });

        //zip： 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。
        //如果多个Observable发射的数据量不一样，则以最少的Observable为标准进行压合。内部通过OperatorZip进行压合。
        Observable<Integer> observable3 = Observable.just(4, 5, 6);
        Disposable disposable2 = Observable.zip(observable1, observable3, new BiFunction<Integer, Integer, Object>() {
            @Override
            public Object apply(Integer integer, Integer integer2) throws Exception {
                return integer + "&" + integer2;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("zip accept：" + o);
            }
        });



    }
}
