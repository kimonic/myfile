package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             MyPopularizeBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/16
 * description：
 * history：
 * *==================================================================
 */

public class MyPopularizeBean implements BaseBean {

    /**
     * total : {"todayAccounted":"Y","accounted":0,"accounting":0,"unAaccount":0}
     * limit : 100
     * countInfo : {"income":0,"person_count":4,"tender_income":0,"repay_income":0}
     * person_count : 4
     * income : 0
     * share_url : http://m.test.tudoujf.com/wap/common/member/reg?invite=MTgwMTExMTExMTE=
     * tender_income : 0
     * repay_income : 0
     */

    private TotalBean total;
    private String limit;
    private CountInfoBean countInfo;
    private String person_count;
    private String income;
    private String share_url;
    private String tender_income;
    private String repay_income;

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public CountInfoBean getCountInfo() {
        return countInfo;
    }

    public void setCountInfo(CountInfoBean countInfo) {
        this.countInfo = countInfo;
    }

    public String getPerson_count() {
        return person_count;
    }

    public void setPerson_count(String person_count) {
        this.person_count = person_count;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTender_income() {
        return tender_income;
    }

    public void setTender_income(String tender_income) {
        this.tender_income = tender_income;
    }

    public String getRepay_income() {
        return repay_income;
    }

    public void setRepay_income(String repay_income) {
        this.repay_income = repay_income;
    }

    public static class TotalBean {
        /**
         * todayAccounted : Y
         * accounted : 0
         * accounting : 0
         * unAaccount : 0
         */

        private String todayAccounted;
        private String accounted;
        private String accounting;
        private String unAaccount;

        public String getTodayAccounted() {
            return todayAccounted;
        }

        public void setTodayAccounted(String todayAccounted) {
            this.todayAccounted = todayAccounted;
        }

        public String getAccounted() {
            return accounted;
        }

        public void setAccounted(String accounted) {
            this.accounted = accounted;
        }

        public String getAccounting() {
            return accounting;
        }

        public void setAccounting(String accounting) {
            this.accounting = accounting;
        }

        public String getUnAaccount() {
            return unAaccount;
        }

        public void setUnAaccount(String unAaccount) {
            this.unAaccount = unAaccount;
        }
    }

    public static class CountInfoBean {
        /**
         * income : 0
         * person_count : 4
         * tender_income : 0
         * repay_income : 0
         */

        private String income;
        private String person_count;
        private String tender_income;
        private String repay_income;

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getPerson_count() {
            return person_count;
        }

        public void setPerson_count(String person_count) {
            this.person_count = person_count;
        }

        public String getTender_income() {
            return tender_income;
        }

        public void setTender_income(String tender_income) {
            this.tender_income = tender_income;
        }

        public String getRepay_income() {
            return repay_income;
        }

        public void setRepay_income(String repay_income) {
            this.repay_income = repay_income;
        }
    }
}
