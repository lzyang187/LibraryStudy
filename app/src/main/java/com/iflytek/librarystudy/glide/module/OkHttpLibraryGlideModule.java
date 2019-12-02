package com.iflytek.librarystudy.glide.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.LibraryGlideModule;
import com.iflytek.librarystudy.glide.okhttp.MyOkHttpClient;

import java.io.InputStream;

/**
 * @author: cyli8
 * @date: 2019-11-26 17:28
 */
@GlideModule
public class OkHttpLibraryGlideModule extends LibraryGlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(MyOkHttpClient.getHttpClient()));
    }
}
