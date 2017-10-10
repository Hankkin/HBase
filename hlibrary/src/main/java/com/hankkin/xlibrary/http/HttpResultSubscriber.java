package com.hankkin.xlibrary.http;


import java.net.ConnectException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;


/**
 * Created by hankkin on 2017/10/10.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {


    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        switch (tHttpResult.getCode()){//根据状态码做相应处理
            case 200:
                onSuccess(tHttpResult.getData());
                break;
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {//错误处理
        e.printStackTrace();
        if (e instanceof ConnectException
                ||e instanceof SocketException
                ||e instanceof TimeoutException){
            onError(-1000);
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int code);
}
