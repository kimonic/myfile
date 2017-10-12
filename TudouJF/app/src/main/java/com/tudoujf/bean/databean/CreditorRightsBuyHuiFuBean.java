package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             CreditorRightsBuyHuiFuBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/12
 * description：  债权确认购买,请求汇付页面时的bean
 * history：
 * *==================================================================
 */

public class CreditorRightsBuyHuiFuBean implements BaseBean {

    /**
     * Version : 10
     * CmdId : CreditAssign
     * MerCustId : 6000060002745604
     * SellCustId : 6000060007630674
     * CreditAmt : 0.00
     * CreditDealAmt : 10000.00
     * BidDetails : {"BidDetails":[{"BidOrdId":"17091110415337930344","BidOrdDate":"20171012","BidCreditAmt":"0.00","BorrowerDetails":[{"BorrowerCustId":"6000060007620122","BorrowerCreditAmt":"0.00","PrinAmt":"0.00","ProId":""}]}]}
     * Fee : 50.00
     * DivDetails : [{"DivAcctId":"MDT000001","DivAmt":"50.00"}]
     * BuyCustId : 6000060007620122
     * OrdId : 17101209555439811521
     * OrdDate : 20171012
     * RetUrl : http://m.test.tudoujf.com/phone/chinapnr/buyTransfer/return/17101209555439811521
     * BgRetUrl : http://m.test.tudoujf.com/phone/chinapnr/buyTransfer/notify
     * MerPriv : 3896
     * PageType : 1
     * ChkValue : 3E4D136559DC7445ADE83916B7BBD0F65011CA2BC5EB5BE86EDAC47D40EF2A139945238F4976C6ABC441181EC61D3E1C717EB18BC379D710F0E01F5D01EEBE8918F0424C838C9FF3B7AAADF2B0011B7E9FF9C2522175E3D0400B7395DD4FC2A83A3B6A2D733FCC13343F07B910BE97B5C81F217E90F63C223FDF21C1568F6748
     * submit_url : http://mertest.chinapnr.com/muser/publicRequests
     */

    private String Version;
    private String CmdId;
    private String MerCustId;
    private String SellCustId;
    private String CreditAmt;
    private String CreditDealAmt;
    private String BidDetails;
    private String Fee;
    private String DivDetails;
    private String BuyCustId;
    private String OrdId;
    private String OrdDate;
    private String RetUrl;
    private String BgRetUrl;
    private String MerPriv;
    private String PageType;
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

    public String getMerCustId() {
        return MerCustId;
    }

    public void setMerCustId(String MerCustId) {
        this.MerCustId = MerCustId;
    }

    public String getSellCustId() {
        return SellCustId;
    }

    public void setSellCustId(String SellCustId) {
        this.SellCustId = SellCustId;
    }

    public String getCreditAmt() {
        return CreditAmt;
    }

    public void setCreditAmt(String CreditAmt) {
        this.CreditAmt = CreditAmt;
    }

    public String getCreditDealAmt() {
        return CreditDealAmt;
    }

    public void setCreditDealAmt(String CreditDealAmt) {
        this.CreditDealAmt = CreditDealAmt;
    }

    public String getBidDetails() {
        return BidDetails;
    }

    public void setBidDetails(String BidDetails) {
        this.BidDetails = BidDetails;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public String getDivDetails() {
        return DivDetails;
    }

    public void setDivDetails(String DivDetails) {
        this.DivDetails = DivDetails;
    }

    public String getBuyCustId() {
        return BuyCustId;
    }

    public void setBuyCustId(String BuyCustId) {
        this.BuyCustId = BuyCustId;
    }

    public String getOrdId() {
        return OrdId;
    }

    public void setOrdId(String OrdId) {
        this.OrdId = OrdId;
    }

    public String getOrdDate() {
        return OrdDate;
    }

    public void setOrdDate(String OrdDate) {
        this.OrdDate = OrdDate;
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

    public String getPageType() {
        return PageType;
    }

    public void setPageType(String PageType) {
        this.PageType = PageType;
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
