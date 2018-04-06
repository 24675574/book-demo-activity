package com.lianggzone.activity.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lianggzone.activity.entity.SysUser;

/**
 * <h3>概要:</h3><p>UserInfo</p>
 * <h3>功能:</h3><p>用户信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class UserInfo implements Serializable{

    private String userId;
    private String username;
    private boolean enable;
	private String userType;

	public List<SysUser> getUserRoles(){
		List<SysUser> roles = ((List<SysUser>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return roles;
	}
}