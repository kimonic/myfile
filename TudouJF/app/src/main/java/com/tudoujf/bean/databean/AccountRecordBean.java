package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             AccountRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/27
 * description：  我的推广-->结算记录接口返回的数据bean
 * history：
 * *==================================================================
 */

public class AccountRecordBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 13
     * total_pages : 2
     * items : [{"verify_admin_id":1,"status":"结算成功","verify_ip":993874806,"log_ids":"1,3","verify_time":1461926884,"member_id":3,"id":1,"add_ip":467619322,"verify_status":1,"ind":"059bacecf6c928c954008421d94ffae4","money":6.38,"add_time":"2016-04-29","member_name":"jingbo"}]
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
         * verify_admin_id : 1
         * status : 结算成功
         * verify_ip : 993874806
         * log_ids : 1,3
         * verify_time : 1461926884
         * member_id : 3
         * id : 1
         * add_ip : 467619322
         * verify_status : 1
         * ind : 059bacecf6c928c954008421d94ffae4
         * money : 6.38
         * add_time : 2016-04-29
         * member_name : jingbo
         */

        private String verify_admin_id;
        private String status;
        private String verify_ip;
        private String log_ids;
        private String verify_time;
        private String member_id;
        private String id;
        private String add_ip;
        private String verify_status;
        private String ind;
        private String money;
        private String add_time;
        private String member_name;
        private  int  bacFlag;

        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

        public String getVerify_admin_id() {
            return verify_admin_id;
        }

        public void setVerify_admin_id(String verify_admin_id) {
            this.verify_admin_id = verify_admin_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVerify_ip() {
            return verify_ip;
        }

        public void setVerify_ip(String verify_ip) {
            this.verify_ip = verify_ip;
        }

        public String getLog_ids() {
            return log_ids;
        }

        public void setLog_ids(String log_ids) {
            this.log_ids = log_ids;
        }

        public String getVerify_time() {
            return verify_time;
        }

        public void setVerify_time(String verify_time) {
            this.verify_time = verify_time;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdd_ip() {
            return add_ip;
        }

        public void setAdd_ip(String add_ip) {
            this.add_ip = add_ip;
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

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }
    }
}
