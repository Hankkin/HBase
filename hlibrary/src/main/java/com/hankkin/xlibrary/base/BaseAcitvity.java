package com.hankkin.xlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hankkin.xlibrary.R;
import com.hankkin.xlibrary.event.EventMap;
import com.hankkin.xlibrary.mvp.base.MvpView;
import com.hankkin.xlibrary.utils.ToastUtils;
import com.hankkin.xlibrary.widget.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by hankkin on 2017/3/31.
 */

public abstract class BaseAcitvity extends AppCompatActivity implements MvpView {


    protected Activity activity;
    protected ProgressDialog progress;
    private boolean hasBus = false;
    protected View noDataStubView;
    protected View sysErrStubView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        initBind();
        initViews(savedInstanceState);
        initData();
    }

    protected void initBind() {
        ButterKnife.bind(activity);
    }

    protected int getLayoutId() {
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


    public void showProgress() {
        if (progress == null) {
            progress = new ProgressDialog(activity);
        }
        progress.show();
    }

    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.hide();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (hasBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMap.BaseEvent event) {
        if (event != null && event instanceof EventMap.HExceptionEvent) {
            if (!TextUtils.isEmpty(event.message)) {
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



    public interface OnRightClickListener {
        void rightClick();
    }

    protected Toolbar initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setToolBar(title, toolbar);
        return toolbar;
    }

    /**
     * 统一初始化titlebar右侧文字
     * @param title
     * @return
     */
    protected Toolbar initToolBarRightTxt(String title, String right, final OnRightClickListener listener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setToolBar(title,toolbar);
        TextView tvRight = (TextView) findViewById(R.id.tv_tool_bar_right);
        tvRight.setText(right);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
        return toolbar;
    }

    /**
     * 统一初始化titlebar右侧图片
     * @param title
     * @return
     */
    protected Toolbar initToolBarRightImg(String title, int rightId,final OnRightClickListener listener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setToolBar(title,toolbar);
        ImageView ivRight = (ImageView) findViewById(R.id.iv_tool_bar_right);
        ivRight.setImageResource(rightId);
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
        return toolbar;
    }

    /**
     * 统一设置标题栏
     * @param title
     * @param toolbar
     */
    private void setToolBar(String title, Toolbar toolbar) {
        ImageView ivBack = (ImageView) findViewById(R.id.tool_bar_back);
        TextView tvTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsRelative(10, 0);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    protected void initSwipe(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener listener){
        refreshLayout.setOnRefreshListener(listener);
        refreshLayout.setColorSchemeColors(Color.rgb(88, 79, 96));
        refreshLayout.setRefreshing(true);
    }

    protected void back() {
        if (activity != null) {
            activity.finish();
        }
    }
}
