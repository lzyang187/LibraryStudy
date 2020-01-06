package com.iflytek.librarystudy.rxjava.operator.filter;

import com.iflytek.librarystudy.rxjava.operator.observer.BaseJavaObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 过滤操作符
 *
 * @author: cyli8
 * @date: 2019/2/18 11:16
 */
public class RxJavaFilter {
    public static void main(String[] args) {
        //filter： 过滤数据。内部通过OnSubscribeFilter过滤数据
        Disposable disposable = Observable.just(4, 5, 6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer >= 5;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("filter accept：" + integer);
            }
        });

        //ofType： 过滤指定类型的数据，与filter类似，
        Disposable disposable1 = Observable.just(1, 2, "3", "4").ofType(Integer.class).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("ofType accept：" + integer);
            }
        });

        //take： 只发射开始的N项数据或者一定时间内的数据。内部通过OperatorTake和OperatorTakeTimed过滤数据。
        Disposable disposable2 = Observable
                .just(3, 4, 5, 6)
                .take(2)//发射前2个数据
                .take(1, TimeUnit.SECONDS)//发射1s内的数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("take accept：" + integer);
                    }
                });
        //takeLast：
        Disposable disposable3 = Observable.just(1, 2, 3, 4).takeLast(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("takeLast accept：" + integer);
            }
        });

        //takeUntil：
        Observable<Integer> justObservable = Observable.just(1, 2, 3);
        Disposable disposable4 = justObservable.takeUntil(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer < 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("takeUntil accept：" + integer);
            }
        });

        //firstElement/lastElement/elementAt
        Disposable subscribe = justObservable.elementAt(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("elementAt(1) accept：" + integer);
            }
        });

        //first：只发射第一项（或者满足某个条件的第一项）数据，可以指定默认值
        Disposable disposable5 = justObservable.first(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("first accept：" + integer);
            }
        });
        //last：只发射最后一项数据，可以指定默认值
        Disposable disposable6 = justObservable.last(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("last accept：" + integer);
            }
        });

        //skip：跳过开始的N项数据或者一定时间内的数据。内部通过OperatorSkip和OperatorSkipTimed实现过滤。
        //skipLast：跳过最后的N项数据或者一定时间内的数据。内部通过OperatorSkipLast和OperatorSkipLastTimed实现过滤。
        Disposable disposable7 = justObservable
                .skip(1)// 跳过正序的前1项
                .skipLast(1)// 跳过正序的后1项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("skip accept：" + integer);
                    }
                });

        //distinct：过滤重复数据，内部通过OperatorDistinct实现。
        Disposable disposable8 = Observable.just(1, 2, 3, 3, 2).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("distinct accept：" + integer);
            }
        });
        //distinctUntilChanged：过滤事件序列中 连续重复的事件
        Disposable disposable9 = Observable.just(1, 1, 2, 1).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("distinctUntilChanged accept：" + integer);
            }
        });

        //timeout： 如果原始Observable过了指定的一段时长没有发射任何数据，就发射一个异常或者使用备用的Observable。
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(1000);
                emitter.onNext(1);
            }
        }).timeout(900, TimeUnit.MICROSECONDS).subscribe(new BaseJavaObserver<>("timeout符"));

        Observable<Integer> sleepObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(600);
                emitter.onNext(3);
            }
        });
        //throttleFirst/ throttleLast：在某段时间内，只发送该段时间内第1次事件 / 最后1次事件
        Disposable disposable10 = sleepObservable.throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("throttleFirst accept：" + integer);
            }
        });
        Disposable disposable11 = sleepObservable.throttleLast(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("throttleLast accept：" + integer);
            }
        });

        //debounce：
        Disposable disposable12 = sleepObservable.debounce(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("debounce accept：" + integer);
            }
        });

    }
}
