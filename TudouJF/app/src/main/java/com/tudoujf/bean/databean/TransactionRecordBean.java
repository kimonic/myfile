package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             TransactionRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/24
 * description：交易记录接口返回的数据bean
 * history：
 * *==================================================================
 */

public class TransactionRecordBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 675
     * total_pages : 68
     * items : [{"pre_expend":350811.74,"money_type":"expend","expend":350812.07,"pre_freeze":27800,"pre_income":350811.74,"balance":9861585.96,"amount_money":0.33,"un_freeze":0,"freeze":27800,"new_expend":0.33,"fee_name":"提现服务费","income":1.024019803E7,"money":0.33,"new_freeze":0,"add_time":"1508727359","new_income":0}]
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
         * pre_expend : 350811.74
         * money_type : expend
         * expend : 350812.07
         * pre_freeze : 27800
         * pre_income : 350811.74
         * balance : 9861585.96
         * amount_money : 0.33
         * un_freeze : 0
         * freeze : 27800
         * new_expend : 0.33
         * fee_name : 提现服务费
         * income : 1.024019803E7
         * money : 0.33
         * new_freeze : 0
         * add_time : 1508727359
         * new_income : 0
         * "loan_name": "laishang10000"
         */

        private  String  loan_name="";
        private String pre_expend;
        private String money_type;
        private String expend;
        private String pre_freeze;
        private String pre_income;
        private String balance;
        private String amount_money;
        private String un_freeze;
        private String freeze;
        private String new_expend;
        private String fee_name;
        private String income;
        private String money;
        private String new_freeze;
        private String add_time;
        private String new_income;

        public String getLoan_name() {
            return loan_name;
        }

        public void setLoan_name(String loan_name) {
            this.loan_name = loan_name;
        }

        public String getPre_expend() {
            return pre_expend;
        }

        public void setPre_expend(String pre_expend) {
            this.pre_expend = pre_expend;
        }

        public String getMoney_type() {
            return money_type;
        }

        public void setMoney_type(String money_type) {
            this.money_type = money_type;
        }

        public String getExpend() {
            return expend;
        }

        public void setExpend(String expend) {
            this.expend = expend;
        }

        public String getPre_freeze() {
            return pre_freeze;
        }

        public void setPre_freeze(String pre_freeze) {
            this.pre_freeze = pre_freeze;
        }

        public String getPre_income() {
            return pre_income;
        }

        public void setPre_income(String pre_income) {
            this.pre_income = pre_income;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getUn_freeze() {
            return un_freeze;
        }

        public void setUn_freeze(String un_freeze) {
            this.un_freeze = un_freeze;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getNew_expend() {
            return new_expend;
        }

        public void setNew_expend(String new_expend) {
            this.new_expend = new_expend;
        }

        public String getFee_name() {
            return fee_name;
        }

        public void setFee_name(String fee_name) {
            this.fee_name = fee_name;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNew_freeze() {
            return new_freeze;
        }

        public void setNew_freeze(String new_freeze) {
            this.new_freeze = new_freeze;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getNew_income() {
            return new_income;
        }

        public void setNew_income(String new_income) {
            this.new_income = new_income;
        }
    }
}
