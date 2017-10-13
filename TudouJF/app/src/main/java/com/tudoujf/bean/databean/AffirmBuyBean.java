package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             AffirmBuyBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/13
 * description：  产品购买接口返回的数据bean
 * history：
 * *==================================================================
 */

public class AffirmBuyBean implements BaseBean {

    /**
     * member : {"balance_amount":16423.76,"is_paypwd":"no"}
     * apr : 8
     * category_id : 10
     * award_scale : null
     * period_name : 1个月
     * period : 1
     * tender_amount_max : 0
     * award_status : -1
     * roam_info : null
     * is_password : no
     * name : 房地产抵押借款20170926003
     * tender_amount_min : 10
     * award_proportion : null
     * additional_status : -1
     * category_type : 1
     * repay_type : 3
     * repay_type_name : 到期本息
     * additional_apr : 0
     * wait_amount : 30000
     */

    private MemberBean member;
    private String apr;
    private String category_id;
    private JsonElement award_scale;
    private String period_name;
    private String period;
    private String tender_amount_max;
    private String award_status;
    private JsonElement roam_info;
    private String is_password;
    private String name;
    private String tender_amount_min;
    private JsonElement award_proportion;
    private String additional_status;
    private String category_type;
    private String repay_type;
    private String repay_type_name;
    private String additional_apr;
    private String wait_amount;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public JsonElement getAward_scale() {
        return award_scale;
    }

    public void setAward_scale(JsonElement award_scale) {
        this.award_scale = award_scale;
    }

    public String getPeriod_name() {
        return period_name;
    }

    public void setPeriod_name(String period_name) {
        this.period_name = period_name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTender_amount_max() {
        return tender_amount_max;
    }

    public void setTender_amount_max(String tender_amount_max) {
        this.tender_amount_max = tender_amount_max;
    }

    public String getAward_status() {
        return award_status;
    }

    public void setAward_status(String award_status) {
        this.award_status = award_status;
    }

    public JsonElement getRoam_info() {
        return roam_info;
    }

    public void setRoam_info(JsonElement roam_info) {
        this.roam_info = roam_info;
    }

    public String getIs_password() {
        return is_password;
    }

    public void setIs_password(String is_password) {
        this.is_password = is_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTender_amount_min() {
        return tender_amount_min;
    }

    public void setTender_amount_min(String tender_amount_min) {
        this.tender_amount_min = tender_amount_min;
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

    public String getAdditional_apr() {
        return additional_apr;
    }

    public void setAdditional_apr(String additional_apr) {
        this.additional_apr = additional_apr;
    }

    public String getWait_amount() {
        return wait_amount;
    }

    public void setWait_amount(String wait_amount) {
        this.wait_amount = wait_amount;
    }

    public static class MemberBean {
        /**
         * balance_amount : 16423.76
         * is_paypwd : no
         */

        private String balance_amount;
        private String is_paypwd;

        public String getBalance_amount() {
            return balance_amount;
        }

        public void setBalance_amount(String balance_amount) {
            this.balance_amount = balance_amount;
        }

        public String getIs_paypwd() {
            return is_paypwd;
        }

        public void setIs_paypwd(String is_paypwd) {
            this.is_paypwd = is_paypwd;
        }
    }
}
