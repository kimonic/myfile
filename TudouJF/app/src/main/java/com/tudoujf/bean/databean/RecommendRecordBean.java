package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             RecommendRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/24
 * description：
 * history：
 * *==================================================================
 */

public class RecommendRecordBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 117
     * total_pages : 6
     * items : [{"proportion":"0.125%","award":0.13,"add_time":1461746835,"spreaded_member_name":"Winnie0919","amount_type":"本金","spread_type":"投资提成"}]
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
         * proportion : 0.125%
         * award : 0.13
         * add_time : 1461746835
         * spreaded_member_name : Winnie0919
         * amount_type : 本金
         * spread_type : 投资提成
         */

        private String proportion;
        private String award;
        private String add_time;
        private String spreaded_member_name;
        private String amount_type;
        private String spread_type;
        private  int  bacFlag;

        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
        }

        public String getAward() {
            return award;
        }

        public void setAward(String award) {
            this.award = award;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSpreaded_member_name() {
            return spreaded_member_name;
        }

        public void setSpreaded_member_name(String spreaded_member_name) {
            this.spreaded_member_name = spreaded_member_name;
        }

        public String getAmount_type() {
            return amount_type;
        }

        public void setAmount_type(String amount_type) {
            this.amount_type = amount_type;
        }

        public String getSpread_type() {
            return spread_type;
        }

        public void setSpread_type(String spread_type) {
            this.spread_type = spread_type;
        }
    }
}
