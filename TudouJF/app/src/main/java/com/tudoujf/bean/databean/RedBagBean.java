package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             RedBagBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/16
 * description：  获取可用红包接口返回的bean
 * history：
 * *==================================================================
 */

public class RedBagBean implements BaseBean {

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
         * status : -1
         * getway : 1
         * serial_id : 2017-02-24-0007
         * member_id : 12269
         * amount : 10
         * id : 38391
         * end_time : 1510502400
         * time_limit : 1
         * invest_source : 0
         * name : 100元1个月
         * tender_amount_min : 100
         * add_time : 1487924514
         * member_name : 18033333333
         * is_beginer : 1
         * sign : 抽奖获得
         */

        private String status;
        private String getway;
        private String serial_id;
        private String member_id;
        private String amount;
        private String id;
        private String end_time;
        private String time_limit;
        private String invest_source;
        private String name;
        private String tender_amount_min;
        private String add_time;
        private String member_name;
        private String is_beginer;
        private String sign;
        private String together_status;
        private int background=1;

        public String getTogether_status() {
            return together_status;
        }

        public void setTogether_status(String together_status) {
            this.together_status = together_status;
        }

        public int getBackground() {
            return background;
        }

        public void setBackground(int background) {
            this.background = background;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGetway() {
            return getway;
        }

        public void setGetway(String getway) {
            this.getway = getway;
        }

        public String getSerial_id() {
            return serial_id;
        }

        public void setSerial_id(String serial_id) {
            this.serial_id = serial_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getInvest_source() {
            return invest_source;
        }

        public void setInvest_source(String invest_source) {
            this.invest_source = invest_source;
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

        public String getIs_beginer() {
            return is_beginer;
        }

        public void setIs_beginer(String is_beginer) {
            this.is_beginer = is_beginer;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
