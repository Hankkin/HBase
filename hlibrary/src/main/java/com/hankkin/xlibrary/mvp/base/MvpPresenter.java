package com.hankkin.xlibrary.mvp.base;

/**
 * 作者: Hankkin on 2017/9/22 20:37
 */

//高度抽象presenter
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
