package com.Craffic.myshop.jersey.Utils;

import java.time.Instant;

public class DateUtils {

    /**
     * 获取当前时间
     */
    public static Instant getCurrentTime(){
        return Instant.now();
    }

    /**
     * 获取当前的时间戳
     * @param mode: 0-秒  1-毫秒
     */
    public static long getTimestamp(int mode){
        Instant now = Instant.now();
        if (mode == 0){
            // 秒为单位的时间戳
            return now.getEpochSecond();
        } else if (mode == 1) {
            // 毫秒为单位的时间戳
            return now.toEpochMilli();
        }
        return 0;
    }

    /**
     * 将long秒数或毫秒数转换为Instant
     * @param mode: 0-秒  1-毫秒
     * @param timeStamp 时间戳
     * @return Instant
     */
    public static Instant changeToInstant(int mode, long timeStamp){
        if (mode == 0){
            // 根据秒数转换时间
            Instant instant = Instant.ofEpochSecond(timeStamp);
            return instant;
        } else if (mode == 1) {
            // 根据毫秒数转换时间
            Instant instant = Instant.ofEpochMilli(timeStamp);
        }
        return Instant.now();
    }

    /**
     * 根据字符串转换Instant对象
     * @param strTime 日期字符串
     * @return
     */
    public static Instant convertStringToInstant(String strTime){
//        2020-06-24T01:30:05+08:00[Asia/Shanghai]
//        2020-06-24T01:30:05.928+08:00[Asia/Shanghai]
        Instant secondInstant = Instant.parse(strTime);
        return secondInstant;
    }
}
