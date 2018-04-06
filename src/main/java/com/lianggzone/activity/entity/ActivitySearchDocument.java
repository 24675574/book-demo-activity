package com.lianggzone.activity.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "activity_search", type = "search_document")
@Mapping
public class ActivitySearchDocument {
	@Id
	@Field(type = FieldType.text, index = false, store = true)
	private String id;
    // 对象ID
	@Field(type = FieldType.Long)
	private Long objectId;		
    // 主标题
	@Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik", store = true)
	private String title;		
    // 副标题
	@Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik", store = true)
	private String subTitle;		
    // 摘要
	@Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik", store = true)
	private String introduce;		
    // 内容
	@Field(type = FieldType.text, searchAnalyzer = "ik_max_word", analyzer = "ik", store = true)
	private String content;		
    // 是否启用（1：启用 2：禁用）
	@Field(type = FieldType.Integer)
	private Integer enable;		
    // 优先级
	@Field(type = FieldType.Integer)
	private Integer prior;
    // 扩展信息
	private JSONObject ext;
    // 创建时间
	private Long createTime;		
    // 更新时间
	private Long updateTime;		
}
