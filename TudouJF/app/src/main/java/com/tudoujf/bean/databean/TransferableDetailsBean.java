package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             TransferableDetailsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/20
 * description：      我的项目---我的债权想项目---可转让详情json数据
 * history：
 * *==================================================================
 */

public class TransferableDetailsBean implements BaseBean {

    /**
     * coefficient : null
     * cancel_count : 0
     * transfer_status : -1
     * loan_name : jiekuan-1120001
     * tender_id : 4016
     * transfer_coefficient_min : 95
     * period : 1
     * transfer_fee : 0.005,0.000
     * recover_time : 2018-05-20 10:22:36
     * amount : null
     * amount_money : 1000
     * transfer_coefficient_max : 100
     * transfer_id : null
     * total_period : 1
     */

    private String coefficient;
    private String cancel_count;
    private String transfer_status;
    private String loan_name;
    private String tender_id;
    private String transfer_coefficient_min;
    private String period;
    private String transfer_fee;
    private String recover_time;
    private String amount;
    private String amount_money;
    private String transfer_coefficient_max;
    private String transfer_id;
    private String total_period;

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getCancel_count() {
        return cancel_count;
    }

    public void setCancel_count(String cancel_count) {
        this.cancel_count = cancel_count;
    }

    public String getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(String transfer_status) {
        this.transfer_status = transfer_status;
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

    public String getTransfer_coefficient_min() {
        return transfer_coefficient_min;
    }

    public void setTransfer_coefficient_min(String transfer_coefficient_min) {
        this.transfer_coefficient_min = transfer_coefficient_min;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTransfer_fee() {
        return transfer_fee;
    }

    public void setTransfer_fee(String transfer_fee) {
        this.transfer_fee = transfer_fee;
    }

    public String getRecover_time() {
        return recover_time;
    }

    public void setRecover_time(String recover_time) {
        this.recover_time = recover_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount_money() {
        return amount_money;
    }

    public void setAmount_money(String amount_money) {
        this.amount_money = amount_money;
    }

    public String getTransfer_coefficient_max() {
        return transfer_coefficient_max;
    }

    public void setTransfer_coefficient_max(String transfer_coefficient_max) {
        this.transfer_coefficient_max = transfer_coefficient_max;
    }

    public String getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(String transfer_id) {
        this.transfer_id = transfer_id;
    }

    public String getTotal_period() {
        return total_period;
    }

    public void setTotal_period(String total_period) {
        this.total_period = total_period;
    }
}
