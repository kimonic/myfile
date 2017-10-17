package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             RechargeRecodeBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/17
 * description：   充值记录接口返回的数据bean
 * history：
 * *==================================================================
 */

public class RechargeRecodeBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 383
     * total_pages : 39
     * items : [{"amount":"0.10","ind":"764b36541eedf28970ffa4fd849dc7e9","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502324722","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"4d5ed0e3622558f3d925ace2a37d2824","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502241959","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"901a812f4cf44fa0d98935ce5eab9c18","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502241721","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"be1e3ddbfcd0017aabd260f2d7628c5f","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502151603","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"938a797d85a1e26695ae2ab71c7899eb","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502151107","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"8db2c5c99db1a056c70938bd79e74866","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502103309","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"dac07c07f1411e94866ba012a7379495","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502102845","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"f950c01ad0b7413f71eda4fc20fad149","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502094639","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"0.10","ind":"243d9c4522f11365d12b1966499bbb24","status":"1","status_name":"充值成功","payment_name":"后台充值","type_name":"线下充值","add_time":"1502088300","verify_remark":"","type":"admin","payment_nid":"admin"},{"amount":"100.00","ind":"17070711574892783767","status":"-2","status_name":"待审核","payment_name":"汇付托管","type_name":"网上充值","add_time":"1499399868","verify_remark":"","type":"online","payment_nid":"chinapnrTrust"}]
     * params : 100.9
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
         * amount : 0.10
         * ind : 764b36541eedf28970ffa4fd849dc7e9
         * status : 1
         * status_name : 充值成功
         * payment_name : 后台充值
         * type_name : 线下充值
         * add_time : 1502324722
         * verify_remark :
         * type : admin
         * payment_nid : admin
         */

        private String amount;
        private String ind;
        private String status;
        private String status_name;
        private String payment_name;
        private String type_name;
        private String add_time;
        private String verify_remark;
        private String type;
        private String payment_nid;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInd() {
            return ind;
        }

        public void setInd(String ind) {
            this.ind = ind;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPayment_nid() {
            return payment_nid;
        }

        public void setPayment_nid(String payment_nid) {
            this.payment_nid = payment_nid;
        }
    }
}
