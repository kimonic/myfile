package com.tudoujf.bean.databean;

import com.tudoujf.base.BaseBean;

/**
 * * ====================================================================
 * name:            LoginBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：   登陆成功后的返回bean
 * history：
 * * ====================================================================
 */

public class LoginBean implements BaseBean {

    /**
     * member : {"id":12321,"name":"15222222222","trustAccount":null,"password":"8a9acce84088bc64f589dde04796eb2a","email":null,"phone":15222222222,"paypassword":null,"count":9,"registerIp":660157581,"registerTime":1502703667,"registerDate":"Aug 14, 2017 12:00:00 AM","lastloginIp":660157581,"lastlogStringime":1502712692,"lastloginDate":"Aug 14, 2017 12:00:00 AM","registerType":1,"role":1,"isRealname":-1,"isEmail":-1,"isPhone":1,"isVideo":-1,"isId5":-1,"isVip":-1,"isAuto":"-1","pwdAttach":"0rE819JKR2","status":1,"creditPoString":0,"lockTime":null,"group":1,"groupStatus":"-1","vipStartTime":null,"vipEndTime":null,"vipCategoryId":null,"vipCategoryInd":null,"amount":0,"channelSource":null,"channelId":null,"provience":"山东省","ranking":null}
     * remark : 
     * login_token : 12321
     */

    private MemberBean member;
    private String remark;
    private String login_token;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }

    public static class MemberBean {
        /**
         * id : 12321
         * name : 15222222222
         * trustAccount : null
         * password : 8a9acce84088bc64f589dde04796eb2a
         * email : null
         * phone : 15222222222
         * paypassword : null
         * count : 9
         * registerIp : 660157581
         * registerTime : 1502703667
         * registerDate : Aug 14, 2017 12:00:00 AM
         * lastloginIp : 660157581
         * lastlogStringime : 1502712692
         * lastloginDate : Aug 14, 2017 12:00:00 AM
         * registerType : 1
         * role : 1
         * isRealname : -1
         * isEmail : -1
         * isPhone : 1
         * isVideo : -1
         * isId5 : -1
         * isVip : -1
         * isAuto : -1
         * pwdAttach : 0rE819JKR2
         * status : 1
         * creditPoString : 0
         * lockTime : null
         * group : 1
         * groupStatus : -1
         * vipStartTime : null
         * vipEndTime : null
         * vipCategoryId : null
         * vipCategoryInd : null
         * amount : 0
         * channelSource : null
         * channelId : null
         * provience : 山东省
         * ranking : null
         */

        private String id;
        private String name;
        private String trustAccount;
        private String password;
        private String email;
        private String phone;
        private String paypassword;
        private String count;
        private String registerIp;
        private String registerTime;
        private String registerDate;
        private String lastloginIp;
        private String lastlogStringime;
        private String lastloginDate;
        private String registerType;
        private String role;
        private String isRealname;
        private String isEmail;
        private String isPhone;
        private String isVideo;
        private String isId5;
        private String isVip;
        private String isAuto;
        private String pwdAttach;
        private String status;
        private String creditPoString;
        private String lockTime;
        private String group;
        private String groupStatus;
        private String vipStartTime;
        private String vipEndTime;
        private String vipCategoryId;
        private String vipCategoryInd;
        private String amount;
        private String channelSource;
        private String channelId;
        private String provience;
        private String ranking;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTrustAccount() {
            return trustAccount;
        }

        public void setTrustAccount(String trustAccount) {
            this.trustAccount = trustAccount;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(String paypassword) {
            this.paypassword = paypassword;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getLastloginIp() {
            return lastloginIp;
        }

        public void setLastloginIp(String lastloginIp) {
            this.lastloginIp = lastloginIp;
        }

        public String getLastlogStringime() {
            return lastlogStringime;
        }

        public void setLastlogStringime(String lastlogStringime) {
            this.lastlogStringime = lastlogStringime;
        }

        public String getLastloginDate() {
            return lastloginDate;
        }

        public void setLastloginDate(String lastloginDate) {
            this.lastloginDate = lastloginDate;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getIsRealname() {
            return isRealname;
        }

        public void setIsRealname(String isRealname) {
            this.isRealname = isRealname;
        }

        public String getIsEmail() {
            return isEmail;
        }

        public void setIsEmail(String isEmail) {
            this.isEmail = isEmail;
        }

        public String getIsPhone() {
            return isPhone;
        }

        public void setIsPhone(String isPhone) {
            this.isPhone = isPhone;
        }

        public String getIsVideo() {
            return isVideo;
        }

        public void setIsVideo(String isVideo) {
            this.isVideo = isVideo;
        }

        public String getIsId5() {
            return isId5;
        }

        public void setIsId5(String isId5) {
            this.isId5 = isId5;
        }

        public String getIsVip() {
            return isVip;
        }

        public void setIsVip(String isVip) {
            this.isVip = isVip;
        }

        public String getIsAuto() {
            return isAuto;
        }

        public void setIsAuto(String isAuto) {
            this.isAuto = isAuto;
        }

        public String getPwdAttach() {
            return pwdAttach;
        }

        public void setPwdAttach(String pwdAttach) {
            this.pwdAttach = pwdAttach;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreditPoString() {
            return creditPoString;
        }

        public void setCreditPoString(String creditPoString) {
            this.creditPoString = creditPoString;
        }

        public String getLockTime() {
            return lockTime;
        }

        public void setLockTime(String lockTime) {
            this.lockTime = lockTime;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getGroupStatus() {
            return groupStatus;
        }

        public void setGroupStatus(String groupStatus) {
            this.groupStatus = groupStatus;
        }

        public String getVipStartTime() {
            return vipStartTime;
        }

        public void setVipStartTime(String vipStartTime) {
            this.vipStartTime = vipStartTime;
        }

        public String getVipEndTime() {
            return vipEndTime;
        }

        public void setVipEndTime(String vipEndTime) {
            this.vipEndTime = vipEndTime;
        }

        public String getVipCategoryId() {
            return vipCategoryId;
        }

        public void setVipCategoryId(String vipCategoryId) {
            this.vipCategoryId = vipCategoryId;
        }

        public String getVipCategoryInd() {
            return vipCategoryInd;
        }

        public void setVipCategoryInd(String vipCategoryInd) {
            this.vipCategoryInd = vipCategoryInd;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getChannelSource() {
            return channelSource;
        }

        public void setChannelSource(String channelSource) {
            this.channelSource = channelSource;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getProvience() {
            return provience;
        }

        public void setProvience(String provience) {
            this.provience = provience;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }
    }
}
