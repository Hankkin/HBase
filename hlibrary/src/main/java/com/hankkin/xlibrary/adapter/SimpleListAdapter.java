package com.hankkin.xlibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hankkin.xlibrary.adapter.base.HListAdapter;

import java.util.List;

/**
 * Created by hankkin on 2017/3/27.
 */

public abstract class SimpleListAdapter<T,H> extends HListAdapter<T> {
    public SimpleListAdapter(Context context) {
        super(context);
    }

    public SimpleListAdapter(Context context, ListItemCallBack callBack) {
        super(context, callBack);
    }

    public SimpleListAdapter(List data, Context context) {
        super(data, context);
    }

    public SimpleListAdapter(List data, ListItemCallBack callBack, Context context) {
        super(data, callBack, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder = null;
        T item = data.get(position);

        if (convertView == null) {
            convertView = View.inflate(mContext, getLayoutId(), null);
            holder = newViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (H) convertView.getTag();
        }

        onBindViewHolder(holder, item, position);

        return convertView;
    }

    protected abstract H newViewHolder(View convertView);

    protected abstract int getLayoutId();

    protected abstract void onBindViewHolder(H holder, T item, int position);
}
