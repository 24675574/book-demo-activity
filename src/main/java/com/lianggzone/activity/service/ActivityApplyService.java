package com.lianggzone.activity.service;

import com.lianggzone.activity.entity.ActivityApply;
import com.lianggzone.activity.entity.ActivityApplyCriteria;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityApplyService</p>
 * <h3>功能:</h3><p>活动报名 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface ActivityApplyService {
	long insert(ActivityApply record);
}