package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             WelfareListBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/1
 * description：
 * history：
 * *==================================================================
 */

public class WelfareListBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 91
     * total_pages : 10
     * items : [{"rule_id":"28","activity_id":"5","remark":"2017-10-31 10:56:32&nbsp;&nbsp;<a href=\"/common/loan/loaninfoview#?id=303\">[房地产抵押借款20171030001]<\/a>&nbsp;&nbsp;投资金额:￥5,000.00  &nbsp;&nbsp;<span class=\"text-red\"> 红包:￥10.00<\/span>","status":"冻结","use_time":"1509418592","tender_id":"4007","getway":"1","member_id":"12267","serial_id":"2016-12-30-0013","statusCode":"1","amount":"10.00","id":"38375","end_time":"1510502400","time_limit":"1","name":"2000元1个月","type_name":"活动红包","tender_amount_min":"2000.00","add_time":"1483086836","member_name":"18011111111","loan_id":"303"}]
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
         * rule_id : 28
         * activity_id : 5
         * remark : 2017-10-31 10:56:32&nbsp;&nbsp;<a href="/common/loan/loaninfoview#?id=303">[房地产抵押借款20171030001]</a>&nbsp;&nbsp;投资金额:￥5,000.00  &nbsp;&nbsp;<span class="text-red"> 红包:￥10.00</span>
         * status : 冻结
         * use_time : 1509418592
         * tender_id : 4007
         * getway : 1
         * member_id : 12267
         * serial_id : 2016-12-30-0013
         * statusCode : 1
         * amount : 10.00
         * id : 38375
         * end_time : 1510502400
         * time_limit : 1
         * name : 2000元1个月
         * type_name : 活动红包
         * tender_amount_min : 2000.00
         * add_time : 1483086836
         * member_name : 18011111111
         * loan_id : 303
         */

        private String rule_id;
        private String activity_id;
        private String remark;
        private String status;
        private String use_time;
        private String tender_id;
        private String getway;
        private String member_id;
        private String serial_id;
        private String statusCode;
        private String amount;
        private String id;
        private String end_time;
        private String time_limit;
        private String name;
        private String type_name;
        private String tender_amount_min;
        private String add_time;
        private String member_name;
        private String loan_id;

        public String getRule_id() {
            return rule_id;
        }

        public void setRule_id(String rule_id) {
            this.rule_id = rule_id;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
        }

        public String getGetway() {
            return getway;
        }

        public void setGetway(String getway) {
            this.getway = getway;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getSerial_id() {
            return serial_id;
        }

        public void setSerial_id(String serial_id) {
            this.serial_id = serial_id;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getTender_amount_min() {
            return tender_amount_min;
        }

        public void setTender_amount_min(String tender_amount_min) {
            this.tender_amount_min = tender_amount_min;
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

        public String getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(String loan_id) {
            this.loan_id = loan_id;
        }
    }
}
