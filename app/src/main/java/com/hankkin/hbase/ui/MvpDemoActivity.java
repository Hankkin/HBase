package com.hankkin.hbase.ui;

import android.os.Bundle;

import com.hankkin.hbase.R;
import com.hankkin.hbase.mvp.MvpDemoPresenter;
import com.hankkin.hbase.mvp.MvpDemoView;
import com.hankkin.xlibrary.base.MvpActivity;

import java.util.List;


public class MvpDemoActivity extends MvpActivity<MvpDemoView,MvpDemoPresenter> implements MvpDemoView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_demo);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        presenter.getDataHttp();
    }

    @Override
    public MvpDemoPresenter initPresenter() {
        return new MvpDemoPresenter();
    }

    @Override
    public void setDataSuc(List<String> data) {

    }

    @Override
    public void setDataFail() {

    }
}
