package com.lianggzone.activity.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <h3>概要:</h3><p>MailMessageModel</p>
 * <h3>功能:</h3><p>邮件信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class MailMessageModel {
    @JSONField(name = "from")
    private String from;
 
    @JSONField(name = "to")
    private String to;
 
    @JSONField(name = "subject")
    private String subject;
 
    @JSONField(name = "text")
    private String text;
 
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Email{from:").append(this.from).append(", ");
        sb.append("to:").append(this.to).append(", ");
        sb.append("subject:").append(this.subject).append(", ");
        sb.append("text:").append(this.text).append("}");
        return sb.toString();
    }
}