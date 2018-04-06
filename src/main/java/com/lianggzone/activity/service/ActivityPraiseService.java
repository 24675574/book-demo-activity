package com.lianggzone.activity.service;

import com.lianggzone.activity.entity.ActivityApply;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityPraiseService</p>
 * <h3>功能:</h3><p>活动点赞 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface ActivityPraiseService {
	public long save(Long activityId);
	public List<String> getPraiseList(List<String> activityIdList);
}