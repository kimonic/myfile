package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             JiaXiQuanBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/16
 * description：   获取可用加息券接口返回的bean
 * history：
 * *==================================================================
 */

public class JiaXiQuanBean implements BaseBean {

    private List<ListBean> list;
    private int  type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * remark : pc端登陆赠送
         * status : -1
         * coupon_type : 1
         * dead_line : 0
         * coupon_days : 0
         * getway : 1
         * member_id : 12269
         * id : 12
         * coupon_multiple : 0
         * end_time : 1510502400
         * time_limit : 1
         * interest : 2
         * name : 无限制(1个月)
         * tender_amount_min : 0
         * add_time : 1485878400
         * member_name : 18033333333
         */

        private String remark;
        private String status;
        private String coupon_type;
        private String dead_line;
        private String coupon_days;
        private String getway;
        private String member_id;
        private String id;
        private String coupon_multiple;
        private String end_time;
        private String time_limit;
        private String interest;
        private String name;
        private String tender_amount_min;
        private String add_time;
        private String member_name;
        private int background=1;

        public int getBackground() {
            return background;
        }

        public void setBackground(int background) {
            this.background = background;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public String getDead_line() {
            return dead_line;
        }

        public void setDead_line(String dead_line) {
            this.dead_line = dead_line;
        }

        public String getCoupon_days() {
            return coupon_days;
        }

        public void setCoupon_days(String coupon_days) {
            this.coupon_days = coupon_days;
        }

        public String getGetway() {
            return getway;
        }

        public void setGetway(String getway) {
            this.getway = getway;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoupon_multiple() {
            return coupon_multiple;
        }

        public void setCoupon_multiple(String coupon_multiple) {
            this.coupon_multiple = coupon_multiple;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTender_amount_min() {
            return tender_amount_min;
        }

        public void setTender_amount_min(String tender_amount_min) {
            this.tender_amount_min = tender_amount_min;
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
