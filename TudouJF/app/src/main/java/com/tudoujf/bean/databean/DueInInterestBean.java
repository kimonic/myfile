package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             DueInInterestBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/29
 * description：
 * history：
 * *==================================================================
 */

public class DueInInterestBean implements BaseBean {


    /**
     * amount : 330
     * pageObj : {"page":1,"epage":20,"total_items":6,"total_pages":1,"items":[{"loan_name":"房产抵押借款20160728001","add_time":1469783100,"principal":2000,"interest":15}],"params":null}
     */

    private String amount;
    private PageObjBean pageObj;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PageObjBean getPageObj() {
        return pageObj;
    }

    public void setPageObj(PageObjBean pageObj) {
        this.pageObj = pageObj;
    }

    public static class PageObjBean {
        /**
         * page : 1
         * epage : 20
         * total_items : 6
         * total_pages : 1
         * items : [{"loan_name":"房产抵押借款20160728001","add_time":1469783100,"principal":2000,"interest":15}]
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
             * loan_name : 房产抵押借款20160728001
             * add_time : 1469783100
             * principal : 2000
             * interest : 15
             */

            private String loan_name;
            private String add_time;
            private String principal;
            private String interest;

            public String getLoan_name() {
                return loan_name;
            }

            public void setLoan_name(String loan_name) {
                this.loan_name = loan_name;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }
        }
    }
}
