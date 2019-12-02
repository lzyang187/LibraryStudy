package com.iflytek.librarystudy.swipe;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.smartrefresh.MyAdapter;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemLongClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class SwipeActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener, SwipeRecyclerView.LoadMoreListener {
    private static final String TAG = "SwipeActivity";
    private SwipeRecyclerView swipeRecyclerView;
    private MyAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        swipeRecyclerView = findViewById(R.id.swipe_recycler_view);
        swipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //分隔线
        swipeRecyclerView.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        //点击
        swipeRecyclerView.setOnItemClickListener(this);
        //长按
        swipeRecyclerView.setOnItemLongClickListener(this);
        //header
        ImageView headerView = new ImageView(this);
        headerView.setImageResource(R.mipmap.ic_launcher);
        swipeRecyclerView.addHeaderView(headerView);
        //footer
//        TextView footerView = new TextView(this);
//        footerView.setText("我是Footer1");
//        swipeRecyclerView.addFooterView(footerView);
//        swipeRecyclerView.removeFooterView(footerView);
        //加载更多
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        loadMoreView.setLoadMoreListener(this);
        swipeRecyclerView.addFooterView(loadMoreView); // 添加为Footer。
        swipeRecyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        swipeRecyclerView.setLoadMoreListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("item" + i);
        }
        adapter = new MyAdapter(this, list);
        swipeRecyclerView.setAdapter(adapter);
        // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        swipeRecyclerView.loadMoreFinish(false, true);

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe: 执行一次发射");
                emitter.onNext(1);
            }
        });
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "第一个accept: " + integer);
            }
        });
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "第二个 accept: " + integer);
            }
        });
    }

    @Override
    public void onItemClick(View view, int adapterPosition) {
        Toast.makeText(this, adapterPosition + "被点击", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemLongClick(View view, int adapterPosition) {
        Toast.makeText(this, adapterPosition + "被长按点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        swipeRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                int start = list.size();
//                for (int i = start; i < start + 20; i++) {
//                    list.add("item" + i);
//                }
//                adapter.notifyItemRangeInserted(start, 20);
                // 第一个参数：表示此次数据是否为空。
                // 第二个参数：表示是否还有更多数据。
                swipeRecyclerView.loadMoreFinish(false, true);
                swipeRecyclerView.loadMoreError(0, "网络未连接");
            }
        }, 1000);
    }

}
