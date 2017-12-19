package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             IntegralRankingListBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：
 * history：
 * *==================================================================
 */

public class IntegralRankingListBean implements BaseBean {


    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * credit_point : 30855
         * name : 1801***
         */

        private String credit_point;
        private String name;
        private String position;
        private int resId;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(String credit_point) {
            this.credit_point = credit_point;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
