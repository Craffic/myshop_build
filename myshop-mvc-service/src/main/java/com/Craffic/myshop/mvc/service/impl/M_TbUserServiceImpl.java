package com.Craffic.myshop.mvc.service.impl;

import com.Craffic.myshop.mvc.dao.TbUserDao;
import com.Craffic.myshop.mvc.domain.vo.M_TbUserVo;
import com.Craffic.myshop.mvc.service.M_TbUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class M_TbUserServiceImpl implements M_TbUserService {
    @Autowired
    TbUserDao userDao;

    @Override
    public M_TbUserVo getTbUserDetail(String userId) {
        if (userId == null){
            return null;
        }

        M_TbUserVo user = new M_TbUserVo();
        if (Long.parseLong(userId) == 3L){
            user = userDao.selectById(Long.parseLong(userId));
        }
        return user;
    }
}
