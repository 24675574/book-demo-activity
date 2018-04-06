package com.lianggzone.activity.service;

import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.entity.ActivitySearchDocument;
import com.lianggzone.activity.model.SearchParamModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivityCommentService</p>
 * <h3>功能:</h3><p>活动搜索 服务接口</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
public interface ActivitySearchDocumentService {
	public String insertOrUpdate(ActivitySearchDocument searchDocument);
	public void delete(Long objectId);
	public ActivitySearchDocument query(Long objectId);
	public Long querySearchDocumentCount(SearchParamModel searchParamModel);
	public List<ActivitySearchDocument> querySearchDocumentList(
			SearchParamModel searchParamModel);
}
