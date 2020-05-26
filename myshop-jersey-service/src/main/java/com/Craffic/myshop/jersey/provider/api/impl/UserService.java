package com.Craffic.myshop.jersey.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class UserService implements com.Craffic.myshop.common.api.UserService {

    @Override
    public String sayHi() {
        return "Hello duboo";
    }
}
