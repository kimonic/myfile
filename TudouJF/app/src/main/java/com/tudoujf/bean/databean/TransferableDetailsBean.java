package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

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
    private String service_charge;


    /**
     * amount : 30039.45
     * repay_status : -1
     * amount_money : 30652.5
     * income : 1310.55
     * transfer_id : 5
     * loan_name : 测试借款
     * expire_time : 1490492841
     * total_period : 1
     * period : 1
     * recover : {"page":1,"epage":15,"total_items":1,"total_pages":1,"items":[{"advance_interest":0,"interest_yes":0,"tender_id":3673,"recover_time":"2017-03-26","loan_member_name":"ljq001","late_interest":0,"amount":31350,"id":3922,"web_provide_amount":0,"principal":30000,"status_name":"未回款","ind":"d328529d962b0a067641912a0b9323f4","interest":1350,"principal_yes":0,"prepayment_status":-1,"period_no":1,"repay_period_id":167,"transfer_member_name":"18011111111","tender_member_name":"tdjf001","status":-1,"transfer_member_id":12267,"overdue_income_fee":0,"amount_yes":0,"loan_name":"测试借款","period":1,"recover_fee":0,"tender_member_id":12237,"loan_member_id":12231,"add_time":1474854441,"repay_type_id":3,"loan_id":154}],"params":null}
     */

    private String repay_status;
    private String income;
    private String expire_time;
    private RecoverBean recover;

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public String getRepay_status() {
        return repay_status;
    }

    public void setRepay_status(String repay_status) {
        this.repay_status = repay_status;
    }


    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }


    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }


    public RecoverBean getRecover() {
        return recover;
    }

    public void setRecover(RecoverBean recover) {
        this.recover = recover;
    }


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

    public static class RecoverBean {
        /**
         * page : 1
         * epage : 15
         * total_items : 1
         * total_pages : 1
         * items : [{"advance_interest":0,"interest_yes":0,"tender_id":3673,"recover_time":"2017-03-26","loan_member_name":"ljq001","late_interest":0,"amount":31350,"id":3922,"web_provide_amount":0,"principal":30000,"status_name":"未回款","ind":"d328529d962b0a067641912a0b9323f4","interest":1350,"principal_yes":0,"prepayment_status":-1,"period_no":1,"repay_period_id":167,"transfer_member_name":"18011111111","tender_member_name":"tdjf001","status":-1,"transfer_member_id":12267,"overdue_income_fee":0,"amount_yes":0,"loan_name":"测试借款","period":1,"recover_fee":0,"tender_member_id":12237,"loan_member_id":12231,"add_time":1474854441,"repay_type_id":3,"loan_id":154}]
         * params : null
         */

        private String page;
        private String epage;
        private String total_items;
        private String total_pages;
        private Object params;
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

        public Object getParams() {
            return params;
        }

        public void setParams(Object params) {
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
             * advance_interest : 0
             * interest_yes : 0
             * tender_id : 3673
             * recover_time : 2017-03-26
             * loan_member_name : ljq001
             * late_interest : 0
             * amount : 31350
             * id : 3922
             * web_provide_amount : 0
             * principal : 30000
             * status_name : 未回款
             * ind : d328529d962b0a067641912a0b9323f4
             * interest : 1350
             * principal_yes : 0
             * prepayment_status : -1
             * period_no : 1
             * repay_period_id : 167
             * transfer_member_name : 18011111111
             * tender_member_name : tdjf001
             * status : -1
             * transfer_member_id : 12267
             * overdue_income_fee : 0
             * amount_yes : 0
             * loan_name : 测试借款
             * period : 1
             * recover_fee : 0
             * tender_member_id : 12237
             * loan_member_id : 12231
             * add_time : 1474854441
             * repay_type_id : 3
             * loan_id : 154
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
            private String period_no;
            private String repay_period_id;
            private String transfer_member_name;
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

            public String getPeriod_no() {
                return period_no;
            }

            public void setPeriod_no(String period_no) {
                this.period_no = period_no;
            }

            public String getRepay_period_id() {
                return repay_period_id;
            }

            public void setRepay_period_id(String repay_period_id) {
                this.repay_period_id = repay_period_id;
            }

            public String getTransfer_member_name() {
                return transfer_member_name;
            }

            public void setTransfer_member_name(String transfer_member_name) {
                this.transfer_member_name = transfer_member_name;
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

}
