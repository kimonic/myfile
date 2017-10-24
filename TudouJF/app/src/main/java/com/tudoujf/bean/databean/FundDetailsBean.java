package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             FundDetailsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/24
 * description： 资金详情数据接口返回的bean
 * history：
 * *==================================================================
 */

public class FundDetailsBean implements BaseBean {

    /**
     * total_amount : 199206.02
     * freeze_amount : 32400
     * balance_amount : 166806.02
     * wait_repay_amount : 0
     * income_amount : 270899.27
     * recharge_total : 264929.21
     * principal_wait_total : 66100
     * interest_yes_total : 970.06
     * interest_wait_total : 2182.24
     * role : 1
     * interest_award : 970.06
     * withdraw_total : 0
     * expend_amount : 71693.25
     */

    private String total_amount;
    private String freeze_amount;
    private String balance_amount;
    private String wait_repay_amount;
    private String income_amount;
    private String recharge_total;
    private String principal_wait_total;
    private String interest_yes_total;
    private String interest_wait_total;
    private String role;
    private String interest_award;
    private String withdraw_total;
    private String expend_amount;

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getFreeze_amount() {
        return freeze_amount;
    }

    public void setFreeze_amount(String freeze_amount) {
        this.freeze_amount = freeze_amount;
    }

    public String getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }

    public String getWait_repay_amount() {
        return wait_repay_amount;
    }

    public void setWait_repay_amount(String wait_repay_amount) {
        this.wait_repay_amount = wait_repay_amount;
    }

    public String getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(String income_amount) {
        this.income_amount = income_amount;
    }

    public String getRecharge_total() {
        return recharge_total;
    }

    public void setRecharge_total(String recharge_total) {
        this.recharge_total = recharge_total;
    }

    public String getPrincipal_wait_total() {
        return principal_wait_total;
    }

    public void setPrincipal_wait_total(String principal_wait_total) {
        this.principal_wait_total = principal_wait_total;
    }

    public String getInterest_yes_total() {
        return interest_yes_total;
    }

    public void setInterest_yes_total(String interest_yes_total) {
        this.interest_yes_total = interest_yes_total;
    }

    public String getInterest_wait_total() {
        return interest_wait_total;
    }

    public void setInterest_wait_total(String interest_wait_total) {
        this.interest_wait_total = interest_wait_total;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInterest_award() {
        return interest_award;
    }

    public void setInterest_award(String interest_award) {
        this.interest_award = interest_award;
    }

    public String getWithdraw_total() {
        return withdraw_total;
    }

    public void setWithdraw_total(String withdraw_total) {
        this.withdraw_total = withdraw_total;
    }

    public String getExpend_amount() {
        return expend_amount;
    }

    public void setExpend_amount(String expend_amount) {
        this.expend_amount = expend_amount;
    }
}
