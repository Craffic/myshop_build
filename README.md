# myshop_build
first Kafka program

*******************************************************************************************
*******************************************************************************************
*******************************************************************************************
#2020年6月12日 01点20分
##搭建PowerMockito测试环境框架
###1. 添加依赖
````xml
<dependencies>
    <!--mockito begin-->
    <dependency>
        <groupId>org.powermock</groupId>
        <artifactId>powermock-api-mockito2</artifactId>
        <version>2.0.4</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>2.23.4</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.powermock</groupId>
        <artifactId>powermock-module-junit4</artifactId>
        <version>2.0.4</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.powermock</groupId>
        <artifactId>powermock-module-junit4-rule-agent</artifactId>
        <version>2.0.4</version>
        <scope>test</scope>
    </dependency>
     <!--mockito end-->
</dependencies>
````
###2. new BaseMockito类，所有测试类都要继承此类
````java
package com.Craffic.myshop.jersey;

import org.junit.Rule;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

public class BaseRuleMock {
    static {
        PowerMockAgent.initializeIfNeeded();
    }
    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();
}
````

###3. 使用PowerMock测试类
````java
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
````