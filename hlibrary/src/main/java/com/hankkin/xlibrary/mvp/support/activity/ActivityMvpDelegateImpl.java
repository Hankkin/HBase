package com.hankkin.xlibrary.mvp.support.activity;

import android.os.Bundle;

import com.hankkin.xlibrary.mvp.MvpCallback;
import com.hankkin.xlibrary.mvp.ProxyMvpCallback;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;

/**
 * Created by hankkin on 2017/9/19.
 */
//第一重代理
//角色2目标对象ActivityMvpDelegate
public class ActivityMvpDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements ActivityMvpDelegate<V,P> {

    private MvpCallback<V,P> mvpCallback;
    private ProxyMvpCallback<V,P> proxyMvCallBack;


    public ActivityMvpDelegateImpl(MvpCallback<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;

    }

    private ProxyMvpCallback<V, P> getProxyMvCallBack() {
        if (this.proxyMvCallBack == null){
            this.proxyMvCallBack = new ProxyMvpCallback<V,P>(this.mvpCallback);
        }
        return proxyMvCallBack;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getProxyMvCallBack().createPresenter();
        getProxyMvCallBack().attachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {
        getProxyMvCallBack().detachView();
    }
}
