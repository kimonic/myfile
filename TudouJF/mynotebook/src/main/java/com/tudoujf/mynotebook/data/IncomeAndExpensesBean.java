package com.tudoujf.mynotebook.data;

import org.litepal.crud.DataSupport;

/**
 * * ===============================================================
 * name:             IncomeAndExpensesBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/24
 * description：
 * history：
 * *==================================================================
 */

public class IncomeAndExpensesBean extends DataSupport {
    /**时间*/
    private  String  time;
    /**金额*/
    private  float  amount;
    /**用途*/
    private  String  purpose;
    /**关系人*/
    private  String  affiliated;
    /**保存的序号*/
    private long   saveId;
    /**最后修改时间*/
    private  String  endChangeTime;

    public String getEndChangeTime() {
        return endChangeTime;
    }

    public void setEndChangeTime(String endChangeTime) {
        this.endChangeTime = endChangeTime;
    }

    public long getSaveId() {
        return saveId;
    }

    public void setSaveId(long saveId) {
        this.saveId = saveId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(String affiliated) {
        this.affiliated = affiliated;
    }
}
