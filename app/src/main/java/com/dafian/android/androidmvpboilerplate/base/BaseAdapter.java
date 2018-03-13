package com.dafian.android.androidmvpboilerplate.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author Dafian on 13/03/18
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected void loadImagesFromURL(Context context, ImageView view, String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .into(view);
    }
}
