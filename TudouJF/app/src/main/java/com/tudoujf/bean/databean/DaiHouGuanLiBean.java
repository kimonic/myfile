package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             DaiHouGuanLiBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/26
 * description：  贷后管理bean
 * history：
 * *==================================================================
 */

public class DaiHouGuanLiBean implements BaseBean {

    /**
     * code : 200
     * result : success
     * data : [{"id":4,"add_time":1513912557,"member_name":"ljq001","admin_name":"diyou","contents":"12312312sdfdsfsdfsdfsdfsds"},{"id":3,"add_time":1513911989,"member_name":"ljq001","admin_name":"diyou","contents":"合规、共债、催收、出海、联合放贷等是目前比较关注的话题，而风控、反欺诈和催收是现金贷的命门。具备强大风控技术能力、产品能力、贷后催收能力的团队将在这一竞争中率先胜出。   精细化催收运营体系的搭建，全面构建反欺诈体系收集多方位数据，构建客户画像，搭建系统架构，支持实时反欺诈功能，利用数据分析 / 策略防御欺诈。"},{"id":2,"add_time":1513911078,"member_name":"ljq001","admin_name":"diyou","contents":"利率、合规、共债、催收、出海、联合放贷等是目前比较关注的话题，而风控、反欺诈和催收是现金贷的命门。具备强大风控技术能力、产品能力、贷后催收能力的团队将在这一竞争中率先胜出。   精细化催收运营体系的搭建，全面构建反欺诈体系收集多方位数据，构建客户画像，搭建系统架构，支持实时反欺诈功能，利用数据分析 / 策略防御欺诈。"}]
     * description : 
     * xmdy : null
     * diyou : null
     */

    private int code;
    private String result;
    private String description;
    private JsonElement xmdy;
    private JsonElement diyou;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
         * id : 4
         * add_time : 1513912557
         * member_name : ljq001
         * admin_name : diyou
         * contents : 12312312sdfdsfsdfsdfsdfsds
         */

        private String id;
        private String add_time;
        private String member_name;
        private String admin_name;
        private String contents;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }
    }
}
