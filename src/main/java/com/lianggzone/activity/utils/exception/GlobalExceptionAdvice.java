package com.lianggzone.activity.utils.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lianggzone.activity.utils.errorcode.ErrorMsg;
/**
 * <h3>概要:</h3><p>GlobalExceptionAdvice</p>
 * <h3>功能:</h3><p>GlobalExceptionAdvice</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@ControllerAdvice
@RestController
public class GlobalExceptionAdvice {
	
	@ExceptionHandler(value = JsonException.class)
	public ErrorMessage jsonErrorHandler(HttpServletRequest request, HttpServletResponse response, JsonException ex) {
		response.setStatus(ex.getStatus().value());
		ErrorMessage errorMessage = ex.getErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStackTrace(ExceptionUtils.getMessage(ex));
		errorMessage.setServerTime(DateTime.now().toDate());
		return errorMessage;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
	@ExceptionHandler(value = Exception.class)
    public ErrorMessage defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {		
		ErrorMessage errorMessage = new ErrorMessage("ACTIVITY/INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorMessage.setMessage(ErrorMsg.INTERNAL_SERVER_ERROR.getMsg());
		errorMessage.setStackTrace(ExceptionUtils.getMessage(e));
		errorMessage.setServerTime(DateTime.now().toDate());
		return errorMessage;
    }
}