package com.lianggzone.activity.controller.admin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.SysUserRole;
import com.lianggzone.activity.service.SysUserRoleService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>SysUserRoleMController</p>
 * <h3>功能:</h3><p>用户角色控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/m/users"})
public class SysUserRoleMController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserRoleMController.class);
	
	@Resource
	private SysUserRoleService sysUserRoleService;

	/**
	 * 新增用户角色
	 * @sysUserRole userId
	 * @sysUserRole roleId
	 * @return
	 */
	@RequestMapping(value={"/{userId:\\d+}/sys_roles/{roleId:\\d+}"}, method=RequestMethod.POST)
	public SysUserRole insert(@PathVariable long userId, @PathVariable int roleId){
		try {
			this.sysUserRoleService.insert(userId, roleId);
			return this.sysUserRoleService.checkUserRole(userId, roleId);
		} catch (DuplicateKeyException e) {
			logger.error("methodName:{}, cause:{}", new Object[] {"SysUserRoleController.insert", e});
			throw new JsonException(ErrorCode.USER_ROLE_HAS_EXIST); 
		}
	}
	
	/**
	 * 修改用户角色
	 * @sysUserRole userId
	 * @sysUserRole roleId
	 * @return
	 */
	@RequestMapping(value={"/{userId:\\d+}/sys_roles/{roleId:\\d+}"}, method=RequestMethod.DELETE)
	public void delete(@PathVariable long userId, @PathVariable int roleId){
		this.sysUserRoleService.delete(userId, roleId);
	}
}