package com.iflytek.librarystudy.rxjava.operator.condition;

import com.iflytek.librarystudy.rxjava.operator.observer.BaseJavaObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 条件操作符
 *
 * @author: cyli8
 * @date: 2019/2/18 16:50
 */
public class RxJavaCondition {
    public static void main(String[] args) {
        //all：
        Disposable subscribe = Observable.just(1, 2, 3, 4).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 3;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("all accept：" + aBoolean);
            }
        });

        //contains： 判断是否存在数据项满足某个条件。内部通过OperatorAny实现
        Disposable subscribe1 = Observable.just(1, 3, 5).contains(2).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("contains accept：" + aBoolean);
            }
        });

        //sequenceEqual： 用于判断两个Observable发射的数据是否相同（数据，发射顺序，终止状态）。
        Disposable subscribe2 = Observable.sequenceEqual(Observable.just(1, 2), Observable.just(1, 2)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("sequenceEqual accept：" + aBoolean);
            }
        });

        //isEmpty： 用于判断Observable发射完毕时，有没有发射数据。有数据false，如果只收到了onComplete通知则为true。
        Disposable subscribe3 = Observable.empty().isEmpty().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("isEmpty accept：" + aBoolean);
            }
        });

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(1000);
                emitter.onNext(1);
            }
        });
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(2);
            }
        });
        //ambArray：给定多个Observable，只让第一个发射数据的Observable发射全部数据，其他Observable将会被忽略。
        Observable.ambArray(observable, observable1).subscribe(new BaseJavaObserver<>("ambArray"));

        //switchIfEmpty：如果原始Observable正常终止后仍然没有发射任何数据，就使用备用的Observable。
        Observable.empty().switchIfEmpty(Observable.just(1, 2)).subscribe(new BaseJavaObserver<>("switchIfEmpty"));

    }

}
