package com.mrxu.cloud.common.config;

/**
 * 常量
 * @author ifocusing-xuzhiwei
 * @since 2017/8/3
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    public static final String CURRENT_COMPANY_ID = "current_companyId";
    public static final String CURRENT_USER_LOGIN_NAME = "current_loginName";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    
    
    // 删除标记（0：正常；1：删除；2：审核；）
    public static final String DEL_FLAG = "delFlag";
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";
    
    
    //系统的文件服务类型
    public static final String FILESERVER_OSS="OSS";
    public static final String FILESERVER_FDFS="SDFS";
    
    
    public static final String ENCODE_TYPE = "UTF-8"; // 设置编码格式
    
    public static final String SNAPSHOTJOB_ACTION = "SubmitSnapshotJob";  // 截图作业
    public static final String TRANSCODING_ACTION = "SubmitJobs"; //转码作业
    
    
    public static final String SYS_DEFAULT_COMPANY_ID = "0";
    public static final String SYS_DEFAULT_FLOD_ID="0001";
    
    
    public static final String DEFAULT_APPLICATION_CACHE_KEY="DEFAULT_APPLICTION_ID";
    
    public static final String NODE_KEY_CACHE_VERIFY_CODE = "NODE_KEY_VERIFY_CODE_";
    public static final String DEFAULT_NODE_VERIFY_CODE = "NODE_DEFAULT_CODE";
}
