package com.Craffic.myshop.jersey.api.impl;

import com.Craffic.myshop.jersey.api.apiUserService;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Service(version = "1.0.0")
public class apiUserServiceImpl implements apiUserService {

    @Autowired
    TbUserDao userDao;

    @Override
    public List<TbUserVo> getUserList(){
        List<TbUserVo> userList = userDao.getUserList();
        return userList;
    }
}
