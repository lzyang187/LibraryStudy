package com.iflytek.librarystudy.rxjava.use;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iflytek.librarystudy.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IntervalActivity extends AppCompatActivity {
    private static final String TAG = "IntervalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        Disposable disposable = Observable.interval(2, 3, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() { //每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "第" + aLong + "次轮询");
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://fy.iciba.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                        IGetRequest request = retrofit.create(IGetRequest.class);
                        Observable<CBTranslation> observable = request.getCall();
                        observable.subscribeOn(Schedulers.io())//切换到io线程进行网络请求
                                .observeOn(AndroidSchedulers.mainThread())//切换到主线程处理请求结果
                                .subscribe(new Observer<CBTranslation>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(CBTranslation cbTranslation) {
                                        cbTranslation.showInfo(TAG);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "请求失败");
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + aLong);
                    }
                });
    }
}
