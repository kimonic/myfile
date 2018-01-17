package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             ExchangeRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/16
 * description：  积分商城--兑换记录返回的接口数据
 * history：
 * *==================================================================
 */

public class ExchangeRecordBean implements BaseBean {

    /**
     * page : 1
     * epage : 20
     * total_items : 7
     * total_pages : 1
     * items : [{"post":"","remark":" ","consignee":"21231","goods_name":"小米手环","order_status":"-1","member_id":"12267","id":"73","add_ip":"2079039658","delivery_status":"未寄送","address":"32131321","goods_id":"19","add_time":"1515550597","member_name":"18011111111","credit":"379","order_count":"1","mobile":"16516514651"},{"post":"","remark":" ","consignee":"123","goods_name":"小米手环","order_status":"-1","member_id":"12267","id":"72","add_ip":"2079039658","delivery_status":"未寄送","address":"12312","goods_id":"19","add_time":"1515550580","member_name":"18011111111","credit":"379","order_count":"1","mobile":"15614616513"},{"post":"","remark":" ","consignee":"123","goods_name":"小米手环","order_status":"-1","member_id":"12267","id":"71","add_ip":"2079039658","delivery_status":"未寄送","address":"zsfds","goods_id":"19","add_time":"1515550343","member_name":"18011111111","credit":"379","order_count":"1","mobile":"15984513132"},{"post":"","remark":" ","consignee":"1233","goods_name":"小米手环","order_status":"-1","member_id":"12267","id":"70","add_ip":"2079040957","delivery_status":"未寄送","address":"efrsfdfsgfdgasasfd","goods_id":"19","add_time":"1515550077","member_name":"18011111111","credit":"379","order_count":"1","mobile":"15914961951"},{"post":"","remark":" ","consignee":"123","goods_name":"小米手环","order_status":"-1","member_id":"12267","id":"69","add_ip":"2079040957","delivery_status":"未寄送","address":"sdhfshlkfajklsjdfljal;djfslkjasdlfjlajsdf","goods_id":"19","add_time":"1515549619","member_name":"18011111111","credit":"379","order_count":"1","mobile":"18554654654"},{"post":"","remark":" ","consignee":"eewewe","goods_name":"中国移动充值卡20元","order_status":"-1","member_id":"12267","id":"68","add_ip":"2079039658","delivery_status":"未寄送","address":"ewewewewe","goods_id":"39","add_time":"1515487435","member_name":"18011111111","credit":"109","order_count":"1","mobile":"11111111111"},{"post":"","remark":" ","consignee":"dsdsd","goods_name":"中国移动充值卡20元","order_status":"-1","member_id":"12267","id":"67","add_ip":"2079039658","delivery_status":"未寄送","address":"dsdsd","goods_id":"39","add_time":"1515482618","member_name":"18011111111","credit":"109","order_count":"1","mobile":"11111111111"}]
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
         * post :
         * remark :
         * consignee : 21231
         * goods_name : 小米手环
         * order_status : -1
         * member_id : 12267
         * id : 73
         * add_ip : 2079039658
         * delivery_status : 未寄送
         * address : 32131321
         * goods_id : 19
         * add_time : 1515550597
         * member_name : 18011111111
         * credit : 379
         * order_count : 1
         * mobile : 16516514651
         * "images": "https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160914/6191e43f0f29eb106d40360b051091ba.png",
         */

        private String post;
        private String remark;
        private String consignee;
        private String goods_name;
        private String order_status;
        private String member_id;
        private String id;
        private String add_ip;
        private String delivery_status;
        private String address;
        private String goods_id;
        private String add_time;
        private String member_name;
        private String credit;
        private String order_count;
        private String mobile;
        private String images;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        private  int  bacFlag;

        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
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

        public String getAdd_ip() {
            return add_ip;
        }

        public void setAdd_ip(String add_ip) {
            this.add_ip = add_ip;
        }

        public String getDelivery_status() {
            return delivery_status;
        }

        public void setDelivery_status(String delivery_status) {
            this.delivery_status = delivery_status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
