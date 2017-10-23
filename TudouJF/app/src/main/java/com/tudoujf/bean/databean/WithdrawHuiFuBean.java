package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             WithdrawHuiFuBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/23
 * description：  请求提现接口,提交汇付bean
 * history：
 * *==================================================================
 */

public class WithdrawHuiFuBean implements BaseBean {


    /**
     * Version : 20
     * CmdId : Cash
     * MerCustId : 6000060002745604
     * OrdId : 17102310092977638667
     * UsrCustId : 6000060005589777
     * TransAmt : 1000.00
     * ServFee : 3.00
     * ServFeeAcctId : BASEDT
     * OpenAcctId :
     * RetUrl : http://m.test.tudoujf.com/phone/chinapnr/withdraw/return/17102310092977638667
     * BgRetUrl : http://m.test.tudoujf.com/phone/chinapnr/withdraw/notify
     * Remark :
     * MerPriv :
     * ReqExt : [{"FeeObjFlag":"U"}]
     * PageType : 1
     * ChkValue : 4FCFDDD7B250342E8CC2A2D7A66A3E1C17A76C01E3BB9253F74FB1A778E27980E709A7A3ECCD242186C4C3E64010657CF228BAD26D0DA4CBB8DD692062B037423C83B95A3C866DF47EFF02BECA4F58399D38A955DEEBD880773B2C814ABF9A34B947EFB818A191DB0BB4EDD819D8D846A4F6D9AE446FF8FD3E9F6A795452E479
     * CharSet : UTF-8
     * submit_url : http://mertest.chinapnr.com/muser/publicRequests
     */

    private String Version;
    private String CmdId;
    private String MerCustId;
    private String OrdId;
    private String UsrCustId;
    private String TransAmt;
    private String ServFee;
    private String ServFeeAcctId;
    private String OpenAcctId;
    private String RetUrl;
    private String BgRetUrl;
    private String Remark;
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

    public String getTransAmt() {
        return TransAmt;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public String getServFee() {
        return ServFee;
    }

    public void setServFee(String ServFee) {
        this.ServFee = ServFee;
    }

    public String getServFeeAcctId() {
        return ServFeeAcctId;
    }

    public void setServFeeAcctId(String ServFeeAcctId) {
        this.ServFeeAcctId = ServFeeAcctId;
    }

    public String getOpenAcctId() {
        return OpenAcctId;
    }

    public void setOpenAcctId(String OpenAcctId) {
        this.OpenAcctId = OpenAcctId;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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
