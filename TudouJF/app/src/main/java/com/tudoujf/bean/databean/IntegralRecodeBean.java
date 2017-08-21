package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            IntegralRecodeBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：   签到-->我的积分-->积分记录,返回的dataBean
 * history：
 * * ====================================================================
 */

public class IntegralRecodeBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 103
     * total_pages : 6
     * items : [{"type":"签到送积分","point":"1","time":"1503275816"},{"type":"签到送积分","point":"1","time":"1503046724"},{"type":"补签消耗","point":"2","time":"1502242100"},{"type":"材料认证","point":"10","time":"1502242100"},{"type":"抽奖消耗","point":"5","time":"1502152151"},{"type":"材料认证","point":"10","time":"1502152151"},{"type":"抽奖消耗","point":"5","time":"1502151090"},{"type":"材料认证","point":"10","time":"1502151090"},{"type":"抽奖消耗","point":"5","time":"1502150508"},{"type":"材料认证","point":"10","time":"1502150508"},{"type":"抽奖消耗","point":"5","time":"1502113418"},{"type":"材料认证","point":"10","time":"1502113418"},{"type":"抽奖消耗","point":"15","time":"1502103586"},{"type":"抽奖消耗","point":"10","time":"1502094820"},{"type":"材料认证","point":"10","time":"1502094820"},{"type":"签到送积分","point":"1","time":"1502094806"},{"type":"抽奖消耗","point":"5","time":"1502094575"},{"type":"材料认证","point":"10","time":"1502094575"},{"type":"每投资成功1000元","point":"5","time":"1499758687"},{"type":"每投资成功1000元","point":"5","time":"1499758453"}]
     * params : null
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private JsonElement params;
    private List<ItemsBean> items;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getEpage() {
        return epage;
    }

    public void setEpage(int epage) {
        this.epage = epage;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public JsonElement getParams() {
        return params;
    }

    public void setParams(JsonElement params) {
        this.params = params;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * type : 签到送积分
         * point : 1
         * time : 1503275816
         */

        private String type;
        private String point;
        private String time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
