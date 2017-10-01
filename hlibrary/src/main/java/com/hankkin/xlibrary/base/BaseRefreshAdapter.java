package com.hankkin.xlibrary.base;

import android.content.Context;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hankkin on 2017/9/29.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public abstract class BaseRefreshAdapter<D,L,VH extends BaseViewHolder> extends BaseQuickAdapter<D,VH>{


    private Context context;
    private List<D> list;
    private LayoutInflater inflater;

    public BaseRefreshAdapter(Context context,int layout) {
        super(layout);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public List<D> getList() {
        return list;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void refreshAdapter(boolean isRefresh, List<D> list){
        if (isRefresh){
            getList().clear();
        }
        if (list != null){
            getList().addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getAdapterItemCount(){
        return getList().size();
    }


}
