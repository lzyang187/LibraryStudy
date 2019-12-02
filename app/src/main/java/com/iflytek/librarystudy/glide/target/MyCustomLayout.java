package com.iflytek.librarystudy.glide.target;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author: cyli8
 * @date: 2019-11-29 11:14
 */
public class MyCustomLayout extends RelativeLayout {
    private CustomViewTarget<MyCustomLayout, Drawable> target;

    public MyCustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        target = new CustomViewTarget<MyCustomLayout, Drawable>(this) {
            @Override
            protected void onResourceCleared(@Nullable Drawable placeholder) {
                if (placeholder != null) {
                    setBackground(placeholder);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (errorDrawable != null) {
                    setBackground(errorDrawable);
                }
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                setBackground(resource);
            }
        };
    }


    public CustomViewTarget getTarget() {
        return target;
    }
}
