package com.lianggzone.activity.redisdao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisBaseDao {
    @Resource(name = "redisTemplate")
    protected RedisTemplate<String, String> redisTemplate;
    
    @Resource(name = "redisTemplate")
    protected HashOperations<String, String, String> hashOperations;
    
    @Resource(name = "redisTemplate")
    protected ZSetOperations<String, String> zsetOperations;
    
    @Resource(name = "redisTemplate")
    protected ValueOperations<String, String> valueOperations;
    
    //---------------------------------------------------------------------
    // ZSetOperations -> Redis Sort Set 操作
    //---------------------------------------------------------------------
    
    public boolean addZSetValue(String key, String member, long score){
        return zsetOperations.add(key, member, score);
    }
    public boolean addZSetValue(String key, String member, double score){
        return zsetOperations.add(key, member, score);
    }
    public long addBatchZSetValue(String key, Set<ZSetOperations.TypedTuple<String>> tuples){
        return zsetOperations.add(key, tuples);
    }
    public void incZSetValue(String key, String member, long delta){
        zsetOperations.incrementScore(key, member, delta);
    }
    public long getZSetScore(String key, String member){
        Double score = zsetOperations.score(key, member);
        if(score==null){
            return 0;
        }else{
            return score.longValue();   
        }       
    }
    public double getZSetScore4Double(String key, String member){
        Double score = zsetOperations.score(key, member);
        if(score==null){
            return 0;
        }else{
            return score;   
        }       
    }
    public Set<ZSetOperations.TypedTuple<String>> getZSetRank(String key, long start, long end){
        return zsetOperations.rangeWithScores(key, start, end);
    }
    public Set<ZSetOperations.TypedTuple<String>> getZSetRevRank(String key, long start, long end){
        return zsetOperations.reverseRangeWithScores(key, start, end);
    }
    public long getZSetCount(String key){
        return zsetOperations.size(key);
    }
    
    //---------------------------------------------------------------------
    // HashOperations -> Redis Redis Hash 操作
    //---------------------------------------------------------------------

    public void addHashValue(String key, String hashKey, String value) {    
        hashOperations.put(key, hashKey, value);
    }
    public void addBatchHashValue(String key, Map<String, String> map) {
        hashOperations.putAll(key, map);
    }
    public void incHashValue(String key, String hashKey, long delta) {
        hashOperations.increment(key, hashKey, delta);
    }
    public void deleteHashValue(String key, String hashKey) {    
        hashOperations.delete(key, hashKey);
    }
    public String getHashValue(String key, String hashKey) {
        return hashOperations.get(key, hashKey);      
    } 
    public List<String> getHashAllValue(String key) {
        List<String> values = hashOperations.values(key);
        return values;
    }
    public List<String> getHashMultiValue(String key, List<String> hashKeys) {
        List<String> values = hashOperations.multiGet(key, hashKeys);
        return values;
    }
    public Long getHashCount(String key) {
        return hashOperations.size(key);
    }
    
    //---------------------------------------------------------------------
    // ValueOperations -> Redis String/Value 操作
    //---------------------------------------------------------------------

    public void addValue(String key, String value){
        valueOperations.set(key, value);
    }
    public String getValue(String key){
        return valueOperations.get(key);
    }
    
    //---------------------------------------------------------------------
    // redisTemplate
    //---------------------------------------------------------------------

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
    public void expire(String key, final long timeout, final TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
    public void delete(Set<String> keys) {
        redisTemplate.delete(keys);
    }
}
