package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             DiscoverBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/1
 * description：
 * history：
 * *==================================================================
 */

public class DiscoverBean implements BaseBean {


    /**
     * page : 1
     * epage : 20
     * total_items : 2
     * total_pages : 1
     * items : [{"id":10,"name":"te","summary":null,"sortIndex":"0","foreverStatus":"-1","category":null,"status":null,"startTime":"1512057600","endTime":"1515081600","image":"http://imgview.test.tudoujf.com/shop/activity/20171201/f23d89857148395b22c14d8fa7d8d9cc.jpg","jumpurl":"http://www.baidu.com","contents":null,"addTime":null,"ranking":null},{"id":9,"name":"tgggggg","summary":null,"sortIndex":"1","foreverStatus":"1","category":null,"status":null,"startTime":null,"endTime":null,"image":"http://imgview.test.tudoujf.com/shop/activity/20171201/1ca7bce3f3aa68ff766b7be472d995b6.jpg","jumpurl":"http://mp.weixin.qq.com/s/G7X2Rud9MHiUajLCvxNuYA","contents":"<p style=\"text-align: center;\"><strong>hhhhhhh<\/strong><\/p><p style=\"text-align: left;\"><em>额喂喂喂个人体会有人提问而其他前额发个给我更多广告的规定发放过好几天雨同一天通融通融<\/em><\/p>","addTime":null,"ranking":null}]
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
         * id : 10
         * name : te
         * summary : null
         * sortIndex : 0
         * foreverStatus : -1
         * category : null
         * status : null
         * startTime : 1512057600
         * endTime : 1515081600
         * image : http://imgview.test.tudoujf.com/shop/activity/20171201/f23d89857148395b22c14d8fa7d8d9cc.jpg
         * jumpurl : http://www.baidu.com
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
