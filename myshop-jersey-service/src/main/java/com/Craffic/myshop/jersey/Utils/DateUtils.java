package com.Craffic.myshop.jersey.Utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public final static String DATE_PATTEN_1 = "yyyyMMdd";
    public final static String DATE_PATTEN_2 = "yyyy-MM-dd";
    public final static String DATE_PATTEN_3 = "yyyy/MM/dd";

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
            return Instant.ofEpochSecond(timeStamp);
        } else if (mode == 1) {
            // 根据毫秒数转换时间
            return Instant.ofEpochMilli(timeStamp);
        }
        return Instant.now();
    }

    /**
     * 根据字符串转换Instant对象
     * @param strTime 日期字符串
     * @return
     */
    public static Instant convertStringToInstant(String strTime){
        Instant secondInstant = Instant.parse(strTime);
        return secondInstant;
    }

    /**
     * 指定日期
     */
    public static LocalDate specifiedDate(int year, int month, int day){
        LocalDate specifiedDate = LocalDate.of(year, month, day);
        return specifiedDate;
    }

    /**
     * 比较日期是否相等
     */
    public static Boolean compareDates(LocalDate localDate1, LocalDate localDate2){
        return localDate1.isAfter(localDate2);
    }

    /**
     * 格式化LocalDate
     */
    public static String formatDate(LocalDate date, String datePatten){
        String formatDate = "";
        if (DATE_PATTEN_1.equals(datePatten)){
            formatDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        } else if (DATE_PATTEN_2.equals(datePatten)){
            formatDate = date.format(DateTimeFormatter.ISO_DATE);
        } else if (DATE_PATTEN_3.equals(datePatten)){
            formatDate = date.format(DateTimeFormatter.ofPattern(datePatten));
        }
        return formatDate;
    }

    /**
     * Date to Instant
     * @param date
     * @return
     */
    public static Instant dateConvertInstant(Date date){
        Instant instant = date.toInstant();
        return instant;
    }

    /**
     * Date to LocalDate
     */
    public static LocalDate dateConvertLocalDate(Date date){
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * Date to LocalDateTime
     */
    public static LocalDateTime dateConvertLocalDateTime(Date date){
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }

    /**
     * LocalDateTime to Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalDate to Date
     */
    public static Date localDateConvertDate(LocalDate localDate){
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
