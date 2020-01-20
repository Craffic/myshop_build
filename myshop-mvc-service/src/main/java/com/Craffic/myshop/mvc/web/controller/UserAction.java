package com.Craffic.myshop.mvc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserAction {

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(){
        System.out.println("hello spring boot mvc");
        return "hello spring boot mvc";
    }

}
