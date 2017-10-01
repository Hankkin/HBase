package com.hankkin.xlibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected LayoutInflater inflater;
    private View contentView;
    private ViewGroup container;
    protected String TAG;
    protected Activity activity;
    protected LayoutInflater mInflater;
    private ProgressDialog dialog;

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = this.getClass().getSimpleName();
        activity = (Activity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this,convertView);
        initViewsAndEvents(convertView);
        isInitView = true;
        lazyLoadData();
        return convertView;
    }

    protected abstract void initViewsAndEvents(View view);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();

        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (isFirstLoad) {
            Log.e(">>>>>","第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        } else {
            Log.e(">>>>>","不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            Log.e(">>>>>","不加载" + "   " + this.getClass().getSimpleName());
            return;
        }
        initData();
        isFirstLoad = false;
    }

    protected void initSwipe(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener listener){
        refreshLayout.setOnRefreshListener(listener);
        refreshLayout.setColorSchemeColors(Color.rgb(32, 140, 89));
        refreshLayout.setRefreshing(true);
    }

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView = null;
        container = null;
        inflater = null;
    }

    public static String getMoneyFormatInt(double money) {
        DecimalFormat df = new DecimalFormat();
        String pattern = "###,##0";
        df.applyPattern(pattern);
        if (money != 0) {
            return df.format(money);
        } else {
            return "0";
        }
    }


    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void showLoadingDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(activity);
        }
        dialog.show();
    }

    public void dismissLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz,boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public  void gotoActivity(Class<?> clz,boolean isCloseCurrentActivity,Bundle ex) {
        Intent intent=new Intent(getActivity(), clz);
        if(ex!=null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            getActivity().finish();
        }
    }

}
