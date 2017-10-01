package com.hankkin.hbase.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hankkin.hbase.R;
import com.hankkin.hbase.adapter.HomeAdapter;
import com.hankkin.xlibrary.base.BaseAcitvity;
import com.hankkin.xlibrary.utils.ToastUtils;
import com.hankkin.xlibrary.widget.view.HLoading;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseAcitvity {

    @BindView(R.id.rv_main) RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
                    case 1:
//                        showProgress();
                        HLoading loading = new HLoading(activity);
                        loading.show();
                        break;
                    case 2:
                        gotoActivity(CommonDialogActivity.class);
                        break;
                    case 3:
                        selectImg();
                        break;
                    case 4:
                        gotoActivity(SmsActivity.class);
                        break;
                    case 5:
                        gotoActivity(RVActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void selectImg(){
        PictureConfig.getInstance()
                .openPhoto(this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {
                        if (list != null){
                        }
                    }

                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {
                        if (localMedia != null){
                            ToastUtils.showShortToast(localMedia.getPath());
                        }
                    }
                });
    }

}
