package com.iflytek.librarystudy.dagger2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.dagger2.bean.Person;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {
    private static final String TAG = "Dagger2Activity";

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        //第1种初始化
        DaggerDagger2ActivityComponent.create().injectTo(this);
        //第2种初始化
//        DaggerDagger2ActivityComponent.builder().build().injectTo(this);
        Log.i(TAG, "person: " + person.name);

    }
}
