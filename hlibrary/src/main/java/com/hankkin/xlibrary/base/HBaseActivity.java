package com.hankkin.xlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hankkin.xlibrary.mvp.BasePresent;
import com.hankkin.xlibrary.mvp.IBaseView;

/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class HBaseActivity<V extends IBaseView,P extends BasePresent<V>> extends HBaseParentAcitvity {

    protected P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        presenter.attachView((V) this,activity);
    }

    protected abstract void createPresenter();


    protected void setPresenter(P presenter){
        this.presenter = presenter;
    }

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();

        super.onDestroy();
    }
}
