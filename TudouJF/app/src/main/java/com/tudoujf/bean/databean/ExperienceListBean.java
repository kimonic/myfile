package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             ExperienceListBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/6
 * description：
 * history：
 * *==================================================================
 */

public class ExperienceListBean implements BaseBean {

    /**
     * page : 2
     * epage : 10
     * total_items : 17
     * total_pages : 2
     * items : [{"amount":28888,"id":3719,"add_time":1475974432,"member_name":"18077777777"},{"amount":28888,"id":3714,"add_time":1475908939,"member_name":"13698525252"},{"amount":28888,"id":3710,"add_time":1475899407,"member_name":"17944445555"},{"amount":28888,"id":3708,"add_time":1475898143,"member_name":"13667887670"},{"amount":28888,"id":3706,"add_time":1475897989,"member_name":"13667887668"},{"amount":28888,"id":3704,"add_time":1475897910,"member_name":"13667887669"},{"amount":28888,"id":3700,"add_time":1475891543,"member_name":"13667887667"}]
     * params : null
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private JsonElement params;
    private List<NewcomerExperienceBidBean.TenderListBean> items;

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

    public List<NewcomerExperienceBidBean.TenderListBean> getItems() {
        return items;
    }

    public void setItems(List<NewcomerExperienceBidBean.TenderListBean> items) {
        this.items = items;
    }

   
}
