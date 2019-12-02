package com.iflytek.librarystudy.glide.module;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;


/**
 * @author: cyli8
 * @date: 2019-11-27 11:31
 */
@GlideExtension
public class MyGlideExtension {

    private MyGlideExtension() {

    }

    //只缓存原始图片的配置
    @GlideOption
    public static BaseRequestOptions<?> onlyCacheSource(BaseRequestOptions<?> options) {
        return options.diskCacheStrategy(DiskCacheStrategy.DATA);
    }

}
