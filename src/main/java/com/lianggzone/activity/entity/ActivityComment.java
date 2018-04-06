package com.lianggzone.activity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lianggzone.activity.utils.validator.Groups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Document(collection="activity_comment")
public class ActivityComment {
    @Id
	private String id;

    @Indexed
    private String userId;

    @Indexed
    private Long activityId;

    @NotEmpty(groups = {Groups.INSERT.class}, message = "REQUIRE_ARGUMENT")
    @Length(min = 0, max = 120, groups = {Groups.INSERT.class, Groups.UPDATE.class}, message = "INVALID_ARGUMENT")
    private String content;

    @JsonIgnore
    private Date updatetime;
}
