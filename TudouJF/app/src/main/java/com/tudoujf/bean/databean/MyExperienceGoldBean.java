package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            MyExperienceGoldBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：   我的体验金返回的bean
 * history：
 * * ====================================================================
 */

public class MyExperienceGoldBean implements BaseBean {

    /**
     * amount_all : 9807308.96
     * Experience_amount : 0
     * tender_list : {"page":1,"epage":20,"total_items":1,"total_pages":1,"items":[{"id":3720,"amount":28888,"status":"已结清","interest_earning":0,"interest_earned":20.06,"add_time":1475974910,"member_name":"18011111111"}],"params":null}
     * amount_balance : 9781108.96
     */

    private String amount_all;
    private String Experience_amount;
    private TenderListBean tender_list;
    private String amount_balance;

    public String getAmount_all() {
        return amount_all;
    }

    public void setAmount_all(String amount_all) {
        this.amount_all = amount_all;
    }

    public String getExperience_amount() {
        return Experience_amount;
    }

    public void setExperience_amount(String Experience_amount) {
        this.Experience_amount = Experience_amount;
    }

    public TenderListBean getTender_list() {
        return tender_list;
    }

    public void setTender_list(TenderListBean tender_list) {
        this.tender_list = tender_list;
    }

    public String getAmount_balance() {
        return amount_balance;
    }

    public void setAmount_balance(String amount_balance) {
        this.amount_balance = amount_balance;
    }

    public static class TenderListBean {
        /**
         * page : 1
         * epage : 20
         * total_items : 1
         * total_pages : 1
         * items : [{"id":3720,"amount":28888,"status":"已结清","interest_earning":0,"interest_earned":20.06,"add_time":1475974910,"member_name":"18011111111"}]
         * params : null
         */

        private String page;
        private String epage;
        private String total_items;
        private String total_pages;
        private JsonElement params;
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
             * id : 3720
             * amount : 28888
             * status : 已结清
             * interest_earning : 0
             * interest_earned : 20.06
             * add_time : 1475974910
             * member_name : 18011111111
             */

            private String id;
            private String amount;
            private String status;
            private String interest_earning;
            private String interest_earned;
            private String add_time;
            private String member_name;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getInterest_earning() {
                return interest_earning;
            }

            public void setInterest_earning(String interest_earning) {
                this.interest_earning = interest_earning;
            }

            public String getInterest_earned() {
                return interest_earned;
            }

            public void setInterest_earned(String interest_earned) {
                this.interest_earned = interest_earned;
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
