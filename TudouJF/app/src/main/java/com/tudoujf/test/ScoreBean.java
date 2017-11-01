package com.tudoujf.test;

import org.litepal.crud.DataSupport;

/**
 * * ===============================================================
 * name:             ScoreBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/1
 * description：   猜猜乐概率出现存储bean
 * history：
 * *==================================================================
 */

public class ScoreBean extends DataSupport {

    private int red;
    private int yellow;
    private int blue;
    private int green;


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

}
