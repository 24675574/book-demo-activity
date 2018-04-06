package com.lianggzone.activity.dao;

import com.lianggzone.activity.entity.ActivityApply;
import com.lianggzone.activity.entity.ActivityApplyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityApplyMapper {
    int countByExample(ActivityApplyCriteria example);

    int deleteByExample(ActivityApplyCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityApply record);

    int insertSelective(ActivityApply record);

    List<ActivityApply> selectByExample(ActivityApplyCriteria example);

    ActivityApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityApply record, @Param("example") ActivityApplyCriteria example);

    int updateByExample(@Param("record") ActivityApply record, @Param("example") ActivityApplyCriteria example);

    int updateByPrimaryKeySelective(ActivityApply record);

    int updateByPrimaryKey(ActivityApply record);
}