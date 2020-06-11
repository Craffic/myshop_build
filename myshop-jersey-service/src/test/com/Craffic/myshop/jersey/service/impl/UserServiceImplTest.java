package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.jersey.BaseRuleMock;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PrepareForTest(UserServiceImpl.class)
public class UserServiceImplTest  extends BaseRuleMock {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private TbUserDao userDao;

    @Test
    public void getTbUserDetailTest(){

        List<TbUserVo> userVoList = new ArrayList<>();
        TbUserVo userVo = new TbUserVo();
        userVo.setId("DIMWUSXNJM2339183K437SX");
        userVo.setUserName("Craffic");
        userVo.setEmail("392301161@qq.com");
        userVo.setPhone("13428282526");
        userVo.setCreated(new Date());
        userVo.setUpdated(new Date());
        userVoList.add(null);
        Mockito.when(userDao.getUserList()).thenReturn(userVoList);
        List<TbUserVo> userList = userService.getUserList();
        Assert.assertTrue(userList.size() == 1);
    }

}
