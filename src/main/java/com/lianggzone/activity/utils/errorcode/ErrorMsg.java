package com.lianggzone.activity.utils.errorcode;

/**
 * <h3>概要:</h3><p>ErrorMsg</p>
 * <h3>功能:</h3><p>错误枚举</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 *
 * @author 粱桂钊
 * @since 0.1
 */
public enum ErrorMsg {

    BAD_REQUEST("BAD_REQUEST", "无效请求"),
    REQUIRE_ARGUMENT("REQUIRE_ARGUMENT", "缺少参数%s"),
    INVALID_ARGUMENT("INVALID_ARGUMENT", "%s参数错误"),
    REPEAT_ARGUMENT("REPEAT_ARGUMENT", "%s参数重复"),
    UNAUTHORIZED("UNAUTHORIZED", "未授权(默认)"),
    AUTH_DENIED("AUTH_DENIED", "授权受限（无权限或IP地址受限等）"),
    ACCESS_DENIED("ACCESS_DENIED", "不允许访问"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "服务器错误"),

    DENTRY_NOT_FOUND("DENTRY_NOT_FOUND", "文件未找到"),

    USER_NOT_FOUND("USER_NOT_FOUND", "用户未找到"),
    USER_HAS_EXIST("USER_HAS_EXIST", "用户已存在"),

    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "角色未找到"),
    ROLE_HAS_EXIST("ROLE_HAS_EXIST", "角色已存在"),

    USER_ROLE_NOT_FOUND("USER_ROLE_NOT_FOUND", "用户角色未找到"),
    USER_ROLE_HAS_EXIST("USER_ROLE_HAS_EXIST", "用户角色已存在"),

    APPLY_HAS_EXIST("APPLY_HAS_EXIST", "报名已存在"),

    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "评论未找到"),
    ;

    private final String value;
    private final String msg;

    ErrorMsg(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return this.value;
    }

    public String getMsg() {
        return this.msg;
    }

    public static String getErrorMsg(String value) {
        for (ErrorMsg errorMsg : ErrorMsg.values()) {
            if (errorMsg.getValue().equals(value)) {
                return errorMsg.getMsg();
            }
        }
        return null;
    }
}