package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.IntegralShopBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             IntegralShopMoreBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：
 * history：
 * *==================================================================
 */

public class IntegralShopMoreBean implements BaseBean {

    /**
     * page : 5
     * epage : 10
     * total_items : 47
     * total_pages : 5
     * items : [{"id":63,"bewrite":"苏泊尔保温杯","num":"RC08","stock":10,"name":"苏泊尔保温杯","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/7fef8ab5975e1fa82e59311d1efd407f.jpg","credit":549},{"id":65,"bewrite":"儿童实木学习桌椅组合","num":"ET01","stock":10,"name":"儿童实木学习桌椅组合","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/2dfc0c5e2de71120362c1c2e866eeb92.jpg","credit":1979},{"id":66,"bewrite":"贝恩施儿童过家家厨房玩具","num":"ET02","stock":10,"name":"贝恩施儿童厨房玩具","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/23c6ef58930acd289cfd008e88a0d8e9.jpg","credit":989},{"id":67,"bewrite":"LOVO家纺罗莱全棉四件套","num":"RC09","stock":10,"name":"罗莱家纺四件套","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/943b4c6ae04bd697f130e3cb18507327.jpg","credit":2219},{"id":68,"bewrite":"花上车载空气净化器","num":"CHZ003","stock":10,"name":"花上车载空气净化器","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/e32a6b9223a5da7f726c4c96cdffc383.jpg","credit":1389},{"id":69,"bewrite":"美的家用手持式吸尘器","num":"SH07","stock":10,"name":"美的吸尘器","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/4a1ed099a32258cb01fde10acc571b42.jpg","credit":2059},{"id":70,"bewrite":"宗棠紫砂功夫茶具套装38件","num":"RC10","stock":10,"name":"宗棠紫砂功夫茶具套装","images":"https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20170203/dbe5ac070bb3f6b05a689953e7c59caf.jpg","credit":1489}]
     * params : null
     */

    private int page;
    private int epage;
    private int total_items;
    private int total_pages;
    private JsonElement params;
    private List<IntegralShopBean.GoodsBean.ItemsBean> items;

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

    public List<IntegralShopBean.GoodsBean.ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<IntegralShopBean.GoodsBean.ItemsBean> items) {
        this.items = items;
    }

}
