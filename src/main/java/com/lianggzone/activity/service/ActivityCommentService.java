package com.lianggzone.activity.service;

import com.lianggzone.activity.entity.ActivityComment;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityCommentService</p>
 * <h3>功能:</h3><p>活动评论 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface ActivityCommentService {
	public void insert(ActivityComment activityComment);
	public void update(ActivityComment activityComment);
	public void delete(String id);
	public ActivityComment query(String id);
	public List<ActivityComment> queryActivityCommentList(Long activityId,
		String title, int offset, int limit);
}