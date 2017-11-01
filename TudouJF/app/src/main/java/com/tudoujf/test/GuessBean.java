package com.tudoujf.test;

import org.litepal.crud.DataSupport;

/**
 * * ===============================================================
 * name:             GuessBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/1
 * description：   猜猜乐存储bean
 * history：
 * *==================================================================
 */

public class GuessBean extends DataSupport {

    private  String  userName;
    private  int  amount;
    private  int  red;
    private  int  yellow;
    private  int  blue;
    private  int  green;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
