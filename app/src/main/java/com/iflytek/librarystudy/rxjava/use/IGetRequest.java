package com.iflytek.librarystudy.rxjava.use;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author: cyli8
 * @date: 2018/3/12 15:26
 */

public interface IGetRequest {
    // 注解里传入 网络请求 的部分URL地址
    // getCall()是接受网络请求数据的方法
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Observable<CBTranslation> getCall();
}
