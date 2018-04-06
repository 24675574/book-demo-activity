package com.lianggzone.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianggzone.activity.dao.SysRoleMapper;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;
import com.lianggzone.activity.service.SysRoleService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>SysRoleServiceImpl</p>
 * <h3>功能:</h3><p>角色 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
    private SysRoleMapper sysRoleMapper;
	
	@Override
	public int insert(SysRole record) {
		this.sysRoleMapper.insertSelective(record);
		return record.getRoleId();
	}

	@Override
	public int update(SysRole record) {
		return this.sysRoleMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int delete(Integer id) {
		return this.sysRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SysRole findSysRoleInfo(Integer id) {
		return this.sysRoleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public SysRole checkSysRoleInfo(Integer id) {
		SysRole sysRole = findSysRoleInfo(id);
		if(sysRole==null){
			throw new JsonException(ErrorCode.ROLE_NOT_FOUND);  
		}
		return sysRole;
	}

	@Override
	public List<SysRole> findSysRoleList(SysRoleCriteria criteria) {
		return this.sysRoleMapper.selectByExample(criteria);
	}

	@Override
	public int countSysRoleList(SysRoleCriteria criteria) {
		return this.sysRoleMapper.countByExample(criteria);
	}	
}