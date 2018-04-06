package com.lianggzone.activity.model;

import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lianggzone.activity.utils.validator.Groups;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * <h3>概要:</h3><p>SysUserBO</p>
 * <h3>功能:</h3><p>用户信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class SysUserBO {
	
    private Long userId;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 0, max = 10, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String username;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 10, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String realname;
    
    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 0, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String password;

    @Length(min = 0, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String email;

    @Length(min = 0, max = 32, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String weixin;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 0, max = 11, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String tel;

    @Range(min = 1, max = 2, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String sex;

    @Range(min = 1, max = 2, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private Integer enable;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}