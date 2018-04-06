package com.lianggzone.activity.utils.errorcode;


/**
 * <h3>概要:</h3><p>ErrorCode</p>
 * <h3>功能:</h3><p>错误枚举</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 *
 * @author 粱桂钊
 * @since 0.1
 */
public enum ErrorCode {
    // 无效请求
    BAD_REQUEST(400, "BAD_REQUEST"),
    // 缺少参数
    REQUIRE_ARGUMENT(400, "REQUIRE_ARGUMENT"),
    // 无效参数(格式不对,长度过长或过短等)
    INVALID_ARGUMENT(400, "INVALID_ARGUMENT"),
    // 未授权(默认)
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    // 授权受限（无权限或IP地址受限等）
    AUTH_DENIED(403, "AUTH_DENIED"),
    // 不允许访问
    ACCESS_DENIED(403, "ACCESS_DENIED"),
    // 服务器错误
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),

    // 用户未找到
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    // 用户已存在
    USER_HAS_EXIST(409, "USER_HAS_EXIST"),

    // 系统角色未找到
    ROLE_NOT_FOUND(404, "ROLE_NOT_FOUND"),
    // 角色已存在
    ROLE_HAS_EXIST(409, "ROLE_HAS_EXIST"),

    // 用户角色未找到
    USER_ROLE_NOT_FOUND(404, "USER_ROLE_NOT_FOUND"),
    // 用户角色已存在
    USER_ROLE_HAS_EXIST(409, "USER_ROLE_HAS_EXIST"),

    // 报名已存在
    APPLY_HAS_EXIST(409, "APPLY_HAS_EXIST"),

    // 评论未找到
    COMMENT_NOT_FOUND(404, "COMMENT_NOT_FOUND"),
    ;

    private final int value;
    private final String code;

    ErrorCode(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public int getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }
}
