package com.Craffic.myshop.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@MapperScan(basePackages = "com.Craffic.myshop.mvc.dao")
@SpringBootApplication
public class MyshopMVCApplication {
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(){
        System.out.println("hello spring boot 111");
        return "hello spring boot 111";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyshopMVCApplication.class, args);
    }
}