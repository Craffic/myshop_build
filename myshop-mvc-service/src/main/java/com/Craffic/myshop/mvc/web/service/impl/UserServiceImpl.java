package com.Craffic.myshop.mvc.web.service.impl;

import com.Craffic.myshop.domain.model.TbUser;
import com.Craffic.myshop.mvc.dao.TbUserDao;
import com.Craffic.myshop.mvc.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserDao userDao;

    @Override
    public TbUser getTbUSerDetail(String userId) {
        if (userId == null){
            return null;
        }

        TbUser user = new TbUser();
        if (Long.parseLong(userId) == 3L){
            user = userDao.selectById(Long.parseLong(userId));
        }
        return user;
    }

}
