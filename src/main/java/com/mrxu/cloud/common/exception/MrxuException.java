package com.mrxu.cloud.common.exception;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public class MrxuException extends RuntimeException {
    private static final long serialVersionUID = 308480494588096662L;

    //错误代码
    private int code = 0;
    private String command;

    public MrxuException(String message) {
        super(message);
    }

    public MrxuException(String message, Throwable cause) {
        super(message, cause);
    }

    public MrxuException(int code, String message) {
        super(code + "@@@@@" + message);
        this.code = code;
    }

    public MrxuException(MrxuExceptionEnums exception) {
        super(exception.getCode() + "@@@@@" + exception.getMessage());
        this.code = exception.getCode();
    }

    public MrxuException(MrxuExceptionEnums exception, String message) {
        super(exception.getCode() + "@@@@@" + message);
        this.code = exception.getCode();
    }

    public MrxuException(MrxuExceptionEnums exception, Throwable cause) {
        super(exception.getCode() + "@@@@@" + exception.getMessage(), cause);
        this.code = code;
    }

    public MrxuException(int code, String message, Throwable cause) {
        super(code + "@@@@@" + message, cause);
        this.code = code;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
