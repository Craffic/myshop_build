package com.Craffic.myshop.jersey.Utils;

import com.Craffic.myshop.jersey.BaseRuleMock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@PrepareForTest(DateUtils.class)
public class DateUtilsTest extends BaseRuleMock {

    @InjectMocks
    private DateUtils dateUtils;

    @Test
    public void getCurrentTime1(){
        // 这个时间比北京时间少了8个小时
        Instant currentTime = DateUtils.getCurrentTime();
        System.out.println("标准时间："+currentTime.toString());

        // 北京时间
        ZonedDateTime beiJingTime = currentTime.atZone(ZoneId.systemDefault());
        System.out.println("北京时间："+beiJingTime);
    }

    @Test
    public void getCurrentTime2(){
        PowerMockito.mockStatic(DateUtils.class);
        Instant now = Instant.now();
        PowerMockito.when(DateUtils.getCurrentTime()).thenReturn(now);
        Assert.assertTrue(now != null);
    }

    @Test
    public void getTimpstamp(){
        int mode = 0;
        System.out.println("时间戳 - 秒："+ DateUtils.getTimestamp(mode));
        mode = 1;
        System.out.println("时间戳 - 毫秒："+ DateUtils.getTimestamp(mode));
    }

    @Test
    public void changeSecondToInstantTest(){
        long epochSecond = Instant.now().getEpochSecond();
        Instant instant = DateUtils.changeToInstant(0, epochSecond);
        System.out.println(instant.atZone(ZoneId.systemDefault()));

        long epochMilli = Instant.now().toEpochMilli();
        Instant miliInstant = DateUtils.changeToInstant(1, epochMilli);
        System.out.println(miliInstant.atZone(ZoneId.systemDefault()));
    }

    @Test
    public void convertStringToInstantTest(){
        String  text = "2020-06-10T08:46:55.967Z";
        Instant parseInstant = Instant.parse(text);
        System.out.println("秒时间戳:" + parseInstant.getEpochSecond());
        System.out.println("豪秒时间戳:" + parseInstant.toEpochMilli());
        System.out.println("纳秒:" + parseInstant.getNano());
    }

}
