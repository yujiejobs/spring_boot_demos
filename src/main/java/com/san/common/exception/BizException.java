package com.san.common.exception;

import com.san.common.enums.SystemErrorCodeEnum;
import lombok.Data;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.exception
 * @className BizException
 * @description 业务异常
 * @date 2019/05/19 23:02
 */
@Data
public class BizException extends RuntimeException {

    /**
     * 错误异常Code SystemErrorCodeEnum code 一一对应
     */
    private int errorCode;

    /**
     * properties {1} 展示用
     */
    private String[] showMessage;
    /**
     * 自己的日常 打log 用
     */
    private String message;
    /**
     * api接口返回直接给前台展示用的Message
     */
    private String apiMessage;

    public BizException(int errorCode, String[] showMessages, String message) {
        super(message);
        this.errorCode = errorCode;
        this.showMessage = showMessages;
        this.message = message;
    }

    public BizException() {
        super();
    }

    public BizException(int errorCode, String[] showMessages) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.showMessage = showMessages;
    }

    public BizException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BizException(String apiMessage) {
        super(apiMessage);
        this.apiMessage = apiMessage;
    }

    public BizException(SystemErrorCodeEnum sece) {
        super(sece.getDesc());
        this.errorCode = sece.getCode();
        this.message = sece.getDesc();
    }
}

