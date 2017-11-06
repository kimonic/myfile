package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             BankCardManageBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/6
 * description：  绑定的银行卡列表接口返回的数据bean
 * history：
 * *==================================================================
 */

public class BankCardManageBean implements BaseBean {

    /**
     * bank_info : [{"id":1370,"bank_name":"建设银行","status":1,"name":"建设银行","bank_nid":"CCB","is_quickpay":-1,"account":"6210*********3456","realname":"赵**","bank_img":"http://m.test.tudoujf.com/wapassets/trust/egimages/bank/CCB.jpg"},{"id":1371,"bank_name":"中国工商银行","status":1,"name":"中国工商银行","bank_nid":"ICBC","is_quickpay":-1,"account":"6211*********3456","realname":"赵**","bank_img":"http://m.test.tudoujf.com/wapassets/trust/egimages/bank/ICBC.jpg"},{"id":1372,"bank_name":"农业银行","status":1,"name":"农业银行","bank_nid":"ABC","is_quickpay":-1,"account":"6228*********3456","realname":"赵**","bank_img":"http://m.test.tudoujf.com/wapassets/trust/egimages/bank/ABC.jpg"}]
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
         * id : 1370
         * bank_name : 建设银行
         * status : 1
         * name : 建设银行
         * bank_nid : CCB
         * is_quickpay : -1
         * account : 6210*********3456
         * realname : 赵**
         * bank_img : http://m.test.tudoujf.com/wapassets/trust/egimages/bank/CCB.jpg
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
    }
}
