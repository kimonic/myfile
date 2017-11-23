package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             VIPRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/23
 * description：
 * history：
 * *==================================================================
 */

public class VIPRecordBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 20
     * total_pages : 1
     * items : [{"money":30,"add_time":1511407990,"verify_remark":"自动审核","verify_status":"申请成功","ind":"17112311331073148467","category_ind":"1个月"}]
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
         * money : 30
         * add_time : 1511407990
         * verify_remark : 自动审核
         * verify_status : 申请成功
         * ind : 17112311331073148467
         * category_ind : 1个月
         */

        private String money;
        private String add_time;
        private String verify_remark;
        private String verify_status;
        private String ind;
        private String category_ind;
        private  int  bacFlag;

        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getVerify_remark() {
            return verify_remark;
        }

        public void setVerify_remark(String verify_remark) {
            this.verify_remark = verify_remark;
        }

        public String getVerify_status() {
            return verify_status;
        }

        public void setVerify_status(String verify_status) {
            this.verify_status = verify_status;
        }

        public String getInd() {
            return ind;
        }

        public void setInd(String ind) {
            this.ind = ind;
        }

        public String getCategory_ind() {
            return category_ind;
        }

        public void setCategory_ind(String category_ind) {
            this.category_ind = category_ind;
        }
    }
}
