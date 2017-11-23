package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             VipHuiFuBuyBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/23
 * description：  购买VIP付款汇付界面bean
 * history：
 * *==================================================================
 */

public class VipHuiFuBuyBean implements BaseBean {

    /**
     * Version : 10
     * CmdId : UsrAcctPay
     * OrdId : 17112311244088776767
     * UsrCustId : 6000060005589777
     * MerCustId : 6000060002745604
     * TransAmt : 30.00
     * InAcctId : MDT000001
     * InAcctType : MERDT
     * RetUrl : http://m.test.tudoujf.com/phone/chinapnr/vipApply/return/17112311244088776767
     * BgRetUrl : http://m.test.tudoujf.com/phone/chinapnr/vipApply/notify
     * MerPriv :
     * ChkValue : 281324F45A030A2E3587B64459B4128CDA54E5E850524BC4216E4315B844F3B9BF957AFB71D7D2CF0E04BCD7DB9D4E5BABAAB20F014D8514FEF1887A1942229F5F7589E178111C262B0531F0F634460C549E66F45186DF8ABFDC1538FD10172E61ACE10FC904CB5D41342F6CF9CAC1C76DC1776DAF5CEB623E6252A557448D98
     * submit_url : http://mertest.chinapnr.com/muser/publicRequests
     */

    private String Version;
    private String CmdId;
    private String OrdId;
    private String UsrCustId;
    private String MerCustId;
    private String TransAmt;
    private String InAcctId;
    private String InAcctType;
    private String RetUrl;
    private String BgRetUrl;
    private String MerPriv;
    private String ChkValue;
    private String submit_url;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getCmdId() {
        return CmdId;
    }

    public void setCmdId(String CmdId) {
        this.CmdId = CmdId;
    }

    public String getOrdId() {
        return OrdId;
    }

    public void setOrdId(String OrdId) {
        this.OrdId = OrdId;
    }

    public String getUsrCustId() {
        return UsrCustId;
    }

    public void setUsrCustId(String UsrCustId) {
        this.UsrCustId = UsrCustId;
    }

    public String getMerCustId() {
        return MerCustId;
    }

    public void setMerCustId(String MerCustId) {
        this.MerCustId = MerCustId;
    }

    public String getTransAmt() {
        return TransAmt;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public String getInAcctId() {
        return InAcctId;
    }

    public void setInAcctId(String InAcctId) {
        this.InAcctId = InAcctId;
    }

    public String getInAcctType() {
        return InAcctType;
    }

    public void setInAcctType(String InAcctType) {
        this.InAcctType = InAcctType;
    }

    public String getRetUrl() {
        return RetUrl;
    }

    public void setRetUrl(String RetUrl) {
        this.RetUrl = RetUrl;
    }

    public String getBgRetUrl() {
        return BgRetUrl;
    }

    public void setBgRetUrl(String BgRetUrl) {
        this.BgRetUrl = BgRetUrl;
    }

    public String getMerPriv() {
        return MerPriv;
    }

    public void setMerPriv(String MerPriv) {
        this.MerPriv = MerPriv;
    }

    public String getChkValue() {
        return ChkValue;
    }

    public void setChkValue(String ChkValue) {
        this.ChkValue = ChkValue;
    }

    public String getSubmit_url() {
        return submit_url;
    }

    public void setSubmit_url(String submit_url) {
        this.submit_url = submit_url;
    }
}
