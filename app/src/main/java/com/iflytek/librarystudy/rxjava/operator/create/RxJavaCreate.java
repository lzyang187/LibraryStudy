package com.iflytek.librarystudy.rxjava.operator.create;

import com.iflytek.librarystudy.rxjava.operator.observer.BaseJavaConsumer;
import com.iflytek.librarystudy.rxjava.operator.observer.BaseJavaObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author: cyli8
 * @date: 2019-09-17 14:41
 */
public class RxJavaCreate {

    public static void main(String[] args) {
        Observable<Integer> createObervable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
                emitter.onNext(3);
//                emitter.onError(new NullPointerException("空指针异常"));
            }
        });
        //订阅所有事件
        createObervable.subscribe(new BaseJavaObserver<Integer>("订阅所有事件"));

        //订阅单个类型事件
        Disposable disposable = createObervable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Consumer accept :" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Consumer accept onError：" + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Consumer action：run");
            }
        });

        // just操作符
        Observable.just(4, 5).subscribe(new BaseJavaObserver<Integer>("just符"));

        // empty：创建一个什么都不做直接通知完成的Observable
        Observable.empty().subscribe(new BaseJavaObserver<>("empty符"));

        // error：创建一个什么都不做直接通知错误的Observable
        Observable.error(new IllegalArgumentException("直接通知错误")).subscribe(new BaseJavaObserver<>("error符"));

        // never： 创建一个什么都不做的Observable
        Observable.never().subscribe(new BaseJavaObserver<>("never符"));

        // fromArray
        Observable.fromArray("a", "b").subscribe(new BaseJavaObserver<String>("fromArray符"));

        // fromIterable操作符
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(11);
        Disposable disposable1 = Observable.fromIterable(intList).subscribe(new BaseJavaConsumer<Integer>("fromIterable符"));

    }

}
