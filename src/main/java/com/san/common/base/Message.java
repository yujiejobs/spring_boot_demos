package com.san.common.base;

import com.san.common.enums.SystemErrorCodeEnum;
import com.san.utils.SpringContextUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;

import static com.san.common.enums.SystemErrorCodeEnum.SYSTEM_ERROR;
import static com.san.common.enums.SystemErrorCodeEnum.SYSTEM_SUCCESS;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.base
 * @className Message
 * @description
 * @date 2019/05/19 21:16
 */
@Data
@ApiModel(description = "系统返回结果封装类")
public class Message implements Serializable {
    private static final long serialVersionUID = 4866502646431480808L;


    @ApiModelProperty("结果msg")
    private String content;

    @ApiModelProperty("结果状态码")
    private int code;

    @ApiModelProperty("返回的数据")
    private Object data;

    @ApiModelProperty("当前时间")
    private Date time = new Date();

    public Message() {

    }


    public Message(int code, String content, Object data, Object... args) {
        this.content = SpringContextUtil.getMessage(content, args);
        this.data = data;
        this.code = code;
    }


    public static Message successData(Object data) {
        return new Message(SYSTEM_SUCCESS.getCode(), SYSTEM_SUCCESS.getDesc(), data, null);
    }

    public static Message successData(String content, Object data, Object... args) {
        return new Message(SYSTEM_SUCCESS.getCode(), content, data, args);
    }

    public static Message successData(int code, String content, Object data, Object... args) {
        return new Message(code, content, data, args);
    }


    public static Message success() {
        return new Message(SYSTEM_SUCCESS.getCode(), SYSTEM_SUCCESS.getDesc(), null, null);
    }

    public static Message success(String content, Object... args) {
        return new Message(HttpServletResponse.SC_OK, content, null, args);
    }

    public static Message success(int code, String content, Object... args) {
        return new Message(code, content, null, args);
    }


    public static Message error() {
        return new Message(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getDesc(), null, null);
    }


    public static Message error(SystemErrorCodeEnum codeEnum, Object... args) {
        return new Message(codeEnum.getCode(), codeEnum.getDesc(), null, args);
    }


    public static Message error(int code, String content, Object... args) {
        return new Message(code, content, null, args);
    }

    public static Message error(String content, Object... args) {
        return new Message(SYSTEM_ERROR.getCode(), content, null, args);
    }


    public static Message errorData(String content, Object data, Object... args) {
        return new Message(SYSTEM_ERROR.getCode(), content, data, args);
    }

    public static Message errorData(int code, String content, Object data, Object... args) {
        return new Message(code, content, data, args);
    }


    public static Message error(int errorCode, Object[] showMessage, String apiMessage) {
        if (StringUtils.isNotBlank(apiMessage)) {
            return new Message(SYSTEM_ERROR.getCode(), apiMessage, null, new Object[]{});
        }
        if (showMessage == null) {
            showMessage = new Object[]{};
        }
        if (errorCode == 0) {
            errorCode = SYSTEM_ERROR.getCode();
        }
        return new Message(errorCode, SystemErrorCodeEnum.getEnumByCode(errorCode).getDesc(), null, showMessage);
    }


}
