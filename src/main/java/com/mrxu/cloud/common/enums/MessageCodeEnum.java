package com.mrxu.cloud.common.enums;

import com.mrxu.cloud.common.View;

import java.io.Serializable;

/**
 * @author ifocusing-xujia
 * @since 2017/9/26
 */
public enum MessageCodeEnum {

    SUCCESS(200,"操作成功"),
    ERROR(50000,"操作失败");

    private int code;
    private String msg;

    MessageCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 返回一个View对象
     * @return
     */
    public View toView(){
        View view = new View<>();
        view.setCode(this.code);
        view.setMessage(this.msg);
        return view;
    }

    /**
     * 返回一个View对象 -- 带提示信息message
     * 方法重载
     * @param message
     * @return
     */
    public View toView(String message){
        View view = new View<>();
        view.setCode(this.code);
        view.setMessage(message);
        return view;
    }

    /**
     * 返回一个View对象 --带返回数据data
     * 方法重载
     * @param data
     * @return
     */
    public View toView(Object data){
        View view = new View<>();
        view.setCode(this.code);
        if(null != data){
            view.setData((Serializable) data);
        }
        view.setMessage(this.msg);
        return view;
    }

    /**
     * 返回一个View对象 -- 带提示信息message、带返回数据data
     * 方法重载
     * @param message
     * @return
     */
    public View toView(Object data, String message){
        View view = new View<>();
        view.setCode(this.code);
        if(null != data){
            view.setData((Serializable) data);
        }
        view.setMessage(message);
        return view;
    }
}
