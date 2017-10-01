package com.hankkin.xlibrary.mvp.base.impl;


import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;

/**
 * 作者: Hankkin on 2017/9/22 20:39
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    public V getView() {
        return view;
    }

    //绑定
    public void attachView(V view){
        this.view = view;
    }

    //解绑
    public void detachView(){
        this.view = null;
    }

}
