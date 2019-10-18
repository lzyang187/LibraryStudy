package com.iflytek.librarystudy.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;
import com.iflytek.librarystudy.R;

public class TextLayoutBuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_layout_builder);
        CustomView customView = findViewById(R.id.cv);
        TextLayoutBuilder builder = new TextLayoutBuilder()
                .setText("TextLayoutBuilder makes life easy")
                .setTextColor(Color.BLUE)
                .setLineHeight(600)
                .setWidth(600 /*, MEASURE_MODE_EXACTLY */);
        Layout layout = builder.build();
        customView.setLayout(layout);
    }
}
