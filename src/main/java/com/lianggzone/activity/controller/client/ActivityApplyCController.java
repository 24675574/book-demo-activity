package com.lianggzone.activity.controller.client;

import com.lianggzone.activity.controller.AbstractController;
import com.lianggzone.activity.entity.ActivityApply;
import com.lianggzone.activity.entity.SysUser;
import com.lianggzone.activity.model.SysUserBO;
import com.lianggzone.activity.service.ActivityApplyService;
import com.lianggzone.activity.service.EmailService;
import com.lianggzone.activity.service.SysUserService;
import com.lianggzone.activity.utils.commons.LogUtil;
import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;
import com.lianggzone.activity.utils.validator.Groups;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <h3>概要:</h3><p>ActivityApplyCController</p>
 * <h3>功能:</h3><p>报名控制器</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@RestController
@RequestMapping({"v1/c/activities"})
public class ActivityApplyCController extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(ActivityApplyCController.class);
	
	@Resource
	private ActivityApplyService activityApplyService;
	@Resource
	private EmailService emailService;
	@Resource
	private SysUserService sysUserService;

	/**
	 * 活动报名
	 * @param activityId
	 * @param activityApply
	 * @param errors
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/{activityId:\\d+}/applies"}, method=RequestMethod.POST)
	public ActivityApply insert(@PathVariable long activityId,
								@RequestBody @Validated({Groups.INSERT.class}) ActivityApply activityApply,
								Errors errors, HttpServletRequest request,
								Principal user){
		if (errors.hasFieldErrors()) {
            throw new JsonException(
					ErrorCode.valueOf(errors.getFieldError().getDefaultMessage()),
					errors.getFieldError());
        }
		try {
			activityApply.setActivityId(activityId);
			long id = this.activityApplyService.insert(activityApply);
			activityApply.setId(id);
			// 发送邮件
			SysUser sysUser = this.sysUserService.findSysUserInfo(Long.valueOf(user.getName()));
			this.emailService.sendEmail(sysUser.getEmail(), "亲爱的"+sysUser.getRealname()+", 您已成功报名！");
			return activityApply;
		} catch (DuplicateKeyException e) {
			LogUtil.getServerError("ActivityApplyCController.insert", ExceptionUtils.getStackTrace(e));
			throw new JsonException(ErrorCode.APPLY_HAS_EXIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}