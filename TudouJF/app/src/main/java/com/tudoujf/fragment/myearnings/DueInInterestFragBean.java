package com.tudoujf.fragment.myearnings;

/**
 * * ================================================
 * name:            DueInInterestFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/30
 * description：  我的收益页面activity--->待收利息fragment-->bean
 * 伪数据bean,有接口数据后删除
 * history：
 * ===================================================
 */

public class DueInInterestFragBean {
    private String  date;
    private String  name;
    private String  jine;
    private String  shouYi;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getShouYi() {
        return shouYi;
    }

    public void setShouYi(String shouYi) {
        this.shouYi = shouYi;
    }
}
