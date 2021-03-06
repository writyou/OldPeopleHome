package com.scorpiomiku.oldpeoplehome.utils;

import android.annotation.SuppressLint;

import com.scorpiomiku.oldpeoplehome.bean.SleepData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ScorpioMiku on 2019/8/18.
 */

public class TimeUtils {
    public static String getTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date) + "";
    }

    public static String getUpDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date) + "";
    }

    /**
     * 运动时间转换
     *
     * @param time
     * @return
     */
    public static String getSportTime(int time) {
        String t = "";
        int sec = time % 60;
        int min = time / 60 % 60;
        int hour = time / 60 / 60;
        if (hour > 0 && hour < 10) {
            t = t + "0" + hour + ":";
        } else if (hour == 0) {
            t = t + "00:";
        }
        if (min < 10) {
            t = t + "0" + min;
        } else {
            t = t + min;
        }
        if (sec < 10) {
            t = t + ":0" + sec;
        } else {
            t = t + ":" + sec;
        }
        return t;
    }

    /**
     * 是否有人
     *
     * @return
     */
    public static String getIsIn() {
        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int hour = calendars.get(Calendar.HOUR_OF_DAY);
        if (hour > 22 || hour < 8) {
            return "0";
        }
        return "1";
    }


    /**
     * 获取号数
     *
     * @return
     */
    public static int getDay() {
        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int day = calendars.get(Calendar.DATE);
        return day;
    }


    /**
     * 获取睡觉总时间
     *
     * @param sleepData
     * @return
     */
    public static int getSleepWholeTime(SleepData sleepData) {
        int whole = 0;
        whole += timeString2Second(sleepData.getAwakeTime());
        whole += timeString2Second(sleepData.getDeepTime());
        whole += timeString2Second(sleepData.getLightTime());
        LogUtils.loge(whole + "");
        return whole;
    }

    public static int timeString2Second(String time) {
        String[] times = time.split(":");
        int whole = 0;
        whole += Integer.valueOf(times[0]) * 60 * 60;
        LogUtils.logd(whole + "");
        whole += Integer.valueOf(times[1]) * 60;
        LogUtils.logd(whole + "");
        whole += Integer.valueOf(times[2]);
        return whole;
    }

    public static String getWholeTimeString(int wholeTime) {
        int hour, minute, second;
        String time = "";
        hour = wholeTime / 3600;
        minute = (wholeTime - hour * 3600) / 60;
        second = wholeTime - hour * 3600 - minute * 60;
        if (hour < 10) {
            time += "0";
            time += hour;
        } else {
            time += hour;
        }
        time += ":";
        if (minute < 10) {
            time += "0";
            time += minute;
        } else {
            time += minute;
        }
        time += ":";
        if (second < 10) {
            time += "0";
            time += second;
        } else {
            time += second;
        }
        return time;
    }

    /**
     * 比较大小
     *
     * @param sleepData1
     * @param sleepData2
     * @return 1>2 ? 1 : 0
     */
    public static int compareTime(String sleepData1, String sleepData2) {
        String[] times1 = sleepData1.split("-");
        String[] times2 = sleepData2.split("-");
        if (Integer.valueOf(times1[1]) > Integer.valueOf(times2[1])) {
            return -1;
        } else if (Integer.valueOf(times1[1]) == Integer.valueOf(times2[1])) {
            if (Integer.valueOf(times1[2]) > Integer.valueOf(times2[2])) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

}
