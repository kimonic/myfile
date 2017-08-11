package com.tudoujf.bean;

/**
 * * ====================================================================
 * name:            IntegralShopActBean
 * guide:           HomeActivity-->HomeFragment-->SignInActivity-->IntegralShopActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/11
 * description：   积分商城数据bean
 * history：
 * * ====================================================================
 */

public class IntegralShopActBean {
    private String  title;
    private String  integral;
    private String  count;
    private int resId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
