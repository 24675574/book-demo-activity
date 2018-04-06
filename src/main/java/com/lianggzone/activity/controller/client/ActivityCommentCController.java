package com.lianggzone.activity.controller.client;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.ActivityApply;
import com.lianggzone.activity.entity.ActivityComment;
import com.lianggzone.activity.service.ActivityApplyService;
import com.lianggzone.activity.service.ActivityCommentService;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <h3>概要:</h3><p>ActivityCommentCController</p>
 * <h3>功能:</h3><p>活动评论控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/activities"})
public class ActivityCommentCController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityCommentCController.class);
	
	@Resource
	private ActivityCommentService activityCommentService;

	/**
	 * 活动评论
	 * @param activityId
	 * @param activityComment
	 * @param errors
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/{activityId:\\d+}/comments"}, method=RequestMethod.POST)
	public ActivityComment insert(@PathVariable long activityId,
								@RequestBody @Validated({Groups.INSERT.class}) ActivityComment activityComment,
								Errors errors, HttpServletRequest request, Principal user){
		if (errors.hasFieldErrors()) {
            throw new JsonException(
					ErrorCode.valueOf(errors.getFieldError().getDefaultMessage()),
					errors.getFieldError());
        }

		activityComment.setActivityId(activityId);
		activityComment.setUserId(user.getName());
		this.activityCommentService.insert(activityComment);
		return activityComment;
	}

	/**
	 * 删除评论
	 * @param activityId
	 * @param commentId
	 * @param user
	 */
	@RequestMapping(value={"/{activityId:\\d+}/comments/{commentId}"}, method=RequestMethod.DELETE)
	public ActivityComment delete(@PathVariable long activityId, @PathVariable String commentId, Principal user){
		ActivityComment activityComment = this.activityCommentService.query(commentId);
		if(activityComment == null){
			throw new JsonException(ErrorCode.COMMENT_NOT_FOUND);
		}
		if(!activityComment.getUserId().equals(user.getName())){
			throw new JsonException(ErrorCode.ACCESS_DENIED);
		}
		this.activityCommentService.delete(commentId);
		return activityComment;
	}
}