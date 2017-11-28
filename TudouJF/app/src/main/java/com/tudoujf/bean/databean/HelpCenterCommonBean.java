package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             HelpCenterCommonBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/28
 * description：
 * history：
 * *==================================================================
 */

public class HelpCenterCommonBean implements BaseBean {


    /**
     * code : 200
     * result : success
     * data : [{"id":74,"summary":"登陆土豆金服首页（http://www.tudoujf.com），点击网页顶部右侧【注册】按钮，进入注册页面；","author":"TDJF","title":"如何在土豆金服注册？","contents":"<p><span style=\"font-family: 微软雅黑, &#39;Microsoft YaHei&#39;; color: rgb(0, 0, 0);font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 1、登陆土豆金服首页（<a href=\"http://www.tudoujf.com\" target=\"_self\" title=\"土豆金服\"><span style=\"font-size:16px\">http://www.tudoujf.com<\/span><\/a>），点击网页顶部右侧【注册】按钮，进入注册页面；<\/span><\/p><p><span style=\"font-family:微软雅黑, Microsoft YaHei\"><span style=\"color: rgb(0, 0, 0);font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 2、根据注册页面的提示，填写注册信息，然后点击【免费注册】;<\/span><\/span><\/p><p><span style=\"font-family:微软雅黑, Microsoft YaHei;font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 3、<span style=\"color:#000000\">填写手机上的效验码 , 点击【验证】按钮，完成手机验证后，即可注册成功；<\/span><\/span><\/p>","category_id":88,"add_time":1459332717},{"id":74,"summary":"登陆土豆金服首页（http://www.tudoujf.com），点击网页顶部右侧【注册】按钮，进入注册页面；","author":"TDJF","title":"如何在土豆金服注册？","contents":"<p><span style=\"font-family: 微软雅黑, &#39;Microsoft YaHei&#39;; color: rgb(0, 0, 0);font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 1、登陆土豆金服首页（<a href=\"http://www.tudoujf.com\" target=\"_self\" title=\"土豆金服\"><span style=\"font-size:16px\">http://www.tudoujf.com<\/span><\/a>），点击网页顶部右侧【注册】按钮，进入注册页面；<\/span><\/p><p><span style=\"font-family:微软雅黑, Microsoft YaHei\"><span style=\"color: rgb(0, 0, 0);font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 2、根据注册页面的提示，填写注册信息，然后点击【免费注册】;<\/span><\/span><\/p><p><span style=\"font-family:微软雅黑, Microsoft YaHei;font-size:16px\">&nbsp; &nbsp; &nbsp; &nbsp; 3、<span style=\"color:#000000\">填写手机上的效验码 , 点击【验证】按钮，完成手机验证后，即可注册成功；<\/span><\/span><\/p>","category_id":88,"add_time":1459332717}]
     * description : null
     * xmdy : null
     * diyou : null
     */

    private String code;
    private String result;
    private JsonElement description;
    private JsonElement xmdy;
    private JsonElement diyou;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JsonElement getDescription() {
        return description;
    }

    public void setDescription(JsonElement description) {
        this.description = description;
    }

    public JsonElement getXmdy() {
        return xmdy;
    }

    public void setXmdy(JsonElement xmdy) {
        this.xmdy = xmdy;
    }

    public JsonElement getDiyou() {
        return diyou;
    }

    public void setDiyou(JsonElement diyou) {
        this.diyou = diyou;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 74
         * summary : 登陆土豆金服首页（http://www.tudoujf.com），点击网页顶部右侧【注册】按钮，进入注册页面；
         * author : TDJF
         * title : 如何在土豆金服注册？
         * contents : <p><span style="font-family: 微软雅黑, &#39;Microsoft YaHei&#39;; color: rgb(0, 0, 0);font-size:16px">&nbsp; &nbsp; &nbsp; &nbsp; 1、登陆土豆金服首页（<a href="http://www.tudoujf.com" target="_self" title="土豆金服"><span style="font-size:16px">http://www.tudoujf.com</span></a>），点击网页顶部右侧【注册】按钮，进入注册页面；</span></p><p><span style="font-family:微软雅黑, Microsoft YaHei"><span style="color: rgb(0, 0, 0);font-size:16px">&nbsp; &nbsp; &nbsp; &nbsp; 2、根据注册页面的提示，填写注册信息，然后点击【免费注册】;</span></span></p><p><span style="font-family:微软雅黑, Microsoft YaHei;font-size:16px">&nbsp; &nbsp; &nbsp; &nbsp; 3、<span style="color:#000000">填写手机上的效验码 , 点击【验证】按钮，完成手机验证后，即可注册成功；</span></span></p>
         * category_id : 88
         * add_time : 1459332717
         */

        private String id;
        private String summary;
        private String author;
        private String title;
        private String contents;
        private String category_id;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
