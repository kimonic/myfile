package com.tudoujf.bean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             IntegralShopBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/18
 * description：
 * history：
 * *==================================================================
 */

public class IntegralShopBean implements BaseBean {

    /**
     * goods : {"page":1,"epage":10,"total_items":47,"total_pages":5,"items":[{"id":14,"bewrite":"Apple MacBook 12英寸笔记本电脑","num":"SM01","stock":10,"name":"苹果MacBook","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/33d328ca8d2881a8c96767e6216da179.png","credit":51199},{"id":15,"bewrite":"Apple Watch Sport 智能手表 38毫米","num":"SM02","stock":10,"name":"Apple Watch","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/3aff029cb538830a9f483556febc0397.png","credit":12599},{"id":18,"bewrite":"小米（MI）随身Mini 迷你无线蓝牙便携音箱","num":"SM03","stock":9,"name":"小米蓝牙音箱","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6ac00a75295a006099794720d7175526.png","credit":429},{"id":19,"bewrite":"小米（MI）小米智能手环 标准版","num":"SM04","stock":10,"name":"小米手环","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/f6868271d093d2715ffaa951dbab4701.png","credit":379},{"id":20,"bewrite":"小米（MI）小米新款移动电源/充电宝10000mAh","num":"SM05","stock":8,"name":"小米移动电源","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6a1e6a1b14c6dd0d7711c6913b581985.png","credit":529},{"id":23,"bewrite":"JmGO坚果G1投影仪","num":"SM06","stock":10,"name":"坚果G1投影仪","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/f8cfd06f8e76b051a400c4ee1795d89a.png","credit":14899},{"id":25,"bewrite":"小米（MI）定制版Ninebot九号平衡车","num":"SM07","stock":10,"name":"小米平衡车","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6057ebf0a800c0caed4c34b65b2194a8.png","credit":10999},{"id":26,"bewrite":"MARS BY CRAZYBABY L141 磁悬浮hifi音响","num":"SM08","stock":10,"name":"磁悬浮hifi音响","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/1e21a016cb883eed9029d85490587910.png","credit":10999},{"id":28,"bewrite":"倍思-时尚充电LED音乐小台灯","num":"SM09","stock":10,"name":"倍思-充电LED音乐小台灯","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6191e43f0f29eb106d40360b051091ba.png","credit":549},{"id":29,"bewrite":"iPhone 6s","num":"SJ01","stock":10,"name":"iPhone 6s","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/bcf39fa3c695bc9aba3fc2fdcff0aa36.png","credit":29099}],"params":null}
     * myPoint : 30854
     */

    private GoodsBean goods;
    private String myPoint;

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public String getMyPoint() {
        return myPoint;
    }

    public void setMyPoint(String myPoint) {
        this.myPoint = myPoint;
    }

    public static class GoodsBean {
        /**
         * page : 1
         * epage : 10
         * total_items : 47
         * total_pages : 5
         * items : [{"id":14,"bewrite":"Apple MacBook 12英寸笔记本电脑","num":"SM01","stock":10,"name":"苹果MacBook","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/33d328ca8d2881a8c96767e6216da179.png","credit":51199},{"id":15,"bewrite":"Apple Watch Sport 智能手表 38毫米","num":"SM02","stock":10,"name":"Apple Watch","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/3aff029cb538830a9f483556febc0397.png","credit":12599},{"id":18,"bewrite":"小米（MI）随身Mini 迷你无线蓝牙便携音箱","num":"SM03","stock":9,"name":"小米蓝牙音箱","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6ac00a75295a006099794720d7175526.png","credit":429},{"id":19,"bewrite":"小米（MI）小米智能手环 标准版","num":"SM04","stock":10,"name":"小米手环","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/f6868271d093d2715ffaa951dbab4701.png","credit":379},{"id":20,"bewrite":"小米（MI）小米新款移动电源/充电宝10000mAh","num":"SM05","stock":8,"name":"小米移动电源","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6a1e6a1b14c6dd0d7711c6913b581985.png","credit":529},{"id":23,"bewrite":"JmGO坚果G1投影仪","num":"SM06","stock":10,"name":"坚果G1投影仪","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/f8cfd06f8e76b051a400c4ee1795d89a.png","credit":14899},{"id":25,"bewrite":"小米（MI）定制版Ninebot九号平衡车","num":"SM07","stock":10,"name":"小米平衡车","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6057ebf0a800c0caed4c34b65b2194a8.png","credit":10999},{"id":26,"bewrite":"MARS BY CRAZYBABY L141 磁悬浮hifi音响","num":"SM08","stock":10,"name":"磁悬浮hifi音响","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/1e21a016cb883eed9029d85490587910.png","credit":10999},{"id":28,"bewrite":"倍思-时尚充电LED音乐小台灯","num":"SM09","stock":10,"name":"倍思-充电LED音乐小台灯","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6191e43f0f29eb106d40360b051091ba.png","credit":549},{"id":29,"bewrite":"iPhone 6s","num":"SJ01","stock":10,"name":"iPhone 6s","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/bcf39fa3c695bc9aba3fc2fdcff0aa36.png","credit":29099}]
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
             * id : 14
             * bewrite : Apple MacBook 12英寸笔记本电脑
             * num : SM01
             * stock : 10
             * name : 苹果MacBook
             * images : https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/33d328ca8d2881a8c96767e6216da179.png
             * credit : 51199
             */

            private String id;
            private String bewrite;
            private String num;
            private String stock;
            private String name;
            private String images;
            private String credit;

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
        }
    }
}
