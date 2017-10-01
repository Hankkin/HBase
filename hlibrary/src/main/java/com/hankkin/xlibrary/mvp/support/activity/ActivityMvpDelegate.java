package com.hankkin.xlibrary.mvp.support.activity;

import android.os.Bundle;

import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;

/**
 * Created by hankkin on 2017/9/19.
 */
//第一重代理
//角色1目标接口
public interface ActivityMvpDelegate <V extends MvpView,P extends MvpPresenter<V>>{

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestory();
}
