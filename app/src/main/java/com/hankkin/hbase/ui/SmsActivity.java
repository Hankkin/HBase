package com.hankkin.hbase.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hankkin.hbase.R;
import com.hankkin.hbase.popwindow.SmsRightPopwindow;
import com.hankkin.xlibrary.base.BaseAcitvity;

import butterknife.BindView;

public class SmsActivity extends BaseAcitvity {

    @BindView(R.id.iv_tool_bar_right) ImageView ivJiaHao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sms;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initToolBarRightImg("消息POP", R.mipmap.icon_sms_jiahao, new OnRightClickListener() {
            @Override
            public void rightClick() {
                final SmsRightPopwindow popwindow = new SmsRightPopwindow(activity,new OnClickLintener());
                popwindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            popwindow.dismiss();
                        }
                    }
                });
                //设置默认获取焦点
                popwindow.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                popwindow.showAsDropDown(ivJiaHao);
                //如果窗口存在，则更新
                popwindow.update();
            }
        });
    }

    @Override
    protected void initData() {

    }

    class OnClickLintener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

            }
        }
    }
}
