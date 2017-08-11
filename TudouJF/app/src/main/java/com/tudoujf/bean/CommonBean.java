package com.tudoujf.bean;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tudoujf.base.BaseBean;

import org.json.JSONObject;


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

public class CommonBean implements BaseBean{

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
    private JsonElement data;
    private JsonElement description;
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

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public JsonElement getDescription() {
        return description;
    }

    public void setDescription(JsonElement description) {
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
