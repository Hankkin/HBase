package com.hankkin.xlibrary.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hankkin.xlibrary.R;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.lce.activity.MvpLceView;
import com.hankkin.xlibrary.mvp.lce.fragment.MvpLceFragment;
import com.hankkin.xlibrary.widget.CustomLoadMoreView;

import butterknife.ButterKnife;

/**
 * Created by hankkin on 2017/9/29.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public abstract class MvpRefreshFragment<D,V extends MvpLceView<D>,P extends MvpPresenter<V>> extends MvpLceFragment<D,V,P> {

    //下拉刷新组件
    private SwipeRefreshLayout refreshLayout;

    //列表
    private RecyclerView recyclerView;

    //适配器
    private BaseRefreshAdapter adapter;

    //是否下拉刷新
    private boolean isRefresh;

    //分割线管理器
    private LinearLayoutManager linearLayoutManager;

    private boolean isFirstLoad = true;//是否是第一次加载数据
    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系



    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public BaseRefreshAdapter getAdapter() {
        return adapter;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(setContentView(),null);
        return content;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initContentView(view);
        lazyLoadData();
    }

    //初始化内容View
    public void initContentView(View contentView){
        //初始化导航条->忽略了->上一期课程讲解过

        //讲解初始化下拉刷新组件
        initRefreshView(contentView);
    }


    //初始化
    private void initRefreshView(View contentView){
        //初始化下拉刷新
        refreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.refreshView);
        refreshLayout.setColorSchemeColors(Color.rgb(32, 140, 89));
        refreshLayout.setRefreshing(true);

        //初始化rv列表
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //绑定适配器
        adapter = bindAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setLoadMoreView(new CustomLoadMoreView());

        //添加刷新监听
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(true);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refreshData(false);
            }
        },recyclerView);
    }

    @Override
    public void bindData(D data, boolean isPullRefresh) {
        super.bindData(data, isPullRefresh);
        if (isPullRefresh){
            getRefreshLayout().setRefreshing(false);
        }
        else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            lazyLoadData();

        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (isFirstLoad) {
            Log.e(">>>>>","第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        } else {
            Log.e(">>>>>","不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            Log.e(">>>>>","不加载" + "   " + this.getClass().getSimpleName());
            return;
        }
        initData();
        isFirstLoad = false;
    }


    //更新状态->具体的实现由子类决定
    public void refreshData(boolean isDownPullRefresh){
        this.isRefresh = isDownPullRefresh;
    }


    public abstract void initData();
    public abstract int setContentView();
    //这个具体的实现由子类实现
    public abstract BaseRefreshAdapter bindAdapter();
}


