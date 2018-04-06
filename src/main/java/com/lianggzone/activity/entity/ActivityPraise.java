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
public class ActivityPraise {
	private Long activityId;
    private Long count;
    @JsonIgnore
    private Date updatetime;
}
