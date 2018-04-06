package com.lianggzone.activity.controller.client;

import com.alibaba.fastjson.JSONObject;
import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.entity.ActivitySearchDocument;
import com.lianggzone.activity.model.SearchParamModel;
import com.lianggzone.activity.service.ActivityCommentService;
import com.lianggzone.activity.service.ActivitySearchDocumentService;
import com.lianggzone.activity.utils.commons.AssertUtils;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>概要:</h3><p>ActivitySearchDocumentCController</p>
 * <h3>功能:</h3><p>活动控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/activities"})
public class ActivitySearchDocumentCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivitySearchDocumentCController.class);

	/** 默认offset */
	public static final int DEFAULT_PARAM_OFFSET_VALUE = 0;

	/** 默认limit min */
	public static final int DEFAULT_PARAM_LIMIT_VALUE = 20;

	/** 默认limit max */
	public static final int DEFAULT_PARAM_LIMI_MAX_VALUE = 200;

	@Resource
	private ActivitySearchDocumentService activitySearchDocumentService;

	/**
	 * 保存文档
	 * @param jsonObject
	 * @param objectId
	 * @return
	 * @author cxz
	 */
	@RequestMapping(value={"/{objectId:\\d+}/documents"}, method=RequestMethod.POST)
	public ActivitySearchDocument addDocument(@RequestBody JSONObject jsonObject,
									  @PathVariable Long objectId) {
		// 获取参数
		String title = jsonObject.getString("title");
		String subTitle = jsonObject.getString("sub_title");
		String introduce = jsonObject.getString("introduce");
		String content = jsonObject.getString("content");
		String enable = jsonObject.getString("enable");
		String prior = jsonObject.getString("prior");
		String ext = jsonObject.getString("ext");
		// TODO ：参数判断

		// 设置参数
		ActivitySearchDocument searchDocument = new ActivitySearchDocument();
		searchDocument.setObjectId(objectId);
		searchDocument.setTitle(title);
		searchDocument.setSubTitle(subTitle);
		searchDocument.setIntroduce(introduce);
		searchDocument.setContent(content);
		searchDocument.setPrior(StringUtils.isBlank(prior)? 0 : Integer.valueOf(prior));
		searchDocument.setEnable(Integer.valueOf(enable));

		Long createTime = System.currentTimeMillis();
		searchDocument.setCreateTime(createTime);
		searchDocument.setUpdateTime(createTime);
		if(StringUtils.isNotBlank(ext)) {
			searchDocument.setExt(JSONObject.parseObject(ext));
		}
		// 新增
		this.activitySearchDocumentService.insertOrUpdate(searchDocument);
		return searchDocument;
	}

	/**
	 * 搜索活动信息
	 * @param searchParamModel
	 */
	@RequestMapping(value={"/search"}, method=RequestMethod.POST)
	public Map<String, Object> search(@RequestBody SearchParamModel searchParamModel) {
		// 参数判断
		AssertUtils.isEmpty(searchParamModel.getKey(), "key");

		if(null == searchParamModel.getOffset()) {
			searchParamModel.setOffset(DEFAULT_PARAM_OFFSET_VALUE);
		} else {
			searchParamModel.setOffset(
					Math.max(DEFAULT_PARAM_OFFSET_VALUE, searchParamModel.getOffset()));
		}

		if(null == searchParamModel.getLimit()) {
			searchParamModel.setLimit(DEFAULT_PARAM_LIMIT_VALUE);
		} else {
			int limit = searchParamModel.getLimit();

			if(limit <= 0) {
				limit = DEFAULT_PARAM_LIMIT_VALUE;
			} else if(limit > DEFAULT_PARAM_LIMI_MAX_VALUE) {
				limit = DEFAULT_PARAM_LIMI_MAX_VALUE;
			}
			searchParamModel.setLimit(limit);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("count", this.activitySearchDocumentService.querySearchDocumentCount(searchParamModel));
		result.put("items", this.activitySearchDocumentService.querySearchDocumentList(searchParamModel));
		return result;
	}
}