package com.hankkin.hbase.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hankkin.hbase.R;

/**
 * Created by hankkin on 2017/6/9.
 */

public class RVAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RVAdapter() {
        super(R.layout.adapter_rv_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adapter_rv,item);
    }
}
