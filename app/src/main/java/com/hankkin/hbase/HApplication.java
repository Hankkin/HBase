package com.hankkin.hbase;

import android.app.Application;

import com.hankkin.xlibrary.utils.Utils;

/**
 * Created by hankkin on 2017/4/5.
 */

public class HApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
