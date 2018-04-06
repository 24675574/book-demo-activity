package com.lianggzone.activity.entity;

import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lianggzone.activity.utils.validator.Groups;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotEmpty;

@Data
public class SysUser implements GrantedAuthority{
	
	private Long userId;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 10, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String username;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 10, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String realname;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    @JsonIgnore
    private String password;

    @Length(min = 1, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String email;

    @Length(min = 1, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String weixin;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 11, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String tel;

    @Range(min = 1, max = 2, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String sex;

    @Range(min = 1, max = 2, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private Integer enable;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @Override
    public String getAuthority() {
        return null;
    }
}