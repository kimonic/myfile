package com.tudoujf.bean;
/**
 * * ================================================
 * name:            SafetyControlActLvBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：SafetyControlActivity中listview的bean
 * history：
 * ===================================================
 */

public class SafetyControlActLvBean {
    private int  imaUrl;
    private String  title;
    private String  content;
    private boolean  flag=false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getImaUrl() {
        return imaUrl;
    }

    public void setImaUrl(int imaUrl) {
        this.imaUrl = imaUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
