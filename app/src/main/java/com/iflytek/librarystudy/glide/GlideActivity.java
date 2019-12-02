package com.iflytek.librarystudy.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.glide.target.MyCustomLayout;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class GlideActivity extends AppCompatActivity {
    private static final String TAG = "GlideActivity";
    private ImageView iv, iv1, iv2;
    private MyCustomLayout myCustomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        iv = findViewById(R.id.iv);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        myCustomLayout = findViewById(R.id.my_custom_layout);
        String url = "http://adnet.qq.com/assets/images/feedback.jpg";
        //1、基础配置
        RequestOptions options = new RequestOptions()
                //如果最终是圆角，会出现下方placeholder显示的情况
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.shape_glide_error)
                //只会将图片加载成100*100像素的尺寸
                .override(100, 100)
                //仅从缓存加载图片
//                .onlyRetrieveFromCache(true)
                //跳过内存缓存
                .skipMemoryCache(true)
                //禁用磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .circleCrop();

        //2、基础使用
        Glide.with(this)
                //指定为gif图
//                .asGif()
                //指定为静态图
                .asBitmap()
                .load(url)
                .apply(options)
                //缩略图尺寸为 View 或 Target 的某个百分比
                .thumbnail(0.5f)
                .into(iv);

        //3、preload()预加载
        Glide.with(this)
                .load(url)
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                //预加载图片的原始尺寸，
                .preload();

        //4、submit()下载图片
        downloadImage();

        //5、listener（）监听
        listener();

        //6、图片变换

        //7、自定义模块
        customTarget();

        //8、Generated API
//        GlideApp.with(this)
//                .load(url)
//                .onlyCacheSource()
//                .into(iv);

    }

    private void customTarget() {
        String url = "http://www.guolin.tech/book.png";
        CustomTarget target = new CustomTarget<Bitmap>(100, 100) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                iv2.setImageBitmap(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                iv2.setImageDrawable(placeholder);
            }
        };
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(target);

        Glide.with(this)
                .load(url)
                .into(myCustomLayout.getTarget());
    }

    private void listener() {
        String url = "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png";
        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    //图片加载失败的时候就会回调
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //返回true表示处理了，false表示没处理
                        Toast.makeText(GlideActivity.this, "iv1加载失败了", Toast.LENGTH_LONG).show();
                        return false;
                    }

                    //图片加载完成的时候就会回调
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(GlideActivity.this, "iv1加载成功了", Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .into(iv1);
    }

    private void downloadImage() {
        //子线程执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://www.guolin.tech/book.png";
                FutureTarget<File> futureTarget = Glide.with(GlideActivity.this.getApplicationContext())
                        .asFile()
                        .load(url)
                        //下载指定尺寸图片
//                        .submit(100, 100)
                        //下载原始尺寸的图片
                        .submit();
                try {
                    //下载是同步的
                    final File file = futureTarget.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "下载后的路径" + file.getAbsolutePath());
//                            Toast.makeText(GlideActivity.this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
