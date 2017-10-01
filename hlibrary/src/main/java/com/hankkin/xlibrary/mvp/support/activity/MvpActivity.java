package com.hankkin.xlibrary.mvp.support.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hankkin.xlibrary.mvp.MvpCallback;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;


/**
 * 作者: Hankkin on 2017/9/18 22:33
 */

//第一重代理
//角色三：代理对象->MvpActivity
//两个特点
//特点一：实现目标接口(可有可无)
//特点二：持有目标对象


//第二个代理->关联MVP
//角色二：目标对象->MvpActivity
//必需有的一个特点:实现目标接口
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends Activity implements MvpCallback<V, P>, MvpView {

    private ActivityMvpDelegate<V, P> activityMvpDelegate;

    private ActivityMvpDelegate getActivityMvpDelegate(){
        if (this.activityMvpDelegate == null){
            this.activityMvpDelegate = new ActivityMvpDelegateImpl<V, P>(this);
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getActivityMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getActivityMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getActivityMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityMvpDelegate().onDestory();
    }
}
