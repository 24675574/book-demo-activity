package com.lianggzone.activity.config;

import com.alibaba.fastjson.JSONObject;
import com.lianggzone.activity.model.MailMessageModel;
import com.lianggzone.activity.utils.commons.LogUtil;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Component("mailMessageListenerAdapter")
public class MailMessageListenerAdapter extends MessageListenerAdapter {
 
    @Resource
    private JavaMailSender mailSender;
 
    @Value("${mail.username}")
    private String mailUsername;
 
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            // 解析RabbitMQ消息体
            String messageBody = new String(message.getBody());
            MailMessageModel mailMessageModel = JSONObject.toJavaObject(JSONObject.parseObject(messageBody), MailMessageModel.class);
            // 发送邮件
            String to =  mailMessageModel.getTo();
            String subject = mailMessageModel.getSubject();
            String text = mailMessageModel.getText();
            sendHtmlMail(to, subject, text);
            // 手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            LogUtil.getServerError("MailMessageListenerAdapter.onMessage",
                    ExceptionUtils.getStackTrace(e));
        }
    }
 
    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param text
     * @throws Exception
     */
    private void sendHtmlMail(String to, String subject, String text) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(mailUsername);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        // 发送邮件
        mailSender.send(mimeMessage);
    }
}