package com.iflytek.librarystudy.rxjava.operator.map;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.rxjava.operator.RxJavaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaMapActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_map);
        final Observable<Integer> observable = Observable.just(1, 2, 3);
        //map操作符：对被观察者发送的每1个事件都通过 指定的函数 处理，从而变换成另外一种事件
        Disposable disposable = observable
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf("字符串：" + integer);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "map accept=" + s);
                    }
                });

        //FlatMap操作符：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
        Disposable disposable1 = observable
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        // 通过flatMap中将被观察者生产的事件序列先进行拆分
                        final List<String> list = new ArrayList<>();
                        // 再将每个事件转换为一个新的发送三个String事件
                        for (int i = 0; i < 3; i++) {
                            String s = "flatMap事件" + integer + "拆分后的子事件" + i;
                            Log.e(TAG, "插入：" + s);
                            list.add(s);
                        }
                        if (integer == 2) {
                            return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
                        }
                        // 最终合并，再发送给被观察者
                        return Observable.fromIterable(list);
                    }
                })
                .compose(RxJavaUtil.<String>rxScheduler(Schedulers.newThread()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "flatMap accept=" + s);
                    }
                });

        //ConcatMap操作符：与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列的顺序 = 被观察者旧序列生产的顺序
        Disposable disposable2 = observable
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        // 通过flatMap中将被观察者生产的事件序列先进行拆分
                        final List<String> list = new ArrayList<>();
                        // 再将每个事件转换为一个新的发送三个String事件
                        for (int i = 0; i < 3; i++) {
                            String s = "concatMap事件" + integer + "拆分后的子事件" + i;
                            Log.e(TAG, "插入：" + s);
                            list.add(s);
                        }
                        if (integer == 2) {
                            return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
                        }
                        // 最终合并，再发送给被观察者
                        return Observable.fromIterable(list);
                    }
                })
                .compose(RxJavaUtil.<String>rxScheduler(Schedulers.newThread()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "concatMap accept=" + s);
                    }
                });
    }
}
