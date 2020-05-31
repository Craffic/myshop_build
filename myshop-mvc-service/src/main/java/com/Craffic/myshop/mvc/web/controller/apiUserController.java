package com.Craffic.myshop.mvc.web.controller;

import com.Craffic.myshop.jersey.api.apiUserService;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.service.UserService;
import com.Craffic.myshop.mvc.domain.model.M_TbUser;
import com.Craffic.myshop.mvc.domain.vo.M_TbUserVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController

public class apiUserController {

    @Reference(version = "1.0.0")
    UserService userService;

    @Reference(version = "1.0.0")
    apiUserService apiUserService;

    @RequestMapping(value = "api/user")
    public String queryUserById(M_TbUser tbUser){
        return "";
    }

    @RequestMapping(value = "api/user/all")
    public List<M_TbUserVo> getUserList(){
        List<M_TbUserVo> tbUserVos = new ArrayList();
        List<TbUserVo> userList = userService.getUserList();
        if (!CollectionUtils.isEmpty(userList)){
            for (TbUserVo tbUserVo : userList) {
                M_TbUserVo mTbUserVo = new M_TbUserVo();
                BeanUtils.copyProperties(tbUserVo, mTbUserVo);
                tbUserVos.add(mTbUserVo);
            }
            return tbUserVos;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "api/user/all/v1")
    public List<M_TbUserVo> getUserList_v1(){
        List<M_TbUserVo> tbUserVos = new ArrayList();
        List<TbUserVo> userList = apiUserService.getUserList();
        if (!CollectionUtils.isEmpty(userList)){
            for (TbUserVo tbUserVo : userList) {
                M_TbUserVo mTbUserVo = new M_TbUserVo();
                BeanUtils.copyProperties(tbUserVo, mTbUserVo);
                tbUserVos.add(mTbUserVo);
            }
            return tbUserVos;
        } else {
            return null;
        }
    }

}
