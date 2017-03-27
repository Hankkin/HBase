package com.hankkin.xlibrary.adapter.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hankkin.xlibrary.adapter.ListItemCallBack;
import com.hankkin.xlibrary.adapter.SimpleItemCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hankkin on 2017/3/27.
 */

public abstract class HListAdapter<T> extends BaseAdapter {

    protected List<T> data = new ArrayList<>();
    private ListItemCallBack mCallBack;
    protected Context mContext;

    public HListAdapter(Context context) {
        mContext = context;
    }

    public HListAdapter(Context context,ListItemCallBack callBack) {
        this(context);
        mCallBack = callBack;
    }

    public HListAdapter(List<T> data, Context context) {
        mContext = context;
        this.data.clear();
        this.data.addAll(data);
    }


    public HListAdapter(List<T> data, ListItemCallBack callBack, Context context) {
        mCallBack = callBack;
        mContext = context;
        this.data.clear();
        this.data.addAll(data);
    }

    /**
     * 设置数据源 更新适配器
     * @param data
     */
    public void setData(List<T> data){
        this.data.clear();
        if (data != null){
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setData(T[] data){
        if (data != null && data.length > 0) {
            setData(Arrays.asList(data));
        }
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(T[] data) {
        addData(Arrays.asList(data));
    }

    /**
     * 删除元素
     *
     * @param element
     */
    public void removeElement(T element) {
        if (data.contains(element)) {
            data.remove(element);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除元素
     *
     * @param position
     */
    public void removeElement(int position) {
        if (data != null && data.size() > position) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(List<T> elements) {
        if (data != null && elements != null && elements.size() > 0
                && data.size() >= elements.size()) {

            for (T element : elements) {
                if (data.contains(element)) {
                    data.remove(element);
                }
            }

            notifyDataSetChanged();
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(T[] elements) {
        if (elements != null && elements.length > 0) {
            removeElements(Arrays.asList(elements));
        }
    }

    /**
     * 更新元素
     *
     * @param element
     * @param position
     */
    public void updateElement(T element, int position) {
        if (position >= 0 && data.size() > position) {
            data.set(position, element);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void addElement(T element) {
        if (element != null) {
            data.add(element);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加元素
     *
     * @param position
     * @param element
     */
    public void addElement(int position, T element) {
        if (element != null) {
            data.add(position, element);
            notifyDataSetChanged();
        }
    }

    /**
     * 清除数据源
     */
    public void clearData() {
        if (this.data != null) {
            this.data.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据源大小
     *
     * @return
     */
    public int getSize() {
        return data == null ? 0 : data.size();
    }

    public T getItemData(int position) {
        return data.get(position);
    }

    /**
     * 设置控件可见
     *
     * @param view
     */
    protected void setVisible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置控件不可见
     *
     * @param view
     */
    protected void setGone(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * 设置控件不可见
     *
     * @param view
     */
    protected void setInvisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置控件可用
     *
     * @param view
     */
    protected void setEnable(View view) {
        view.setEnabled(true);
    }

    /**
     * 设置控件不可用
     *
     * @param view
     */
    protected void setDisable(View view) {
        view.setEnabled(false);
    }

    /**
     * 获取图片资源
     *
     * @param resId
     * @return
     */
    protected Drawable getDrawable(int resId) {
        return mContext.getResources().getDrawable(resId);
    }

    /**
     * 获取字符串资源
     *
     * @param resId
     * @return
     */
    protected String getString(int resId) {
        return mContext.getResources().getString(resId);
    }

    /**
     * 获取颜色资源
     *
     * @param resId
     * @return
     */
    protected int getColor(int resId) {
        return mContext.getResources().getColor(resId);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public List<T> getDataSource() {
        return data;
    }

    public void setItemClick(ListItemCallBack recItemClick) {
        this.mCallBack = recItemClick;
    }

    public ListItemCallBack getItemClick() {
        return mCallBack;
    }

    public SimpleItemCallback getSimpleItemClick() {
        if (mCallBack instanceof SimpleItemCallback)
            return (SimpleItemCallback) mCallBack;
        return null;
    }


    @Override
    public int getCount() {
        return data == null || data.isEmpty() ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

}
