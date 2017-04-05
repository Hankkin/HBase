package com.hankkin.hbase;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hankkin.xlibrary.base.HBaseParentAcitvity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends HBaseParentAcitvity {

    @BindView(R.id.rv_main) RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        String[] data = getResources().getStringArray(R.array.mainArrays);
        HomeAdapter homeAdapter = new HomeAdapter(R.layout.adapter_home_item, Arrays.asList(data));
        rv.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter.openLoadAnimation();
        rv.setAdapter(homeAdapter);

        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        gotoActivity(PickerViewActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
