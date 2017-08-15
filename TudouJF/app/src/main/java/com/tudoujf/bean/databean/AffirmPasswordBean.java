package com.tudoujf.bean.databean;

/**
 * * ====================================================================
 * name:            AffirmPasswordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/15
 * description：   确认新密码页返回的bean
 * history：
 * * ====================================================================
 */

public class AffirmPasswordBean {

    /**
     * code : 200
     * result : success
     * data : 密码修改成功
     * description : 密码修改成功
     * xmdy : null
     * diyou : null
     */

    private String code;
    private String result;
    private String data;
    private String description;
    private String xmdy;
    private String diyou;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getXmdy() {
        return xmdy;
    }

    public void setXmdy(String xmdy) {
        this.xmdy = xmdy;
    }

    public String getDiyou() {
        return diyou;
    }

    public void setDiyou(String diyou) {
        this.diyou = diyou;
    }
}
