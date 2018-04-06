package com.lianggzone.activity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserCriteria;

/**
 * <h3>概要:</h3><p>SysUserService</p>
 * <h3>功能:</h3><p>用户 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface SysUserService extends UserDetailsService{
	
	/**
	 * 新增用户
	 * @param record
	 * @return
	 */
	long insert(SysUser record);
	
	/**
	 * 修改用户
	 * @param record
	 * @return
	 */
	int update(SysUser record);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int delete(Long id);
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public SysUser findSysUserInfo(Long id);
	
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	public SysUser findSysUserInfoByUsername(String username);
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public SysUser checkSysUserInfo(Long id);
	
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	public SysUser checkSysUserInfoByUsername(String username);
	
	
	/**
	 * 获取用户列表
	 * @param criteria
	 * @return
	 */
    List<SysUser> findSysUserList(SysUserCriteria criteria); 
    
    /**
	 * 获取用户总数
	 * @param criteria
	 * @return
	 */
    int countSysUserList(SysUserCriteria criteria);
}