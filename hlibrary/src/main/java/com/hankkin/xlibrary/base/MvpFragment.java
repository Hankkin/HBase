package com.hankkin.xlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hankkin on 2017/9/18.
 */

public abstract class MvpFragment<V extends BaseView,P extends BasePresent<V>> extends BaseFragment {

    private P presenter;


    @Override
    protected void initViewsAndEvents(View view) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (presenter == null){
            presenter = initPresenter();
            presenter.attach((V) this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected void initData() {

   }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null){
            presenter.detach();
        }
    }

    public P getPresenter() {
        return presenter;
    }

    public abstract P initPresenter();
}
