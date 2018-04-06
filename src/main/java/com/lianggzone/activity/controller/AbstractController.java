package com.lianggzone.activity.controller;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lianggzone.activity.component.Pagination;

/**
 * <h3>概要:</h3><p>AbstractController</p>
 * <h3>功能:</h3><p>AbstractController</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@MapperScan("com.lianggzone.activity.dao")
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
