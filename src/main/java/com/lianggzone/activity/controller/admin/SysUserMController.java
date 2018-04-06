package com.lianggzone.activity.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lianggzone.activity.model.SysUserBO;
import com.lianggzone.activity.utils.commons.LogUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;

/**
 * <h3>概要:</h3><p>SysUserMController</p>
 * <h3>功能:</h3><p>用户控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/m/users"})
public class SysUserMController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserMController.class);
	
	@Resource
	private SysUserService sysUserService;

	/**
	 * 新增用户
	 * @param sysUserBO
	 * @param errors
	 * @param request
	 * @return
	 */
	@RequestMapping(value={""}, method=RequestMethod.POST)
	public SysUser insert(@RequestBody @Validated({Groups.INSERT.class}) SysUserBO sysUserBO, Errors errors, HttpServletRequest request){
		if (errors.hasFieldErrors()) {
            throw new JsonException(ErrorCode.valueOf(errors.getFieldError().getDefaultMessage()), errors.getFieldError());
        }
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserBO, sysUser);
		sysUser.setPassword(sysUser.getPassword());
		try {
			long id = this.sysUserService.insert(sysUser);
			sysUser.setUserId(id);
			return sysUser;
		} catch (DuplicateKeyException e) {
			LogUtil.getServerError("SysUserController.insert", ExceptionUtils.getStackTrace(e));
			throw new JsonException(ErrorCode.USER_HAS_EXIST); 
		}
	}
	
	/**
	 * 修改用户
	 * @param sysUserId
	 * @param sysUser
	 * @param errors
	 * @return
	 */
	@RequestMapping(value={"/{sysUserId:\\d+}"}, method=RequestMethod.PUT)
	public SysUser update(@PathVariable long sysUserId, @RequestBody @Validated({Groups.UPDATE.class}) SysUser sysUser, Errors errors){
		if (errors.hasFieldErrors()) {
            throw new JsonException(ErrorCode.valueOf(errors.getFieldError().getDefaultMessage()), errors.getFieldError());
        }
		this.sysUserService.checkSysUserInfo(sysUserId);
		sysUser.setUserId(sysUserId);
		// 保存
        try {
			this.sysUserService.update(sysUser);
			return sysUser;
		} catch (DuplicateKeyException e) {
			LogUtil.getServerError("SysUserController.update", ExceptionUtils.getStackTrace(e));
			throw new JsonException(ErrorCode.USER_HAS_EXIST); 
		}
	}
	
	/**
	 * 删除用户
	 * @param sysUserId
	 */
	@RequestMapping(value={"/{sysUserId:\\d+}"}, method=RequestMethod.DELETE)
	public void delete(@PathVariable long sysUserId){
		this.sysUserService.checkSysUserInfo(sysUserId);
		this.sysUserService.delete(sysUserId);
	}
}