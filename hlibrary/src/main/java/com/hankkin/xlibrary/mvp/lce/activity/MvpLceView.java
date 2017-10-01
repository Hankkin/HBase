package com.hankkin.xlibrary.mvp.lce.activity;


import com.hankkin.xlibrary.mvp.base.MvpView;

/**
 * Created by hankkin on 2017/9/28.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */
//目标接口
public interface MvpLceView<D> extends MvpView{


    //显示loading
    void showLoading(boolean isRefresh);

    //显示content
    void showContent(boolean isRefresh);

    //显示错误
    void showError(boolean isRefresh);

    //绑定数据
    void bindData(D data,boolean isRefresh);

    //加载数据
    void loadData(boolean isRefresh);

}
