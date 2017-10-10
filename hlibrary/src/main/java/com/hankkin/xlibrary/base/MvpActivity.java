package com.hankkin.xlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class MvpActivity<V extends BaseView,P extends BasePresent<V>> extends BaseAcitvity {

    private P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null){
            presenter = initPresenter();
            presenter.attach((V) this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null){
            presenter.detach();
        }
        super.onDestroy();
    }


    public P getPresenter() {
        return presenter;
    }

    public abstract P initPresenter();
}
