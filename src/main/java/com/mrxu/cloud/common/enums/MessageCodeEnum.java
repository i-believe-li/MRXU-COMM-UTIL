package com.mrxu.cloud.common.enums;


import com.mrxu.cloud.common.View;

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

    public View toView(){
        View view = new View<>();
        view.setCode(this.code);
        view.setMessage(this.msg);
        return view;
    }
}
