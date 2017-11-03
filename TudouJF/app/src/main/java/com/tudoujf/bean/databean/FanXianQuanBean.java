package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             FanXianQuanBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/3
 * description：
 * history：
 * *==================================================================
 */

public class FanXianQuanBean implements BaseBean {

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
         * proportion : 1
         * remark : 投资抽奖
         * status : -1
         * getway : 9
         * member_id : 12267
         * id : 7
         * sign : 投资抽奖
         * together_status : -1
         * end_time : 1512057599
         * time_limit : 1
         * name : 10元以上(至少3个月)
         * tender_amount_min : 10
         * add_time : 1509167177
         * member_name : 18011111111
         */

        private String proportion;
        private String remark;
        private String status;
        private String getway;
        private String member_id;
        private String id;
        private String sign;
        private String together_status;
        private String end_time;
        private String time_limit;
        private String name;
        private String tender_amount_min;
        private String add_time;
        private String member_name;
        private  int  background=1;

        public int getBackground() {
            return background;
        }

        public void setBackground(int background) {
            this.background = background;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTogether_status() {
            return together_status;
        }

        public void setTogether_status(String together_status) {
            this.together_status = together_status;
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
