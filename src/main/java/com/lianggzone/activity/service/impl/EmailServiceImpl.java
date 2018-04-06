package com.lianggzone.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lianggzone.activity.service.EmailService;
import com.lianggzone.activity.utils.commons.LogUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <h3>概要:</h3><p>EmailServiceImpl</p>
 * <h3>功能:</h3><p>邮件 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
 
    @Resource( name = "rabbitTemplate" )
    private RabbitTemplate rabbitTemplate;
 
    @Value("${mq.exchange}")
    private String exchange;
 
    @Value("${mq.routekey}")
    private String routeKey;
 
    @Override
    public void sendEmail(String to, String text) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to", to);
        jsonObject.put("subject", "活动报名通知");
        String message = "<html><head></head><body><h1>活动报名通知</h1><p>"+text+"</p></body></html>";
        jsonObject.put("text", message);
        try {
            rabbitTemplate.convertAndSend(exchange, routeKey, jsonObject.toJSONString());
        }catch (Exception e){
            LogUtil.getServerError("EmailServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
        }
    }
}
