package com.Craffic.myshop.jersey.thread;

import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 查询用户信息线程
 */
public class QueryUserInfoTread implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<TbUserVo> userList;
    private CountDownLatch cd;

    public QueryUserInfoTread(List<TbUserVo> userList, CountDownLatch cd) {
        this.userList = userList;
        this.cd = cd;
    }

    @Override
    public void run() {
        // 处理业务
        if (!CollectionUtils.isEmpty(userList)){
            userList.stream().forEach(user -> {
                logger.info("姓名：{}， 电话：{}， 邮箱：{}", user.getUserName(), user.getPhone(), user.getEmail());
            });
        }
    }
}
