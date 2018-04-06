package com.lianggzone.activity.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.service.SysUserRoleService;

/**
 * <h3>概要:</h3><p>SysUserRoleController</p>
 * <h3>功能:</h3><p>用户角色控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c"})
public class SysUserRoleCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserRoleCController.class);
	
	@Resource
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value={"/users/{userId:\\d+}/sys_roles"}, method=RequestMethod.GET)
	public Map<String, Object> findSysRoleList(@PathVariable long userId){
		// 获取用户角色列表
	    List<SysRole> sysRoleList = this.sysUserRoleService.findUserRoleList(userId);
	    // 返回列表
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("items", sysRoleList);
	    return param;
	}
	
	/**
	 * 获取角色用户列表
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/sys_roles/{roleId:\\d+}/users"}, method=RequestMethod.GET)
	public Map<String, Object> findList(@PathVariable int roleId, HttpServletRequest request){
		// 获取分页参数并校验
        pagination.init(request);
		// 获取用户角色列表
	    List<SysUser> sysUserList = this.sysUserRoleService.findUserRoleList(roleId, pagination.getStart(), pagination.getSize());
	    // 返回列表
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("count", this.sysUserRoleService.countUserRoleList(roleId));
	    param.put("items", sysUserList);
	    return param;
	}
}