package com.hankkin.hbase.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hankkin.hbase.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yangyu
 *         功能描述：标题按钮上的弹窗（继承自PopupWindow）
 */
public class SmsRightPopwindow extends PopupWindow {


    private View mainView;
    private int width;
    private int height;

    @BindView(R.id.tv_sms_pop_group_chat) TextView tvGroupChat;
    @BindView(R.id.tv_sms_pop_add_friend) TextView tvAddFriend;
    @BindView(R.id.tv_sms_pop_scan) TextView tvScan;
    @BindView(R.id.tv_sms_pop_share) TextView tvShare;

    public SmsRightPopwindow(Activity paramActivity, View.OnClickListener paramOnClickListener) {

        WindowManager wm = (WindowManager) paramActivity.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        //窗口布局
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.popwindow_sms_right, null);
        ButterKnife.bind(this,mainView);
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            tvGroupChat.setOnClickListener(paramOnClickListener);
            tvAddFriend.setOnClickListener(paramOnClickListener);
            tvScan.setOnClickListener(paramOnClickListener);
            tvShare.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(width);
        //设置高度
        setHeight(height);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(-00000));
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
}
