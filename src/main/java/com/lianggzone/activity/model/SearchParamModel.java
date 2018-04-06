package com.lianggzone.activity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * <h3>概要:</h3><p>SearchParamModel</p>
 * <h3>功能:</h3><p>搜索信息</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Data
public class SearchParamModel {
	/**
	 * 关键字
	 * title、sub_title、introduce、content
	 * */
	private String key;

	@JsonProperty("offset")
	private Integer offset;

	@JsonProperty("limit")
	private Integer limit;
}
