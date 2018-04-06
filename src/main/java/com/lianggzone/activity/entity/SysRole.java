package com.lianggzone.activity.entity;

import java.util.Date;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;

import com.lianggzone.activity.utils.validator.Groups;

import javax.validation.constraints.NotEmpty;

@Data
public class SysRole implements GrantedAuthority{
    private Integer roleId;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String roleName;

    @Range(min = 1, max = 2, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private Integer enable;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 120, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String remark;

    private Date updateTime;
    
    @Override
    public String getAuthority() {
        return roleName;
    }
}