package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ===============================================================
 * name:             PersonalCenterBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/23
 * description：  我的接口返回的数据bean
 * history：
 * *==================================================================
 */

public class PersonalCenterBean implements BaseBean {

    /**
     * experience_balance : 0
     * is_trust : 1
     * isVip : 1
     * count : 57
     * amount_all : 556489.06
     * member_name : 18011111111
     * avatar : http://imgview.test.tudoujf.com/member/memberinfo/20171009/897e18b694f3188f80134488a3344f9c.png
     * amount_balance : 9861585.96
     * interest_award : 1426.72
     * vipEndTime : 2019-05-01
     */

    private String experience_balance;
    private String is_trust;
    private String isVip;
    private String count;
    private String amount_all;
    private String member_name;
    private String avatar;
    private String amount_balance;
    private String interest_award;
    private String vipEndTime;
    private String weChatID;

    public String getWeChatID() {
        return weChatID;
    }

    public void setWeChatID(String weChatID) {
        this.weChatID = weChatID;
    }

    public String getExperience_balance() {
        return experience_balance;
    }

    public void setExperience_balance(String experience_balance) {
        this.experience_balance = experience_balance;
    }

    public String getIs_trust() {
        return is_trust;
    }

    public void setIs_trust(String is_trust) {
        this.is_trust = is_trust;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAmount_all() {
        return amount_all;
    }

    public void setAmount_all(String amount_all) {
        this.amount_all = amount_all;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAmount_balance() {
        return amount_balance;
    }

    public void setAmount_balance(String amount_balance) {
        this.amount_balance = amount_balance;
    }

    public String getInterest_award() {
        return interest_award;
    }

    public void setInterest_award(String Stringerest_award) {
        this.interest_award = Stringerest_award;
    }

    public String getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(String vipEndTime) {
        this.vipEndTime = vipEndTime;
    }
}
