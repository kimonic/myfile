package com.tudoujf.bean;


/**
 * * ================================================
 * name:            ManageMoneyMattersFragBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/26
 * description：ManageMoneyMattersFragment中listview的bean
 * history：
 * ===================================================
 */

public class ManageMoneyMattersFragBean {
    private String title;
    private String nianHuaShouYi;
    private String investTime;
    private String investSum;
    private float investProgress;
    private String shengYuKeTou;
    private boolean investNow;
    private boolean award;
    private float awardValue;

    public float getAwardValue() {

        return awardValue;
    }

    public void setAwardValue(float awardValue) {
        this.awardValue = awardValue;
    }

    public boolean isAward() {
        return award;
    }

    public void setAward(boolean award) {
        this.award = award;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNianHuaShouYi() {
        return nianHuaShouYi;
    }

    public void setNianHuaShouYi(String nianHuaShouYi) {
        this.nianHuaShouYi = nianHuaShouYi;
    }

    public String getInvestTime() {
        return investTime;
    }

    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }

    public String getInvestSum() {
        return investSum;
    }

    public void setInvestSum(String investSum) {
        this.investSum = investSum;
    }

    public float getInvestProgress() {
        return investProgress;
    }

    public void setInvestProgress(float investProgress) {
        this.investProgress = investProgress;
    }

    public String getShengYuKeTou() {
        return shengYuKeTou;
    }

    public void setShengYuKeTou(String shengYuKeTou) {
        this.shengYuKeTou = shengYuKeTou;
    }

    public boolean isInvestNow() {
        return investNow;
    }

    public void setInvestNow(boolean investNow) {
        this.investNow = investNow;
    }
}
