package com.hankkin.xlibrary.mvp.support.fragment;

import android.os.Bundle;
import android.view.View;

import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.base.MvpView;


/**
 * 作者: Hankkin on 2017/9/18 22:28
 */

//第一重代理
//角色一：目标接口->ActivityMvpDelegate
public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    public void onCreate(Bundle savedInstanceState);

    public void onActivityCreated(Bundle savedInstanceState);

    public void onViewCreated(View view, Bundle savedInstanceState);

    public void onStart();

    public void onPause();

    public void onResume();

    public void onStop();

    public void onDestroyView();

    public void onDestroy();

    public void onDetach();

}
