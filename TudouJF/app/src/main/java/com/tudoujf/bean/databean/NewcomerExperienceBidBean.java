package com.tudoujf.bean.databean;

import com.google.gson.JsonElement;
import com.tudoujf.base.BaseBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            NewcomerExperienceBidBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：   新手体验标详情返回的bean
 * history：
 * * ====================================================================
 */
public class NewcomerExperienceBidBean implements BaseBean {

    /**
     * member : {"phone":15212345678,"register_time":1512365453,"count":5,"is_vip":1,"lastlogin_date":"十二月 4, 2017","password":"24fb10fc0f2d8e8df9758233c7ae4a6a","vip_start_time":1512366997,"balance_amount":7514.46,"id":12413,"amount":30,"is_video":-1,"group_status":-1,"register_date":"十二月 4, 2017","vip_end_time":1515045397,"can_tender_new":1,"name":"15212345678","role":1,"is_login":1,"vip_category_id":5,"is_auto":"-1","is_email":-1,"pwd_attach":"0y938vbts8","credit_point":2,"is_phone":1,"status":1,"self_loan":-1,"is_realname":1,"lastlogin_ip":2079040957,"register_ip":2079039658,"is_id5":-1,"provience":"山东省","vip_category_ind":"vip1","trust_account":"6000060008036912","lastlogin_time":1512368738,"register_type":1,"group":1}
     * loan_info : {"progress":"0.00","vouch_company_id":"0","tender_count":"17","left_amount":"-491096.00","use":10101,"tender_amount_max":0,"validate":30,"award_status":-1,"amount":"0.00","id":"160","additional_amount_max":0,"status_name":"已过期","overdue_time":"2017-12-02 09:01:27","name":"新手体验标","additional_status":-1,"is_promise":"-1","repay_type":"5","is_auto":1,"serialno":"201610080001","apr":"5.00","contents":"<p>sf<\/p>","status":"-2","category_id":"12","period_name":"5天","credited_amount":"491096.00","period":"5","member_id":"-1","hits":405,"category_name":"天��","is_company":-1,"agreementTitle":"天标协议","tender_amount_min":0,"password_status":false,"category_type":"4","member_name":"diyou","additional_apr":0,"wait_amount":"-491096.00","agreementNid":"day"}
     * iscompany : -1
     * tender_list : [{"amount":28888,"id":3700,"add_time":1475891543,"member_name":"13**"},{"amount":28888,"id":3704,"add_time":1475897910,"member_name":"13**"},{"amount":28888,"id":3706,"add_time":1475897989,"member_name":"13**"},{"amount":28888,"id":3708,"add_time":1475898143,"member_name":"13**"},{"amount":28888,"id":3710,"add_time":1475899407,"member_name":"17**"},{"amount":28888,"id":3714,"add_time":1475908939,"member_name":"13**"},{"amount":28888,"id":3719,"add_time":1475974432,"member_name":"18**"},{"amount":28888,"id":3720,"add_time":1475974910,"member_name":"18**"},{"amount":28888,"id":3722,"add_time":1475976419,"member_name":"18**"},{"amount":28888,"id":3725,"add_time":1475979784,"member_name":"18**"}]
     * repay_type : {"id":5,"contents":"按天计息，到期还本息","remark":"","name":"按天计息到期还本息"}
     * attaList : []
     */

    private MemberBean member;
    private LoanInfoBean loan_info;
    private String iscompany;
    private RepayTypeBean repay_type;
    private List<TenderListBean> tender_list;
    private JsonElement attaList;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public LoanInfoBean getLoan_info() {
        return loan_info;
    }

    public void setLoan_info(LoanInfoBean loan_info) {
        this.loan_info = loan_info;
    }

    public String getIscompany() {
        return iscompany;
    }

    public void setIscompany(String iscompany) {
        this.iscompany = iscompany;
    }

    public RepayTypeBean getRepay_type() {
        return repay_type;
    }

    public void setRepay_type(RepayTypeBean repay_type) {
        this.repay_type = repay_type;
    }

    public List<TenderListBean> getTender_list() {
        return tender_list;
    }

    public void setTender_list(List<TenderListBean> tender_list) {
        this.tender_list = tender_list;
    }

    public JsonElement getAttaList() {
        return attaList;
    }

    public void setAttaList(JsonElement attaList) {
        this.attaList = attaList;
    }

    public static class MemberBean {
        /**
         * phone : 15212345678
         * register_time : 1512365453
         * count : 5
         * is_vip : 1
         * lastlogin_date : 十二月 4, 2017
         * password : 24fb10fc0f2d8e8df9758233c7ae4a6a
         * vip_start_time : 1512366997
         * balance_amount : 7514.46
         * id : 12413
         * amount : 30
         * is_video : -1
         * group_status : -1
         * register_date : 十二月 4, 2017
         * vip_end_time : 1515045397
         * can_tender_new : 1
         * name : 15212345678
         * role : 1
         * is_login : 1
         * vip_category_id : 5
         * is_auto : -1
         * is_email : -1
         * pwd_attach : 0y938vbts8
         * credit_point : 2
         * is_phone : 1
         * status : 1
         * self_loan : -1
         * is_realname : 1
         * lastlogin_ip : 2079040957
         * register_ip : 2079039658
         * is_id5 : -1
         * provience : 山东省
         * vip_category_ind : vip1
         * trust_account : 6000060008036912
         * lastlogin_time : 1512368738
         * register_type : 1
         * group : 1
         */

        private String phone;
        private String register_time;
        private String count;
        private String is_vip;
        private String lastlogin_date;
        private String password;
        private String vip_start_time;
        private String balance_amount;
        private String id;
        private String amount;
        private String is_video;
        private String group_status;
        private String register_date;
        private String vip_end_time;
        private String can_tender_new;
        private String name;
        private String role;
        private String is_login;
        private String vip_category_id;
        private String is_auto;
        private String is_email;
        private String pwd_attach;
        private String credit_point;
        private String is_phone;
        private String status;
        private String self_loan;
        private String is_realname;
        private String lastlogin_ip;
        private String register_ip;
        private String is_id5;
        private String provience;
        private String vip_category_ind;
        private String trust_account;
        private String lastlogin_time;
        private String register_type;
        private String group;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getLastlogin_date() {
            return lastlogin_date;
        }

        public void setLastlogin_date(String lastlogin_date) {
            this.lastlogin_date = lastlogin_date;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVip_start_time() {
            return vip_start_time;
        }

        public void setVip_start_time(String vip_start_time) {
            this.vip_start_time = vip_start_time;
        }

        public String getBalance_amount() {
            return balance_amount;
        }

        public void setBalance_amount(String balance_amount) {
            this.balance_amount = balance_amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getIs_video() {
            return is_video;
        }

        public void setIs_video(String is_video) {
            this.is_video = is_video;
        }

        public String getGroup_status() {
            return group_status;
        }

        public void setGroup_status(String group_status) {
            this.group_status = group_status;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getVip_end_time() {
            return vip_end_time;
        }

        public void setVip_end_time(String vip_end_time) {
            this.vip_end_time = vip_end_time;
        }

        public String getCan_tender_new() {
            return can_tender_new;
        }

        public void setCan_tender_new(String can_tender_new) {
            this.can_tender_new = can_tender_new;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getIs_login() {
            return is_login;
        }

        public void setIs_login(String is_login) {
            this.is_login = is_login;
        }

        public String getVip_category_id() {
            return vip_category_id;
        }

        public void setVip_category_id(String vip_category_id) {
            this.vip_category_id = vip_category_id;
        }

        public String getIs_auto() {
            return is_auto;
        }

        public void setIs_auto(String is_auto) {
            this.is_auto = is_auto;
        }

        public String getIs_email() {
            return is_email;
        }

        public void setIs_email(String is_email) {
            this.is_email = is_email;
        }

        public String getPwd_attach() {
            return pwd_attach;
        }

        public void setPwd_attach(String pwd_attach) {
            this.pwd_attach = pwd_attach;
        }

        public String getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(String credit_point) {
            this.credit_point = credit_point;
        }

        public String getIs_phone() {
            return is_phone;
        }

        public void setIs_phone(String is_phone) {
            this.is_phone = is_phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSelf_loan() {
            return self_loan;
        }

        public void setSelf_loan(String self_loan) {
            this.self_loan = self_loan;
        }

        public String getIs_realname() {
            return is_realname;
        }

        public void setIs_realname(String is_realname) {
            this.is_realname = is_realname;
        }

        public String getLastlogin_ip() {
            return lastlogin_ip;
        }

        public void setLastlogin_ip(String lastlogin_ip) {
            this.lastlogin_ip = lastlogin_ip;
        }

        public String getRegister_ip() {
            return register_ip;
        }

        public void setRegister_ip(String register_ip) {
            this.register_ip = register_ip;
        }

        public String getIs_id5() {
            return is_id5;
        }

        public void setIs_id5(String is_id5) {
            this.is_id5 = is_id5;
        }

        public String getProvience() {
            return provience;
        }

        public void setProvience(String provience) {
            this.provience = provience;
        }

        public String getVip_category_ind() {
            return vip_category_ind;
        }

        public void setVip_category_ind(String vip_category_ind) {
            this.vip_category_ind = vip_category_ind;
        }

        public String getTrust_account() {
            return trust_account;
        }

        public void setTrust_account(String trust_account) {
            this.trust_account = trust_account;
        }

        public String getLastlogin_time() {
            return lastlogin_time;
        }

        public void setLastlogin_time(String lastlogin_time) {
            this.lastlogin_time = lastlogin_time;
        }

        public String getRegister_type() {
            return register_type;
        }

        public void setRegister_type(String register_type) {
            this.register_type = register_type;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }
    }

    public static class LoanInfoBean {
        /**
         * progress : 0.00
         * vouch_company_id : 0
         * tender_count : 17
         * left_amount : -491096.00
         * use : 10101
         * tender_amount_max : 0
         * validate : 30
         * award_status : -1
         * amount : 0.00
         * id : 160
         * additional_amount_max : 0
         * status_name : 已过期
         * overdue_time : 2017-12-02 09:01:27
         * name : 新手体验标
         * additional_status : -1
         * is_promise : -1
         * repay_type : 5
         * is_auto : 1
         * serialno : 201610080001
         * apr : 5.00
         * contents : <p>sf</p>
         * status : -2
         * category_id : 12
         * period_name : 5天
         * credited_amount : 491096.00
         * period : 5
         * member_id : -1
         * hits : 405
         * category_name : 天��
         * is_company : -1
         * agreementTitle : 天标协议
         * tender_amount_min : 0
         * password_status : false
         * category_type : 4
         * member_name : diyou
         * additional_apr : 0
         * wait_amount : -491096.00
         * agreementNid : day
         */

        private String progress;
        private String vouch_company_id;
        private String tender_count;
        private String left_amount;
        private String use;
        private String tender_amount_max;
        private String validate;
        private String award_status;
        private String amount;
        private String id;
        private String additional_amount_max;
        private String status_name;
        private String overdue_time;
        private String name;
        private String additional_status;
        private String is_promise;
        private String repay_type;
        private String is_auto;
        private String serialno;
        private String apr;
        private String contents;
        private String status;
        private String category_id;
        private String period_name;
        private String credited_amount;
        private String period;
        private String member_id;
        private String hits;
        private String category_name;
        private String is_company;
        private String agreementTitle;
        private String tender_amount_min;
        private String password_status;
        private String category_type;
        private String member_name;
        private String additional_apr;
        private String wait_amount;
        private String agreementNid;

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getVouch_company_id() {
            return vouch_company_id;
        }

        public void setVouch_company_id(String vouch_company_id) {
            this.vouch_company_id = vouch_company_id;
        }

        public String getTender_count() {
            return tender_count;
        }

        public void setTender_count(String tender_count) {
            this.tender_count = tender_count;
        }

        public String getLeft_amount() {
            return left_amount;
        }

        public void setLeft_amount(String left_amount) {
            this.left_amount = left_amount;
        }

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        public String getTender_amount_max() {
            return tender_amount_max;
        }

        public void setTender_amount_max(String tender_amount_max) {
            this.tender_amount_max = tender_amount_max;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public String getAward_status() {
            return award_status;
        }

        public void setAward_status(String award_status) {
            this.award_status = award_status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdditional_amount_max() {
            return additional_amount_max;
        }

        public void setAdditional_amount_max(String additional_amount_max) {
            this.additional_amount_max = additional_amount_max;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getOverdue_time() {
            return overdue_time;
        }

        public void setOverdue_time(String overdue_time) {
            this.overdue_time = overdue_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdditional_status() {
            return additional_status;
        }

        public void setAdditional_status(String additional_status) {
            this.additional_status = additional_status;
        }

        public String getIs_promise() {
            return is_promise;
        }

        public void setIs_promise(String is_promise) {
            this.is_promise = is_promise;
        }

        public String getRepay_type() {
            return repay_type;
        }

        public void setRepay_type(String repay_type) {
            this.repay_type = repay_type;
        }

        public String getIs_auto() {
            return is_auto;
        }

        public void setIs_auto(String is_auto) {
            this.is_auto = is_auto;
        }

        public String getSerialno() {
            return serialno;
        }

        public void setSerialno(String serialno) {
            this.serialno = serialno;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getPeriod_name() {
            return period_name;
        }

        public void setPeriod_name(String period_name) {
            this.period_name = period_name;
        }

        public String getCredited_amount() {
            return credited_amount;
        }

        public void setCredited_amount(String credited_amount) {
            this.credited_amount = credited_amount;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getIs_company() {
            return is_company;
        }

        public void setIs_company(String is_company) {
            this.is_company = is_company;
        }

        public String getAgreementTitle() {
            return agreementTitle;
        }

        public void setAgreementTitle(String agreementTitle) {
            this.agreementTitle = agreementTitle;
        }

        public String getTender_amount_min() {
            return tender_amount_min;
        }

        public void setTender_amount_min(String tender_amount_min) {
            this.tender_amount_min = tender_amount_min;
        }

        public String isPassword_status() {
            return password_status;
        }

        public void setPassword_status(String password_status) {
            this.password_status = password_status;
        }

        public String getCategory_type() {
            return category_type;
        }

        public void setCategory_type(String category_type) {
            this.category_type = category_type;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getAdditional_apr() {
            return additional_apr;
        }

        public void setAdditional_apr(String additional_apr) {
            this.additional_apr = additional_apr;
        }

        public String getWait_amount() {
            return wait_amount;
        }

        public void setWait_amount(String wait_amount) {
            this.wait_amount = wait_amount;
        }

        public String getAgreementNid() {
            return agreementNid;
        }

        public void setAgreementNid(String agreementNid) {
            this.agreementNid = agreementNid;
        }
    }

    public static class RepayTypeBean {
        /**
         * id : 5
         * contents : 按天计息，到期还本息
         * remark : 
         * name : 按天计息到期还本息
         */

        private String id;
        private String contents;
        private String remark;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TenderListBean {
        /**
         * amount : 28888
         * id : 3700
         * add_time : 1475891543
         * member_name : 13**
         */

        private String amount;
        private String id;
        private String add_time;
        private String member_name;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }
    }
}








//
//public class NewcomerExperienceBidBean implements BaseBean {
//
//    /**
//     * loan_info : {"progress":20.62,"vouch_company_id":0,"hidden_status":1,"comment_status":-1,"password":"123456789","verify_time":1474252306,"tender_amount_max":100000,"validate":15,"attachment_ids":"","award_status":-1,"amount":970000,"verify_admin_name":"diyou","additional_amount_max":0,"ind":"8ea19d1309ed27ffed7139f8a7cd9337","company_name":"","overdue_time":1475548306,"loan_start_time":1474252292,"is_promise":-1,"additional_status":-1,"fail_award_status":-1,"is_auto":1,"status":6,"contents":"<p>542444<\/p>","reverify_admin_name":"diyou","category_id":10,"add_date":"九月 19, 2016","credited_amount":200000,"period":12,"hits":3,"tender_amount_min":10000,"additional_apr":0,"loan_id":153,"verify_admin_id":1,"tender_count":0,"amount_category_id":3,"reverify_time":1474256719,"use":10108,"deposit_certificate":-1,"sort_index":1,"id":153,"name":"123456","repay_type":3,"reverify_ip":467619322,"serialno":201609190001,"comment_count":0,"apr":15,"verify_ip":467619322,"member_id":12231,"add_ip":467619322,"freeze_amount_proportion":0,"is_company":-1,"reverify_remark":"dfg","reverify_admin_id":1,"category_type":"1","add_time":1474252255,"part_status":-1,"member_name":"ljq001"}
//     * member_loan_info : {"loan_success_amount":614950,"repay_success_count":40,"loan_success_count":59,"late_repay":8,"wait_repay_total":546530.56,"loan_count":104,"late_repay_max":8,"late_amount":3296.03}
//     * tender_list : {"page":1,"epage":20,"total_items":2,"total_pages":1,"items":[{"id":3670,"amount":100000,"status":"回款中","Stringerest_earning":15000,"Stringerest_earned":0,"add_time":1474252368,"member_name":"dxg19950327"},{"id":3671,"amount":100000,"status":"回款中","Stringerest_earning":15000,"Stringerest_earned":0,"add_time":1474256499,"member_name":"hghg111"}],"params":null}
//     * repay_type : {"id":3,"contents":"到期还本还息","remark":"","status":1,"name":"到期本息"}
//     */
//
//    private LoanInfoBean loan_info;
//    private MemberLoanInfoBean member_loan_info;
//    private TenderListBean tender_list;
//    private RepayTypeBean repay_type;
//
//    public LoanInfoBean getLoan_info() {
//        return loan_info;
//    }
//
//    public void setLoan_info(LoanInfoBean loan_info) {
//        this.loan_info = loan_info;
//    }
//
//    public MemberLoanInfoBean getMember_loan_info() {
//        return member_loan_info;
//    }
//
//    public void setMember_loan_info(MemberLoanInfoBean member_loan_info) {
//        this.member_loan_info = member_loan_info;
//    }
//
//    public TenderListBean getTender_list() {
//        return tender_list;
//    }
//
//    public void setTender_list(TenderListBean tender_list) {
//        if (tender_list!=null){
//            this.tender_list = tender_list;
//        }else {
//            this.tender_list=null;
//        }
//    }
//
//    public RepayTypeBean getRepay_type() {
//        return repay_type;
//    }
//
//    public void setRepay_type(RepayTypeBean repay_type) {
//        this.repay_type = repay_type;
//    }
//
//    public static class LoanInfoBean {
//        /**
//         * progress : 20.62
//         * vouch_company_id : 0
//         * hidden_status : 1
//         * comment_status : -1
//         * password : 123456789
//         * verify_time : 1474252306
//         * tender_amount_max : 100000
//         * validate : 15
//         * attachment_ids :
//         * award_status : -1
//         * amount : 970000
//         * verify_admin_name : diyou
//         * additional_amount_max : 0
//         * ind : 8ea19d1309ed27ffed7139f8a7cd9337
//         * company_name :
//         * overdue_time : 1475548306
//         * loan_start_time : 1474252292
//         * is_promise : -1
//         * additional_status : -1
//         * fail_award_status : -1
//         * is_auto : 1
//         * status : 6
//         * contents : <p>542444</p>
//         * reverify_admin_name : diyou
//         * category_id : 10
//         * add_date : 九月 19, 2016
//         * credited_amount : 200000
//         * period : 12
//         * hits : 3
//         * tender_amount_min : 10000
//         * additional_apr : 0
//         * loan_id : 153
//         * verify_admin_id : 1
//         * tender_count : 0
//         * amount_category_id : 3
//         * reverify_time : 1474256719
//         * use : 10108
//         * deposit_certificate : -1
//         * sort_index : 1
//         * id : 153
//         * name : 123456
//         * repay_type : 3
//         * reverify_ip : 467619322
//         * serialno : 201609190001
//         * comment_count : 0
//         * apr : 15
//         * verify_ip : 467619322
//         * member_id : 12231
//         * add_ip : 467619322
//         * freeze_amount_proportion : 0
//         * is_company : -1
//         * reverify_remark : dfg
//         * reverify_admin_id : 1
//         * category_type : 1
//         * add_time : 1474252255
//         * part_status : -1
//         * member_name : ljq001
//         */
//
//        private String progress;
//        private String vouch_company_id;
//        private String hidden_status;
//        private String comment_status;
//        private String password;
//        private String verify_time;
//        private String tender_amount_max;
//        private String validate;
//        private String attachment_ids;
//        private String award_status;
//        private String amount;
//        private String verify_admin_name;
//        private String additional_amount_max;
//        private String ind;
//        private String company_name;
//        private String overdue_time;
//        private String loan_start_time;
//        private String is_promise;
//        private String additional_status;
//        private String fail_award_status;
//        private String is_auto;
//        private String status;
//        private String contents;
//        private String reverify_admin_name;
//        private String category_id;
//        private String add_date;
//        private String credited_amount;
//        private String period;
//        private String hits;
//        private String tender_amount_min;
//        private String additional_apr;
//        private String loan_id;
//        private String verify_admin_id;
//        private String tender_count;
//        private String amount_category_id;
//        private String reverify_time;
//        private String use;
//        private String deposit_certificate;
//        private String sort_index;
//        private String id;
//        private String name;
//        private String repay_type;
//        private String reverify_ip;
//        private String serialno;
//        private String comment_count;
//        private String apr;
//        private String verify_ip;
//        private String member_id;
//        private String add_ip;
//        private String freeze_amount_proportion;
//        private String is_company;
//        private String reverify_remark;
//        private String reverify_admin_id;
//        private String category_type;
//        private String add_time;
//        private String part_status;
//        private String member_name;
//
//        public String getProgress() {
//            return progress;
//        }
//
//        public void setProgress(String progress) {
//            this.progress = progress;
//        }
//
//        public String getVouch_company_id() {
//            return vouch_company_id;
//        }
//
//        public void setVouch_company_id(String vouch_company_id) {
//            this.vouch_company_id = vouch_company_id;
//        }
//
//        public String getHidden_status() {
//            return hidden_status;
//        }
//
//        public void setHidden_status(String hidden_status) {
//            this.hidden_status = hidden_status;
//        }
//
//        public String getComment_status() {
//            return comment_status;
//        }
//
//        public void setComment_status(String comment_status) {
//            this.comment_status = comment_status;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public String getVerify_time() {
//            return verify_time;
//        }
//
//        public void setVerify_time(String verify_time) {
//            this.verify_time = verify_time;
//        }
//
//        public String getTender_amount_max() {
//            return tender_amount_max;
//        }
//
//        public void setTender_amount_max(String tender_amount_max) {
//            this.tender_amount_max = tender_amount_max;
//        }
//
//        public String getValidate() {
//            return validate;
//        }
//
//        public void setValidate(String validate) {
//            this.validate = validate;
//        }
//
//        public String getAttachment_ids() {
//            return attachment_ids;
//        }
//
//        public void setAttachment_ids(String attachment_ids) {
//            this.attachment_ids = attachment_ids;
//        }
//
//        public String getAward_status() {
//            return award_status;
//        }
//
//        public void setAward_status(String award_status) {
//            this.award_status = award_status;
//        }
//
//        public String getAmount() {
//            return amount;
//        }
//
//        public void setAmount(String amount) {
//            this.amount = amount;
//        }
//
//        public String getVerify_admin_name() {
//            return verify_admin_name;
//        }
//
//        public void setVerify_admin_name(String verify_admin_name) {
//            this.verify_admin_name = verify_admin_name;
//        }
//
//        public String getAdditional_amount_max() {
//            return additional_amount_max;
//        }
//
//        public void setAdditional_amount_max(String additional_amount_max) {
//            this.additional_amount_max = additional_amount_max;
//        }
//
//        public String getInd() {
//            return ind;
//        }
//
//        public void setInd(String ind) {
//            this.ind = ind;
//        }
//
//        public String getCompany_name() {
//            return company_name;
//        }
//
//        public void setCompany_name(String company_name) {
//            this.company_name = company_name;
//        }
//
//        public String getOverdue_time() {
//            return overdue_time;
//        }
//
//        public void setOverdue_time(String overdue_time) {
//            this.overdue_time = overdue_time;
//        }
//
//        public String getLoan_start_time() {
//            return loan_start_time;
//        }
//
//        public void setLoan_start_time(String loan_start_time) {
//            this.loan_start_time = loan_start_time;
//        }
//
//        public String getIs_promise() {
//            return is_promise;
//        }
//
//        public void setIs_promise(String is_promise) {
//            this.is_promise = is_promise;
//        }
//
//        public String getAdditional_status() {
//            return additional_status;
//        }
//
//        public void setAdditional_status(String additional_status) {
//            this.additional_status = additional_status;
//        }
//
//        public String getFail_award_status() {
//            return fail_award_status;
//        }
//
//        public void setFail_award_status(String fail_award_status) {
//            this.fail_award_status = fail_award_status;
//        }
//
//        public String getIs_auto() {
//            return is_auto;
//        }
//
//        public void setIs_auto(String is_auto) {
//            this.is_auto = is_auto;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getContents() {
//            return contents;
//        }
//
//        public void setContents(String contents) {
//            this.contents = contents;
//        }
//
//        public String getReverify_admin_name() {
//            return reverify_admin_name;
//        }
//
//        public void setReverify_admin_name(String reverify_admin_name) {
//            this.reverify_admin_name = reverify_admin_name;
//        }
//
//        public String getCategory_id() {
//            return category_id;
//        }
//
//        public void setCategory_id(String category_id) {
//            this.category_id = category_id;
//        }
//
//        public String getAdd_date() {
//            return add_date;
//        }
//
//        public void setAdd_date(String add_date) {
//            this.add_date = add_date;
//        }
//
//        public String getCredited_amount() {
//            return credited_amount;
//        }
//
//        public void setCredited_amount(String credited_amount) {
//            this.credited_amount = credited_amount;
//        }
//
//        public String getPeriod() {
//            return period;
//        }
//
//        public void setPeriod(String period) {
//            this.period = period;
//        }
//
//        public String getHits() {
//            return hits;
//        }
//
//        public void setHits(String hits) {
//            this.hits = hits;
//        }
//
//        public String getTender_amount_min() {
//            return tender_amount_min;
//        }
//
//        public void setTender_amount_min(String tender_amount_min) {
//            this.tender_amount_min = tender_amount_min;
//        }
//
//        public String getAdditional_apr() {
//            return additional_apr;
//        }
//
//        public void setAdditional_apr(String additional_apr) {
//            this.additional_apr = additional_apr;
//        }
//
//        public String getLoan_id() {
//            return loan_id;
//        }
//
//        public void setLoan_id(String loan_id) {
//            this.loan_id = loan_id;
//        }
//
//        public String getVerify_admin_id() {
//            return verify_admin_id;
//        }
//
//        public void setVerify_admin_id(String verify_admin_id) {
//            this.verify_admin_id = verify_admin_id;
//        }
//
//        public String getTender_count() {
//            return tender_count;
//        }
//
//        public void setTender_count(String tender_count) {
//            this.tender_count = tender_count;
//        }
//
//        public String getAmount_category_id() {
//            return amount_category_id;
//        }
//
//        public void setAmount_category_id(String amount_category_id) {
//            this.amount_category_id = amount_category_id;
//        }
//
//        public String getReverify_time() {
//            return reverify_time;
//        }
//
//        public void setReverify_time(String reverify_time) {
//            this.reverify_time = reverify_time;
//        }
//
//        public String getUse() {
//            return use;
//        }
//
//        public void setUse(String use) {
//            this.use = use;
//        }
//
//        public String getDeposit_certificate() {
//            return deposit_certificate;
//        }
//
//        public void setDeposit_certificate(String deposit_certificate) {
//            this.deposit_certificate = deposit_certificate;
//        }
//
//        public String getSort_index() {
//            return sort_index;
//        }
//
//        public void setSort_index(String sort_index) {
//            this.sort_index = sort_index;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getRepay_type() {
//            return repay_type;
//        }
//
//        public void setRepay_type(String repay_type) {
//            this.repay_type = repay_type;
//        }
//
//        public String getReverify_ip() {
//            return reverify_ip;
//        }
//
//        public void setReverify_ip(String reverify_ip) {
//            this.reverify_ip = reverify_ip;
//        }
//
//        public String getSerialno() {
//            return serialno;
//        }
//
//        public void setSerialno(String serialno) {
//            this.serialno = serialno;
//        }
//
//        public String getComment_count() {
//            return comment_count;
//        }
//
//        public void setComment_count(String comment_count) {
//            this.comment_count = comment_count;
//        }
//
//        public String getApr() {
//            return apr;
//        }
//
//        public void setApr(String apr) {
//            this.apr = apr;
//        }
//
//        public String getVerify_ip() {
//            return verify_ip;
//        }
//
//        public void setVerify_ip(String verify_ip) {
//            this.verify_ip = verify_ip;
//        }
//
//        public String getMember_id() {
//            return member_id;
//        }
//
//        public void setMember_id(String member_id) {
//            this.member_id = member_id;
//        }
//
//        public String getAdd_ip() {
//            return add_ip;
//        }
//
//        public void setAdd_ip(String add_ip) {
//            this.add_ip = add_ip;
//        }
//
//        public String getFreeze_amount_proportion() {
//            return freeze_amount_proportion;
//        }
//
//        public void setFreeze_amount_proportion(String freeze_amount_proportion) {
//            this.freeze_amount_proportion = freeze_amount_proportion;
//        }
//
//        public String getIs_company() {
//            return is_company;
//        }
//
//        public void setIs_company(String is_company) {
//            this.is_company = is_company;
//        }
//
//        public String getReverify_remark() {
//            return reverify_remark;
//        }
//
//        public void setReverify_remark(String reverify_remark) {
//            this.reverify_remark = reverify_remark;
//        }
//
//        public String getReverify_admin_id() {
//            return reverify_admin_id;
//        }
//
//        public void setReverify_admin_id(String reverify_admin_id) {
//            this.reverify_admin_id = reverify_admin_id;
//        }
//
//        public String getCategory_type() {
//            return category_type;
//        }
//
//        public void setCategory_type(String category_type) {
//            this.category_type = category_type;
//        }
//
//        public String getAdd_time() {
//            return add_time;
//        }
//
//        public void setAdd_time(String add_time) {
//            this.add_time = add_time;
//        }
//
//        public String getPart_status() {
//            return part_status;
//        }
//
//        public void setPart_status(String part_status) {
//            this.part_status = part_status;
//        }
//
//        public String getMember_name() {
//            return member_name;
//        }
//
//        public void setMember_name(String member_name) {
//            this.member_name = member_name;
//        }
//    }
//
//    public static class MemberLoanInfoBean {
//        /**
//         * loan_success_amount : 614950
//         * repay_success_count : 40
//         * loan_success_count : 59
//         * late_repay : 8
//         * wait_repay_total : 546530.56
//         * loan_count : 104
//         * late_repay_max : 8
//         * late_amount : 3296.03
//         */
//
//        private String loan_success_amount;
//        private String repay_success_count;
//        private String loan_success_count;
//        private String late_repay;
//        private String wait_repay_total;
//        private String loan_count;
//        private String late_repay_max;
//        private String late_amount;
//
//        public String getLoan_success_amount() {
//            return loan_success_amount;
//        }
//
//        public void setLoan_success_amount(String loan_success_amount) {
//            this.loan_success_amount = loan_success_amount;
//        }
//
//        public String getRepay_success_count() {
//            return repay_success_count;
//        }
//
//        public void setRepay_success_count(String repay_success_count) {
//            this.repay_success_count = repay_success_count;
//        }
//
//        public String getLoan_success_count() {
//            return loan_success_count;
//        }
//
//        public void setLoan_success_count(String loan_success_count) {
//            this.loan_success_count = loan_success_count;
//        }
//
//        public String getLate_repay() {
//            return late_repay;
//        }
//
//        public void setLate_repay(String late_repay) {
//            this.late_repay = late_repay;
//        }
//
//        public String getWait_repay_total() {
//            return wait_repay_total;
//        }
//
//        public void setWait_repay_total(String wait_repay_total) {
//            this.wait_repay_total = wait_repay_total;
//        }
//
//        public String getLoan_count() {
//            return loan_count;
//        }
//
//        public void setLoan_count(String loan_count) {
//            this.loan_count = loan_count;
//        }
//
//        public String getLate_repay_max() {
//            return late_repay_max;
//        }
//
//        public void setLate_repay_max(String late_repay_max) {
//            this.late_repay_max = late_repay_max;
//        }
//
//        public String getLate_amount() {
//            return late_amount;
//        }
//
//        public void setLate_amount(String late_amount) {
//            this.late_amount = late_amount;
//        }
//    }
//
//    public static class TenderListBean {
//        /**
//         * page : 1
//         * epage : 20
//         * total_items : 2
//         * total_pages : 1
//         * items : [{"id":3670,"amount":100000,"status":"回款中","Stringerest_earning":15000,"Stringerest_earned":0,"add_time":1474252368,"member_name":"dxg19950327"},{"id":3671,"amount":100000,"status":"回款中","Stringerest_earning":15000,"Stringerest_earned":0,"add_time":1474256499,"member_name":"hghg111"}]
//         * params : null
//         */
//
//        private String page;
//        private String epage;
//        private String total_items;
//        private String total_pages;
//        private JsonElement params;
//        private List<ItemsBean> items;
//
//        public String getPage() {
//            return page;
//        }
//
//        public void setPage(String page) {
//            this.page = page;
//        }
//
//        public String getEpage() {
//            return epage;
//        }
//
//        public void setEpage(String epage) {
//            this.epage = epage;
//        }
//
//        public String getTotal_items() {
//            return total_items;
//        }
//
//        public void setTotal_items(String total_items) {
//            this.total_items = total_items;
//        }
//
//        public String getTotal_pages() {
//            return total_pages;
//        }
//
//        public void setTotal_pages(String total_pages) {
//            this.total_pages = total_pages;
//        }
//
//        public JsonElement getParams() {
//            return params;
//        }
//
//        public void setParams(JsonElement params) {
//            this.params = params;
//        }
//
//        public List<ItemsBean> getItems() {
//            return items;
//        }
//
//        public void setItems(List<ItemsBean> items) {
//            this.items = items;
//        }
//
//        public static class ItemsBean {
//            /**
//             * id : 3670
//             * amount : 100000
//             * status : 回款中
//             * Stringerest_earning : 15000
//             * Stringerest_earned : 0
//             * add_time : 1474252368
//             * member_name : dxg19950327
//             */
//
//            private String id;
//            private String amount;
//            private String status;
//            private String Stringerest_earning;
//            private String Stringerest_earned;
//            private String add_time;
//            private String member_name;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getAmount() {
//                return amount;
//            }
//
//            public void setAmount(String amount) {
//                this.amount = amount;
//            }
//
//            public String getStatus() {
//                return status;
//            }
//
//            public void setStatus(String status) {
//                this.status = status;
//            }
//
//            public String getStringerest_earning() {
//                return Stringerest_earning;
//            }
//
//            public void setStringerest_earning(String Stringerest_earning) {
//                this.Stringerest_earning = Stringerest_earning;
//            }
//
//            public String getStringerest_earned() {
//                return Stringerest_earned;
//            }
//
//            public void setStringerest_earned(String Stringerest_earned) {
//                this.Stringerest_earned = Stringerest_earned;
//            }
//
//            public String getAdd_time() {
//                return add_time;
//            }
//
//            public void setAdd_time(String add_time) {
//                this.add_time = add_time;
//            }
//
//            public String getMember_name() {
//                return member_name;
//            }
//
//            public void setMember_name(String member_name) {
//                this.member_name = member_name;
//            }
//        }
//    }
//
//    public static class RepayTypeBean {
//        /**
//         * id : 3
//         * contents : 到期还本还息
//         * remark :
//         * status : 1
//         * name : 到期本息
//         */
//
//        private String id;
//        private String contents;
//        private String remark;
//        private String status;
//        private String name;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getContents() {
//            return contents;
//        }
//
//        public void setContents(String contents) {
//            this.contents = contents;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//}
