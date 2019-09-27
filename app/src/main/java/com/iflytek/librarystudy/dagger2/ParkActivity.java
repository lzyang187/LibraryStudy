package com.iflytek.librarystudy.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.iflytek.librarystudy.R;

import javax.inject.Inject;

public class ParkActivity extends AppCompatActivity {
    private static final String TAG = "ParkActivity";

    @Sign
    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ParkComponent component = DaggerParkComponent.builder().parkModule(new ParkModule("王二", 12)).build();
        component.inject(this);
        ((TextView) findViewById(R.id.tv)).setText(bus.toString());
        Log.i(TAG, bus.getClass().getName() + "@" + Integer.toHexString(bus.hashCode()));
        //第二次注入
        component.inject(this);
        Log.i(TAG, bus.getClass().getName() + "@" + Integer.toHexString(bus.hashCode()));


    }

}
