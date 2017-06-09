package com.hankkin.hbase;

import android.app.Application;

import com.hankkin.xlibrary.utils.Utils;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;

/**
 * Created by hankkin on 2017/4/5.
 */

public class HApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initPickerImg();
    }

    private void initPickerImg() {
        FunctionOptions options = new FunctionOptions.Builder()
                .setType(FunctionConfig.TYPE_IMAGE)
                .setCompress(true)
                .setGrade(Luban.CUSTOM_GEAR)
                .setSelectMode(FunctionConfig.MODE_MULTIPLE)
                .create();
        PictureConfig.getInstance().init(options);
    }
}
