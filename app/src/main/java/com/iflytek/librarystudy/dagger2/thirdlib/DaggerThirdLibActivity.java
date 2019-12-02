package com.iflytek.librarystudy.dagger2.thirdlib;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.dagger2.thirdlib.bean.Human;
import com.iflytek.librarystudy.dagger2.thirdlib.bean.Soul;
import com.iflytek.librarystudy.dagger2.thirdlib.bean.Woman;

import javax.inject.Inject;

public class DaggerThirdLibActivity extends AppCompatActivity {
    private static final String TAG = "DaggerThirdLibActivity";
    @Inject
    Human human;
    @Inject
    Woman woman;
    @Inject
    Soul soul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_third_lib);
        DaggerDaggerThirdLibComponent
                .builder()
                .daggerThirdLibActivityModule(new DaggerThirdLibActivityModule(100))
                .build()
                .injectTo(this);

        Log.i(TAG, "human: " + human.name);
        Log.i(TAG, "woman: " + woman.soul.money);
        Log.i(TAG, "soul: " + soul.money);
    }
}
