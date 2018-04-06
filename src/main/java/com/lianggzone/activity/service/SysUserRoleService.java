package com.lianggzone.activity.service;

import java.util.List;

import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserRole;

/**
 * <h3>概要:</h3><p>SysUserRoleService</p>
 * <h3>功能:</h3><p>用户角色 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface SysUserRoleService {
	
	/**
	 * 新增用户角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	long insert(Long userId, Integer roleId);
	
	/**
	 * 删除用户角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int delete(Long userId, Integer roleId);
	
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public SysUserRole findUserRole(Long userId, Integer roleId);  
	
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public SysUserRole checkUserRole(Long userId, Integer roleId);  
	
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @return
	 */
	public List<SysRole> findUserRoleList(Long userId);  
	
	/**
	 * 获取角色用户列表
	 * @param roleId
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<SysUser> findUserRoleList(Integer roleId, int offset, int limit); 
	
	/**
	 * 获取角色用户总数
	 * @param roleId
	 * @return
	 */
	public int countUserRoleList(Integer roleId); 
}