package com.hankkin.hbase.ui.demo;

import com.hankkin.xlibrary.base.BaseRefreshAdapter;
import com.hankkin.xlibrary.base.MvpRefreshFragment;
import com.hankkin.xlibrary.mvp.lce.activity.MvpLceView;

/**
 * Created by hankkin on 2017/9/29.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class DemoFragment extends MvpRefreshFragment<DemoModel,MvpLceView<DemoModel>,DemoPresenter>{
    @Override
    public void initData() {

    }

    @Override
    public int setContentView() {
        return 0;
    }

    @Override
    public BaseRefreshAdapter bindAdapter() {
        return null;
    }
}
