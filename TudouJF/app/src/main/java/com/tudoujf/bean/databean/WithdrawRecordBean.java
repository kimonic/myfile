package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             WithdrawRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/19
 * description：  提现记录接口返回的数据bean
 * history：
 * *==================================================================
 */

public class WithdrawRecordBean implements BaseBean {


    /**
     * page : 1
     * epage : 10
     * total_items : 2
     * total_pages : 1
     * items : [{"verify_admin_id":"0","bank_name":"农业银行","status":"1","bank_nid":"ABC","amount_income":"10198.70","verify_time":"1493015482","city":"0","member_id":"12267","amount":"10230.30","id":"757","fee":"1.00","add_ip":"2130706433","service_fee":"30.60","ind":"17042414311234487067","status_name":"提现成功","platform_fee":"0.00","province":"0","account":"62282*********56","add_time":"1493015482","verify_remark":"自动审核","realname":"赵小白","member_name":"18011111111"},{"verify_admin_id":"0","bank_name":"农业银行","status":"1","bank_nid":"ABC","amount_income":"100.00","verify_time":"1493015347","city":"0","member_id":"12267","amount":"101.30","id":"756","fee":"1.00","add_ip":"2130706433","service_fee":"0.30","ind":"17042414285712210867","status_name":"提现成功","platform_fee":"0.00","province":"0","account":"62282*********56","add_time":"1493015347","verify_remark":"自动审核","realname":"赵小白","member_name":"18011111111"}]
     * params : 10331.6
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private String params;
    private List<ItemsBean> items;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getEpage() {
        return epage;
    }

    public void setEpage(int epage) {
        this.epage = epage;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
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
         * verify_admin_id : 0
         * bank_name : 农业银行
         * status : 1
         * bank_nid : ABC
         * amount_income : 10198.70
         * verify_time : 1493015482
         * city : 0
         * member_id : 12267
         * amount : 10230.30
         * id : 757
         * fee : 1.00
         * add_ip : 2130706433
         * service_fee : 30.60
         * ind : 17042414311234487067
         * status_name : 提现成功
         * platform_fee : 0.00
         * province : 0
         * account : 62282*********56
         * add_time : 1493015482
         * verify_remark : 自动审核
         * realname : 赵小白
         * member_name : 18011111111
         */

        private String verify_admin_id;
        private String bank_name;
        private String status;
        private String bank_nid;
        private String amount_income;
        private String verify_time;
        private String city;
        private String member_id;
        private String amount;
        private String id;
        private String fee;
        private String add_ip;
        private String service_fee;
        private String ind;
        private String status_name;
        private String platform_fee;
        private String province;
        private String account;
        private String add_time;
        private String verify_remark;
        private String realname;
        private String member_name;

        public String getVerify_admin_id() {
            return verify_admin_id;
        }

        public void setVerify_admin_id(String verify_admin_id) {
            this.verify_admin_id = verify_admin_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBank_nid() {
            return bank_nid;
        }

        public void setBank_nid(String bank_nid) {
            this.bank_nid = bank_nid;
        }

        public String getAmount_income() {
            return amount_income;
        }

        public void setAmount_income(String amount_income) {
            this.amount_income = amount_income;
        }

        public String getVerify_time() {
            return verify_time;
        }

        public void setVerify_time(String verify_time) {
            this.verify_time = verify_time;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getAdd_ip() {
            return add_ip;
        }

        public void setAdd_ip(String add_ip) {
            this.add_ip = add_ip;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public String getInd() {
            return ind;
        }

        public void setInd(String ind) {
            this.ind = ind;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getPlatform_fee() {
            return platform_fee;
        }

        public void setPlatform_fee(String platform_fee) {
            this.platform_fee = platform_fee;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getVerify_remark() {
            return verify_remark;
        }

        public void setVerify_remark(String verify_remark) {
            this.verify_remark = verify_remark;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }
    }
}
