package com.lianggzone.activity.service.impl;

import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.entity.ActivitySearchDocument;
import com.lianggzone.activity.esdao.ActivitySearchDocumentDao;
import com.lianggzone.activity.model.SearchParamModel;
import com.lianggzone.activity.mongodao.ActivityCommentDao;
import com.lianggzone.activity.service.ActivityCommentService;
import com.lianggzone.activity.service.ActivitySearchDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivitySearchDocumentServiceImpl</p>
 * <h3>功能:</h3><p>活动搜索 服务实现类</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Service
public class ActivitySearchDocumentServiceImpl
		implements ActivitySearchDocumentService {

	@Autowired
	private ActivitySearchDocumentDao activitySearchDocumentDao;

	@Override
	public String insertOrUpdate(ActivitySearchDocument searchDocument) {
		return this.activitySearchDocumentDao.insertOrUpdate(searchDocument);
	}

	@Override
	public void delete(Long objectId) {
		this.activitySearchDocumentDao.delete(objectId);
	}

	@Override
	public ActivitySearchDocument query(Long objectId) {
		return this.activitySearchDocumentDao.query(objectId);
	}

	@Override
	public Long querySearchDocumentCount(SearchParamModel searchParamModel) {
		return this.activitySearchDocumentDao.querySearchDocumentCount(searchParamModel);
	}

	@Override
	public List<ActivitySearchDocument> querySearchDocumentList(
			SearchParamModel searchParamModel) {
		return this.activitySearchDocumentDao.querySearchDocumentList(searchParamModel);
	}
}