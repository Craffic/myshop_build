package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.domain.model.TbUser;
import com.Craffic.myshop.jersey.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public TbUser getTbUSerDetail(String userId) {
        if (userId == null){
            return null;
        }

        TbUser user = new TbUser();
        if (Long.parseLong(userId) == 1L){
            user.setId(1L);
            user.setUserName("zhangsan");
            user.setPassword("123456");
            user.setPhone("13428282526");
            user.setEmail("39230111@qq.com");
        }
        return user;
    }
}
