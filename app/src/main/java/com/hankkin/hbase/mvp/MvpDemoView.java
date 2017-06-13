package com.hankkin.hbase.mvp;

import com.hankkin.xlibrary.mvp.BaseView;

import java.util.List;

/**
 * Created by hankkin on 2017/6/11.
 */

public interface MvpDemoView extends BaseView{
    void setDataSuc(List<String> data);
    void setDataFail();
}
