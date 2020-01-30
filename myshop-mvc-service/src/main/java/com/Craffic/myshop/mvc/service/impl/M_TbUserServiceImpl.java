package com.Craffic.myshop.mvc.service.impl;

import com.Craffic.myshop.domain.model.TbUser;
import com.Craffic.myshop.mvc.dao.TbUserDao;
import com.Craffic.myshop.mvc.service.M_TbUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class M_TbUserServiceImpl implements M_TbUserService {
    @Autowired
    TbUserDao userDao;

    @Override
    public TbUser getTbUserDetail(String userId) {
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
