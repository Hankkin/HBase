package com.hankkin.hbase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.hankkin.xlibrary.base.HBaseParentAcitvity;
import com.hankkin.xlibrary.bean.JsonBean;
import com.hankkin.xlibrary.other.PickerManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class PickerViewActivity extends HBaseParentAcitvity {


    @BindView(R.id.btn_picker_time)
    Button btnTime;
    @BindView(R.id.btn_picker_province)
    Button btnProvince;

    private TimePickerView pvTime;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_picker_view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initTimePicker();
    }

    @Override
    protected void initData() {
        PickerManager.getInstance().initProvince(activity);
        options1Items = PickerManager.getInstance().getOptions1Items();
        options2Items = PickerManager.getInstance().getOptions2Items();
        options3Items = PickerManager.getInstance().getOptions3Items();
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();

        Calendar startDate = Calendar.getInstance();
        startDate.set(1970,0,23);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2100,11,28);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null

                /*btn_Time.setText(getTime(date));*/
                btnTime.setText(getTime(date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate,endDate)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @OnClick(R.id.btn_picker_time)
    void showTime(){
        pvTime.show();
    }

    @OnClick(R.id.btn_picker_province)
    void showOptions(){
        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);

                btnProvince.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }
}
