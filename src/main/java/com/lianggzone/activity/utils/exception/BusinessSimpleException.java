package com.lianggzone.activity.utils.exception;

import org.springframework.http.HttpStatus;

/**
 * <h3>概要:</h3><p>BusinessSimpleException</p>
 * <h3>功能:</h3><p>简单的异常类对象</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public class BusinessSimpleException extends BusinessException implements BusinessExceptionSupport {

    private String code = "ACTIVITY/INTERNAL_SERVER_ERROR";
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public BusinessSimpleException(String message) {
		super(message);
	}
	
	public BusinessSimpleException(String code, String message) {	
		super(message);
		this.code = code;
	}
	
	public BusinessSimpleException(HttpStatus status, String code, String message) {
		super(message);
		this.code = code;
		this.status = status;
	}

	@Override
	public ErrorMessage getErrorMessage() {
		ErrorMessage em = new ErrorMessage();
		em.setCode(code);
		em.setMessage(super.getMessage());
		return em;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}
}