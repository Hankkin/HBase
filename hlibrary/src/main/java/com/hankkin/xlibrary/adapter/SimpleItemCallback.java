package com.hankkin.xlibrary.adapter;

/**
 * Created by shihao on 2017/3/9.
 */

public abstract class SimpleItemCallback<T, H> extends ListItemCallBack<T> {
    public void onItemClick(int position, T model, int tag, H holder) {
    }

    public void onItemLongClick(int position, T model, int tag, H holder) {
    }
}
