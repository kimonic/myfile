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
     * page : 1
     * epage : 10
     * total_items : 62
     * total_pages : 7
     * items : [{"id":12546,"title":"收到提前还款","send_time":1499758768,"contents":"亲爱的18011111111，您好！\r\n用户(ljq001)在2017-07-11对您所投资的借款标[laishang10000]已经成功提前还款,还款金额￥1000.00","status":1},{"id":12544,"title":"投标成功","send_time":1499758687,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">您已于2017-07-11成功投资(ljq001)[zailai1000]一笔￥1000.00。","status":1},{"id":12541,"title":"收到还款","send_time":1499758506,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-11对您所投资的借款标[laishang10000]第[1]期已经成功还款,还款金额￥11.67<\/div>","status":1},{"id":12539,"title":"投标成功","send_time":1499758453,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">您已于2017-07-11成功投资(ljq001)[laishang10000]一笔￥1000.00。","status":1},{"id":12439,"title":"收到还款","send_time":1499050986,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-03对您所投资的借款标[jie-10000]第[1]期已经成功还款,还款金额￥10066.67<\/div>","status":1},{"id":12437,"title":"投标成功","send_time":1499050954,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">您已于2017-07-03成功投资(ljq001)[jie-10000]一笔￥10000.00。","status":1},{"id":12434,"title":"收到还款","send_time":1499049320,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-03对您所投资的借款标[借款-2000]第[1]期已经成功还款,还款金额￥2013.33<\/div>","status":1},{"id":12431,"title":"收到还款","send_time":1499048997,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-03对您所投资的借款标[vip-ceshi1]第[2]期已经成功还款,还款金额￥458.33<\/div>","status":1},{"id":12429,"title":"收到还款","send_time":1499047757,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-03对您所投资的借款标[jiekuan-5000]第[1]期已经成功还款,还款金额￥4030.00<\/div>","status":1},{"id":12426,"title":"收到还款","send_time":1499047452,"contents":"<p><div>亲爱的&nbsp;<b>18011111111<\/b>，您好！<\/div>\r\n<div style=\"text-indent:2em;\">用户(ljq001)在2017-07-03对您所投资的借款标[jiekuan-1000-1]第[1]期已经成功还款,还款金额￥1010.00<\/div>","status":1}]
     * params : null
     */

    private String page;
    private String epage;
    private String total_items;
    private String total_pages;
    /**此参数可能会出问题*/
    private String params;
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
         * id : 12546
         * title : 收到提前还款
         * send_time : 1499758768
         * contents : 亲爱的18011111111，您好！
         用户(ljq001)在2017-07-11对您所投资的借款标[laishang10000]已经成功提前还款,还款金额￥1000.00
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
