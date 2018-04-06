package com.lianggzone.activity.controller.client;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lianggzone.activity.utils.commons.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserCriteria;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.commons.AssertUtils;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;
/**
 * <h3>概要:</h3><p>SysUserController</p>
 * <h3>功能:</h3><p>用户控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/users"})
public class SysUserCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserCController.class);
	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 获取用户信息
	 * @param sysUserId
	 */
	@RequestMapping(value={"/{sysUserId:\\d+}"}, method=RequestMethod.GET)
	public SysUser findOne(@PathVariable long sysUserId){  
		return this.sysUserService.checkSysUserInfo(sysUserId);
	}
	
	/**
	 * 获取用户信息
	 * @param username
	 */
	@RequestMapping(value={"/{username}/username"}, method=RequestMethod.GET)
	public SysUser findOne(@PathVariable String username){  
		return this.sysUserService.checkSysUserInfoByUsername(username);
	}
	
	/**
	 * 批量查询用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/batch"}, method=RequestMethod.GET)
	public Map<String, Object> findList4UserIds(HttpServletRequest request){
		// 获取分页参数并校验
		String userIds = request.getParameter("user_ids");
		AssertUtils.isEmpty(userIds, "user_ids");
		List<String> list = ImmutableList.copyOf(userIds.split(","));
		List<Long> userIdList = new ArrayList<Long>(list.size());
		
		int size = Math.min(20, list.size());
		for (int i = 0; i < size; i++) {
			try {
				long userId = Long.valueOf(list.get(i));
				userIdList.add(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 获取用户列表
		SysUserCriteria sysUserCriteria = new SysUserCriteria();
		SysUserCriteria.Criteria criteria = sysUserCriteria.createCriteria();
		criteria.andUserIdIn(userIdList);
	    List<SysUser> sysUserList = this.sysUserService.findSysUserList(sysUserCriteria);
	    // 返回列表
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("count", this.sysUserService.countSysUserList(sysUserCriteria));
	    param.put("items", sysUserList);
	    return param;
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public Map<String, Object> findList(HttpServletRequest request){
		
		// 获取分页参数并校验
        pagination.init(request);
		// 获取用户列表
		SysUserCriteria sysUserCriteria = new SysUserCriteria();
		SysUserCriteria.Criteria criteria = sysUserCriteria.createCriteria();
		
		String keyword = request.getParameter("keyword");
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andRealnameLike("%" + keyword + "%");
        }
		
		sysUserCriteria.setLimitStart(pagination.getStart());
		sysUserCriteria.setLimitEnd(pagination.getSize());
	    List<SysUser> sysUserList = this.sysUserService.findSysUserList(sysUserCriteria);
	    // 返回列表
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("count", this.sysUserService.countSysUserList(sysUserCriteria));
	    param.put("items", sysUserList);
	    return param;
	}
	
	/**
	 * 修改用户
	 * @param sysUserId
	 * @param sysUser
	 * @param errors
	 * @param user
	 * @return
	 */
	@RequestMapping(value={"/{sysUserId:\\d+}"}, method=RequestMethod.PUT)
	public SysUser update(@PathVariable long sysUserId, @RequestBody @Validated({Groups.UPDATE.class}) SysUser sysUser, Errors errors, Principal user){
		if (errors.hasFieldErrors()) {
            throw new JsonException(ErrorCode.valueOf(errors.getFieldError().getDefaultMessage()), errors.getFieldError());
        }
		
		SysUser sysUser2 = this.sysUserService.checkSysUserInfo(sysUserId);
		if(sysUser2.getUserId().longValue() != Long.valueOf(user.getName())){
			throw new JsonException(ErrorCode.ACCESS_DENIED);
		}
		
		sysUser.setUserId(sysUserId);
		// 保存
        try {
        	sysUser.setRealname(null);
        	sysUser.setUsername(null);
        	sysUser.setPassword(null);
			this.sysUserService.update(sysUser);
			return sysUser;
		} catch (DuplicateKeyException e) {
			LogUtil.getServerError("SysUserController.update", ExceptionUtils.getStackTrace(e));
			throw new JsonException(ErrorCode.USER_HAS_EXIST); 
		}
	}
}