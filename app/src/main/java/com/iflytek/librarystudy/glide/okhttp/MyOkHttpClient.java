package com.iflytek.librarystudy.glide.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;

/**
 * @author: cyli8
 * @date: 2019-12-02 10:05
 */
public class MyOkHttpClient {

    public static OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new OkhttpLogInterceptor());
//                .sslSocketFactory(getSslSocketFactory());
        return builder.build();
    }

    private static SSLSocketFactory getSslSocketFactory() {
        return null;
    }


}
