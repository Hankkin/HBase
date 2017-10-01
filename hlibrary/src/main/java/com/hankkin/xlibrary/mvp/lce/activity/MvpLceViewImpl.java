package com.hankkin.xlibrary.mvp.lce.activity;

import android.view.View;

import com.hankkin.xlibrary.R;
import com.hankkin.xlibrary.mvp.lce.ILceAnimator;
import com.hankkin.xlibrary.mvp.lce.impl.DefaultLceAnimator;

/**
 * Created by hankkin on 2017/9/28.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class MvpLceViewImpl<D> implements MvpLceView<D> {



    private View contentView;
    private View errortView;
    private View loadingView;

    private ILceAnimator lceAnimator;

    public ILceAnimator getLceAnimator() {
        if (this.lceAnimator == null){
            this.lceAnimator = new DefaultLceAnimator();
        }
        return this.lceAnimator;
    }

    public void setLceAnimator(ILceAnimator lceAnimator) {
        this.lceAnimator = lceAnimator;
    }

    public void initView(View rootView){
        if (rootView == null){
            throw new NullPointerException("rootView不能为空");
        }
        if (this.loadingView == null){
            this.loadingView = rootView.findViewById(R.id.loadingView);
        }
        if (this.contentView == null){
            this.contentView = rootView.findViewById(R.id.contentView);
        }
        if(this.errortView == null){
            this.errortView = rootView.findViewById(R.id.errorView);
        }
        if (loadingView == null){
            throw new NullPointerException("loadingView不能为空");
        }
        if (contentView == null){
            throw new NullPointerException("contentView不能为空");
        }
        if (this.errortView == null){
            throw new NullPointerException("errorView不能为空");
        }
    }

    @Override
    public void showLoading(boolean isRefresh) {
        if (!isRefresh){
            getLceAnimator().showLoadingView(this.loadingView,this.contentView,this.errortView);
        }
    }

    @Override
    public void showContent(boolean isRefresh) {
        if (!isRefresh){
            getLceAnimator().showContentView(this.loadingView,this.contentView,this.errortView);
        }
    }

    @Override
    public void showError(boolean isRefresh) {
        if (!isRefresh){
            getLceAnimator().showErrorView(this.loadingView,this.contentView,this.errortView);
        }
    }

    @Override
    public void bindData(D data, boolean isRefresh) {

    }

    @Override
    public void loadData(boolean isRefresh) {

    }

}
