package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             SignatureBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/3/7
 * method:
 * <p>
 * <p>
 * description：
 * history：
 * *==================================================================
 */

public class SignatureBean implements BaseBean {

    /**
     * card_id : 510524195903315340
     * realname : 赵小白
     */

    private String card_id;
    private String realname;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
