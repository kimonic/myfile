package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             HotExchangeBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：
 * history：
 * *==================================================================
 */

public class HotExchangeBean implements BaseBean {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 39
         * bewrite : 中国移动充值卡20元
         * num : CZ01
         * stock : 6
         * name : 中国移动充值卡20元
         * images : https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/0ba07b10c937c12048ccb1458bd38a32.jpg
         * credit : 109
         * goods_count : 71
         */

        private String id;
        private String bewrite;
        private String num;
        private String stock;
        private String name;
        private String images;
        private String credit;
        private String goods_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBewrite() {
            return bewrite;
        }

        public void setBewrite(String bewrite) {
            this.bewrite = bewrite;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }
    }
}
