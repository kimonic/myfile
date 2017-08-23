package com.tudoujf.bean.databean;

/**
 * * ====================================================================
 * name:            CalendarBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/22
 * description：   日历组件的bean
 * history：
 * * ====================================================================
 */

public class CalendarBean {
    /**背景色标识*/
    private int flag;
    /**
     * 默认背景色标识
     */
    private int defaultFlag;
    /**当前日期*/
    private int day;
    /**yyyy-mm-dd格式日期*/
    private String  dateStr;

    public int getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(int defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(int year,int month,int day) {
        StringBuilder builder=new StringBuilder();
        builder.append(year).append("-");
        if (month<10){
            builder.append("0").append(month).append("-");
        }else {
            builder.append(month).append("-");
        }

        if (day<10){
            builder.append("0").append(day);
        }else {
            builder.append(day);
        }
        this.dateStr = builder.toString();
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
