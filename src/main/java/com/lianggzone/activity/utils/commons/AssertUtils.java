package com.lianggzone.activity.utils.commons;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>AssertUtils</p>
 * <h3>功能:</h3><p>AssertUtils</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public abstract class AssertUtils {

    private AssertUtils(){}
    
	/**
	 * 断言 为空
	 * @param str
	 * @param message
	 */
	public static void isEmpty(String str, String message) {
	    if(StringUtils.isBlank(str)){
		    throw new JsonException(ErrorCode.REQUIRE_ARGUMENT, new Object[]{message});
		}
	}
    
    /**
     * 断言  数字为2以内
     * @param str
     * @return boolean
     */
    public static void isNumberWith2(String str, String message) {
        if (!str.matches("^[1-2]$")){
            throw new JsonException(ErrorCode.INVALID_ARGUMENT, new Object[]{message});
        }
    }
}
