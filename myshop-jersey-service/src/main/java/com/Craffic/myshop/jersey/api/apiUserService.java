package com.Craffic.myshop.jersey.api;

import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

@Service(version = "1.0.0")
public interface apiUserService {
    public List<TbUserVo> getUserList();
}
