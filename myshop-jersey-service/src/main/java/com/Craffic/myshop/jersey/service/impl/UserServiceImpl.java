package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.pojo.TbUserReq;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TbUserDao userDao;

    @Override
    public TbUser getTbUserDetail(String userId) {
        if (userId == null){
            throw new RuntimeException("用户id不能为空");
        }
        return userDao.selectById(userId);
    }

    public List<TbUserVo> getUserList(){
        List<TbUserVo> userList = userDao.getUserList(new RowBounds(1, 10));
        return userList;
    }

    @Override
    public List<TbUserVo> findUserByCondition(TbUserReq userReq) {
        logger.info("根据条件查询符合条件的用户......");
        RowBounds rowBounds = new RowBounds((userReq.getPageNum() -1 ) * userReq.getPageCount(), userReq.getPageCount());
        List<TbUserVo> userVoList = new ArrayList<>();
        Integer count = userDao.countUsersByCondition(userReq, rowBounds);
        if (count > 0){
            userVoList = userDao.findUsersByCondition(userReq, rowBounds);
        }
        for (TbUserVo userVo : userVoList) {
            logger.info("共查找到{}位用户, id: {} 姓名：{}", userVoList.size(), userVo.getId(), userVo.getUserName());
        }
        return userVoList;
    }
}
