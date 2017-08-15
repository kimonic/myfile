package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ====================================================================
 * name:            SignInBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/16
 * description：   签到请求返回bean
 * history：
 * * ====================================================================
 */

public class SignInBean implements BaseBean {

    /**
     * sign_status : -1
     * credit_point : 105
     */

    private String sign_status;
    private String credit_point;

    public String getSign_status() {
        return sign_status;
    }

    public void setSign_status(String sign_status) {
        this.sign_status = sign_status;
    }

    public String getCredit_point() {
        return credit_point;
    }

    public void setCredit_point(String credit_point) {
        this.credit_point = credit_point;
    }
}
