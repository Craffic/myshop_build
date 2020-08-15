package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.Utils.BeanUtils;
import com.Craffic.myshop.jersey.Utils.redis.RedisUtil;
import com.Craffic.myshop.jersey.domain.common.ResponseBody;
import com.Craffic.myshop.jersey.exception.ServerStatusCode;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Component
@Path("/redis")
public class RedisAction {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询redis
     * url:http://localhost:8080/services/v1/redis/query/data
     * @param str: {"key":"Craffic"}
     * @return
     * @throws Exception
     */
    @POST
    @Path("query/data")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<String> queryByKey(String str) throws Exception {
        JSONObject object = BeanUtils.strToJsonObj(str);
        String key = (String) object.get("key");
        log.info("redis查询， 传入的key: {}", key);
        String value = redisUtil.queryByKey(key);
        log.info("查询redis，返回的value: {}", value);
        return ResponseBody.success(value);
    }

    @POST
    @Path("query/lock")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<String> queryLocker(String str) throws Exception {
        JSONObject object = BeanUtils.strToJsonObj(str);
        String key = (String) object.get("key");
        Boolean flag = redisUtil.queryLocker(key);
        if (flag) {
            return ResponseBody.success("success");
        } else {
            return ResponseBody.failure(ServerStatusCode.REDIS_QUERY_ERROR.getCode(), "该值没有被锁", "false");
        }
    }

    @POST
    @Path("insert/data")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<String> insertData(String json) throws Exception {
        JSONObject object = BeanUtils.strToJsonObj(json);
        String key = (String) object.get("key");
        String value = (String) object.get("value");
        Long minutes = 5L;
        boolean flag = redisUtil.insertData(key, value, minutes);
        if (flag) {
            return ResponseBody.success("key = {"+key+"} 插入redis成功");
        } else {
            return ResponseBody.failure(ServerStatusCode.REDIS_INSERT_ERROR.getCode(), "数据插入redis失败", "false");
        }
    }
}