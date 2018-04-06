package com.lianggzone.activity.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lianggzone.activity.dao.SysUserMapper;
import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserCriteria;
import com.lianggzone.activity.model.CustomUserDetails;
import com.lianggzone.activity.model.UCUserDetails;
import com.lianggzone.activity.model.UserInfo;
import com.lianggzone.activity.service.SysUserRoleService;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>SysUserServiceImpl</p>
 * <h3>功能:</h3><p>用户 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	@Autowired
    private SysUserMapper sysUserMapper;
	@Autowired
    private SysUserRoleService sysUserRoleService;
	
	@Override
	public long insert(SysUser record) {
		this.sysUserMapper.insertSelective(record);
		return record.getUserId();
	}

	@Override
	public int update(SysUser record) {
		return this.sysUserMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int delete(Long id) {
		SysUserCriteria sysUserCriteria = new SysUserCriteria();
		SysUserCriteria.Criteria criteria = sysUserCriteria.createCriteria();
		criteria.andUserIdEqualTo(id);
		return this.sysUserMapper.deleteByExample(sysUserCriteria);
	}

	@Override
	public SysUser findSysUserInfo(Long id) {
		SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(id);
		return sysUser;
	}
	
	@Override
	public SysUser findSysUserInfoByUsername(String username) {
		SysUser sysUser = this.sysUserMapper.selectByUsername(username);
		return sysUser;
	}
	
	@Override
	public SysUser checkSysUserInfo(Long id) {
		SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(id);
		if(sysUser==null){
			throw new JsonException(ErrorCode.USER_NOT_FOUND);  
		}
		return sysUser;
	}
	
	@Override
	public SysUser checkSysUserInfoByUsername(String username) {
		SysUser sysUser = this.sysUserMapper.selectByUsername(username);
		if(sysUser==null){
			throw new JsonException(ErrorCode.USER_NOT_FOUND);  
		}
		return sysUser;
	}

	@Override
	public List<SysUser> findSysUserList(SysUserCriteria criteria) {
		return this.sysUserMapper.selectByExample(criteria);
	}

	@Override
	public int countSysUserList(SysUserCriteria criteria) {
		return this.sysUserMapper.countByExample(criteria);
	}	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1、获取用户信息
        SysUser sysUser = this.findSysUserInfoByUsername(username);
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(sysUser, user);
        user.setUserId(sysUser.getUserId()+"");
        logger.debug("user:{}, userId:{}", sysUser, sysUser.getUserId());
        // 2、获取角色信息
        List<SysRole> roleDetailsCollection = this.sysUserRoleService.findUserRoleList(sysUser.getUserId());
        // 3、封装details信息
        UCUserDetails details = new UCUserDetails(user, roleDetailsCollection);
        // Not involve authorities, so pass null to authorities
        return new CustomUserDetails(sysUser, true, true, true, true, details.getAuthorities());
	}
}