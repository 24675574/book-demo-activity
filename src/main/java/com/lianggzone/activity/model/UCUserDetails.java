package com.lianggzone.activity.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.lianggzone.activity.entity.SysRole;

/**
 * <h3>概要:</h3><p>UCUserDetails</p>
 * <h3>功能:</h3><p>UCUserDetails</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public class UCUserDetails {

    private UserInfo userInfo;
    private List<SysRole> authorities;

    public UCUserDetails(UserInfo userInfo, List<SysRole> roles) {
        Assert.notNull(userInfo, "userInfo cannot be null.");
        
        this.userInfo = userInfo;
        this.authorities = roles;
        if (this.authorities == null){
            this.authorities = new ArrayList<>();
        }    
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}
