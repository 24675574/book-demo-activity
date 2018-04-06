package com.lianggzone.activity.dao;

import com.lianggzone.activity.entity.SysRole;
import com.lianggzone.activity.entity.SysRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    int countByExample(SysRoleCriteria example);

    int deleteByExample(SysRoleCriteria example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleCriteria example);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleCriteria example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleCriteria example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}