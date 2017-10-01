package com.hankkin.xlibrary.mvp.lce;

import android.view.View;

/**
 * Created by hankkin on 2017/9/28.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

//策略接口
public interface ILceAnimator {

    /**
     * 显示加载页
     */
    void showLoadingView(View loadingView,View contentView,View errorView);

    void showContentView(View loadingView,View contentView,View errorView);

    void showErrorView(View loadingView,View contentView,View errorView);

}
