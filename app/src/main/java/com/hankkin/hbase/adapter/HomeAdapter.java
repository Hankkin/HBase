package com.hankkin.hbase.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hankkin.hbase.R;

import java.util.List;

/**
 * Created by hankkin on 2017/3/28.
 */

public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public HomeAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_home_item,item);
    }
}
