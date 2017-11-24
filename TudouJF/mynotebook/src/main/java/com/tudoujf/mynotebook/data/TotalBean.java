package com.tudoujf.mynotebook.data;

import org.litepal.crud.DataSupport;

/**
 * * ===============================================================
 * name:             TotalBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/24
 * description：
 * history：
 * *==================================================================
 */

public class TotalBean extends DataSupport {
    /**普通文本记录的总条数*/
    private long   total;
    /**金钱来往记录总条数*/
    private long moneyTotal;

    public long getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(long moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
