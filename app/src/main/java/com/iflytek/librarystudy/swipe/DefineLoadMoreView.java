package com.iflytek.librarystudy.swipe;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iflytek.librarystudy.R;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

/**
 * @author: cyli8
 * @date: 2019-11-05 15:29
 */
public class DefineLoadMoreView extends LinearLayout
        implements SwipeRecyclerView.LoadMoreView, View.OnClickListener {
    private ProgressBar mProgressBar;
    private TextView mTvMessage;

    private SwipeRecyclerView.LoadMoreListener mLoadMoreListener;

    public void setLoadMoreListener(SwipeRecyclerView.LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }

    public DefineLoadMoreView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        setGravity(Gravity.CENTER);
        setVisibility(GONE);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int minHeight = (int) (displayMetrics.density * 60 + 0.5);
        setMinimumHeight(minHeight);

        inflate(context, R.layout.layout_fotter_loadmore, this);
        mProgressBar = findViewById(R.id.progress_bar);
        mTvMessage = findViewById(R.id.tv_message);
        setOnClickListener(this);
    }

    /**
     * 马上开始回调加载更多了，这里应该显示进度条。
     */
    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        mProgressBar.setVisibility(VISIBLE);
        mTvMessage.setVisibility(VISIBLE);
        mTvMessage.setText("正在努力加载，请稍后");
    }

    /**
     * 加载更多完成了。
     *
     * @param dataEmpty 是否请求到空数据。
     * @param hasMore   是否还有更多数据等待请求。
     */
    @Override
    public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);

            if (dataEmpty) {
                mProgressBar.setVisibility(GONE);
                mTvMessage.setVisibility(VISIBLE);
                mTvMessage.setText("暂时没有数据");
            } else {
                mProgressBar.setVisibility(GONE);
                mTvMessage.setVisibility(VISIBLE);
                mTvMessage.setText("没有更多数据啦");
            }
        } else {
            setVisibility(INVISIBLE);
        }
    }

    /**
     * 调用了setAutoLoadMore(false)后，在需要加载更多的时候，这个方法会被调用，并传入加载更多的listener。
     */
    @Override
    public void onWaitToLoadMore(SwipeRecyclerView.LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;

        setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
        mTvMessage.setVisibility(VISIBLE);
        mTvMessage.setText("点我加载更多");
    }

    /**
     * 加载出错啦，下面的错误码和错误信息二选一。
     *
     * @param errorCode    错误码。
     * @param errorMessage 错误信息。
     */
    @Override
    public void onLoadError(int errorCode, String errorMessage) {
        setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
        mTvMessage.setVisibility(VISIBLE);

        // 这里要不直接设置错误信息，要不根据errorCode动态设置错误数据。
        mTvMessage.setText(errorMessage);
    }

    /**
     * 非自动加载更多时mLoadMoreListener才不为空。
     */
    @Override
    public void onClick(View v) {
        if (mLoadMoreListener != null) {
            onLoading();
            mLoadMoreListener.onLoadMore();
        }
    }
}
