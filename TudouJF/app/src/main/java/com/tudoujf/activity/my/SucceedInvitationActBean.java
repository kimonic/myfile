package com.tudoujf.activity.my;

/**
 * * ================================================
 * name:            SucceedInvitationActBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/5
 * description： SucceedInvitationActivity中listview的临时bean,接口调整后删除
 * history：
 * ===================================================
 */

public class SucceedInvitationActBean {
    private String  userName;
    private String  touZiZongE;
    private String  huanKuanZongE;
    private int  bacFlag;

    public int getBacFlag() {

        return bacFlag;
    }

    public void setBacFlag(int bacFlag) {
        this.bacFlag = bacFlag;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTouZiZongE() {
        return touZiZongE;
    }

    public void setTouZiZongE(String touZiZongE) {
        this.touZiZongE = touZiZongE;
    }

    public String getHuanKuanZongE() {
        return huanKuanZongE;
    }

    public void setHuanKuanZongE(String huanKuanZongE) {
        this.huanKuanZongE = huanKuanZongE;
    }
}
