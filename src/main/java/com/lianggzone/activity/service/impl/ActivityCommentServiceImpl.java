package com.lianggzone.activity.service.impl;

import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.service.ActivityCommentService;
import com.lianggzone.activity.mongodao.ActivityCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityCommentServiceImpl</p>
 * <h3>功能:</h3><p>活动评论 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class ActivityCommentServiceImpl implements ActivityCommentService {

	@Autowired
	private ActivityCommentDao activityCommentDao;

	public void insert(ActivityComment activityComment){
		this.activityCommentDao.insert(activityComment);
	}

	public void update(ActivityComment activityComment){
		this.activityCommentDao.update(activityComment);
	}

	public void delete(String id){
		this.activityCommentDao.delete(id);
	}

	public ActivityComment query(String id){
		return this.activityCommentDao.query(id);
	}

	public List<ActivityComment> queryActivityCommentList(Long activityId, String title, int offset, int limit){
		return this.activityCommentDao.queryActivityCommentList(activityId, title, offset, limit);
	}
}