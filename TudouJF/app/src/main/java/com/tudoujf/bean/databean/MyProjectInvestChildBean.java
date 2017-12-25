package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             MyProjectInvestChildBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/25
 * description：我的项目--我的投资项目bean
 * history：
 * *==================================================================
 */

public class MyProjectInvestChildBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 36
     * total_pages : 4
     * items : [{"amount":100,"progress":100,"award_amount":0.6,"tenderId":4060,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":2,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":100,"progress":100,"award_amount":0.6,"tenderId":4063,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":2,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":100,"progress":100,"award_amount":0.6,"tenderId":4064,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":2,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":10000,"progress":100,"award_amount":60,"tenderId":4056,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":200,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":1000,"progress":100,"award_amount":6,"tenderId":4057,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":20,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":100,"progress":100,"award_amount":0.6,"tenderId":4058,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试005-奖励","recover_interest":2,"category_type":"1","next_repay_time":1521595299,"loanId":315},{"amount":5000,"progress":100,"award_amount":0,"tenderId":3988,"status_name":"回款中","amount_surplus":0,"name":"房地产抵押借款20171030002","recover_interest":112.5,"category_type":"1","next_repay_time":1517408584,"loanId":304},{"amount":5000,"progress":100,"award_amount":0,"tenderId":3989,"status_name":"回款中","amount_surplus":0,"name":"房地产抵押借款20171030002","recover_interest":112.5,"category_type":"1","next_repay_time":1517408584,"loanId":304},{"amount":5000,"progress":100,"award_amount":0,"tenderId":4001,"status_name":"回款中","amount_surplus":0,"name":"房地产抵押借款20171030002","recover_interest":112.5,"category_type":"1","next_repay_time":1517408584,"loanId":304},{"amount":3000,"progress":100,"award_amount":0,"tenderId":4034,"status_name":"回款中","amount_surplus":0,"name":"房产抵押借款测试001","recover_interest":20,"category_type":"1","next_repay_time":1515823553,"loanId":309}]
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
         * amount : 100
         * progress : 100
         * award_amount : 0.6
         * tenderId : 4060
         * status_name : 回款中
         * amount_surplus : 0
         * name : 房产抵押借款测试005-奖励
         * recover_interest : 2
         * category_type : 1
         * next_repay_time : 1521595299
         * loanId : 315
         */

        private String amount;
        private String progress;
        private String award_amount;
        private String tenderId;
        private String status_name;
        private String amount_surplus;
        private String name;
        private String recover_interest;
        private String category_type;
        private String next_repay_time;
        private String loanId;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getAward_amount() {
            return award_amount;
        }

        public void setAward_amount(String award_amount) {
            this.award_amount = award_amount;
        }

        public String getTenderId() {
            return tenderId;
        }

        public void setTenderId(String tenderId) {
            this.tenderId = tenderId;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getAmount_surplus() {
            return amount_surplus;
        }

        public void setAmount_surplus(String amount_surplus) {
            this.amount_surplus = amount_surplus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecover_interest() {
            return recover_interest;
        }

        public void setRecover_interest(String recover_interest) {
            this.recover_interest = recover_interest;
        }

        public String getCategory_type() {
            return category_type;
        }

        public void setCategory_type(String category_type) {
            this.category_type = category_type;
        }

        public String getNext_repay_time() {
            return next_repay_time;
        }

        public void setNext_repay_time(String next_repay_time) {
            this.next_repay_time = next_repay_time;
        }

        public String getLoanId() {
            return loanId;
        }

        public void setLoanId(String loanId) {
            this.loanId = loanId;
        }
    }
}
