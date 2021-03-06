package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             SpecialOfferBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/18
 * description：
 * history：
 * *==================================================================
 */

public class SpecialOfferBean implements BaseBean {

    /**
     * page : 1
     * epage : 10
     * total_items : 3
     * total_pages : 1
     * items : [{"id":15,"name":"注册有礼","summary":null,"sortIndex":"1","foreverStatus":"1","category":null,"status":null,"startTime":null,"endTime":null,"image":"http://imgview.test.tudoujf.com/shop/activity/20171215/276ac86ff67543825244eeafab548e5f.jpg","jumpurl":"http://mob.tudoujf.com/wap/common/juneactivity","contents":null,"addTime":null,"ranking":null},{"id":14,"name":"圣诞狂欢","summary":null,"sortIndex":"2","foreverStatus":"-1","category":null,"status":null,"startTime":"1512057600","endTime":"1514649600","image":"http://imgview.test.tudoujf.com/shop/activity/20171215/1232c1c0aade69c02bf7f14914accccc.jpg","jumpurl":"http://mob.tudoujf.com/wap/common/decActive","contents":null,"addTime":null,"ranking":null},{"id":16,"name":"双11投资豪礼","summary":null,"sortIndex":"3","foreverStatus":"-1","category":null,"status":null,"startTime":"1509465600","endTime":"1511971200","image":"http://imgview.test.tudoujf.com/shop/activity/20171215/400133cf7f5bc8961d35a7a51c408c13.jpg","jumpurl":"http://mob.tudoujf.com/wap/common/prizedraw","contents":null,"addTime":null,"ranking":null}]
     * params : null
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private String params;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
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
         * id : 15
         * name : 注册有礼
         * summary : null
         * sortIndex : 1
         * foreverStatus : 1
         * category : null
         * status : null
         * startTime : null
         * endTime : null
         * image : http://imgview.test.tudoujf.com/shop/activity/20171215/276ac86ff67543825244eeafab548e5f.jpg
         * jumpurl : http://mob.tudoujf.com/wap/common/juneactivity
         * contents : null
         * addTime : null
         * ranking : null
         */

        private String id;
        private String name;
        private String summary;
        private String sortIndex;
        private String foreverStatus;
        private String category;
        private String status;
        private String startTime;
        private String endTime;
        private String image;
        private String jumpurl;
        private String contents;
        private String addTime;
        private String ranking;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSortIndex() {
            return sortIndex;
        }

        public void setSortIndex(String sortIndex) {
            this.sortIndex = sortIndex;
        }

        public String getForeverStatus() {
            return foreverStatus;
        }

        public void setForeverStatus(String foreverStatus) {
            this.foreverStatus = foreverStatus;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getJumpurl() {
            return jumpurl;
        }

        public void setJumpurl(String jumpurl) {
            this.jumpurl = jumpurl;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }
    }
}
