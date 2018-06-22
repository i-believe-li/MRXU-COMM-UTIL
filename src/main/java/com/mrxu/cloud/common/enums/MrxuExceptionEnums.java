package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public enum MrxuExceptionEnums {
    //成功
    RC_OK(200, "成功"),
    //无客户端信息
    RC_NO_CLI_INFO(30000, "请求Header参数无客户端信息(CLI_INFO)"),
    //客户端信息格式不正确
    RC_CLI_INFO_FORMAT_ERROR(30001, "请求Header参数客户端信息(CLI_INFO)格式错误"),
    //参数不正确
    RC_BAD_PARAMS(40000, "参数不正确"),
    //未授权
    RC_UNAUTHORIZED(40001, "未授权"),
    //账户或密码错误
    RC_ACCOUNT_PWD_BAD(40002, "账户或密码错误"),
    //账户不存在
    RC_ACCOUNT_NOT_EXIST(40003, "账户不存在"),
    //资源（ID）不存在
    RC_BAD_ID(40004, "资源（ID）不存在"),
    //账户注册错误
    RC_ACCOUNT_ERROR(40005, "账户注册错误"),
    //令牌已失效
    RC_TOKEN_EXPIRE(40006, "令牌已失效"),
    //Header中 （CLI_INFO ）
    RC_CLIENT_TYPE_ERROR(40007, "请求Header参数错误(CLI_INFO -- clientType)"),
    RC_CLIENT_OS_ERROR(40008, "请求Header参数错误(CLI_INFO -- clientOs)"),
    RC_CLIENT_OS_UNSUPPORT(40008, "请求Header参数错误(CLI_INFO -- clientOs),不支持的操作系统！"),
    RC_CLIENT_VERSION_ERROR(40009, "请求Header参数错误(CLI_INFO -- clientVersion)"),
    RC_CLIENT_RESOLUTION_ERROR(40010, "请求Header参数错误(CLI_INFO -- clientResolution)"),
    RC_URL_NOT_EXIST_ERROR(40011, "请求的url限制，不能访问！"),
    RC_BAD_AUTH(40012, "用户没有此操作的权限"),
    RC_REQUEST_TIMEOUT(40013, "请求时间戳Timpstamp超时"),
    RC_REQUEST_BAD_SIGN(40014, "签名校验错误！"),
    RC_ACCOUNT_ALREADY_LOGGED_IN(40012, "当前用户在##ADDRESS##地方、名称为：##DEV##的设备上登陆了"),


    RC_SPC_RESOURCE_NOT_EXIST(60001, "资源不存在"),
    RC_SPC_BAD_HEADER_PARAMS(60002, "请求Header参数错误"),
    RC_SPC_BAD_REQUEST_PARAMS(60003, "请求Query参数格式错误"),
    RC_SPC_BAD_DATA(60004, "数据错误"),
    // 资源异常处理
    RC_SPC_RES_PUBLISHED(60005,"请求对象已发布"),
    RC_SPC_RES_DELETED(60006,"请求对象已删除"),
    RC_SPC_RES_CONFIG_ERROR(60007,"应用配置信息格式错误"),
    RC_SPC_RES_DEBUG(60008,"请求对象已是调试模式"),
    RC_SPC_RES_WITHDRAW(60009,"请求对象已撤回"),
    RC_SPC_RES_FORBID(60010,"请求对象已禁用"),
    RC_SPC_RES_NODE_NOT_EXIST(60011,"组网节点不存在"),
    //file

    RC_SPC_FILE_EXT_NOT_EXIST(60012,"上传文件没有后缀名"),
    RC_SPC_FILE_UPLOAD_ERROR(60013,"上传文件出错！"),
    RC_APP_MODULE_NOT_EXIST(60014,"请求的应用模块不存在！"),
    RC_APP_MODULE_TYPE_ERROR(60015,"无法识别的模块类型！"),
    RC_SPC_UNZIP_TYPE_ERROR(60016,"文件解压失败，请核对文件类型是否正确！"),
    RC_SPC_FILE_SIZE_ERROR(60017,"不能上传空文件！"),
    RC_SPC_FILE_SDF_ERROR(60018,"SDF文件校验错误！"),
    RC_SPC_FILE_NOT_EXIST(60019,"文件不存在!"),

    RC_SRV_ERR(50000, "服务器处理出错"),
    RC_COMMON_ERROR(50001,"业务常规错误！"),
    RC_LIMIT_ERR(50002,"服务器当前访问太多，请稍后再试！");

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
