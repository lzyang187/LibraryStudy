package com.iflytek.librarystudy.glide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.glide.target.MyCustomLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideTransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide_transform);

        //Crop
        //CropTransformation
        loadImg((ImageView) findViewById(R.id.iv1), RequestOptions.bitmapTransform(
                new CropTransformation(300, 300, CropTransformation.CropType.CENTER)));
        //RoundedCornersTransformation
        loadImg((ImageView) findViewById(R.id.iv2), RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(100, 0, RoundedCornersTransformation.CornerType.LEFT)));
        //CropSquareTransformation
        loadImg((ImageView) findViewById(R.id.iv3), RequestOptions.bitmapTransform(
                new CropSquareTransformation()));
        //ColorFilterTransformation
        loadImg((ImageView) findViewById(R.id.iv4), RequestOptions.bitmapTransform(
                new GrayscaleTransformation()));
        //BlurTransformation
        loadImg((ImageView) findViewById(R.id.iv5), RequestOptions.bitmapTransform(
                new BlurTransformation(25, 2)));
        //MaskTransformation
        MyCustomLayout customLayout = findViewById(R.id.customLayout);
        Glide.with(this).load("https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png")
                .apply(RequestOptions.bitmapTransform(new MaskTransformation(R.mipmap.mask_starfish)))
                .into(customLayout.getTarget());
        //混合使用
        loadImg((ImageView) findViewById(R.id.iv6), RequestOptions.bitmapTransform(
                new MultiTransformation<Bitmap>(new BlurTransformation(), new GrayscaleTransformation())));
    }

    private void loadImg(ImageView imageView, RequestOptions options) {
        String url = "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png";
        Glide.with(this).load(url)
                .apply(options)
                .into(imageView);
    }
}
