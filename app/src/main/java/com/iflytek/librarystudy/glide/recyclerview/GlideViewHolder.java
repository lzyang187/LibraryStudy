package com.iflytek.librarystudy.glide.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iflytek.librarystudy.R;

/**
 * @author: cyli8
 * @date: 2019-12-03 14:52
 */
public class GlideViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private ImageView imageView;

    public GlideViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        imageView = itemView.findViewById(R.id.item_iv);
    }

    public void bindView(String url) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }
}
