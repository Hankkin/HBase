package com.hankkin.xlibrary.mvp.lce.activity;

import com.hankkin.xlibrary.R;
import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.support.activity.MvpActivity;

/**
 * Created by hankkin on 2017/9/28.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */
//代理对象
//特点一：实现目标接口
//特点二：持有目标对象接口
public abstract class MvpLceActivity<D,V extends MvpLceView<D>,P extends MvpPresenter<V>> extends MvpActivity<V,P> implements MvpLceView<D> {

    private MvpLceViewImpl<D> mvpLceView;

    public MvpLceViewImpl<D> getMvpLceView() {
        if (this.mvpLceView == null){
            this.mvpLceView = new MvpLceViewImpl<D>();
        }
        return mvpLceView;
    }


    //当我们的布局发生变化的时候立马回调
    //当Activity第一次启动的时候，也会回调该方法
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpLceView().initView(findViewById(R.id.rootView));
    }

    @Override
    public void showLoading(boolean isRefresh) {
        getMvpLceView().showLoading(isRefresh);
    }

    @Override
    public void showContent(boolean isRefresh) {
        getMvpLceView().showContent(isRefresh);
    }

    @Override
    public void showError(boolean isRefresh) {
        getMvpLceView().showError(isRefresh);
    }

    @Override
    public void bindData(D data, boolean isRefresh) {
        getMvpLceView().bindData(data,isRefresh);
    }

    @Override
    public void loadData(boolean isRefresh) {
        getMvpLceView().loadData(isRefresh);
    }

}
