package com.lianggzone.activity.utils.exception;

import org.springframework.http.HttpStatus;

/**
 * <h3>概要:</h3><p>BusinessExceptionSupport</p>
 * <h3>功能:</h3><p>简单的异常类对象</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface BusinessExceptionSupport {

    /**
     * 获取异常信息的值对象
     * @return
     */
    public ErrorMessage getErrorMessage();

    /**
     * 获取Http请求的状态码
     * @return
     */
    public HttpStatus getStatus() ;
}
