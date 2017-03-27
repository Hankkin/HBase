package com.hankkin.xlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hankkin.xlibrary.adapter.base.HRecyclerAdapter;

import java.util.List;

/**
 * Created by hankkin on 2017/3/27.
 */

public abstract class SimpleRecycerAdapter<T,V extends RecyclerView.ViewHolder> extends HRecyclerAdapter<T,V>{
    public SimpleRecycerAdapter(Context context) {
        super(context);
    }

    public SimpleRecycerAdapter(Context context, ListItemCallBack<T> callback) {
        super(context, callback);
    }

    public SimpleRecycerAdapter(Context context, List<T> data) {
        super(context, data);
    }

    public SimpleRecycerAdapter(Context context, List<T> data, ListItemCallBack<T> callback) {
        super(context, data, callback);
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return newViewHolder(view);
    }

    public abstract V newViewHolder(View itemView);

    public abstract int getLayoutId();

}
