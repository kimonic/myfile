package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            InvestListBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/25
 * description：   理财---投资列表返回的bean
 * history：
 * * ====================================================================
 */

public class InvestListBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 3
     * total_pages : 1
     * items : [{"progress":100,"vouch_company_id":0,"tender_count":9,"apr":14,"amount_surplus":0,"status":6,"category_id":1,"period":8,"credited_amount":45000,"award_status":-1,"amount":45000,"id":36,"additional_amount_max":0,"is_roam":-1,"category_name":"消费信用借款","status_name":"还款中","name":"小额信用借款20160601001","additional_status":-1,"is_promise":-1,"category_type":"1","repay_type":1,"repay_type_name":"等额本息","serialno":201606010001},{"progress":10,"vouch_company_id":0,"tender_count":1,"apr":10,"amount_surplus":90000,"status":3,"category_id":1,"period":1,"credited_amount":10000,"award_status":-1,"amount":100000,"id":149,"additional_amount_max":0,"is_roam":-1,"category_name":"消费信用借款","status_name":"借款中","name":"ceshila","additional_status":-1,"is_promise":-1,"category_type":"1","repay_type":1,"repay_type_name":"等额本息","serialno":201609020002},{"progress":100,"vouch_company_id":0,"tender_count":1,"apr":10,"amount_surplus":0,"status":6,"category_id":1,"period":1,"credited_amount":10000,"award_status":-1,"amount":10000,"id":151,"additional_amount_max":0,"is_roam":-1,"category_name":"消费信用借款","status_name":"还款中","name":"1000ce","additional_status":-1,"is_promise":-1,"category_type":"1","repay_type":1,"repay_type_name":"等额本息","serialno":201609020004}]
     * params : null
     */

    private String page;
    private String epage;
    private String total_items;
    private String total_pages;
    private JsonElement params;
    private List<ItemsBean> items;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getEpage() {
        return epage;
    }

    public void setEpage(String epage) {
        this.epage = epage;
    }

    public String getTotal_items() {
        return total_items;
    }

    public void setTotal_items(String total_items) {
        this.total_items = total_items;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
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
         * progress : 100
         * vouch_company_id : 0
         * tender_count : 9
         * apr : 14
         * amount_surplus : 0
         * status : 6
         * category_id : 1
         * period : 8
         * credited_amount : 45000
         * award_status : -1
         * amount : 45000
         * id : 36
         * additional_amount_max : 0
         * is_roam : -1
         * category_name : 消费信用借款
         * status_name : 还款中
         * name : 小额信用借款20160601001
         * additional_status : -1
         * is_promise : -1
         * category_type : 1
         * repay_type : 1
         * repay_type_name : 等额本息
         * serialno : 201606010001
         */

        private String progress;
        private String vouch_company_id;
        private String tender_count;
        private String apr;
        private String amount_surplus;
        private String status;
        private String category_id;
        private String period;
        private String credited_amount;
        private String award_status;
        private String amount;
        private String id;
        private String additional_amount_max;
        private String is_roam;
        private String category_name;
        private String status_name;
        private String name;
        private String additional_status;
        private String is_promise;
        private String category_type;
        private String repay_type;
        private String repay_type_name;
        private String is_locked;
        private long serialno;

        public String getIs_locked() {
            return is_locked;
        }

        public void setIs_locked(String is_locked) {
            this.is_locked = is_locked;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getVouch_company_id() {
            return vouch_company_id;
        }

        public void setVouch_company_id(String vouch_company_id) {
            this.vouch_company_id = vouch_company_id;
        }

        public String getTender_count() {
            return tender_count;
        }

        public void setTender_count(String tender_count) {
            this.tender_count = tender_count;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getAmount_surplus() {
            return amount_surplus;
        }

        public void setAmount_surplus(String amount_surplus) {
            this.amount_surplus = amount_surplus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getCredited_amount() {
            return credited_amount;
        }

        public void setCredited_amount(String credited_amount) {
            this.credited_amount = credited_amount;
        }

        public String getAward_status() {
            return award_status;
        }

        public void setAward_status(String award_status) {
            this.award_status = award_status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdditional_amount_max() {
            return additional_amount_max;
        }

        public void setAdditional_amount_max(String additional_amount_max) {
            this.additional_amount_max = additional_amount_max;
        }

        public String getIs_roam() {
            return is_roam;
        }

        public void setIs_roam(String is_roam) {
            this.is_roam = is_roam;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdditional_status() {
            return additional_status;
        }

        public void setAdditional_status(String additional_status) {
            this.additional_status = additional_status;
        }

        public String getIs_promise() {
            return is_promise;
        }

        public void setIs_promise(String is_promise) {
            this.is_promise = is_promise;
        }

        public String getCategory_type() {
            return category_type;
        }

        public void setCategory_type(String category_type) {
            this.category_type = category_type;
        }

        public String getRepay_type() {
            return repay_type;
        }

        public void setRepay_type(String repay_type) {
            this.repay_type = repay_type;
        }

        public String getRepay_type_name() {
            return repay_type_name;
        }

        public void setRepay_type_name(String repay_type_name) {
            this.repay_type_name = repay_type_name;
        }

        public long getSerialno() {
            return serialno;
        }

        public void setSerialno(long serialno) {
            this.serialno = serialno;
        }
    }
}
