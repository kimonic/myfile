package com.tudoujf.bean.databean;

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
     * my_account_balance : 9797506.45
     * pageObj : {"page":1,"epage":20,"total_items":1,"total_pages":1,"items":[{"recover_status":"已回款","amount":28888,"add_time":1475974910,"recover_interest_wait":0,"recover_interest_yes":20.06}],"params":null}
     * my_experience_balance : 0
     * my_account_amount : 9844766.45
     */

    private String my_account_balance;
    private PageObjBean pageObj;
    private String my_experience_balance;
    private String my_account_amount;

    public String getMy_account_balance() {
        return my_account_balance;
    }

    public void setMy_account_balance(String my_account_balance) {
        this.my_account_balance = my_account_balance;
    }

    public PageObjBean getPageObj() {
        return pageObj;
    }

    public void setPageObj(PageObjBean pageObj) {
        this.pageObj = pageObj;
    }

    public String getMy_experience_balance() {
        return my_experience_balance;
    }

    public void setMy_experience_balance(String my_experience_balance) {
        this.my_experience_balance = my_experience_balance;
    }

    public String getMy_account_amount() {
        return my_account_amount;
    }

    public void setMy_account_amount(String my_account_amount) {
        this.my_account_amount = my_account_amount;
    }

    public static class PageObjBean {
        /**
         * page : 1
         * epage : 20
         * total_items : 1
         * total_pages : 1
         * items : [{"recover_status":"已回款","amount":28888,"add_time":1475974910,"recover_interest_wait":0,"recover_interest_yes":20.06}]
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
             * recover_status : 已回款
             * amount : 28888
             * add_time : 1475974910
             * recover_interest_wait : 0
             * recover_interest_yes : 20.06
             */

            private String recover_status;
            private String amount;
            private String add_time;
            private String recover_interest_wait;
            private String recover_interest_yes;

            public String getRecover_status() {
                return recover_status;
            }

            public void setRecover_status(String recover_status) {
                this.recover_status = recover_status;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getRecover_interest_wait() {
                return recover_interest_wait;
            }

            public void setRecover_interest_wait(String recover_interest_wait) {
                this.recover_interest_wait = recover_interest_wait;
            }

            public String getRecover_interest_yes() {
                return recover_interest_yes;
            }

            public void setRecover_interest_yes(String recover_interest_yes) {
                this.recover_interest_yes = recover_interest_yes;
            }
        }
    }
}
