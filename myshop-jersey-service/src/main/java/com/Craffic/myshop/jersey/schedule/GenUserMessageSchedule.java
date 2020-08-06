package com.Craffic.myshop.jersey.schedule;

import com.Craffic.myshop.jersey.Utils.IdcardAreaGenerator;
import com.Craffic.myshop.jersey.Utils.MessageGenerator;
import com.Craffic.myshop.jersey.dao.IdcardAreaDao;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.model.IdCardArea;
import com.Craffic.myshop.jersey.domain.model.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Slf4j
@Component
@EnableScheduling
public class GenUserMessageSchedule {
    @Autowired
    private TbUserDao userDao;

    @Autowired
    private IdcardAreaDao areaDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 */1 * * * ?") //每分钟执行一次
    public void statusCheck() {
        int sex = MessageGenerator.randomNumGenerator(2);
        String name = MessageGenerator.randomBoyNameGenerator(sex);
        String email = MessageGenerator.randomEmailGenerator();
        String phone = MessageGenerator.randomPhoneNumGenerator();
        String birthday = MessageGenerator.randomBirthday();
        logger.info("姓名：{}， 手机号：{}， 邮箱：{}， 生日：{}", name, phone, email, birthday);
        TbUser user = new TbUser();
        user.setUserName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userDao.insertTbUser(user);

        HashMap<String, Integer> areaMap = IdcardAreaGenerator.getAreaMap();
        Set<HashMap.Entry<String, Integer>> entries = areaMap.entrySet();
        for (HashMap.Entry<String, Integer> entry : entries) {
            String areaName = entry.getKey();
            Integer areaId = entry.getValue();
            System.out.println("编码： "+areaId+"  地区："+areaName);

            IdCardArea area = new IdCardArea();
            area.setAreaCode(areaId);
            area.setAreaName(areaName);
            areaDao.insertArea(area);
        }
    }

}
