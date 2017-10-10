package com.hankkin.xlibrary.http;

import com.hankkin.xlibrary.bean.BaseBean;

/**
 * Created by hankkin on 2017/10/10.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class HttpResult<T> extends BaseBean {

    private int code;//错误码

    private String msg;//错误信息

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
