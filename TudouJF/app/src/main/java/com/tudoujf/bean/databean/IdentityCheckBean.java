package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             IdentityCheckBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/11
 * description：  检测是否实名认证的bean
 * history：
 * *==================================================================
 */

public class IdentityCheckBean implements BaseBean {

    /**
     * is_phone : 1
     * is_trust : 1
     * realname_status : 1
     * is_realname : 1
     * paypassword : null
     * is_email : -1
     */

    private String is_phone;
    private String is_trust;
    private String realname_status;
    private String is_realname;
    private JsonElement paypassword;
    private String is_email;

    public String getIs_phone() {
        return is_phone;
    }

    public void setIs_phone(String is_phone) {
        this.is_phone = is_phone;
    }

    public String getIs_trust() {
        return is_trust;
    }

    public void setIs_trust(String is_trust) {
        this.is_trust = is_trust;
    }

    public String getRealname_status() {
        return realname_status;
    }

    public void setRealname_status(String realname_status) {
        this.realname_status = realname_status;
    }

    public String getIs_realname() {
        return is_realname;
    }

    public void setIs_realname(String is_realname) {
        this.is_realname = is_realname;
    }

    public JsonElement getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(JsonElement paypassword) {
        this.paypassword = paypassword;
    }

    public String getIs_email() {
        return is_email;
    }

    public void setIs_email(String is_email) {
        this.is_email = is_email;
    }
}
