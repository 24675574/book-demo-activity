package com.lianggzone.activity.redisdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityPraiseRedisDao {
    @Autowired
    public RedisBaseDao redisBaseDao;

    private static final String KEY = "activity:praise_count";
    
    /**	
     * 保存点赞数量
     * @param activityId
     * @param count
     */
    public void save(Long activityId, Long count){
        this.redisBaseDao.addHashValue(KEY, activityId.toString(), count+"");
    }

    /**
     * 自增点赞数量
     * @param activityId
     */
    public void inc(Long activityId){
        this.redisBaseDao.incHashValue(KEY, activityId.toString(), 1);
    }
    
    /** 
     * 查询点赞列表
     * @param hashKeys
     */
    public List<String> getPraiseList(List<String> hashKeys){
        return this.redisBaseDao.getHashMultiValue(KEY, hashKeys);
    }
}