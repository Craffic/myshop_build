package com.Craffic.myshop.jersey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan(basePackages = "com.Craffic.myshop.jersey.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MyshopJerseyApplication {
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(){
        System.out.println("hello spring boot 111");
        return "hello spring boot 111";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyshopJerseyApplication.class, args);
    }
}
