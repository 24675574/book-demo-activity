package com.lianggzone.activity.utils.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final String PROJECT_NAME = "book-demo-activity";
    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void getServerError(String method, String cause) {
        logger.error("project:{}, thread_id:{}, methodName:{}, cause:{}",
                new Object[]{PROJECT_NAME, String.valueOf(Thread.currentThread().getId()) + "-SERVICE_SERVER", method, cause});
    }

    public static void getMySqlError(String method, String cause) {
        logger.error("project:{}, thread_id:{}, methodName:{}, cause:{}",
                new Object[]{PROJECT_NAME, String.valueOf(Thread.currentThread().getId()) + "-SERVICE_MYSQL", method, cause});
    }

    public static void getRedisError(String method, String cause) {
        logger.error("project:{}, thread_id:{}, methodName:{}, cause:{}",
                new Object[]{PROJECT_NAME, String.valueOf(Thread.currentThread().getId()) + "-SERVICE_Redis", method, cause});
    }

    //其他方法...
}
