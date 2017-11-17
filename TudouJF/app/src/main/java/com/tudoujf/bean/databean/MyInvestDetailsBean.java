package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             MyInvestDetailsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/17
 * description：
 * history：
 * *==================================================================
 */

public class MyInvestDetailsBean implements BaseBean {


    /**
     * loan_info : {"apr":9,"status":6,"reverify_time":1509459784,"period_name":"3个月","expire_time":1517408584,"period":3,"verify_time":"2017-10-30","award_status":-1,"id":304,"overdue_time":"2017-11-14","name":"房地产抵押借款20171030002","award_proportion":null,"repay_type":3,"repay_type_name":"到期本息","serialno":201710300002,"additional_apr":0}
     * recover_info : [{"advance_interest":0,"interest_yes":0,"tender_id":4001,"recover_time":"2018-01-31","loan_member_name":"ljq001","late_interest":0,"amount":5112.5,"id":4180,"web_provide_amount":0,"principal":5000,"status_name":"未还款","ind":"05dd06a1e8c51940705199fffa4ef337","interest":112.5,"principal_yes":0,"prepayment_status":-1,"repay_period_id":284,"period_no":1,"tender_member_name":"18011111111","status":-1,"transfer_member_id":0,"overdue_income_fee":0,"amount_yes":0,"loan_name":"房地产抵押借款20171030002","period":1,"recover_fee":0,"tender_member_id":12267,"loan_member_id":12231,"add_time":1509459784,"repay_type_id":3,"loan_id":304}]
     * tender_info : {"amount":5000,"award_amount":0,"status_tender_name":"未还款","status_name":"还款中","recover_income":0,"wait_principal":5000,"recover_income_all":112.5,"loan_id":304}
     */

    private LoanInfoBean loan_info;
    private TenderInfoBean tender_info;
    private List<RecoverInfoBean> recover_info;

    public LoanInfoBean getLoan_info() {
        return loan_info;
    }

    public void setLoan_info(LoanInfoBean loan_info) {
        this.loan_info = loan_info;
    }

    public TenderInfoBean getTender_info() {
        return tender_info;
    }

    public void setTender_info(TenderInfoBean tender_info) {
        this.tender_info = tender_info;
    }

    public List<RecoverInfoBean> getRecover_info() {
        return recover_info;
    }

    public void setRecover_info(List<RecoverInfoBean> recover_info) {
        this.recover_info = recover_info;
    }

    public static class LoanInfoBean {
        /**
         * apr : 9
         * status : 6
         * reverify_time : 1509459784
         * period_name : 3个月
         * expire_time : 1517408584
         * period : 3
         * verify_time : 2017-10-30
         * award_status : -1
         * id : 304
         * overdue_time : 2017-11-14
         * name : 房地产抵押借款20171030002
         * award_proportion : null
         * repay_type : 3
         * repay_type_name : 到期本息
         * serialno : 201710300002
         * additional_apr : 0
         */

        private String apr;
        private String status;
        private String reverify_time;
        private String period_name;
        private String expire_time;
        private String period;
        private String verify_time;
        private String award_status;
        private String id;
        private String overdue_time;
        private String name;
        private JsonElement award_proportion;
        private String repay_type;
        private String repay_type_name;
        private String serialno;
        private String additional_apr;
        private String additional_status;
        private String progress;

        public String getAdditional_status() {
            return additional_status;
        }

        public void setAdditional_status(String additional_status) {
            this.additional_status = additional_status;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReverify_time() {
            return reverify_time;
        }

        public void setReverify_time(String reverify_time) {
            this.reverify_time = reverify_time;
        }

        public String getPeriod_name() {
            return period_name;
        }

        public void setPeriod_name(String period_name) {
            this.period_name = period_name;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getVerify_time() {
            return verify_time;
        }

        public void setVerify_time(String verify_time) {
            this.verify_time = verify_time;
        }

        public String getAward_status() {
            return award_status;
        }

        public void setAward_status(String award_status) {
            this.award_status = award_status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOverdue_time() {
            return overdue_time;
        }

        public void setOverdue_time(String overdue_time) {
            this.overdue_time = overdue_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public JsonElement getAward_proportion() {
            return award_proportion;
        }

        public void setAward_proportion(JsonElement award_proportion) {
            this.award_proportion = award_proportion;
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

        public String getSerialno() {
            return serialno;
        }

        public void setSerialno(String serialno) {
            this.serialno = serialno;
        }

        public String getAdditional_apr() {
            return additional_apr;
        }

        public void setAdditional_apr(String additional_apr) {
            this.additional_apr = additional_apr;
        }
    }

    public static class TenderInfoBean {
        /**
         * amount : 5000
         * award_amount : 0
         * status_tender_name : 未还款
         * status_name : 还款中
         * recover_income : 0
         * wait_principal : 5000
         * recover_income_all : 112.5
         * loan_id : 304
         */

        private String amount;
        private String award_amount;
        private String status_tender_name;
        private String status_name;
        private String recover_income;
        private String wait_principal;
        private String recover_income_all;
        private String loan_id;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAward_amount() {
            return award_amount;
        }

        public void setAward_amount(String award_amount) {
            this.award_amount = award_amount;
        }

        public String getStatus_tender_name() {
            return status_tender_name;
        }

        public void setStatus_tender_name(String status_tender_name) {
            this.status_tender_name = status_tender_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getRecover_income() {
            return recover_income;
        }

        public void setRecover_income(String recover_income) {
            this.recover_income = recover_income;
        }

        public String getWait_principal() {
            return wait_principal;
        }

        public void setWait_principal(String wait_principal) {
            this.wait_principal = wait_principal;
        }

        public String getRecover_income_all() {
            return recover_income_all;
        }

        public void setRecover_income_all(String recover_income_all) {
            this.recover_income_all = recover_income_all;
        }

        public String getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(String loan_id) {
            this.loan_id = loan_id;
        }
    }

    public static class RecoverInfoBean {
        /**
         * advance_interest : 0
         * interest_yes : 0
         * tender_id : 4001
         * recover_time : 2018-01-31
         * loan_member_name : ljq001
         * late_interest : 0
         * amount : 5112.5
         * id : 4180
         * web_provide_amount : 0
         * principal : 5000
         * status_name : 未还款
         * ind : 05dd06a1e8c51940705199fffa4ef337
         * interest : 112.5
         * principal_yes : 0
         * prepayment_status : -1
         * repay_period_id : 284
         * period_no : 1
         * tender_member_name : 18011111111
         * status : -1
         * transfer_member_id : 0
         * overdue_income_fee : 0
         * amount_yes : 0
         * loan_name : 房地产抵押借款20171030002
         * period : 1
         * recover_fee : 0
         * tender_member_id : 12267
         * loan_member_id : 12231
         * add_time : 1509459784
         * repay_type_id : 3
         * loan_id : 304
         */

        private String advance_interest;
        private String interest_yes;
        private String tender_id;
        private String recover_time;
        private String loan_member_name;
        private String late_interest;
        private String amount;
        private String id;
        private String web_provide_amount;
        private String principal;
        private String status_name;
        private String ind;
        private String interest;
        private String principal_yes;
        private String prepayment_status;
        private String repay_period_id;
        private String period_no;
        private String tender_member_name;
        private String status;
        private String transfer_member_id;
        private String overdue_income_fee;
        private String amount_yes;
        private String loan_name;
        private String period;
        private String recover_fee;
        private String tender_member_id;
        private String loan_member_id;
        private String add_time;
        private String repay_type_id;
        private String loan_id;

        public String getAdvance_interest() {
            return advance_interest;
        }

        public void setAdvance_interest(String advance_interest) {
            this.advance_interest = advance_interest;
        }

        public String getInterest_yes() {
            return interest_yes;
        }

        public void setInterest_yes(String interest_yes) {
            this.interest_yes = interest_yes;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
        }

        public String getRecover_time() {
            return recover_time;
        }

        public void setRecover_time(String recover_time) {
            this.recover_time = recover_time;
        }

        public String getLoan_member_name() {
            return loan_member_name;
        }

        public void setLoan_member_name(String loan_member_name) {
            this.loan_member_name = loan_member_name;
        }

        public String getLate_interest() {
            return late_interest;
        }

        public void setLate_interest(String late_interest) {
            this.late_interest = late_interest;
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

        public String getWeb_provide_amount() {
            return web_provide_amount;
        }

        public void setWeb_provide_amount(String web_provide_amount) {
            this.web_provide_amount = web_provide_amount;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
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

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getPrincipal_yes() {
            return principal_yes;
        }

        public void setPrincipal_yes(String principal_yes) {
            this.principal_yes = principal_yes;
        }

        public String getPrepayment_status() {
            return prepayment_status;
        }

        public void setPrepayment_status(String prepayment_status) {
            this.prepayment_status = prepayment_status;
        }

        public String getRepay_period_id() {
            return repay_period_id;
        }

        public void setRepay_period_id(String repay_period_id) {
            this.repay_period_id = repay_period_id;
        }

        public String getPeriod_no() {
            return period_no;
        }

        public void setPeriod_no(String period_no) {
            this.period_no = period_no;
        }

        public String getTender_member_name() {
            return tender_member_name;
        }

        public void setTender_member_name(String tender_member_name) {
            this.tender_member_name = tender_member_name;
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

        public String getOverdue_income_fee() {
            return overdue_income_fee;
        }

        public void setOverdue_income_fee(String overdue_income_fee) {
            this.overdue_income_fee = overdue_income_fee;
        }

        public String getAmount_yes() {
            return amount_yes;
        }

        public void setAmount_yes(String amount_yes) {
            this.amount_yes = amount_yes;
        }

        public String getLoan_name() {
            return loan_name;
        }

        public void setLoan_name(String loan_name) {
            this.loan_name = loan_name;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getRecover_fee() {
            return recover_fee;
        }

        public void setRecover_fee(String recover_fee) {
            this.recover_fee = recover_fee;
        }

        public String getTender_member_id() {
            return tender_member_id;
        }

        public void setTender_member_id(String tender_member_id) {
            this.tender_member_id = tender_member_id;
        }

        public String getLoan_member_id() {
            return loan_member_id;
        }

        public void setLoan_member_id(String loan_member_id) {
            this.loan_member_id = loan_member_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getRepay_type_id() {
            return repay_type_id;
        }

        public void setRepay_type_id(String repay_type_id) {
            this.repay_type_id = repay_type_id;
        }

        public String getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(String loan_id) {
            this.loan_id = loan_id;
        }
    }
}
