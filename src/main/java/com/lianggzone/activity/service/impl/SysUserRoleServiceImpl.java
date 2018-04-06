package com.lianggzone.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianggzone.activity.dao.SysUserRoleMapper;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserCriteria;
import com.lianggzone.activity.entity.SysUserRole;
import com.lianggzone.activity.entity.SysUserRoleCriteria;
import com.lianggzone.activity.service.SysRoleService;
import com.lianggzone.activity.service.SysUserRoleService;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>SysUserRoleServiceImpl</p>
 * <h3>功能:</h3><p>用户角色 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
    private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
    private SysUserService sysUserService;
	@Autowired
    private SysRoleService sysRoleService;
	
	@Override
	public long insert(Long userId, Integer roleId) {
		SysUserRole record = new SysUserRole();
		record.setUserId(userId);
		record.setRoleId(roleId);
		this.sysUserRoleMapper.insertSelective(record);
		return record.getId();
	}
	
	@Override
	public int delete(Long userId, Integer roleId) {
		SysUserRoleCriteria sysUserRoleCriteria = new SysUserRoleCriteria();
		SysUserRoleCriteria.Criteria criteria = sysUserRoleCriteria.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andRoleIdEqualTo(roleId);
		return this.sysUserRoleMapper.deleteByExample(sysUserRoleCriteria);
	}
	
	@Override
	public SysUserRole findUserRole(Long userId, Integer roleId){
		SysUserRoleCriteria sysUserRoleCriteria = new SysUserRoleCriteria();
		SysUserRoleCriteria.Criteria criteria = sysUserRoleCriteria.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andRoleIdEqualTo(roleId);
		List<SysUserRole> list = this.sysUserRoleMapper.selectByExample(sysUserRoleCriteria);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	@Override
	public SysUserRole checkUserRole(Long userId, Integer roleId){
		SysUserRole sysUserRole = findUserRole(userId, roleId);
		if(sysUserRole == null){
			throw new JsonException(ErrorCode.USER_ROLE_NOT_FOUND);  
		}
		return sysUserRole;
	}
	
	@Override
	public List<SysRole> findUserRoleList(Long userId) {
		List<SysRole> sysRoleList = new ArrayList<SysRole>();

		SysUser sysUser = this.sysUserService.findSysUserInfo(userId);
		if(sysUser==null){
			return sysRoleList;
		}else{
			// 查询用户角色关联列表
			SysUserRoleCriteria userRoleCriteria = new SysUserRoleCriteria();
			SysUserRoleCriteria.Criteria criteria = userRoleCriteria.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<SysUserRole> sysUserRole = this.sysUserRoleMapper.selectByExample(userRoleCriteria);
			// 查询角色ID列表
			List<Integer> roleIdList = new ArrayList<Integer>(sysUserRole.size());
			for (SysUserRole userRole : sysUserRole) {
				roleIdList.add(userRole.getRoleId());
			}
			// 查询角色列表
			if(CollectionUtils.isNotEmpty(roleIdList)){
				SysRoleCriteria roleCriteria = new SysRoleCriteria();
				SysRoleCriteria.Criteria criteria2 = roleCriteria.createCriteria();
				criteria2.andRoleIdIn(roleIdList);
				return this.sysRoleService.findSysRoleList(roleCriteria);
			}else{
				return new ArrayList<SysRole>();
			}
			
		}
	}
	
	@Override
	public List<SysUser> findUserRoleList(Integer roleId, int offset, int limit) {
		// 查询用户角色关联列表
		SysUserRoleCriteria userRoleCriteria = new SysUserRoleCriteria();
		SysUserRoleCriteria.Criteria criteria = userRoleCriteria.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<SysUserRole> sysUserRole = this.sysUserRoleMapper.selectByExample(userRoleCriteria);
		// 查询用户ID列表
		List<Long> userIdList = new ArrayList<Long>(sysUserRole.size());
		for (SysUserRole userRole : sysUserRole) {
			userIdList.add(userRole.getUserId());
		}
		// 查询用户列表
		SysUserCriteria userCriteria = new SysUserCriteria();
		SysUserCriteria.Criteria criteria2 = userCriteria.createCriteria();
		if(CollectionUtils.isNotEmpty(userIdList)){
			criteria2.andUserIdIn(userIdList);
			userCriteria.setLimitStart(offset);
			userCriteria.setLimitEnd(limit);
			return this.sysUserService.findSysUserList(userCriteria);	
		}else{
			return new ArrayList<SysUser>();
		}
		
	}

	@Override
	public int countUserRoleList(Integer roleId) {
		SysUserRoleCriteria userRoleCriteria = new SysUserRoleCriteria();
		SysUserRoleCriteria.Criteria criteria = userRoleCriteria.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return this.sysUserRoleMapper.countByExample(userRoleCriteria);
	}
}