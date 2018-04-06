package com.lianggzone.activity.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.errorcode.ErrorMsg;

/**
 * <h3>概要:</h3><p>JsonException</p>
 * <h3>功能:</h3><p>JSON异常</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public class JsonException extends BusinessSimpleException {

    private static String prefix = "ACTIVITY/";

    public JsonException(ErrorCode errorCode) {
        super(HttpStatus.valueOf(errorCode.getValue()), prefix + errorCode.getCode(), 
        		ErrorMsg.getErrorMsg(errorCode.getCode()));
    }

    public JsonException(ErrorCode errorCode, Object... objects) {
        super(HttpStatus.valueOf(errorCode.getValue()), prefix + errorCode.getCode(), 
        		String.format(ErrorMsg.getErrorMsg(errorCode.getCode()), objects));
        
    }

    public JsonException(ErrorCode errorCode, FieldError fieldError) {
        super(HttpStatus.valueOf(errorCode.getValue()), prefix + fieldError.getDefaultMessage(),
        		String.format(ErrorMsg.getErrorMsg(errorCode.getCode()), new Object[]{fieldError.getField()}));
    }

    public JsonException(ErrorCode errorCode, Errors errors) {
        super(HttpStatus.valueOf(errorCode.getValue()), prefix + errorCode.getCode(), 
        		errors.getGlobalError().getDefaultMessage());
    }
}