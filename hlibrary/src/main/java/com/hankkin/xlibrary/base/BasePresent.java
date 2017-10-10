package com.hankkin.xlibrary.base;

/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class BasePresent<T>{
    private T view;

    public T getView() {
        return view;
    }

    public void attach(T view){
        this.view = view;
    }

    public void detach(){
        this.view = null;
    }
}
