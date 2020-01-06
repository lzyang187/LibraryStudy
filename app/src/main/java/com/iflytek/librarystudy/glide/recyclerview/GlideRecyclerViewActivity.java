package com.iflytek.librarystudy.glide.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.iflytek.librarystudy.R;

import java.util.ArrayList;
import java.util.List;

public class GlideRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        List<String> urls = new ArrayList<>();
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-001.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-002.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-003.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-004.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-005.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-006.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-007.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-008.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201106/30/mengxiang_jingguan-009.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-001.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-002.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-003.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-004.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-005.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-006.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201507/06/meiguo_hanmu_jijin-007.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201905/18/kaixuanmen.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201905/18/kaixuanmen-003.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201905/18/kaixuanmen-009.jpg");
        urls.add("https://img.ivsky.com/img/tupian/pre/201905/18/kaixuanmen-011.jpg");
        GlideAdapter adapter = new GlideAdapter(this, urls);
        recyclerView.setAdapter(adapter);

        //预加载图片实现
        MyPreloadModelProvider preloadModelProvider = new MyPreloadModelProvider(this, urls);
        FixedPreloadSizeProvider<String> sizeProvider = new FixedPreloadSizeProvider<>(650, 650);
        //maxPreload指示你想预加载多少条数据。最优解是大到能包含两到三行的所有图片
        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<>(Glide.with(this), preloadModelProvider,
                sizeProvider, 20);
        recyclerView.addOnScrollListener(preloader);

    }
}
