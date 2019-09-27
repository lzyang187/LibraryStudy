package com.iflytek.librarystudy.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: cyli8
 * @date: 2018/10/10 11:44
 */
public class CustomView extends View {
    private Layout layout;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the layout.
        layout.draw(canvas);
    }
}
