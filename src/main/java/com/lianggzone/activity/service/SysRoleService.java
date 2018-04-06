package com.lianggzone.activity.service;

import java.util.List;

import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;

/**
 * <h3>概要:</h3><p>SysRoleService</p>
 * <h3>功能:</h3><p>角色 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface SysRoleService {
	
	/**
	 * 新增角色
	 * @param record
	 * @return
	 */
	int insert(SysRole record);
	
	/**
	 * 修改角色
	 * @param record
	 * @return
	 */
	int update(SysRole record);
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 获取角色信息
	 * @param id
	 * @return
	 */
	public SysRole findSysRoleInfo(Integer id);
	
	/**
	 * 获取角色信息
	 * @param id
	 * @return
	 */
	public SysRole checkSysRoleInfo(Integer id);
	
	/**
	 * 获取角色列表
	 * @param criteria
	 * @return
	 */
    List<SysRole> findSysRoleList(SysRoleCriteria criteria);  
    
    /**
	 * 获取角色总数
	 * @param criteria
	 * @return
	 */
    int countSysRoleList(SysRoleCriteria criteria); 
}