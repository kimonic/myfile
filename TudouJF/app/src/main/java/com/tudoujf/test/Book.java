package com.tudoujf.test;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/7/20.
 * 数据库测试用例
 */

public class Book extends DataSupport{
    private int id;
    private String name;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
