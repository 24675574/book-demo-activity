package com.lianggzone.activity.controller.client;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.entity.ActivityPraise;
import com.lianggzone.activity.service.ActivityCommentService;
import com.lianggzone.activity.service.ActivityPraiseService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <h3>概要:</h3><p>ActivityPraiseCController</p>
 * <h3>功能:</h3><p>活动点赞控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/activities"})
public class ActivityPraiseCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityPraiseCController.class);
	
	@Resource
	private ActivityPraiseService activityPraiseService;

	/**
	 * 活动点赞
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value={"/{activityId:\\d+}/praise"}, method=RequestMethod.POST)
	public ActivityPraise prasie(@PathVariable long activityId){
		long count = this.activityPraiseService.save(activityId);
		ActivityPraise activityPraise = new ActivityPraise();
		activityPraise.setActivityId(activityId);
		activityPraise.setCount(count);
		return activityPraise;
	}
}