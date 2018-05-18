package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public enum MrxuExceptionEnums {
    //成功
    RC_OK(200, "成功"),
    RC_COMMON_ERROR(5000, "服务器未响应"),
    ;
    private int code;
    private String message;

    MrxuExceptionEnums(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
