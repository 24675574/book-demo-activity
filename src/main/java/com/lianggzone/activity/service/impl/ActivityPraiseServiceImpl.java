package com.lianggzone.activity.service.impl;

import com.lianggzone.activity.mongodao.ActivityCommentDao;
import com.lianggzone.activity.redisdao.ActivityPraiseRedisDao;
import com.lianggzone.activity.service.ActivityPraiseService;
import com.lianggzone.activity.utils.commons.LogUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityPraiseService</p>
 * <h3>功能:</h3><p>活动点赞 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class ActivityPraiseServiceImpl implements ActivityPraiseService {
	@Autowired
	private ActivityPraiseRedisDao activityPraiseRedisDao;
	/**
	 * 活动点赞
	 * @param activityId
     */
	public long save(Long activityId){
		// 更新数据库
		// this.activityPraiseDao.insertOrUpdate(activityId);
		try {
			// 更新缓存，保存下载次数
			// Long count = this.activityPraiseDao.getPraiseCount(activityId);
			// 测试代码
			Long count = RandomUtils.nextLong();
			this.activityPraiseRedisDao.save(activityId, count);
			return count;
		} catch (Exception e) {
			// 如果 Redis 出现异常，记录日志
			LogUtil.getRedisError("ActivityPraiseServiceImpl.save",
					ExceptionUtils.getStackTrace(e));
		}
		return 0;

	}
	/**
	 * 查询点赞列表
	 * @param activityIdList
	 * @return
     */
	public List<String> getPraiseList(List<String> activityIdList){
		return this.activityPraiseRedisDao.getPraiseList(activityIdList);
	}
}