package com.iflytek.librarystudy.fresco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iflytek.librarystudy.R;

public class FrescoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco);
        SimpleDraweeView sdv = findViewById(R.id.my_image_view);

    }
}
