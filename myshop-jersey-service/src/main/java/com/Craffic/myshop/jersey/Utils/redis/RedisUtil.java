package com.Craffic.myshop.jersey.Utils.redis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@Component
public class RedisUtil extends ApplicationObjectSupport {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String queryByKey(String key) {
        String str = redisTemplate.opsForValue().get(key);
        return str;
    }

    /**
     * 检查key是否被锁
     */
    public boolean queryLocker(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 分布式锁
     */
    public void lockKey(String key, int days) {
        long count = 0L;
        Calendar calendar = Calendar.getInstance();
        if (days > 0) {
            calendar.set(Calendar.DAY_OF_YEAR, days);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date currentDate = calendar.getTime();
        Long liveTime = currentDate.getTime() - new Date().getTime();
        redisTemplate.opsForValue().increment(key, 1);
        // 设置过期时间
        redisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 解锁
     */
    public boolean removeKey(String key){
        redisTemplate.delete(key);
        return true;
    }

    /**
     * 插入redis
     */
    public boolean insertData(String key, String value, long minutes){
        try {
            if (minutes <= 0){
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
            }
            logger.info("插入redis， key = {"+key+"}, value = {"+key+"}, 保存{"+minutes+"}分钟");
            log.info("插入redis， key = {}, value = {}, 保存{}分钟", key, value, minutes);
            return true;
        } catch (Exception e) {
            log.error("插入redis出错， key = {}, value = {}, 异常信息为：{}", key, value, e);
            return false;
        }
    }

}
