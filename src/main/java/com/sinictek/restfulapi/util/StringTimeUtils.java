package com.sinictek.restfulapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author sinictek-pd
 * @Date 2020/6/17 12:18
 * @Version 1.0
 */
public class StringTimeUtils {


    private static String timeStr1;
    private static String timeStr2;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat simpleDateFormatDayMonth = new SimpleDateFormat("yyyyMM");
    /*public static String getTimeStrNow1(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(instance.YEAR);
        int month = instance.get(instance.MARCH);
        int date = instance.get(instance.DAY_OF_MONTH);
        int hour = instance.get(instance.HOUR_OF_DAY);
        int minute = instance.get(instance.MINUTE);
        int secord = instance.get(instance.SECOND);
        timeStr1 =   year+"-"+month+"-"+ date +"  " +hour+ ":" + minute+":"+secord;
        return timeStr1;
    }*/

    /***
     *  时间转换为字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTimeDateToString(Date date){
        //Date date = new Date();

        timeStr1 = simpleDateFormat.format(date);

        return timeStr1;
    }
    /***
     *  时间转换为字符串
     * @param date
     * @return yyyyMM
     */
    public static String getDateToYearMonthString(Date date){
        //Date date = new Date();

        timeStr1 = simpleDateFormatDayMonth.format(date);

        return timeStr1;
    }
    /***
     *  时间转换为字符串
     * @param date
     * @return yyyyMMdd
     */
    public static String getDateToYearMonthDayString(Date date){
        //Date date = new Date();

        timeStr1 = simpleDateFormatDate.format(date);

        return timeStr1;
    }

    public static Date getTimeStringToDate(String str) throws ParseException {
        return  simpleDateFormat.parse(str);
    }

    /***
     * @param instance  Calendar.getInstance();
     * @param days  天数加减 返回 yyyyMMdd
     * @return
     */
    public static String addDataStrNow(Calendar instance,int days) {
        //Calendar instance = Calendar.getInstance(); instance.setTime();
        instance.add(Calendar.DAY_OF_YEAR,days);
        String year = instance.get(instance.YEAR)+"";
        String month = String.valueOf(instance.get(instance.MONTH)+1);
        month = month.length()>1?month:"0"+month;
        String day = instance.get(instance.DAY_OF_MONTH)+"";
        day = day.length()>1?day:"0"+day;

        return year+month+day;
    }

    /***
     * @param instance  Calendar.getInstance();
     * @param hour  小时加减
     * @return
     */
    public static String addHourTimeStrNow(Calendar instance,int hour) {
        //Calendar instance = Calendar.getInstance(); instance.setTime();
        instance.add(Calendar.HOUR_OF_DAY,hour);
        return instance.get(instance.YEAR)+"-"+
                (instance.get(instance.MONTH)+1)+"-"+
                instance.get(instance.DAY_OF_MONTH) +"  " +
                instance.get(instance.HOUR_OF_DAY)+ ":" +
                instance.get(instance.MINUTE)+":"+
                instance.get(instance.SECOND);
    }
}
