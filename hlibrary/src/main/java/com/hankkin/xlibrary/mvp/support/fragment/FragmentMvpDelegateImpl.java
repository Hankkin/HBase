package com.hankkin.xlibrary.mvp.support.fragment;

import android.os.Bundle;
import android.view.View;

import com.hankkin.xlibrary.mvp.MvpCallback;
import com.hankkin.xlibrary.mvp.ProxyMvpCallback;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;


/**
 * 作者: Hankkin on 2017/9/20 22:45
 */

public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements FragmentMvpDelegate<V, P> {

    private MvpCallback<V, P> mvpCallback;
    private ProxyMvpCallback<V, P> proxyMvpCallback;

    public FragmentMvpDelegateImpl(MvpCallback<V, P> mvpCallback){
        this.mvpCallback = mvpCallback;
    }

    private ProxyMvpCallback<V, P> getProxyMvpCallback() {
        if (this.proxyMvpCallback == null){
            this.proxyMvpCallback = new ProxyMvpCallback<V, P>(this.mvpCallback);
        }
        return proxyMvpCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Activity->onCreate回调中绑定
        getProxyMvpCallback().createPresenter();
        getProxyMvpCallback().attachView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {
        getProxyMvpCallback().detachView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }
}
