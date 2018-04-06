package com.lianggzone.activity.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;
import com.lianggzone.activity.service.SysRoleService;
import com.lianggzone.activity.utils.commons.AssertUtils;

/**
 * <h3>概要:</h3><p>SysRoleController</p>
 * <h3>功能:</h3><p>角色控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/sys_roles"})
public class SysRoleCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(SysRoleCController.class);
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 获取角色信息
	 * @param roleId
	 */
	@RequestMapping(value={"/{roleId:\\d+}"}, method=RequestMethod.GET)
	public SysRole findOne(@PathVariable int roleId){  
		return this.sysRoleService.checkSysRoleInfo(roleId);
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public Map<String, Object> findList(HttpServletRequest request){
		// 获取分页参数并校验
        pagination.init(request);
		// 获取角色列表
		SysRoleCriteria sysRoleCriteria = new SysRoleCriteria();
		SysRoleCriteria.Criteria criteria = sysRoleCriteria.createCriteria();
		
		String enable = request.getParameter("enable");
        if(StringUtils.isNotEmpty(enable)){
            AssertUtils.isNumberWith2(enable, "enable");
            criteria.andEnableEqualTo(Integer.valueOf(enable));
        }
		
		sysRoleCriteria.setLimitStart(pagination.getStart());
		sysRoleCriteria.setLimitEnd(pagination.getSize());
	    List<SysRole> sysRoleList = this.sysRoleService.findSysRoleList(sysRoleCriteria);
	    // 返回列表
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("count", this.sysRoleService.countSysRoleList(sysRoleCriteria));
	    param.put("items", sysRoleList);
	    return param;
	}
}