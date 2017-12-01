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
     * total_items : 1
     * total_pages : 1
     * items : [{"id":9,"image":"http://imgview.test.tudoujf.com/shop/activity/20171201/1ca7bce3f3aa68ff766b7be472d995b6.jpg","forever_status":1,"contents":"<p style=\"text-align: center;\"><strong>hhhhhhh<\/strong><\/p><p style=\"text-align: left;\"><em>额喂喂喂个人体会有人提问而其他前额发个给我更多广告的规定发放过好几天雨同一天通融通融<\/em><\/p>","sort_index":1,"name":"tgggggg"}]
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
         * id : 9
         * image : http://imgview.test.tudoujf.com/shop/activity/20171201/1ca7bce3f3aa68ff766b7be472d995b6.jpg
         * forever_status : 1
         * contents : <p style="text-align: center;"><strong>hhhhhhh</strong></p><p style="text-align: left;"><em>额喂喂喂个人体会有人提问而其他前额发个给我更多广告的规定发放过好几天雨同一天通融通融</em></p>
         * sort_index : 1
         * name : tgggggg
         */

        private String id;
        private String image;
        private String forever_status;
        private String contents;
        private String sort_index;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getForever_status() {
            return forever_status;
        }

        public void setForever_status(String forever_status) {
            this.forever_status = forever_status;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getSort_index() {
            return sort_index;
        }

        public void setSort_index(String sort_index) {
            this.sort_index = sort_index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
