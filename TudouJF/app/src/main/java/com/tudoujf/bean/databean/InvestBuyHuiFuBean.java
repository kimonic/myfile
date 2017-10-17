package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             InvestBuyHuiFuBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/17
 * description：  投资购买返回的汇付数据bean
 * history：
 * *==================================================================
 */

public class InvestBuyHuiFuBean implements BaseBean {

    /**
     * Version : 20
     * CmdId : InitiativeTender
     * MerCustId : 6000060002745604
     * OrdId : 17101709355908545169
     * OrdDate : 20171017
     * TransAmt : 10000.00
     * UsrCustId : 6000060005589777
     * MaxTenderRate : 0.50
     * BorrowerDetails : [{"BorrowerCustId":"6000060007620122","BorrowerAmt":"10000.00","BorrowerRate":"0.20","ProId":""}]
     * IsFreeze : Y
     * FreezeOrdId : 17101709355908545169
     * RetUrl : http://m.test.tudoujf.com/phone/chinapnr/tender/return/17101709355908545169
     * BgRetUrl : http://m.test.tudoujf.com/phone/chinapnr/tender/notify
     * ReqExt :
     * MerPriv :
     * PageType : 1
     * ChkValue : 719A905F2B6204C73359E7FDC5C28DA158FF036F23382EF5598AC89A9F9529443774CA6252FFE7220DC80C7EA88CF0FDF3B8193DF40985A2913E5F5877F25D16E892C28840AFC9195C8778F8195AADE58AFEB9152D783F1C66DCA5C11E8BB439AF54C9D214657C72753EB2ACAF01AA9FDD65C770FB0391ED2C86842D21EC3F34
     * CharSet : UTF-8
     * submit_url : http://mertest.chinapnr.com/muser/publicRequests
     */

    private String Version;
    private String CmdId;
    private String MerCustId;
    private String OrdId;
    private String OrdDate;
    private String TransAmt;
    private String UsrCustId;
    private String MaxTenderRate;
    private String BorrowerDetails;
    private String IsFreeze;
    private String FreezeOrdId;
    private String RetUrl;
    private String BgRetUrl;
    private String ReqExt;
    private String MerPriv;
    private String PageType;
    private String ChkValue;
    private String CharSet;
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

    public String getTransAmt() {
        return TransAmt;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public String getUsrCustId() {
        return UsrCustId;
    }

    public void setUsrCustId(String UsrCustId) {
        this.UsrCustId = UsrCustId;
    }

    public String getMaxTenderRate() {
        return MaxTenderRate;
    }

    public void setMaxTenderRate(String MaxTenderRate) {
        this.MaxTenderRate = MaxTenderRate;
    }

    public String getBorrowerDetails() {
        return BorrowerDetails;
    }

    public void setBorrowerDetails(String BorrowerDetails) {
        this.BorrowerDetails = BorrowerDetails;
    }

    public String getIsFreeze() {
        return IsFreeze;
    }

    public void setIsFreeze(String IsFreeze) {
        this.IsFreeze = IsFreeze;
    }

    public String getFreezeOrdId() {
        return FreezeOrdId;
    }

    public void setFreezeOrdId(String FreezeOrdId) {
        this.FreezeOrdId = FreezeOrdId;
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

    public String getReqExt() {
        return ReqExt;
    }

    public void setReqExt(String ReqExt) {
        this.ReqExt = ReqExt;
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

    public String getCharSet() {
        return CharSet;
    }

    public void setCharSet(String CharSet) {
        this.CharSet = CharSet;
    }

    public String getSubmit_url() {
        return submit_url;
    }

    public void setSubmit_url(String submit_url) {
        this.submit_url = submit_url;
    }
}
