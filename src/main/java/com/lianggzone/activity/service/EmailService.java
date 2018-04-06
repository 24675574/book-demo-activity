package com.lianggzone.activity.service;

/**
 * <h3>概要:</h3><p>EmailService</p>
 * <h3>功能:</h3><p>邮件 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface EmailService {
    /**
     * 发送邮件任务存入消息队列
     * @param to
     * @param text
     * @throws Exception
     */
    void sendEmail(String to, String text) throws Exception;
}
