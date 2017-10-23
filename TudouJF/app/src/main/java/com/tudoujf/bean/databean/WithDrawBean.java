package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             WithDrawBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/19
 * description：  提现接口返回的数据bean
 * history：
 * *==================================================================
 */

public class WithDrawBean implements BaseBean {


    /**
     * bank_info : [{"id":1367,"bank_name":"建设银行","status":1,"name":"建设银行","bank_nid":"CCB","is_quickpay":-1,"account":"6210*********3456","realname":"赵**","bank_img":"/wapassets/trust/images/bank/CCB.gif"},{"id":1368,"bank_name":"中国工商银行","status":1,"name":"中国工商银行","bank_nid":"ICBC","is_quickpay":-1,"account":"6211*********3456","realname":"赵**","bank_img":"/wapassets/trust/images/bank/ICBC.gif"},{"id":1369,"bank_name":"农业银行","name":"农业银行","is_quickpay":-1,"bank_nid":"ABC","account":"6228*********3456","realname":"赵**","member_name":"18011111111","bank_img":"/wapassets/trust/images/bank/ABC.gif","member_id":1369}]
     * is_bind : 1
     * is_paypassword : 1
     * addShow : true
     */

    private int is_bind;
    private int is_paypassword;
    private boolean addShow;
    private List<BankInfoBean> bank_info;

    public int getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(int is_bind) {
        this.is_bind = is_bind;
    }

    public int getIs_paypassword() {
        return is_paypassword;
    }

    public void setIs_paypassword(int is_paypassword) {
        this.is_paypassword = is_paypassword;
    }

    public boolean isAddShow() {
        return addShow;
    }

    public void setAddShow(boolean addShow) {
        this.addShow = addShow;
    }

    public List<BankInfoBean> getBank_info() {
        return bank_info;
    }

    public void setBank_info(List<BankInfoBean> bank_info) {
        this.bank_info = bank_info;
    }

    public static class BankInfoBean {
        /**
         * id : 1367
         * bank_name : 建设银行
         * status : 1
         * name : 建设银行
         * bank_nid : CCB
         * is_quickpay : -1
         * account : 6210*********3456
         * realname : 赵**
         * bank_img : /wapassets/trust/images/bank/CCB.gif
         * member_name : 18011111111
         * member_id : 1369
         */

        private int id;
        private String bank_name;
        private int status;
        private String name;
        private String bank_nid;
        private int is_quickpay;
        private String account;
        private String realname;
        private String bank_img;
        private String member_name;
        private int member_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBank_nid() {
            return bank_nid;
        }

        public void setBank_nid(String bank_nid) {
            this.bank_nid = bank_nid;
        }

        public int getIs_quickpay() {
            return is_quickpay;
        }

        public void setIs_quickpay(int is_quickpay) {
            this.is_quickpay = is_quickpay;
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

        public String getBank_img() {
            return bank_img;
        }

        public void setBank_img(String bank_img) {
            this.bank_img = bank_img;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }
    }
}
