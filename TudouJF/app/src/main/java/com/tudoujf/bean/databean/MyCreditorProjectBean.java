package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             MyCreditorProjectBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/15
 * description：  我的项目--我的债权接口返回的数据bean
 * history：
 * *==================================================================
 */

public class MyCreditorProjectBean implements BaseBean {

    /**
     * transfer_buy_total : 62780.6
     * transfer_buy_interest_total : 2032.4
     * transfer_list : {"page":1,"epage":15,"total_items":15,"total_pages":1,"items":[{"wait_interest":1350,"repay_status":-1,"transfer_buy_interest_total":-613.05,"transfer_amount":30039.45,"transfer_status":2,"buy_repay_status":"回收中","wait_principal_interest":31350,"transfer_money":30652.5,"period":1,"buy_repay":2,"transfer_interest _total":null,"id":3673,"wait_period":1,"transfer_status_name":"回收中","transfer_buy_total":30039.45,"transfer_total":null,"transfer_id":5,"wait_principal":30000}],"params":null}
     * transfer_total : 0
     * transfer_interest_total : 0
     */

    private String transfer_buy_total;
    private String transfer_buy_interest_total;
    private TransferListBean transfer_list;
    private String transfer_total;
    private String transfer_interest_total;

    public String getTransfer_buy_total() {
        return transfer_buy_total;
    }

    public void setTransfer_buy_total(String transfer_buy_total) {
        this.transfer_buy_total = transfer_buy_total;
    }

    public String getTransfer_buy_interest_total() {
        return transfer_buy_interest_total;
    }

    public void setTransfer_buy_interest_total(String transfer_buy_interest_total) {
        this.transfer_buy_interest_total = transfer_buy_interest_total;
    }

    public TransferListBean getTransfer_list() {
        return transfer_list;
    }

    public void setTransfer_list(TransferListBean transfer_list) {
        this.transfer_list = transfer_list;
    }

    public String getTransfer_total() {
        return transfer_total;
    }

    public void setTransfer_total(String transfer_total) {
        this.transfer_total = transfer_total;
    }

    public String getTransfer_interest_total() {
        return transfer_interest_total;
    }

    public void setTransfer_interest_total(String transfer_interest_total) {
        this.transfer_interest_total = transfer_interest_total;
    }

    public static class TransferListBean {
        /**
         * page : 1
         * epage : 15
         * total_items : 15
         * total_pages : 1
         * items : [{"wait_interest":1350,"repay_status":-1,"transfer_buy_interest_total":-613.05,"transfer_amount":30039.45,"transfer_status":2,"buy_repay_status":"回收中","wait_principal_interest":31350,"transfer_money":30652.5,"period":1,"buy_repay":2,"transfer_interest _total":null,"id":3673,"wait_period":1,"transfer_status_name":"回收中","transfer_buy_total":30039.45,"transfer_total":null,"transfer_id":5,"wait_principal":30000}]
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
             * wait_interest : 1350`
             * repay_status : -1
             * transfer_buy_interest_total : -613.05
             * transfer_amount : 30039.45
             * transfer_status : 2
             * buy_repay_status : 回收中
             * wait_principal_interest : 31350
             * transfer_money : 30652.5
             * period : 1
             * buy_repay : 2
             * transfer_interest _total : null
             * id : 3673
             * wait_period : 1
             * transfer_status_name : 回收中
             * transfer_buy_total : 30039.45
             * transfer_total : null
             * transfer_id : 5
             * wait_principal : 30000
             */

            private String wait_interest;
            private String repay_status;
            private String transfer_buy_interest_total;
            private String transfer_amount;
            private String transfer_status;
            private String buy_repay_status;
            private String wait_principal_interest;
            private String transfer_money;
            private String period;
            private String buy_repay;
            @SerializedName("transfer_interest _total")
            private JsonElement _$Transfer_interest_total173; // FIXME check this code
            private String id;
            private String wait_period;
            private String transfer_status_name;
            private String transfer_buy_total;
            private JsonElement transfer_total;
            private String transfer_id;
            private String wait_principal;

            public String getWait_interest() {
                return wait_interest;
            }

            public void setWait_interest(String wait_interest) {
                this.wait_interest = wait_interest;
            }

            public String getRepay_status() {
                return repay_status;
            }

            public void setRepay_status(String repay_status) {
                this.repay_status = repay_status;
            }

            public String getTransfer_buy_interest_total() {
                return transfer_buy_interest_total;
            }

            public void setTransfer_buy_interest_total(String transfer_buy_interest_total) {
                this.transfer_buy_interest_total = transfer_buy_interest_total;
            }

            public String getTransfer_amount() {
                return transfer_amount;
            }

            public void setTransfer_amount(String transfer_amount) {
                this.transfer_amount = transfer_amount;
            }

            public String getTransfer_status() {
                return transfer_status;
            }

            public void setTransfer_status(String transfer_status) {
                this.transfer_status = transfer_status;
            }

            public String getBuy_repay_status() {
                return buy_repay_status;
            }

            public void setBuy_repay_status(String buy_repay_status) {
                this.buy_repay_status = buy_repay_status;
            }

            public String getWait_principal_interest() {
                return wait_principal_interest;
            }

            public void setWait_principal_interest(String wait_principal_interest) {
                this.wait_principal_interest = wait_principal_interest;
            }

            public String getTransfer_money() {
                return transfer_money;
            }

            public void setTransfer_money(String transfer_money) {
                this.transfer_money = transfer_money;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getBuy_repay() {
                return buy_repay;
            }

            public void setBuy_repay(String buy_repay) {
                this.buy_repay = buy_repay;
            }

            public JsonElement get_$Transfer_interest_total173() {
                return _$Transfer_interest_total173;
            }

            public void set_$Transfer_interest_total173(JsonElement _$Transfer_interest_total173) {
                this._$Transfer_interest_total173 = _$Transfer_interest_total173;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWait_period() {
                return wait_period;
            }

            public void setWait_period(String wait_period) {
                this.wait_period = wait_period;
            }

            public String getTransfer_status_name() {
                return transfer_status_name;
            }

            public void setTransfer_status_name(String transfer_status_name) {
                this.transfer_status_name = transfer_status_name;
            }

            public String getTransfer_buy_total() {
                return transfer_buy_total;
            }

            public void setTransfer_buy_total(String transfer_buy_total) {
                this.transfer_buy_total = transfer_buy_total;
            }

            public JsonElement getTransfer_total() {
                return transfer_total;
            }

            public void setTransfer_total(JsonElement transfer_total) {
                this.transfer_total = transfer_total;
            }

            public String getTransfer_id() {
                return transfer_id;
            }

            public void setTransfer_id(String transfer_id) {
                this.transfer_id = transfer_id;
            }

            public String getWait_principal() {
                return wait_principal;
            }

            public void setWait_principal(String wait_principal) {
                this.wait_principal = wait_principal;
            }
        }
    }
}
