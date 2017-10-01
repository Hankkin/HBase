package com.hankkin.xlibrary.mvp;

import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;

/**
 * Created by hankkin on 2017/9/19.
 */
//第二重代理->关联MVP
//角色1 目标接口  定义mvp绑定和解绑操作
public interface MvpCallback<V extends MvpView,P extends MvpPresenter<V>> {

    //创建P层
    P createPresenter();

    //得到P层
    P getPresenter();

    //设置P层
    void setPresenter(P presenter);

    //得到V层
    V getMvpView();

}
