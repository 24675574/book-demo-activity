package com.lianggzone.activity.enums;

/**
 * <h3>概要:</h3><p>RoleEnum</p>
 * <h3>功能:</h3><p>RoleEnum</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public enum RoleEnum {
    C("C"),
    M("M"),
    ADMIN("ADMIN");

    private String duty;
    
    public String getDuty() {
        return duty;
    }

    private RoleEnum(String duty) {
        this.duty = duty;
    }
}
