package com.lianggzone.activity.mongodao;

import com.lianggzone.activity.entity.ActivityComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <h3>概要:</h3><p>ActivityCommentDao</p>
 * <h3>功能:</h3><p>ActivityCommentDao</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Repository
public class ActivityCommentDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	/**
	 * 新增评论信息
	 * @param activityComment
	 */
	public void insert(ActivityComment activityComment) {
		activityComment.setUpdatetime(new Date());
		this.mongoTemplate.insert(activityComment);
	}
	/**
	 * 修改评论信息
	 * @param activityComment
	 */
	public void update(ActivityComment activityComment) {
		this.mongoTemplate.save(activityComment);
	}
	/**
	 * 删除评论信息
	 * @param id
	 */
	public void delete(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		this.mongoTemplate.remove(query, ActivityComment.class);
	}
	/**
	 * 查询评论信息
	 * @param id
	 */
	public ActivityComment query(String id) {
		return this.mongoTemplate.findById(id, ActivityComment.class);
	}
	/**
	 * 获取评论信息列表
	 * @param activityId
	 * @param title
	 * @param offset
	 * @param limit
	 */
	public List<ActivityComment> queryActivityCommentList(Long activityId, String title, int offset, int limit) {
		Query query = new Query();
		if (null != activityId) {
			query.addCriteria(Criteria.where("activityId").is(activityId));
		}
		if (StringUtils.isNotBlank(title)) {
			query.addCriteria(Criteria.where("title")
					.regex(Pattern.compile(".*" + title + ".*", Pattern.CASE_INSENSITIVE)));
		}
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		query.skip(offset);
		query.limit(limit);
		return this.mongoTemplate.find(query, ActivityComment.class);
	}
}
