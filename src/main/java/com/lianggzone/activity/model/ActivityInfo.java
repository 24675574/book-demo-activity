package com.lianggzone.activity.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * <h3>概要:</h3><p>ActivityInfo</p>
 * <h3>功能:</h3><p>活动信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class ActivityInfo {
	/** 自增ID */
	private Long id;
	/** 活动主标题 */
	private String activityTitle;
	/** 活动副标题 */
	private String activitySubTitle;
	/** 活动摘要 */
	private String activityIntroduce;
	/** 活动内容 */
	private String activityContent;
	/** 申请开始时间 */
	private Timestamp applyBeginTime;
	/** 申请结束时间 */
	private Timestamp applyEndTime;
	/** 活动开始时间 */
	private Timestamp activityBeginTime;
	/** 活动结束时间 */
	private Timestamp activityEndTime;
	/** 启用状态（1-启用；2-禁用；删除）*/
	private Integer enable;
	/** 活动地点 */
	private String address;
	/** 缩略图ID */
	private String logoDentryId;
	/** 创建时间 */
	private Timestamp createTime;
	/** 更新时间 */
	private Timestamp updateTime;
}