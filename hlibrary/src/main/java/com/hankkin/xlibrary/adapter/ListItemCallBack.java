package com.hankkin.xlibrary.adapter;

/**
 * Created by hankkin on 2017/3/27.
 * 适配器点击回调
 */

public abstract class ListItemCallBack<T> {

    public void onItemClick(int postion,T t, int tag){};

    public void onItemLongClick(int postion,T t,int tag){};
}
