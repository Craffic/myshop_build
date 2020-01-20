package com.Craffic.myshop.jersey.config;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/services/v1")   // 指定所有Endpoint的基础路径
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        // 注册类的方式
//         register(HelloResourceAction.class, HelloResource.class);
        // 注册包的方式
        packages("com.Craffic.myshop.jersey");
        // 注册JSON转换器
        register(JacksonJsonProvider.class);
        //注册文件上传模块
        register(MultiPartFeature.class);
    }

}
