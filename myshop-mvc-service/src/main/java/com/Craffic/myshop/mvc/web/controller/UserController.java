package com.Craffic.myshop.mvc.web.controller;

import com.Craffic.myshop.mvc.domain.pojo.M_TbUserReq;
import com.Craffic.myshop.mvc.domain.vo.M_TbUserVo;
import com.Craffic.myshop.mvc.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String queryUserById(M_TbUserReq tbUser){

        String id = String.valueOf(tbUser);
        M_TbUserVo tbUSerDetail = userService.getTbUSerDetail(id);
        if (tbUSerDetail == null){
            return "请输入有效的用户ID！";
        }
        return tbUSerDetail.toString();
    }
}
