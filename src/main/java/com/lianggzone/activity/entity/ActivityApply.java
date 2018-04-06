package com.lianggzone.activity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lianggzone.activity.utils.validator.Groups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ActivityApply {

    private Long id;

    private Long activityId;

    private Long userId;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 2, max = 20, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String userName;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 1, max = 11, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String phoneNum;

    @Length(min = 5, max = 30, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String companyName;

    @Length(min = 1, max = 10, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String companyPosition;

    @Length(min = 1, max = 640, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String attachmentDentryIds;

    @JsonIgnore
    private Long verifyUserId;

    @Length(min = 1, max = 255, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String verifyResult;

    @Range(min = 1, max = 3, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private Integer status;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}