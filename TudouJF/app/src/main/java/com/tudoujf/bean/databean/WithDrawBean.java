package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             WithDrawBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/19
 * description：  提现接口返回的数据bean
 * history：
 * *==================================================================
 */

public class WithDrawBean implements BaseBean {

    /**
     * is_bind : 0
     * is_paypassword : 1
     * addShow : true
     * type : 0
     */

    private int is_bind;
    private int is_paypassword;
    private boolean addShow;
    private int type;

    public int getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(int is_bind) {
        this.is_bind = is_bind;
    }

    public int getIs_paypassword() {
        return is_paypassword;
    }

    public void setIs_paypassword(int is_paypassword) {
        this.is_paypassword = is_paypassword;
    }

    public boolean isAddShow() {
        return addShow;
    }

    public void setAddShow(boolean addShow) {
        this.addShow = addShow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
