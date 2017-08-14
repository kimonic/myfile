package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ====================================================================
 * name:            CheckPhoneIsExistRegisterActBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/14
 * description：   RegisterActivity中检测注册的手机号是否已存在,返回的dataBean
 * history：
 * * ====================================================================
 */


public class CheckPhoneIsExistRegisterActBean implements BaseBean{

    /**
     * status : 0
     */

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
