package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             SignatureAgreementBean
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

public class SignatureAgreementBean implements BaseBean {

    /**
     * id : 14
     * title : 数字证书服务协议
     * type : null
     * addAdminId : null
     * addTime : null
     * updAdminId : null
     * updTime : 1520215993
     * sortIndex : null
     * status : null
     * contents :
     */

    private int id;
    private String title;
    private Object type;
    private Object addAdminId;
    private Object addTime;
    private Object updAdminId;
    private int updTime;
    private Object sortIndex;
    private Object status;
    private String contents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getAddAdminId() {
        return addAdminId;
    }

    public void setAddAdminId(Object addAdminId) {
        this.addAdminId = addAdminId;
    }

    public Object getAddTime() {
        return addTime;
    }

    public void setAddTime(Object addTime) {
        this.addTime = addTime;
    }

    public Object getUpdAdminId() {
        return updAdminId;
    }

    public void setUpdAdminId(Object updAdminId) {
        this.updAdminId = updAdminId;
    }

    public int getUpdTime() {
        return updTime;
    }

    public void setUpdTime(int updTime) {
        this.updTime = updTime;
    }

    public Object getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Object sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
