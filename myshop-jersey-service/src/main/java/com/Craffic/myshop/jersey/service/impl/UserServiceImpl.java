package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.domain.model.TbUser;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
