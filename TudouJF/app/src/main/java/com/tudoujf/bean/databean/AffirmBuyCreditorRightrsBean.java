package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             AffirmBuyCreditorRightrsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/10
 * description：  购买债权bean
 * history：
 * *==================================================================
 */

public class AffirmBuyCreditorRightrsBean implements BaseBean {

    /**
     * balance_amount : 9781108.96
     * amount : 95.00
     * income : 5.75
     * loan_period : 1个月
     * loan_name : 房产抵押借款20160427001
     * repay_type : 到期本息
     * total_period : 1
     * period : 1
     * next_repay_time : 2016-05-27
     * recover_time : 2016-05-27
     * transfer_fee : 0.95
     */

    private String balance_amount;
    private String amount;
    private String income;
    private String loan_period;
    private String loan_name;
    private String repay_type;
    private String total_period;
    private String period;
    private String next_repay_time;
    private String recover_time;
    private String transfer_fee;

    public String getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(String loan_period) {
        this.loan_period = loan_period;
    }

    public String getLoan_name() {
        return loan_name;
    }

    public void setLoan_name(String loan_name) {
        this.loan_name = loan_name;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNext_repay_time() {
        return next_repay_time;
    }

    public void setNext_repay_time(String next_repay_time) {
        this.next_repay_time = next_repay_time;
    }

    public String getRecover_time() {
        return recover_time;
    }

    public void setRecover_time(String recover_time) {
        this.recover_time = recover_time;
    }

    public String getTransfer_fee() {
        return transfer_fee;
    }

    public void setTransfer_fee(String transfer_fee) {
        this.transfer_fee = transfer_fee;
    }
}
