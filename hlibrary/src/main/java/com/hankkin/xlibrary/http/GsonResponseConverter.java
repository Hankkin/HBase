package com.hankkin.xlibrary.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by hankkin on 2017/10/10.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class GsonResponseConverter<T> implements Converter<ResponseBody,T> {

    private Gson gson;
    private Type type;

    public GsonResponseConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String str = value.toString();
        HttpResult result = gson.fromJson(str,HttpResult.class);
        if (result.getCode() == 200){
            return gson.fromJson(str,type);
        }
        else {
//            ErrorResponse errorResponse = gson.fromJson(str,)
        }
        return null;
    }
}
