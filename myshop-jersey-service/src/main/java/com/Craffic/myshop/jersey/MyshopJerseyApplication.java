package com.Craffic.myshop.jersey;

import com.alibaba.dubbo.container.Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@MapperScan(basePackages = "com.Craffic.myshop.jersey.dao")
// spring在多长时间后强制使redis中的session失效,默认是1800.(单位/秒)
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
// 启动定时器
@EnableScheduling
public class MyshopJerseyApplication {
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(){
        System.out.println("hello spring boot");
        return "hello spring boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyshopJerseyApplication.class, args);
        // 启动 Provider 容器，注意这里的 Main 是 com.alibaba.dubbo.container 包下的
        Main.main(args);
    }

    // 获取线程池数配置
    @Value("${async.executeor.corePoolSize}")
    private int corePoolSize;

    /**
     * 数据收集配置，主要作用在于Spring启动时自动加载一个ExecutorService对象.
     */
    @Bean
    public ExecutorService getThreadPool() {
        System.out.println("获取线程池数配置: "+corePoolSize);
        return Executors.newFixedThreadPool(corePoolSize);
    }
}
