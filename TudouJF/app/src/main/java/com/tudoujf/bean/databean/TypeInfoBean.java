package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             TypeInfoBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/20
 * description：
 * history：
 * *==================================================================
 */

public class TypeInfoBean implements BaseBean {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 0
         * name : 全部
         * children : []
         * pid : 0
         */

        private String id;
        private String name;
        private String pid;
        private int  bacFlag=1;
        private JsonElement children;


        public int getBacFlag() {
            return bacFlag;
        }

        public void setBacFlag(int bacFlag) {
            this.bacFlag = bacFlag;
        }

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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public JsonElement getChildren() {
            return children;
        }

        public void setChildren(JsonElement children) {
            this.children = children;
        }
    }
}
