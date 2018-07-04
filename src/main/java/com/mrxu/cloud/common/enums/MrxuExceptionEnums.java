package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public enum MrxuExceptionEnums {

    //成功
    RC_OK(200, "成功"),

    /**
     * 系统常见接口返回码值段 10000--14999
     */
    RC_NO_CLI_INFO(10000, "请求Header参数无客户端信息(CLI_INFO)"),
    RC_CLI_INFO_FORMAT_ERROR(10001, "请求Header参数客户端信息(CLI_INFO)格式错误"),
    RC_BAD_PARAMS(10002, "参数不正确"),
    RC_SPC_BAD_HEADER_PARAMS(10003, "请求Header参数错误"),
    RC_SPC_BAD_REQUEST_PARAMS(10004, "请求Query参数格式错误"),
    RC_SPC_BAD_DATA(10005, "数据错误"),
    RC_SRV_ERR(10006, "服务器处理出错"),
    RC_COMMON_ERROR(10007,"业务常规错误！"),
    RC_LIMIT_ERR(10008,"服务器当前访问太多，请稍后再试！"),


    /**
     * 用户服务接口返回码值段 15000 -- 19999
     */
    RC_UNAUTHORIZED(15000, "未授权"),
    RC_ACCOUNT_PWD_BAD(15001, "账户或密码错误"),
    RC_ACCOUNT_NOT_EXIST(15002, "账户不存在"),
    RC_ACCOUNT_ERROR(15003, "账户注册错误"),
    RC_TOKEN_EXPIRE(15004, "令牌已失效"),

    /**
     * 文件服务接口返回码值段 20000 -- 24999
     */
    RC_SPC_FILE_SERVER_EXCEPTION(20000,"文件服务器异常"),
    RC_SPC_FILE_EXT_NOT_EXIST(20001,"上传文件没有后缀名"),
    RC_SPC_FILE_UPLOAD_ERROR(20002,"上传文件出错"),
    RC_SPC_FILE_SIZE_ERROR(20003,"不能上传空文件"),
    RC_SPC_FILE_NOT_EXIST(20004,"文件不存在"),
    ;

    private int value;
    private String message;

    MrxuExceptionEnums(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getCode() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
