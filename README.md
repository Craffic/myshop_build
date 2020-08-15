# myshop_build
first Kafka program


#2020年6月12日  01点36分
##添加枚举类enum
###1. 写枚举基类 
````java
public interface BaseEnum<T>{
    T val();
    String desc();
}
````
###2. 单独建立枚举类继承BaseEnum
````java
public enum GenderEnum implements BaseEnum<Integer>, Serializable {
    FEMALE(0, "女性"), MAIL(1, "男性");

    private int value;
    private String desc;

    private GenderEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }
    @Override
    public Integer val() {
        return value;
    }

    @Override
    public String desc() {
        return desc;
    }

    private static GenderEnum parseByValue(Integer value){
        for (GenderEnum enumObject:values()){
            if (enumObject.val().equals(value)){
                return enumObject;
            }
        }
        return null;
    }
}
````



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

*******************************************************************************************
*******************************************************************************************
*******************************************************************************************
#2020年8月15日 10点56分
##搭建SpringBoot整合redis+sentinel环境
###1. 添加依赖
````xml
<!--redis begin-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
            <version>2.0.4.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.8.1</version>
        </dependency>
        <!--redis end-->
````
###2. 配置
````
  #Redis
  redis:
    cluster:
      nodes: 192.168.40.133:6379,192.168.40.133:6380,192.168.40.133:6381
    lettuce:
      pool:
        max-idle: 20
        min-idle: 5
        max-active: 80
        max-wait: 1000s
    sentinel:
      nodes: 192.168.40.133:26379, 192.168.40.133:26380, 192.168.40.133:26381
      master: mymaster
  session:
    #store-type: redis
    redis:
      flush-mode: immediate
    store-type: none
````

###3. action
````
@POST
      @Path("query/data")
      @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
      public ResponseBody<String> rsaDecrypt(String str) throws Exception {
          String value = redisUtil.queryByKey("Craffic");
          return new ResponseBody("200000", value);
      }
````
      
      
###4. redisTemplate
      package com.Craffic.myshop.jersey.Utils.redis;
      
      import lombok.Data;
      import lombok.extern.slf4j.Slf4j;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.context.support.ApplicationObjectSupport;
      import org.springframework.data.redis.core.RedisTemplate;
      import org.springframework.stereotype.Component;
      @Slf4j
      @Data
      @Component
      public class RedisUtil extends ApplicationObjectSupport {
          @Autowired
          private RedisTemplate<String, String> redisTemplate;
      
          public String queryByKey(String key) {
              String str = redisTemplate.opsForValue().get(key);
              return str;
          }
      }
      
