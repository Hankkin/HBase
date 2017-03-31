package com.hankkin.xlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.hankkin.xlibrary.event.EventMap;
import com.hankkin.xlibrary.mvp.IBaseView;
import com.hankkin.xlibrary.utils.ToastUtils;
import com.hankkin.xlibrary.widget.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by hankkin on 2017/3/31.
 */

public abstract class HBaseParentAcitvity extends AppCompatActivity implements IBaseView {


    protected Activity activity;
    protected ProgressDialog progress;
    private boolean hasBus = false;
    protected View noDataStubView;
    protected View sysErrStubView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }
        initBind();
        initViews(savedInstanceState);
        initData();
    }

    protected void initBind(){
        ButterKnife.bind(activity);
    }

    protected int getLayoutId(){
        return 0;
    }

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();


    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }


    @Override
    public void showProgress() {
        if (progress == null){
            progress = new ProgressDialog(activity);
        }
        progress.show();
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing()){
            progress.hide();
        }
    }

    @Override
    public void toast(CharSequence s) {
        ToastUtils.showShortToast(s);
    }

    @Override
    public void toast(int id) {
        ToastUtils.showShortToast(id);
    }

    @Override
    public void toastLong(CharSequence s) {
        ToastUtils.showLongToast(s);
    }

    @Override
    public void toastLong(int id) {
        ToastUtils.showLongToast(id);
    }


    @Override
    public void showNullLayout() {

    }

    @Override
    public void hideNullLayout() {

    }

    @Override
    public void showErrorLayout(View.OnClickListener listener) {

    }

    @Override
    public void hideErrorLayout() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (hasBus){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMap.BaseEvent event){
        if (event != null && event instanceof EventMap.HExceptionEvent){
            if (!TextUtils.isEmpty(event.message)){
                ToastUtils.showShortToast(event.message);
            }
        }
    }


    public boolean isHasBus() {
        return hasBus;
    }

    public void setHasBus(boolean hasBus) {
        this.hasBus = hasBus;
        if (hasBus)
            EventBus.getDefault().register(this);
    }
}
