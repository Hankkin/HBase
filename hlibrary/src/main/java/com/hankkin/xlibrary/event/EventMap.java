package com.hankkin.xlibrary.event;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Hankkin on 16/12/23.
 * 注释:时间总线类
 */

public class EventMap {

    //事件总线基类
    public static class BaseEvent{
        public String code;        //错误码

        public String message;     //错误信息
    }

    //错误类存储器
    public static HashMap<String,String> ERROR_MAPS = new HashMap<>();
    //本地存储各种错误信息
    static {
        ERROR_MAPS.put("-1", "上传失败");
        ERROR_MAPS.put("0", "连接超时，请检查网络后重试");
        ERROR_MAPS.put("1", "服务器内部错误,请重试");
        ERROR_MAPS.put("119", "客户端没有权限执行该项操作");
        ERROR_MAPS.put("127", "手机号无效，尚未发送验证码");
        ERROR_MAPS.put("206", "操作失败");
        ERROR_MAPS.put("210", "密码不正确，请重新输入");
        ERROR_MAPS.put("211", "用户不存在，请重新输入");
        ERROR_MAPS.put("213", "该手机号尚未注册");
        ERROR_MAPS.put("214", "该手机号已经被注册，请更换手机号重新注册");
        ERROR_MAPS.put("215", "该手机号尚未验证，无法修改密码");
        ERROR_MAPS.put("601", "发送短信验证码过快，请稍后重试");
    }

    /**
     * 根据错误码返回错误信息
     * @param code  错误码
     * @return
     */
    public static String pickMessage(String code){
        if (TextUtils.isEmpty(code)){
            return null;
        }
        if (ERROR_MAPS.containsKey(code))
            return ERROR_MAPS.get(code);
        return null;
    }

    /**
     * 错误异常事件
     */
    public static class HExceptionEvent extends BaseEvent{
        public boolean isPickedMessage = false;

        public HExceptionEvent(String message){
            this.message = message;
            this.isPickedMessage = true;
        }

        public HExceptionEvent(int code,String message){
            this.code = String.valueOf(code);
            this.isPickedMessage = ERROR_MAPS.containsKey(this.code);
            String pick = pickMessage(this.code);
            this.message = TextUtils.isEmpty(pick) ? message : pick;
        }
    }


    /**-----------华丽的分割线,你可以在下面定义你的事件继承BaseEvent----------------*/
}
