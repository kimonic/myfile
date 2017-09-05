package com.tudoujf.activity.my.mypopularize;

/**
 * * ================================================
 * name:            RecommendRecordActBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description： RecommendRecordActivity中listview的临时bean,接口调整后删除
 * history：
 * ===================================================
 */

public class RecommendRecordActBean {
    private String userName;
    private String touZiTiCheng;
    private String tiChengBiLi;
    private String jieKuanTiCheng;
    private String date;
    private int bacFlag;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTouZiTiCheng() {
        return touZiTiCheng;
    }

    public void setTouZiTiCheng(String touZiTiCheng) {
        this.touZiTiCheng = touZiTiCheng;
    }

    public String getTiChengBiLi() {
        return tiChengBiLi;
    }

    public void setTiChengBiLi(String tiChengBiLi) {
        this.tiChengBiLi = tiChengBiLi;
    }

    public String getJieKuanTiCheng() {
        return jieKuanTiCheng;
    }

    public void setJieKuanTiCheng(String jieKuanTiCheng) {
        this.jieKuanTiCheng = jieKuanTiCheng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBacFlag() {
        return bacFlag;
    }

    public void setBacFlag(int bacFlag) {
        this.bacFlag = bacFlag;
    }
}
