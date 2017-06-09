package com.hankkin.hbase.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hankkin.hbase.Constant;
import com.hankkin.hbase.R;
import com.hankkin.hbase.adapter.RVAdapter;
import com.hankkin.xlibrary.base.BaseAcitvity;
import com.hankkin.xlibrary.widget.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RVActivity extends BaseAcitvity implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.refreshlayout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv) RecyclerView rv;
    private int dataCount = 0;//数据源个数
    private int page = 1;//分页数
    private RVAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rv;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initToolBar("Rv+SwipeRefresh");
        initSwipe(refreshLayout,this);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        rv.addItemDecoration(
                new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        adapter = new RVAdapter();
        adapter.setOnLoadMoreListener(this,rv);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        rv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        page = 1;
        refreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i=0;i<20;i++){
                    data.add("item"+i);
                }
                adapter.setNewData(data);
                dataCount = adapter.getItemCount();
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
            }
        },1000);

    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        if (dataCount < Constant.PAGE_SIZE){
            adapter.loadMoreEnd(true);
        }
        else {
            page ++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<String> data = new ArrayList<>();
                    for (int i=0;i<20;i++){
                        data.add("item"+i);
                    }
                    adapter.addData(data);
                    dataCount = adapter.getData().size();
                    adapter.notifyDataSetChanged();
                    adapter.loadMoreComplete();
                }
            },1000);
        }
        refreshLayout.setEnabled(true);
    }
}
