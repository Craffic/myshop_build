package com.Craffic.myshop.mvc.web.controller;

import com.Craffic.myshop.common.api.UserService;
import com.Craffic.myshop.domain.model.TbUser;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class apiUserController {

    @Reference(version = "1.0.0")
    UserService userService;

    @RequestMapping(value = "api/user")
    public String queryUserById(TbUser tbUser){

     return userService.sayHi();
    }

}
