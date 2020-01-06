package com.iflytek.librarystudy.gpuimage;

import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSepiaToneFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;

public class GpuImageActivity extends AppCompatActivity {
    private static final String TAG = "GpuImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu_image);
        final String folderName = getExternalCacheDir().getAbsolutePath();
        Uri uri = Uri.parse("https://upload-images.jianshu.io/upload_images/16311248-4ee6c079e02773d1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        //GLSurfaceView
        GLSurfaceView glSurfaceView = findViewById(R.id.gl_sv);
        final GPUImage gpuImage = new GPUImage(this);
        gpuImage.setGLSurfaceView(glSurfaceView);
        gpuImage.setImage(uri);
        gpuImage.setFilter(new GPUImageSketchFilter());

        glSurfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                gpuImage.saveToPictures(folderName, "gpuimage.jpg", new GPUImage.OnPictureSavedListener() {
                    @Override
                    public void onPictureSaved(Uri uri) {
                        Log.e(TAG, "gpuImage: uri=" + uri.toString());
                    }
                });
            }
        }, 1000);



        //ImageView
//        ImageView iv = findViewById(R.id.iv);
//        Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();
//        iv.setImageBitmap(bitmap);

        //GPUImageView
        GPUImageView gpuImageView = findViewById(R.id.gpu_iv);
        gpuImageView.setImage(uri);
        gpuImageView.setFilter(new GPUImageSepiaToneFilter());
//        gpuImageView.saveToPictures(folderName, "gpuimageview.jpg", new GPUImageView.OnPictureSavedListener() {
//            @Override
//            public void onPictureSaved(Uri uri) {
//                Log.e(TAG, "gpuImageView: uri=" + uri.toString());
//            }
//        });
    }
}
