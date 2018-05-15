package com.mrxu.cloud.common;

import java.io.Serializable;

/**
 * @author ifcousing-linfeng
 * @since 2017/4/18
 */
public class View<T extends Serializable> {

    public static final Integer OK = 200;
    public static final Integer ERROR = 5000;
    
    private Integer code;
    private String message;
    private T data;
    private String timestamp;
    private String sign;
    

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
    
}

