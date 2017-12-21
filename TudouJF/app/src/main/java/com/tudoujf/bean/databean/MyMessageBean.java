package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ================================================
 * name:            MyMessageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/17
 * description：   我的消息页activity
 * history：
 * ===================================================
 */

public class MyMessageBean implements BaseBean {


    /**
     * message : 0
     * mail : 21
     * pageObj : {"page":1,"epage":20,"total_items":137,"total_pages":7,"items":[{"id":12111,"title":"投标成功","send_time":1496634552,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">您已于2017-06-05成功投资(ljq001)[vip-ceshi1]一笔￥50000.00。","status":1}]}
     */

    private String message;
    private String mail;
    private PageObjBean pageObj;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public PageObjBean getPageObj() {
        return pageObj;
    }

    public void setPageObj(PageObjBean pageObj) {
        this.pageObj = pageObj;
    }

    public static class PageObjBean {
        /**
         * page : 1
         * epage : 20
         * total_items : 137
         * total_pages : 7
         * items : [{"id":12111,"title":"投标成功","send_time":1496634552,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">您已于2017-06-05成功投资(ljq001)[vip-ceshi1]一笔￥50000.00。","status":1}]
         */

        private String page;
        private String epage;
        private String total_items;
        private String total_pages;
        private List<ItemsBean> items;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getEpage() {
            return epage;
        }

        public void setEpage(String epage) {
            this.epage = epage;
        }

        public String getTotal_items() {
            return total_items;
        }

        public void setTotal_items(String total_items) {
            this.total_items = total_items;
        }

        public String getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(String total_pages) {
            this.total_pages = total_pages;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 12111
             * title : 投标成功
             * send_time : 1496634552
             * contents : <p><div>亲爱的&nbsp;<b>18011111111</b>，您好！</div>
             <div style="text-indent:2em;">您已于2017-06-05成功投资(ljq001)[vip-ceshi1]一笔￥50000.00。
             * status : 1
             */

            private String id;
            private String title;
            private String send_time;
            private String contents;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
