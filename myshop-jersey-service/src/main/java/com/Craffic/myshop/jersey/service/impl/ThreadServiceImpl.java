package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.thread.QueryUserInfoTread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExecutorService executorService;

    @Autowired
    TbUserDao userDao;

    public int threadTest() {
        // 获取当前活跃的线程数目：
        int activeCount = ((ThreadPoolExecutor) executorService).getActiveCount();
        logger.info("当前活跃线程数：{}", activeCount);

        // 查询出TbUser表所有用户数
        Integer totalUserCount = userDao.countUser();
        List<TbUserVo> userList = userDao.getUserList();
        // 每个线程处理最多10条数据
        int maxSize = 100;
        // 计算开启线程数， 每个线程最大处理10条数据
        int maxThreadCount = totalUserCount % maxSize == 0 ? (totalUserCount / maxSize) : ((totalUserCount / maxSize) + 1);
        // 启动线程个数
        int threadNum = 0;
        logger.info("准备开启线程，需要处理的总条数：{}， 开启线程数：{}", totalUserCount, maxThreadCount);
        // 线程计数器， 当一个线程执行关闭后，线程计数器自动减1
        CountDownLatch cd = new CountDownLatch(maxThreadCount);

        // 开启线程处理具体业务
        while (true){
            logger.info("准备开启第{}个线程。。。", threadNum);
            // 已经处理了的任务数量
            int handleTaskCount = (threadNum + 1) * maxSize;
            QueryUserInfoTread userInfoTread;
            if (handleTaskCount >= totalUserCount){
                userInfoTread = new QueryUserInfoTread(userList.subList((threadNum * maxSize), totalUserCount), cd);
            } else {
                userInfoTread = new QueryUserInfoTread(userList.subList(threadNum * maxSize, (threadNum + 1) * maxSize), cd);
            }
            executorService.execute(userInfoTread);
            threadNum++;
            if (threadNum == maxThreadCount) break;
        }
        return activeCount;
    }
}
