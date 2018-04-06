package com.lianggzone.activity.utils.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * <h3>概要:</h3><p>ErrorCode</p>
 * <h3>功能:</h3><p>错误信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class ErrorMessage{

    /** 编码的Code */
    private String code;
    
    /** 错误信息的message信息 */
    private String message;
    
    /** 服务器端错误发生的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date serverTime;
    
    /** 异常堆栈信息 */
    private String stackTrace;
    
    public ErrorMessage() {}
    
    public ErrorMessage(String code) {
        this.code = code;
    }
    
    public ErrorMessage(String code, String message) {
        this.message = message;
        this.code = code;
    }
}