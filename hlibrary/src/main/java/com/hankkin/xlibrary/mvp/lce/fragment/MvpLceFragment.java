package com.hankkin.xlibrary.mvp.lce.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hankkin.xlibrary.mvp.base.MvpPresenter;
import com.hankkin.xlibrary.mvp.lce.ILceAnimator;
import com.hankkin.xlibrary.mvp.lce.activity.MvpLceView;
import com.hankkin.xlibrary.mvp.lce.activity.MvpLceViewImpl;
import com.hankkin.xlibrary.mvp.support.fragment.MvpFragment;

/**
 * Created by hankkin on 2017/9/28.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class MvpLceFragment<D, V extends MvpLceView<D>, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements MvpLceView<D>{

    private MvpLceViewImpl<D> mvpLceView;

    private MvpLceViewImpl<D> getMvpLceView() {
        if (this.mvpLceView == null){
            this.mvpLceView = new MvpLceViewImpl<D>();
        }
        return mvpLceView;
    }

    public void setLceAnimator(ILceAnimator lceAnimator) {
        this.mvpLceView.setLceAnimator(lceAnimator);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpLceView().initView(view);
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        getMvpLceView().showLoading(isPullRefresh);
    }

    @Override
    public void showContent(boolean isPullRefresh) {
        getMvpLceView().showContent(isPullRefresh);
    }

    @Override
    public void showError(boolean isPullRefresh) {
        getMvpLceView().showError(isPullRefresh);
    }

    @Override
    public void bindData(D data, boolean isPullRefresh) {
        getMvpLceView().bindData(data, isPullRefresh);
    }

    @Override
    public void loadData(boolean isPullRefresh) {
        getMvpLceView().loadData(isPullRefresh);
    }

}
