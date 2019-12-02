package com.iflytek.librarystudy.glide.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author: cyli8
 * @date: 2019-11-29 14:13
 */
public class OkhttpLogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        Log.d("OkHttpLog", "正在请求：request: " + request);
        Response response = chain.proceed(request);
        Log.d("OkHttpLog", "请求完成：request: " + request);
        Log.d("OkHttpLog", "请求完成：response: " + response);
        return response;
    }
}
