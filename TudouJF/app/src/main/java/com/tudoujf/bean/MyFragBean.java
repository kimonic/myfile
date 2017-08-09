package com.tudoujf.bean;
/**
 * * ====================================================================
 * name:            MyFragBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/9
 * description：   首页activity中我的fragment中的listview的bean
 * history：
 * * ====================================================================
 */


public class MyFragBean {
    private int resId1;
    private int resId2;
    private String title;
    private String mark="";

    public int getResId1() {
        return resId1;
    }

    public void setResId1(int resId1) {
        this.resId1 = resId1;
    }

    public int getResId2() {
        return resId2;
    }

    public void setResId2(int resId2) {
        this.resId2 = resId2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
