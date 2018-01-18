package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             MyAccountBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/6
 * description：  我的账户接口返回的数据bean
 * history：
 * *==================================================================
 */

public class MyAccountBean implements BaseBean {

    /**
     * back_card : http://imgview.test.tudoujf.comnull
     * phone : 18011111111
     * bank_name : 建设银行
     * account2Wap : 6210*********3456
     * business_license : 
     * card_id : 510************340
     * password : 4fbba2941f32bf7eb05d5910f06bc95a
     * group_status : 
     * is_trust : 1
     * realname_status : 1
     * pay_pwd : 
     * is_email : {"status_code":"-1","status_name":"未认证"}
     * is_phone : {"status_code":"-1","status_name":"已绑定"}
     * is_realname : {"status_code":"1","status_name":"已认证"}
     * enterprise : 1
     * bankName : 建设银行
     * register : 1
     * positive_card : http://imgview.test.tudoujf.comnull
     * pwd : 1
     * trust_account : {"status_code":"","status_name":"6000060005589777"}
     * realname_remark : Trust register notify
     * email : 
     * lastlogin_time : 1509715904
     * paypassword : 
     * is_corp : -1
     * account : 621081123456123456
     * realname : *小白
     * member_name : 18011111111
     */

    private String back_card;
    private String phone;
    private String bank_name;
    private String account2Wap;
    private String business_license;
    private String card_id;
    private String password;
    private String group_status;
    private String is_trust;
    private String realname_status;
    private String pay_pwd;
    private IsEmailBean is_email;
    private IsPhoneBean is_phone;
    private IsRealnameBean is_realname;
    private int enterprise;
    private String bankName;
    private int register;
    private String positive_card;
    private String pwd;
    private TrustAccountBean trust_account;
    private String realname_remark;
    private String email;
    private String lastlogin_time;
    private String paypassword;
    private String is_corp;
    private String account;
    private String realname;
    private String member_name;
    private int isVip;
    private String vipEndTime;
    private String riskAssessmentType;

    public String getRiskAssessmentType() {
        return riskAssessmentType;
    }

    public void setRiskAssessmentType(String riskAssessmentType) {
        this.riskAssessmentType = riskAssessmentType;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public String getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(String vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public String getBack_card() {
        return back_card;
    }

    public void setBack_card(String back_card) {
        this.back_card = back_card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount2Wap() {
        return account2Wap;
    }

    public void setAccount2Wap(String account2Wap) {
        this.account2Wap = account2Wap;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }

    public String getIs_trust() {
        return is_trust;
    }

    public void setIs_trust(String is_trust) {
        this.is_trust = is_trust;
    }

    public String getRealname_status() {
        return realname_status;
    }

    public void setRealname_status(String realname_status) {
        this.realname_status = realname_status;
    }

    public String getPay_pwd() {
        return pay_pwd;
    }

    public void setPay_pwd(String pay_pwd) {
        this.pay_pwd = pay_pwd;
    }

    public IsEmailBean getIs_email() {
        return is_email;
    }

    public void setIs_email(IsEmailBean is_email) {
        this.is_email = is_email;
    }

    public IsPhoneBean getIs_phone() {
        return is_phone;
    }

    public void setIs_phone(IsPhoneBean is_phone) {
        this.is_phone = is_phone;
    }

    public IsRealnameBean getIs_realname() {
        return is_realname;
    }

    public void setIs_realname(IsRealnameBean is_realname) {
        this.is_realname = is_realname;
    }

    public int getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(int enterprise) {
        this.enterprise = enterprise;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getPositive_card() {
        return positive_card;
    }

    public void setPositive_card(String positive_card) {
        this.positive_card = positive_card;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public TrustAccountBean getTrust_account() {
        return trust_account;
    }

    public void setTrust_account(TrustAccountBean trust_account) {
        this.trust_account = trust_account;
    }

    public String getRealname_remark() {
        return realname_remark;
    }

    public void setRealname_remark(String realname_remark) {
        this.realname_remark = realname_remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastlogin_time() {
        return lastlogin_time;
    }

    public void setLastlogin_time(String lastlogin_time) {
        this.lastlogin_time = lastlogin_time;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getIs_corp() {
        return is_corp;
    }

    public void setIs_corp(String is_corp) {
        this.is_corp = is_corp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public static class IsEmailBean {
        /**
         * status_code : -1
         * status_name : 未认证
         */

        private String status_code;
        private String status_name;

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }

    public static class IsPhoneBean {
        /**
         * status_code : -1
         * status_name : 已绑定
         */

        private String status_code;
        private String status_name;

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }

    public static class IsRealnameBean {
        /**
         * status_code : 1
         * status_name : 已认证
         */

        private String status_code;
        private String status_name;

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }

    public static class TrustAccountBean {
        /**
         * status_code : 
         * status_name : 6000060005589777
         */

        private String status_code;
        private String status_name;

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
