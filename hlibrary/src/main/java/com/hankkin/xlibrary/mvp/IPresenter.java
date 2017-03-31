package com.hankkin.xlibrary.mvp;

import android.content.Context;

/**
 * Created by hankkin on 2017/3/31.
 */

public interface IPresenter<V> {

    void attachView(V v, Context context);

    void detachView();
}
