package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ===============================================================
 * name:             GoodsDetailsBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/8
 * description：  积分商城商品详情bean
 * history：
 * *==================================================================
 */

public class GoodsDetailsBean implements BaseBean {

    /**
     * id : 51
     * summary : 美的（Midea） 双杆蒸汽挂烫机 2L
     * bewrite : 美的（Midea） 双杆蒸汽挂烫机 2L
     * num : SH04
     * detail : 发热器材质	铝合金发热器
     水箱容积L	2.0L
     导气管材质	PVC+编织材质
     支挂杆材质	双金属杆
     蒸汽量g/m	30
     适合挂烫的衣物	四季衣物（皮衣除外）
     * stock : 10
     * use_description : 可10件同步熨烫，2L大水箱，9孔烫头，持续强力蒸汽，6档微电脑轻触操作
     * name : Midea美的挂烫机
     * images : ["https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160918/72609166d82d1a47f95c3199e9e017a0.png","https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160918/039fd6af154695225d409f36be2fdd91.png","https://www.tudoujf.com/imagesAHFG876/images/mall/goods/20160918/367ab9b594187a502fdbbc4dbd86d73e.png"]
     * add_time : 1474190187
     * credit : 1649
     */

    private String id;
    private String summary;
    private String bewrite;
    private String num;
    private String detail;
    private String stock;
    private String use_description;
    private String name;
    private String add_time;
    private String credit;
    private List<String> images;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUse_description() {
        return use_description;
    }

    public void setUse_description(String use_description) {
        this.use_description = use_description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
