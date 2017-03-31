package com.hankkin.xlibrary.mvp;

import android.content.Context;

/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class BasePresent<V extends IBaseView> implements IPresenter<V> {

    protected Context mContext;

    public V v;

    @Override
    public void attachView(V v, Context context) {
        this.v = v;
        this.mContext = context;
    }

    @Override
    public void detachView() {
        if (v != null){
            v = null;
        }
        if (mContext != null){
            mContext = null;
        }
    }

    public void loadFinsihUi(){
        v.hideProgress();
    }
}
