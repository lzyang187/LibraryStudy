package com.iflytek.librarystudy.rxjava.operator.filter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.rxjava.operator.RxJavaUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UseFilterActivity extends AppCompatActivity {
    private static final String TAG = "UseFilterActivity";
    private int count = 0;
    private ObservableEmitter<Integer> emitter;
    private ObservableEmitter<String> stringEmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_filter);
        //throttleFirst：防止按钮2s内多次点击重复执行点击事件
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                        UseFilterActivity.this.emitter = emitter;
                    }
                })
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(RxJavaUtil.<Integer>rxScheduler(Schedulers.newThread()))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "按钮点击 accept: " + integer);
                    }
                });

        View throttleFirstView = findViewById(R.id.throttleFirst);
        throttleFirstView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                emitter.onNext(count);
            }
        });

        //搜索联想词
        //debounce: 接收指定时间内最后一次发送的事件
        Disposable disposable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                stringEmitter = emitter;
            }
        }).debounce(1, TimeUnit.SECONDS)
                .compose(RxJavaUtil.<String>rxScheduler(Schedulers.newThread()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "输入框文本变化: " + s);
                    }
                });

        EditText editText = findViewById(R.id.edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "发送输入框文本变化: " + s);
                stringEmitter.onNext(s.toString());
            }
        });

    }

}
