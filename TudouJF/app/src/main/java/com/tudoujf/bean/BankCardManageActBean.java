package com.tudoujf.bean;


/**
 * * ====================================================================
 * name:            BankCardManageActBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：   银行卡管理银行卡信息临时bean-->待删除
 * history：
 * * ====================================================================
 */

public class BankCardManageActBean {
    private String  bankName;
    private String  cardNumber;
    private  int  imageResId;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
