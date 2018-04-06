package com.lianggzone.activity.dao;

import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.entity.SysUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserCriteria example);

    int deleteByExample(SysUserCriteria example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserCriteria example);

    SysUser selectByPrimaryKey(Long userId);
    
    SysUser selectByUsername(String username);
    
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}