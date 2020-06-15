package com.Craffic.myshop.jersey.domain.baseEntity;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.protocol.types.Field;

import java.io.Serializable;

public class BaseVo implements Serializable {
    private static final long serialVersionID  = -4213992025937477652L;

    @Override
    public String toString(){
        return JSONObject.toJSON(this).toString();
    }

}
