package com.Craffic.myshop.jersey.service.impl;

import com.Craffic.myshop.jersey.BaseRuleMock;
import com.Craffic.myshop.jersey.dao.TbUserDao;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@PrepareForTest(UserServiceImpl.class)
public class UserServiceImplTest extends BaseRuleMock {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private TbUserDao userDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

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
        userVoList.add(userVo);
        Mockito.when(userDao.getUserList(any(RowBounds.class))).thenReturn(userVoList);
        List<TbUserVo> userList = userService.getUserList();
        boolean condition = userList.size() == 1;
        Assert.assertTrue(condition);
    }



    @Test
    public void collectDistinctTset(){
        List<TbUserVo> userVoList = new ArrayList<>();
        TbUserVo userVo = new TbUserVo();
        userVo.setId("DIMWUSXNJM2339183K437SX");
        userVo.setUserName("Craffic");
        userVo.setEmail("392301161@qq.com");
        userVo.setPhone("13428282526");
        userVo.setCreated(new Date());
        userVo.setUpdated(new Date());

        TbUserVo userVo1 = new TbUserVo();
        userVo1.setId("DIMWUSXNJM2332383K437SX");
        userVo1.setUserName("Craffic");
        userVo1.setEmail("392301161@qq.com");
        userVo1.setPhone("13428282526");
        userVo1.setCreated(new Date());
        userVo1.setUpdated(new Date());

        userVoList.add(userVo);
        userVoList.add(userVo1);

        List<TbUserVo> lst =userVoList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getUserName() + "" + o.getEmail() + " " + o.getPhone()))), ArrayList::new));
        lst.forEach(person -> System.out.println(person.toString()));
    }
}
