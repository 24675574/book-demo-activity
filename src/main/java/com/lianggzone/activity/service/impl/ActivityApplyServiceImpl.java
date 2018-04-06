package com.lianggzone.activity.service.impl;

import com.lianggzone.activity.dao.ActivityApplyMapper;
import com.lianggzone.activity.dao.SysRoleMapper;
import com.lianggzone.activity.entity.ActivityApply;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.service.ActivityApplyService;
import com.lianggzone.activity.service.EmailService;
import com.lianggzone.activity.service.SysRoleService;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityApplyServiceImpl</p>
 * <h3>功能:</h3><p>活动报名 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {
	@Autowired
    private ActivityApplyMapper activityApplyMapper;

	@Override
	public long insert(ActivityApply record) {
		Date date = new Date();
		record.setCreateTime(date);
		record.setUpdateTime(date);
		this.activityApplyMapper.insertSelective(record);
		return record.getId();
	}
}