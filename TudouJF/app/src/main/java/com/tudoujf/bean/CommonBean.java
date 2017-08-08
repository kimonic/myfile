package com.tudoujf.bean;


import com.google.gson.JsonObject;


/**
 * * ====================================================================
 * name:            CommonBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：   返回的加密json数据中的第一层json数据
 * history：
 * * ====================================================================
 */

public class CommonBean {

    /**
     * code : 100
     * result : error
     * data : null
     * description : 用户不存在!
     * xmdy : null
     * diyou : null
     */

    private String code;
    private String result;
    private JsonObject data;
    private JsonObject description;
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

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public JsonObject getDescription() {
        return description;
    }

    public void setDescription(JsonObject description) {
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
