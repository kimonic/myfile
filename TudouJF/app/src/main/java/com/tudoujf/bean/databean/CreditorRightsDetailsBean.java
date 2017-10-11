package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             CreditorRightsDetailsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/10
 * description：   债权详情bean
 * history：
 * *==================================================================
 */

public class CreditorRightsDetailsBean implements BaseBean {


    /**
     * transferMap : {"amount":10000,"id":12,"loan_member_id":12321,"wait_interest":66.67,"amount_money":10000,"status":2,"income":66.67,"tender_id":3896,"wait_principal":10000,"period":1,"total_period":1,"member_id":12344,"loan_id":275}
     * loanMap : {"member":{"self_loan":-1,"is_login":-1},"loan_info":{"progress":"100.00","vouch_company_id":"0","tender_count":"1","left_amount":"0.00","use":10102,"tender_amount_max":100000,"validate":5,"award_status":-1,"amount":"10000.00","id":"275","additional_amount_max":0,"status_name":"已还完","overdue_time":"2017-09-16 10:26:42","name":"房地产抵押贷款20170911001","additional_status":-1,"is_promise":"-1","repay_type":"3","is_auto":1,"serialno":"201709110001","apr":"8.00","contents":"<p>12123<\/p>","status":"7","category_id":"10","period_name":"1个月","credited_amount":"10000.00","period":"1","member_id":"12321","hits":7,"category_name":"抵押标","is_company":-1,"agreementTitle":"抵押标协议","tender_amount_min":10,"password_status":false,"category_type":"1","member_name":"15222222222","additional_apr":0,"wait_amount":"0.00","agreementNid":"pawn"},"iscompany":"-1","tender_list":[{"amount":10000,"id":3896,"add_time":1505097727,"member_name":"17**"}],"repay_type":{"id":3,"contents":"到期还本还息","remark":"","name":"到期本息"},"attaList":[]}
     */

    private TransferMapBean transferMap;
    private LoanMapBean loanMap;

    public TransferMapBean getTransferMap() {
        return transferMap;
    }

    public void setTransferMap(TransferMapBean transferMap) {
        this.transferMap = transferMap;
    }

    public LoanMapBean getLoanMap() {
        return loanMap;
    }

    public void setLoanMap(LoanMapBean loanMap) {
        this.loanMap = loanMap;
    }

    public static class TransferMapBean {
        /**
         * amount : 10000
         * id : 12
         * loan_member_id : 12321
         * wait_interest : 66.67
         * amount_money : 10000
         * status : 2
         * income : 66.67
         * tender_id : 3896
         * wait_principal : 10000
         * period : 1
         * total_period : 1
         * member_id : 12344
         * loan_id : 275
         * period_yes
         * last_repay_time
         */

        private String period_yes;
        private String last_repay_time;
        private String amount;
        private String id;
        private String loan_member_id;
        private String wait_interest;
        private String amount_money;
        private String status;
        private String income;
        private String tender_id;
        private String wait_principal;
        private String period;
        private String total_period;
        private String member_id;
        private String loan_id;
        private String is_self;

        public String getIs_self() {
            return is_self;
        }

        public void setIs_self(String is_self) {
            this.is_self = is_self;
        }

        public String getPeriod_yes() {
            return period_yes;
        }

        public void setPeriod_yes(String period_yes) {
            this.period_yes = period_yes;
        }

        public String getLast_repay_time() {
            return last_repay_time;
        }

        public void setLast_repay_time(String last_repay_time) {
            this.last_repay_time = last_repay_time;
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

        public String getLoan_member_id() {
            return loan_member_id;
        }

        public void setLoan_member_id(String loan_member_id) {
            this.loan_member_id = loan_member_id;
        }

        public String getWait_interest() {
            return wait_interest;
        }

        public void setWait_interest(String wait_interest) {
            this.wait_interest = wait_interest;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
        }

        public String getWait_principal() {
            return wait_principal;
        }

        public void setWait_principal(String wait_principal) {
            this.wait_principal = wait_principal;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getTotal_period() {
            return total_period;
        }

        public void setTotal_period(String total_period) {
            this.total_period = total_period;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(String loan_id) {
            this.loan_id = loan_id;
        }
    }

    public static class LoanMapBean {
        /**
         * member : {"self_loan":-1,"is_login":-1}
         * loan_info : {"progress":"100.00","vouch_company_id":"0","tender_count":"1","left_amount":"0.00","use":10102,"tender_amount_max":100000,"validate":5,"award_status":-1,"amount":"10000.00","id":"275","additional_amount_max":0,"status_name":"已还完","overdue_time":"2017-09-16 10:26:42","name":"房地产抵押贷款20170911001","additional_status":-1,"is_promise":"-1","repay_type":"3","is_auto":1,"serialno":"201709110001","apr":"8.00","contents":"<p>12123<\/p>","status":"7","category_id":"10","period_name":"1个月","credited_amount":"10000.00","period":"1","member_id":"12321","hits":7,"category_name":"抵押标","is_company":-1,"agreementTitle":"抵押标协议","tender_amount_min":10,"password_status":false,"category_type":"1","member_name":"15222222222","additional_apr":0,"wait_amount":"0.00","agreementNid":"pawn"}
         * iscompany : -1
         * tender_list : [{"amount":10000,"id":3896,"add_time":1505097727,"member_name":"17**"}]
         * repay_type : {"id":3,"contents":"到期还本还息","remark":"","name":"到期本息"}
         * attaList : []
         */

        private MemberBean member;
        private LoanInfoBean loan_info;
        private String iscompany;
        private RepayTypeBean repay_type;
        private List<TenderListBean> tender_list;
        private List<JsonElement> attaList;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public LoanInfoBean getLoan_info() {
            return loan_info;
        }

        public void setLoan_info(LoanInfoBean loan_info) {
            this.loan_info = loan_info;
        }

        public String getIscompany() {
            return iscompany;
        }

        public void setIscompany(String iscompany) {
            this.iscompany = iscompany;
        }

        public RepayTypeBean getRepay_type() {
            return repay_type;
        }

        public void setRepay_type(RepayTypeBean repay_type) {
            this.repay_type = repay_type;
        }

        public List<TenderListBean> getTender_list() {
            return tender_list;
        }

        public void setTender_list(List<TenderListBean> tender_list) {
            this.tender_list = tender_list;
        }

        public List<JsonElement> getAttaList() {
            return attaList;
        }

        public void setAttaList(List<JsonElement> attaList) {
            this.attaList = attaList;
        }

        public static class MemberBean {
            /**
             * self_loan : -1
             * is_login : -1
             */

            private String self_loan;
            private String is_login;

            public String getSelf_loan() {
                return self_loan;
            }

            public void setSelf_loan(String self_loan) {
                this.self_loan = self_loan;
            }

            public String getIs_login() {
                return is_login;
            }

            public void setIs_login(String is_login) {
                this.is_login = is_login;
            }
        }

        public static class LoanInfoBean {
            /**
             * progress : 100.00
             * vouch_company_id : 0
             * tender_count : 1
             * left_amount : 0.00
             * use : 10102
             * tender_amount_max : 100000
             * validate : 5
             * award_status : -1
             * amount : 10000.00
             * id : 275
             * additional_amount_max : 0
             * status_name : 已还完
             * overdue_time : 2017-09-16 10:26:42
             * name : 房地产抵押贷款20170911001
             * additional_status : -1
             * is_promise : -1
             * repay_type : 3
             * is_auto : 1
             * serialno : 201709110001
             * apr : 8.00
             * contents : <p>12123</p>
             * status : 7
             * category_id : 10
             * period_name : 1个月
             * credited_amount : 10000.00
             * period : 1
             * member_id : 12321
             * hits : 7
             * category_name : 抵押标
             * is_company : -1
             * agreementTitle : 抵押标协议
             * tender_amount_min : 10
             * password_status : false
             * category_type : 1
             * member_name : 15222222222
             * additional_apr : 0
             * wait_amount : 0.00
             * agreementNid : pawn
             */

            private String progress;
            private String vouch_company_id;
            private String tender_count;
            private String left_amount;
            private String use;
            private String tender_amount_max;
            private String validate;
            private String award_status;
            private String amount;
            private String id;
            private String additional_amount_max;
            private String status_name;
            private String overdue_time;
            private String name;
            private String additional_status;
            private String is_promise;
            private String repay_type;
            private String is_auto;
            private String serialno;
            private String apr;
            private String contents;
            private String status;
            private String category_id;
            private String period_name;
            private String credited_amount;
            private String period;
            private String member_id;
            private String hits;
            private String category_name;
            private String is_company;
            private String agreementTitle;
            private String tender_amount_min;
            private String password_status;
            private String category_type;
            private String member_name;
            private String additional_apr;
            private String wait_amount;
            private String agreementNid;

            public String getProgress() {
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getVouch_company_id() {
                return vouch_company_id;
            }

            public void setVouch_company_id(String vouch_company_id) {
                this.vouch_company_id = vouch_company_id;
            }

            public String getTender_count() {
                return tender_count;
            }

            public void setTender_count(String tender_count) {
                this.tender_count = tender_count;
            }

            public String getLeft_amount() {
                return left_amount;
            }

            public void setLeft_amount(String left_amount) {
                this.left_amount = left_amount;
            }

            public String getUse() {
                return use;
            }

            public void setUse(String use) {
                this.use = use;
            }

            public String getTender_amount_max() {
                return tender_amount_max;
            }

            public void setTender_amount_max(String tender_amount_max) {
                this.tender_amount_max = tender_amount_max;
            }

            public String getValidate() {
                return validate;
            }

            public void setValidate(String validate) {
                this.validate = validate;
            }

            public String getAward_status() {
                return award_status;
            }

            public void setAward_status(String award_status) {
                this.award_status = award_status;
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

            public String getAdditional_amount_max() {
                return additional_amount_max;
            }

            public void setAdditional_amount_max(String additional_amount_max) {
                this.additional_amount_max = additional_amount_max;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
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

            public String getAdditional_status() {
                return additional_status;
            }

            public void setAdditional_status(String additional_status) {
                this.additional_status = additional_status;
            }

            public String getIs_promise() {
                return is_promise;
            }

            public void setIs_promise(String is_promise) {
                this.is_promise = is_promise;
            }

            public String getRepay_type() {
                return repay_type;
            }

            public void setRepay_type(String repay_type) {
                this.repay_type = repay_type;
            }

            public String getIs_auto() {
                return is_auto;
            }

            public void setIs_auto(String is_auto) {
                this.is_auto = is_auto;
            }

            public String getSerialno() {
                return serialno;
            }

            public void setSerialno(String serialno) {
                this.serialno = serialno;
            }

            public String getApr() {
                return apr;
            }

            public void setApr(String apr) {
                this.apr = apr;
            }

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getPeriod_name() {
                return period_name;
            }

            public void setPeriod_name(String period_name) {
                this.period_name = period_name;
            }

            public String getCredited_amount() {
                return credited_amount;
            }

            public void setCredited_amount(String credited_amount) {
                this.credited_amount = credited_amount;
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

            public String getHits() {
                return hits;
            }

            public void setHits(String hits) {
                this.hits = hits;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getIs_company() {
                return is_company;
            }

            public void setIs_company(String is_company) {
                this.is_company = is_company;
            }

            public String getAgreementTitle() {
                return agreementTitle;
            }

            public void setAgreementTitle(String agreementTitle) {
                this.agreementTitle = agreementTitle;
            }

            public String getTender_amount_min() {
                return tender_amount_min;
            }

            public void setTender_amount_min(String tender_amount_min) {
                this.tender_amount_min = tender_amount_min;
            }

            public String isPassword_status() {
                return password_status;
            }

            public void setPassword_status(String password_status) {
                this.password_status = password_status;
            }

            public String getCategory_type() {
                return category_type;
            }

            public void setCategory_type(String category_type) {
                this.category_type = category_type;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
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

            public String getAgreementNid() {
                return agreementNid;
            }

            public void setAgreementNid(String agreementNid) {
                this.agreementNid = agreementNid;
            }
        }

        public static class RepayTypeBean {
            /**
             * id : 3
             * contents : 到期还本还息
             * remark : 
             * name : 到期本息
             */

            private String id;
            private String contents;
            private String remark;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TenderListBean {
            /**
             * amount : 10000
             * id : 3896
             * add_time : 1505097727
             * member_name : 17**
             */

            private String amount;
            private String id;
            private String add_time;
            private String member_name;

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
        }
    }
}
