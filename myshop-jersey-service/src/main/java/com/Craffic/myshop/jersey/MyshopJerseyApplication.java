package com.Craffic.myshop.jersey;

import com.alibaba.dubbo.container.Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan(basePackages = "com.Craffic.myshop.jersey.dao")
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
}
