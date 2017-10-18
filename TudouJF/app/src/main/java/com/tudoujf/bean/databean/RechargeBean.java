package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             RechargeBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/18
 * description：  充值接口返回的数据bean
 * history：
 * *==================================================================
 */

public class RechargeBean implements BaseBean {

    /**
     * Version : 10
     * CmdId : NetSave
     * MerCustId : 6000060002745604
     * UsrCustId : 6000060005590541
     * OrdId : 17101809093261754269
     * OrdDate : 20171018
     * GateBusiId : B2C
     * OpenBankId :
     * DcFlag : D
     * TransAmt : 10000.00
     * RetUrl : http://m.test.tudoujf.com/phone/chinapnr/recharge/return/17101809093261754269
     * BgRetUrl : http://m.test.tudoujf.com/phone/chinapnr/recharge/notify
     * OpenAcctId :
     * CertId :
     * MerPriv : 12269
     * ReqExt : {"FeeObjFlag":"U"}
     * PageType : 1
     * ChkValue : 778219FD53A301FA06E32B5241431E1B5E4F5C7AADEE8E0F3523575350BBC2C6C564D46A6A926E4B5775718FFBA8A9A17501AF536A5C428EA05C51C7F2B52EFC11D26DDDC2A401C83FBED668B4D3DA75300AE61414CCF2093CD7927765881DA1DEF0EE64C06AB800B02B1C411766AAD44F46F17214FCF271A2310350301585F8
     * CharSet : UTF-8
     * submit_url : http://mertest.chinapnr.com/muser/publicRequests
     */

    private String Version;
    private String CmdId;
    private String MerCustId;
    private String UsrCustId;
    private String OrdId;
    private String OrdDate;
    private String GateBusiId;
    private String OpenBankId;
    private String DcFlag;
    private String TransAmt;
    private String RetUrl;
    private String BgRetUrl;
    private String OpenAcctId;
    private String CertId;
    private String MerPriv;
    private String ReqExt;
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

    public String getUsrCustId() {
        return UsrCustId;
    }

    public void setUsrCustId(String UsrCustId) {
        this.UsrCustId = UsrCustId;
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

    public String getGateBusiId() {
        return GateBusiId;
    }

    public void setGateBusiId(String GateBusiId) {
        this.GateBusiId = GateBusiId;
    }

    public String getOpenBankId() {
        return OpenBankId;
    }

    public void setOpenBankId(String OpenBankId) {
        this.OpenBankId = OpenBankId;
    }

    public String getDcFlag() {
        return DcFlag;
    }

    public void setDcFlag(String DcFlag) {
        this.DcFlag = DcFlag;
    }

    public String getTransAmt() {
        return TransAmt;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
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

    public String getOpenAcctId() {
        return OpenAcctId;
    }

    public void setOpenAcctId(String OpenAcctId) {
        this.OpenAcctId = OpenAcctId;
    }

    public String getCertId() {
        return CertId;
    }

    public void setCertId(String CertId) {
        this.CertId = CertId;
    }

    public String getMerPriv() {
        return MerPriv;
    }

    public void setMerPriv(String MerPriv) {
        this.MerPriv = MerPriv;
    }

    public String getReqExt() {
        return ReqExt;
    }

    public void setReqExt(String ReqExt) {
        this.ReqExt = ReqExt;
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
