package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            CreditorListBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/25
 * description：   理财---债权列表返回的bean
 * history：
 * * ====================================================================
 */

public class CreditorListBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 4
     * total_pages : 1
     * items : [{"progress":100,"loan_apr":9,"wait_Stringerest":37.5,"repay_status":-1,"apr":9,"amount_surplus":0,"status":2,"last_repay_time":1483841888,"loan_name":"复投-10000","tender_id":3776,"category_id":"f2c524df9a2e94a59ee1aabd8ba40912","period":1,"member_id":12237,"amount":5026.25,"id":9,"amount_money":5026.25,"debt_member_id":12231,"debt_member_name":"ljq001","wait_principal":5000,"repay_type":3,"total_period":1,"member_name":"tdjf001","loan_id":203},{"progress":15,"loan_apr":9,"wait_Stringerest":1350,"repay_status":-1,"apr":9,"amount_surplus":170000,"status":2,"last_repay_time":1490492841,"loan_name":"测试借款","tender_id":3673,"category_id":"7f8280e871cade579a48b3cc0d337fcb","period":1,"member_id":12237,"amount":30039.45,"id":5,"amount_money":30652.5,"debt_member_id":12231,"debt_member_name":"ljq001","wait_principal":30000,"repay_type":3,"total_period":1,"member_name":"tdjf001","loan_id":154},{"progress":100,"loan_apr":11,"wait_Stringerest":4.13,"repay_status":1,"apr":11,"amount_surplus":0,"status":2,"last_repay_time":1471315104,"loan_name":"房产抵押借款20160512001","tender_id":102,"category_id":"6cb03e46124ca34d2638a8d2bf3381fc","period":1,"member_id":2,"amount":147.13,"id":2,"amount_money":153.26,"debt_member_id":23,"debt_member_name":"yuxiufeng","wait_principal":150,"repay_type":3,"total_period":1,"member_name":"shengran","loan_id":23},{"progress":100,"loan_apr":9,"wait_Stringerest":0.75,"repay_status":1,"apr":9,"amount_surplus":0,"status":2,"last_repay_time":1464338834,"loan_name":"房产抵押借款20160427001","tender_id":9,"category_id":"86b49a491847c78b14841569140db90e","period":1,"member_id":2,"amount":95,"id":1,"amount_money":100,"debt_member_id":23,"debt_member_name":"yuxiufeng","wait_principal":100,"repay_type":3,"total_period":1,"member_name":"shengran","loan_id":1}]
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
         * loan_apr : 9
         * wait_Stringerest : 37.5
         * repay_status : -1
         * apr : 9
         * amount_surplus : 0
         * status : 2
         * last_repay_time : 1483841888
         * loan_name : 复投-10000
         * tender_id : 3776
         * category_id : f2c524df9a2e94a59ee1aabd8ba40912
         * period : 1
         * member_id : 12237
         * amount : 5026.25
         * id : 9
         * amount_money : 5026.25
         * debt_member_id : 12231
         * debt_member_name : ljq001
         * wait_principal : 5000
         * repay_type : 3
         * total_period : 1
         * member_name : tdjf001
         * loan_id : 203
         */

        private String progress;
        private String loan_apr;
        private String wait_Stringerest;
        private String repay_status;
        private String apr;
        private String amount_surplus;
        private String status;
        private String last_repay_time;
        private String loan_name;
        private String tender_id;
        private String category_id;
        private String period;
        private String member_id;
        private String amount;
        private String id;
        private String amount_money;
        private String debt_member_id;
        private String debt_member_name;
        private String wait_principal;
        private String repay_type;
        private String total_period;
        private String member_name;
        private String loan_id;

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getLoan_apr() {
            return loan_apr;
        }

        public void setLoan_apr(String loan_apr) {
            this.loan_apr = loan_apr;
        }

        public String getWait_Stringerest() {
            return wait_Stringerest;
        }

        public void setWait_Stringerest(String wait_Stringerest) {
            this.wait_Stringerest = wait_Stringerest;
        }

        public String getRepay_status() {
            return repay_status;
        }

        public void setRepay_status(String repay_status) {
            this.repay_status = repay_status;
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

        public String getLast_repay_time() {
            return last_repay_time;
        }

        public void setLast_repay_time(String last_repay_time) {
            this.last_repay_time = last_repay_time;
        }

        public String getLoan_name() {
            return loan_name;
        }

        public void setLoan_name(String loan_name) {
            this.loan_name = loan_name;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
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

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
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

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getDebt_member_id() {
            return debt_member_id;
        }

        public void setDebt_member_id(String debt_member_id) {
            this.debt_member_id = debt_member_id;
        }

        public String getDebt_member_name() {
            return debt_member_name;
        }

        public void setDebt_member_name(String debt_member_name) {
            this.debt_member_name = debt_member_name;
        }

        public String getWait_principal() {
            return wait_principal;
        }

        public void setWait_principal(String wait_principal) {
            this.wait_principal = wait_principal;
        }

        public String getRepay_type() {
            return repay_type;
        }

        public void setRepay_type(String repay_type) {
            this.repay_type = repay_type;
        }

        public String getTotal_period() {
            return total_period;
        }

        public void setTotal_period(String total_period) {
            this.total_period = total_period;
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
