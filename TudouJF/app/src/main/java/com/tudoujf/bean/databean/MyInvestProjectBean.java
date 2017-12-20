package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             MyInvestProjectBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/15
 * description：  我的项目--我的投资项目返回的json数据
 * history：
 * *==================================================================
 */

public class MyInvestProjectBean implements BaseBean {


    private List<UnderwayBean> underway;
    private List<UnderwayBean> repayment;
    private List<UnderwayBean> already;

    public List<UnderwayBean> getUnderway() {
        return underway;
    }

    public void setUnderway(List<UnderwayBean> underway) {
        this.underway = underway;
    }

    public List<UnderwayBean> getRepayment() {
        return repayment;
    }

    public void setRepayment(List<UnderwayBean> repayment) {
        this.repayment = repayment;
    }

    public List<UnderwayBean> getAlready() {
        return already;
    }

    public void setAlready(List<UnderwayBean> already) {
        this.already = already;
    }

    public static class UnderwayBean {
        /**
         * progress : 16.4
         * award_amount : 0
         * auto_status : -1
         * recover_count : 0
         * transfer_status : -1
         * cancel_count : 0
         * recover_interest : 0
         * success_time : null
         * recover_interest_yes : 0
         * deposit_certificate : -1
         * experience_return : 0
         * recover_prepayment_fee : 0
         * recover_count_yes : 0
         * loan_member_name : ljq001
         * id : 4064
         * amount : 100
         * status_name : 投标中
         * ind : 17121913581793924467
         * invest_source : 0
         * recover_status : -1
         * repay_type : 3
         * transfer_member_name : 
         * serialno : 20171219135828
         * tenderId : 4064
         * recover_principal : 0
         * amount_surplus : 83600
         * status : -2
         * transfer_member_id : 0
         * trust_status : -2
         * loan_name : 房产抵押借款测试005-奖励
         * expire_time : null
         * member_id : 12267
         * add_ip : 2130706433
         * recover_overdue_fee : 0
         * loan_member_id : 12231
         * award_interest : 0
         * recover_amount : 0
         * recover_amount_yes : 0
         * add_time : 1513663108
         * member_name : 18011111111
         * loanId : 315
         * recover_principal_yes : 0
         * loan_id : 315
         */

        private String progress;
        private String award_amount;
        private String auto_status;
        private String recover_count;
        private String transfer_status;
        private String cancel_count;
        private String recover_interest;
        private JsonElement success_time;
        private String recover_interest_yes;
        private String deposit_certificate;
        private String experience_return;
        private String recover_prepayment_fee;
        private String recover_count_yes;
        private String loan_member_name;
        private String id;
        private String amount;
        private String status_name;
        private String ind;
        private String invest_source;
        private String recover_status;
        private String repay_type;
        private String transfer_member_name;
        private String serialno;
        private String tenderId;
        private String recover_principal;
        private String amount_surplus;
        private String status;
        private String transfer_member_id;
        private String trust_status;
        private String loan_name;
        private JsonElement expire_time;
        private String member_id;
        private String add_ip;
        private String recover_overdue_fee;
        private String loan_member_id;
        private String award_interest;
        private String recover_amount;
        private String recover_amount_yes;
        private String add_time;
        private String member_name;
        private String loanId;
        private String recover_principal_yes;
        private String loan_id;

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

        public String getAuto_status() {
            return auto_status;
        }

        public void setAuto_status(String auto_status) {
            this.auto_status = auto_status;
        }

        public String getRecover_count() {
            return recover_count;
        }

        public void setRecover_count(String recover_count) {
            this.recover_count = recover_count;
        }

        public String getTransfer_status() {
            return transfer_status;
        }

        public void setTransfer_status(String transfer_status) {
            this.transfer_status = transfer_status;
        }

        public String getCancel_count() {
            return cancel_count;
        }

        public void setCancel_count(String cancel_count) {
            this.cancel_count = cancel_count;
        }

        public String getRecover_interest() {
            return recover_interest;
        }

        public void setRecover_interest(String recover_interest) {
            this.recover_interest = recover_interest;
        }

        public JsonElement getSuccess_time() {
            return success_time;
        }

        public void setSuccess_time(JsonElement success_time) {
            this.success_time = success_time;
        }

        public String getRecover_interest_yes() {
            return recover_interest_yes;
        }

        public void setRecover_interest_yes(String recover_interest_yes) {
            this.recover_interest_yes = recover_interest_yes;
        }

        public String getDeposit_certificate() {
            return deposit_certificate;
        }

        public void setDeposit_certificate(String deposit_certificate) {
            this.deposit_certificate = deposit_certificate;
        }

        public String getExperience_return() {
            return experience_return;
        }

        public void setExperience_return(String experience_return) {
            this.experience_return = experience_return;
        }

        public String getRecover_prepayment_fee() {
            return recover_prepayment_fee;
        }

        public void setRecover_prepayment_fee(String recover_prepayment_fee) {
            this.recover_prepayment_fee = recover_prepayment_fee;
        }

        public String getRecover_count_yes() {
            return recover_count_yes;
        }

        public void setRecover_count_yes(String recover_count_yes) {
            this.recover_count_yes = recover_count_yes;
        }

        public String getLoan_member_name() {
            return loan_member_name;
        }

        public void setLoan_member_name(String loan_member_name) {
            this.loan_member_name = loan_member_name;
        }

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

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getInd() {
            return ind;
        }

        public void setInd(String ind) {
            this.ind = ind;
        }

        public String getInvest_source() {
            return invest_source;
        }

        public void setInvest_source(String invest_source) {
            this.invest_source = invest_source;
        }

        public String getRecover_status() {
            return recover_status;
        }

        public void setRecover_status(String recover_status) {
            this.recover_status = recover_status;
        }

        public String getRepay_type() {
            return repay_type;
        }

        public void setRepay_type(String repay_type) {
            this.repay_type = repay_type;
        }

        public String getTransfer_member_name() {
            return transfer_member_name;
        }

        public void setTransfer_member_name(String transfer_member_name) {
            this.transfer_member_name = transfer_member_name;
        }

        public String getSerialno() {
            return serialno;
        }

        public void setSerialno(String serialno) {
            this.serialno = serialno;
        }

        public String getTenderId() {
            return tenderId;
        }

        public void setTenderId(String tenderId) {
            this.tenderId = tenderId;
        }

        public String getRecover_principal() {
            return recover_principal;
        }

        public void setRecover_principal(String recover_principal) {
            this.recover_principal = recover_principal;
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

        public String getTransfer_member_id() {
            return transfer_member_id;
        }

        public void setTransfer_member_id(String transfer_member_id) {
            this.transfer_member_id = transfer_member_id;
        }

        public String getTrust_status() {
            return trust_status;
        }

        public void setTrust_status(String trust_status) {
            this.trust_status = trust_status;
        }

        public String getLoan_name() {
            return loan_name;
        }

        public void setLoan_name(String loan_name) {
            this.loan_name = loan_name;
        }

        public JsonElement getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(JsonElement expire_time) {
            this.expire_time = expire_time;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getAdd_ip() {
            return add_ip;
        }

        public void setAdd_ip(String add_ip) {
            this.add_ip = add_ip;
        }

        public String getRecover_overdue_fee() {
            return recover_overdue_fee;
        }

        public void setRecover_overdue_fee(String recover_overdue_fee) {
            this.recover_overdue_fee = recover_overdue_fee;
        }

        public String getLoan_member_id() {
            return loan_member_id;
        }

        public void setLoan_member_id(String loan_member_id) {
            this.loan_member_id = loan_member_id;
        }

        public String getAward_interest() {
            return award_interest;
        }

        public void setAward_interest(String award_interest) {
            this.award_interest = award_interest;
        }

        public String getRecover_amount() {
            return recover_amount;
        }

        public void setRecover_amount(String recover_amount) {
            this.recover_amount = recover_amount;
        }

        public String getRecover_amount_yes() {
            return recover_amount_yes;
        }

        public void setRecover_amount_yes(String recover_amount_yes) {
            this.recover_amount_yes = recover_amount_yes;
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

        public String getLoanId() {
            return loanId;
        }

        public void setLoanId(String loanId) {
            this.loanId = loanId;
        }

        public String getRecover_principal_yes() {
            return recover_principal_yes;
        }

        public void setRecover_principal_yes(String recover_principal_yes) {
            this.recover_principal_yes = recover_principal_yes;
        }

        public String getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(String loan_id) {
            this.loan_id = loan_id;
        }
    }


}
