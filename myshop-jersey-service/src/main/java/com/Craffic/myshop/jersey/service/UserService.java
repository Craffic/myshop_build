package com.Craffic.myshop.jersey.service;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.pojo.TbUserReq;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.List;

@Service(version = "1.0.0")
public interface UserService {

    TbUser getTbUserDetail(String userId);

    public List<TbUserVo> getUserList();

    public List<TbUserVo> findUserByCondition(TbUserReq userReq);
}
