package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             NewVersionBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/27
 * description：
 * history：
 * *==================================================================
 */

public class NewVersionBean implements BaseBean {

    /**
     * new_version : 1.0.0.1
     * force_version : null
     */

    private String new_version;
    private String force_version;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNew_version() {
        return new_version;
    }

    public void setNew_version(String new_version) {
        this.new_version = new_version;
    }

    public String getForce_version() {
        return force_version;
    }

    public void setForce_version(String force_version) {
        this.force_version = force_version;
    }
}
