package com.lianggzone.activity.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>概要:</h3><p>EnableEnum</p>
 * <h3>功能:</h3><p>启用状态枚举</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 *
 * @author 粱桂钊
 * @since 0.1
 */
public enum EnableEnum {
    /** 启用 */
    ENABLE(1),
    /** 禁用 */
    DISABLE(2);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    EnableEnum(Integer value) {
        this.value = value;
    }

    /**
     * 获取枚举正则表达式
     *
     * @return
     */
    public static String getRegex() {
        List<String> list = new ArrayList<String>();
        for (EnableEnum e : EnableEnum.values()) {
            list.add(e.getValue() + "");
        }
        return StringUtils.join(list, "|");
    }
}
