package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class HomeBean implements BaseBean {

    /**
     * sign_status : -1
     * message_count : 0
     * loan : [{"id":153,"amount":970000,"max_add_time":1499758608,"award_amount":null,"apr":15,"name":"123456","period_unit":"月","award_proportion":null,"additional_status":-1,"category_type":"1","period":12,"experience_status":-1,"award_status":null},{"id":66,"amount":300000,"max_add_time":1496634353,"award_amount":null,"apr":13,"name":"房产抵押借款20160713001","period_unit":"月","award_proportion":null,"additional_status":-1,"category_type":"1","period":6,"experience_status":-1,"award_status":null},{"id":12,"amount":550000,"max_add_time":1496816212,"award_amount":null,"apr":24,"name":"个人房产抵押","period_unit":"月","award_proportion":null,"additional_status":-1,"category_type":"1","period":3,"experience_status":-1,"award_status":null},{"id":1,"amount":1000,"max_add_time":1499304599,"award_amount":null,"apr":9,"name":"房产抵押借款20160427001","period_unit":"月","award_proportion":null,"additional_status":-1,"category_type":"1","period":1,"experience_status":-1,"award_status":null},{"id":160,"amount":0,"max_add_time":1483498603,"award_amount":null,"apr":5,"name":"新手体验标","period_unit":"天","award_proportion":null,"additional_status":-1,"category_type":"4","period":5,"experience_status":1,"award_status":null},{"id":2,"amount":50000,"max_add_time":1490948033,"award_amount":null,"apr":9,"name":"房产抵押借款20160427002","period_unit":"月","award_proportion":null,"additional_status":1,"category_type":"1","period":1,"experience_status":-1,"award_status":null}]
     * banner : [{"image":"http://imgview.test.tudoujf.com/system/banner/20161101/d0096ec6c83575373e3a21d129ff8fef.jpg","jumpurl":"https://www.tudoujf.com/content/articles/getDetail?id=450&router=/lanmuer"},{"image":"http://imgview.test.tudoujf.com/system/banner/20160826/ce93dc8dcf820c53530f50f8a0e127c2.jpg","jumpurl":"https://www.tudoujf.com/content/articles/getDetail?id=232&router=/lanmuer"},{"image":"http://imgview.test.tudoujf.com/system/banner/20161101/f3ccdd27d2000e3f9255a7e3e2c48800.jpg","jumpurl":"https://www.tudoujf.com/content/articles/getDetail?id=450&router=/lanmuer"},{"image":"http://imgview.test.tudoujf.com/system/banner/20161101/799bad5a3b514f096e69bbc4a7896cd9.jpg","jumpurl":"https://www.tudoujf.com/content/articles/getDetail?id=329&router=/lanmuer"}]
     */

    private String sign_status;
    private String message_count;
    private List<LoanBean> loan;
    private List<BannerBean> banner;

    public String getSign_status() {
        return sign_status;
    }

    public void setSign_status(String sign_status) {
        this.sign_status = sign_status;
    }

    public String getMessage_count() {
        return message_count;
    }

    public void setMessage_count(String message_count) {
        this.message_count = message_count;
    }

    public List<LoanBean> getLoan() {
        return loan;
    }

    public void setLoan(List<LoanBean> loan) {
        this.loan = loan;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class LoanBean {
        /**
         * id : 153
         * amount : 970000
         * max_add_time : 1499758608
         * award_amount : null
         * apr : 15
         * name : 123456
         * period_unit : 月
         * award_proportion : null
         * additional_status : -1
         * category_type : 1
         * period : 12
         * experience_status : -1
         * award_status : null
         */

        private String id;
        private String amount;
        private String max_add_time;
        private JsonElement award_amount;
        private String apr;
        private String name;
        private String period_unit;
        private JsonElement award_proportion;
        private String additional_status;
        private String category_type;
        private String period;
        private String experience_status;
        private String award_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMax_add_time() {
            return max_add_time;
        }

        public void setMax_add_time(String max_add_time) {
            this.max_add_time = max_add_time;
        }

        public JsonElement getAward_amount() {
            return award_amount;
        }

        public void setAward_amount(JsonElement award_amount) {
            this.award_amount = award_amount;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPeriod_unit() {
            return period_unit;
        }

        public void setPeriod_unit(String period_unit) {
            this.period_unit = period_unit;
        }

        public JsonElement getAward_proportion() {
            return award_proportion;
        }

        public void setAward_proportion(JsonElement award_proportion) {
            this.award_proportion = award_proportion;
        }

        public String getAdditional_status() {
            return additional_status;
        }

        public void setAdditional_status(String additional_status) {
            this.additional_status = additional_status;
        }

        public String getCategory_type() {
            return category_type;
        }

        public void setCategory_type(String category_type) {
            this.category_type = category_type;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getExperience_status() {
            return experience_status;
        }

        public void setExperience_status(String experience_status) {
            this.experience_status = experience_status;
        }

        public String getAward_status() {
            return award_status;
        }

        public void setAward_status(String award_status) {
            this.award_status = award_status;
        }
    }

    public static class BannerBean {
        /**
         * image : http://imgview.test.tudoujf.com/system/banner/20161101/d0096ec6c83575373e3a21d129ff8fef.jpg
         * jumpurl : https://www.tudoujf.com/content/articles/getDetail?id=450&router=/lanmuer
         */

        private String image;
        private String jumpurl;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getJumpurl() {
            return jumpurl;
        }

        public void setJumpurl(String jumpurl) {
            this.jumpurl = jumpurl;
        }
    }
}
