package com.iflytek.librarystudy.glide.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iflytek.librarystudy.R;

import java.util.List;

/**
 * @author: cyli8
 * @date: 2019-12-03 14:51
 */
public class GlideAdapter extends RecyclerView.Adapter<GlideViewHolder> {
    private Context context;
    private List<String> urls;

    public GlideAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public GlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GlideViewHolder(context, View.inflate(context, R.layout.glide_recycler_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull GlideViewHolder holder, int position) {
        holder.bindView(urls.get(position));
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
