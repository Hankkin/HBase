package com.hankkin.hbase.base;

import android.content.Context;

import com.hankkin.xlibrary.mvp.base.MvpModel;

/**
 * Created by hankkin on 2017/9/30.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class BaseModel implements MvpModel {

    private Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

    public String getServerUrl(){
        return "http://api.budejie.com/api/";
    }


}
