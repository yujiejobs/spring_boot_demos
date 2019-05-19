package com.san.common.exception;

import com.google.common.collect.Maps;
import com.san.common.base.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.san.common.enums.SystemErrorCodeEnum.SYSTEM_ERROR;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.exception
 * @className GlobalExceptionHandler
 * @description 系统全局异常处理
 * @date 2019/05/19 23:03
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String parameter = StringUtils.trimToNull(request.getParameter(name));
            if (parameter != null) {
                log.error("allExceptionHandler key {} value {}", name, parameter);
            }
        }
        String url = request.getRequestURI();
        log.error("system exception,url:[{}],msg:", url, exception);
        return Message.error("system.error");
    }


    /**
     * 参数校验异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Message handleBindException(BindException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return Message.errorData(SYSTEM_ERROR.getDesc(), getMap(fieldErrors));
    }


    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return Message.errorData(SYSTEM_ERROR.getDesc(), getMap(bindingResult.getFieldErrors()));
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message bizException(BizException e) {
        log.error("", e);
        return Message.error(e.getErrorCode(), e.getShowMessage(), e.getApiMessage());
    }


    private Map<String, String> getMap(List<FieldError> fieldErrors) {
        Map<String, String> map = Maps.newHashMap();
        Iterator var4 = fieldErrors.iterator();

        while (var4.hasNext()) {
            FieldError f = (FieldError) var4.next();
            map.put(f.getField(), f.getDefaultMessage());
        }
        return map;
    }

}

