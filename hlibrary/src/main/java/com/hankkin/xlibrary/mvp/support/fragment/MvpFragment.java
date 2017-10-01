package com.hankkin.xlibrary.mvp.support.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hankkin.xlibrary.base.BaseFragment;
import com.hankkin.xlibrary.mvp.MvpCallback;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;


/**
 * 作者: Hankkin on 2017/9/20 22:41
 */

public class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseFragment implements MvpCallback<V, P>, MvpView {

    private FragmentMvpDelegate<V, P> activityMvpDelegate;

    private FragmentMvpDelegate getFragmentMvpDelegate(){
        if (this.activityMvpDelegate == null){
            this.activityMvpDelegate = new FragmentMvpDelegateImpl<V, P>(this);
        }
        return this.activityMvpDelegate;
    }

    private P presenter;

    @Override
    public P createPresenter() {
        return presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V)this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getFragmentMvpDelegate().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getFragmentMvpDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentMvpDelegate().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getFragmentMvpDelegate().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentMvpDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getFragmentMvpDelegate().onDestroy();
    }

}
