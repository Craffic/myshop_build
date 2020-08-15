package com.Craffic.myshop.jersey.Utils.redis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@Data
public class RedisLockerUtil extends ApplicationObjectSupport {

    private RedisTemplate redisTemplate;

    private static final String SYS_CODE = "AB4";

    /**
     * 查询redis锁
     */
    public boolean queryLocker(String key) throws Exception{
        if (redisTemplate.hasKey(key)){
            return true;
        }
        return false;
    }
}