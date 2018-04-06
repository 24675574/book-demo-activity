package com.lianggzone.activity.dao;

import com.lianggzone.activity.entity.SysUserRole;
import com.lianggzone.activity.entity.SysUserRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    int countByExample(SysUserRoleCriteria example);

    int deleteByExample(SysUserRoleCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleCriteria example);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleCriteria example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleCriteria example);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}