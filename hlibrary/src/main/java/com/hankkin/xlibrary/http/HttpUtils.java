package com.hankkin.xlibrary.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hankkin on 2017/10/10.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class HttpUtils {

    private static HttpUtils mInstance = null;

    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }


    public <C> C createService(Class<C> cClass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(getOkHttpClient())
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(cClass);
    }

    /**
     * 配置okhttp
     */
    private OkHttpClient getOkHttpClient(){
        //创建OKHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new ReceivedCookiesInterceptor(AppManage.getInstance()))
//                .addInterceptor(new AddCookiesInterceptor(AppManage.getInstance()))
//                .addInterceptor(interceptorHeaders)
//            .sslSocketFactory(getSSLSocket(AppManage.getInstance(), cer))
                .build();
        return okHttpClient;
    }
}
