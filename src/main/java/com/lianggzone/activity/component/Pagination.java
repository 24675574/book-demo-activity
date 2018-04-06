package com.lianggzone.activity.component;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lianggzone.activity.utils.errorcode.ErrorCode;
import com.lianggzone.activity.utils.exception.JsonException;

/**
 * <h3>概要:</h3><p>Pagination</p>
 * <h3>功能:</h3><p>Pagination</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Component
@Scope(value = "prototype")
public class Pagination {
	
    private ThreadLocal<Integer> start = new ThreadLocal<Integer>();
    private ThreadLocal<Integer> size = new ThreadLocal<Integer>();
    private ThreadLocal<Integer> page = new ThreadLocal<Integer>();
    
    //@Value("${pagination.size.min}")
    private String minSize = "20";
    //@Value("${pagination.size.max}")
    private String maxSize = "100";
    private String offsetParameterName = "$offset";
    private String limitParameterName = "$limit";

    @PostConstruct
    public void init() {}

    public void init(HttpServletRequest request) {
        // start
        try {
            this.start.set(Integer.valueOf((request.getParameter(this.offsetParameterName) == null) ? "0" : String.valueOf(Math.max(0, Integer.valueOf(request.getParameter(this.offsetParameterName))))));
        } catch (Exception e) {
            throw new JsonException(ErrorCode.INVALID_ARGUMENT, new Object[]{this.offsetParameterName});
        }

        // size
        try {
            if (request.getParameter(this.limitParameterName) == null) {
                this.size.set(Integer.valueOf(this.minSize));
            } else {
                int[] sizeArray = new int[]{Integer.valueOf(this.maxSize), Integer.valueOf(request.getParameter(this.limitParameterName))};
                Arrays.sort(sizeArray);
                this.size.set(Math.max(0, sizeArray[0]));
            }
        } catch (Exception e) {
            throw new JsonException(ErrorCode.INVALID_ARGUMENT,new Object[]{this.limitParameterName});
        }

        this.page.set(this.getStart() / this.getSize() + 1);
    }

    public int getStart() {
        return start.get();
    }

    public void setStart(int start) {
        this.start.set(start);
    }

    public int getSize() {
        return size.get();
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public int getPage() {
        return page.get();
    }

    public void setPage(int page) {
        this.size.set(page);
    }
}
