package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserDao userDao;

    @Override
    public TbUser getTbUserDetail(String userId) {
        if (userId == null){
            throw new RuntimeException("用户id不能为空");
        }
        return userDao.selectById(Long.parseLong(userId));
    }

    public List<TbUserVo> getUserList(){
        List<TbUserVo> userList = userDao.getUserList();
        return userList;
    }
}
