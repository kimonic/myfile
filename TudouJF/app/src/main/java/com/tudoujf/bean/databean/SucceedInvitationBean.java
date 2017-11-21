package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             SucceedInvitationBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/21
 * description：
 * history：
 * *==================================================================
 */

public class SucceedInvitationBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 5
     * total_pages : 1
     * items : [{"id":12261,"spreaded_member_name":"18077777777","status":1,"tender_success_amount":0,"spreaded_member_id":12266,"award_amount_yes":0,"repay_amount_yes":0,"add_time":1475973618,"member_name":"18077777777","type":2,"member_id":12267},{"id":12273,"spreaded_member_name":"18111111111","status":1,"tender_success_amount":0,"spreaded_member_id":12278,"award_amount_yes":0,"repay_amount_yes":0,"add_time":1477296444,"member_name":"18111111111","type":2,"member_id":12267},{"id":12317,"spreaded_member_name":"18545454545","status":1,"tender_success_amount":0,"spreaded_member_id":12319,"award_amount_yes":0,"repay_amount_yes":0,"add_time":1501750352,"member_name":"18545454545","type":2,"member_id":12267},{"id":12318,"spreaded_member_name":"18544445555","status":1,"tender_success_amount":0,"spreaded_member_id":12320,"award_amount_yes":0,"repay_amount_yes":0,"add_time":1501750975,"member_name":"18544445555","type":2,"member_id":12267},{"id":12376,"spreaded_member_name":"13698698888","status":1,"tender_success_amount":0,"spreaded_member_id":12382,"award_amount_yes":0,"repay_amount_yes":0,"add_time":1508486065,"member_name":"13698698888","type":2,"member_id":12267}]
     * params : null
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private JsonElement params;
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

    public JsonElement getParams() {
        return params;
    }

    public void setParams(JsonElement params) {
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
         * id : 12261
         * spreaded_member_name : 18077777777
         * status : 1
         * tender_success_amount : 0
         * spreaded_member_id : 12266
         * award_amount_yes : 0
         * repay_amount_yes : 0
         * add_time : 1475973618
         * member_name : 18077777777
         * type : 2
         * member_id : 12267
         */

        private String id;
        private String spreaded_member_name;
        private String status;
        private String tender_success_amount;
        private String spreaded_member_id;
        private String award_amount_yes;
        private String repay_amount_yes;
        private String add_time;
        private String member_name;
        private String type;
        private String member_id;
        private int  bacFlag=1;

        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSpreaded_member_name() {
            return spreaded_member_name;
        }

        public void setSpreaded_member_name(String spreaded_member_name) {
            this.spreaded_member_name = spreaded_member_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTender_success_amount() {
            return tender_success_amount;
        }

        public void setTender_success_amount(String tender_success_amount) {
            this.tender_success_amount = tender_success_amount;
        }

        public String getSpreaded_member_id() {
            return spreaded_member_id;
        }

        public void setSpreaded_member_id(String spreaded_member_id) {
            this.spreaded_member_id = spreaded_member_id;
        }

        public String getAward_amount_yes() {
            return award_amount_yes;
        }

        public void setAward_amount_yes(String award_amount_yes) {
            this.award_amount_yes = award_amount_yes;
        }

        public String getRepay_amount_yes() {
            return repay_amount_yes;
        }

        public void setRepay_amount_yes(String repay_amount_yes) {
            this.repay_amount_yes = repay_amount_yes;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }
}
