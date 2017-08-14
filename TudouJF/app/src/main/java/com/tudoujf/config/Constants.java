package com.tudoujf.config;

import com.example.encryptionpackages.CreateCode;

/**
 * * ====================================================================
 * name:            Constants
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：    配置文件
 * history：
 * * ====================================================================
 */

public class Constants {
    public static  final String  URL="http://m.test.tudoujf.com";
    /**
     * 网站服务协议
     */
    public static  final  String AGREEMENT_WEBSITE = URL + "/wap/index/appAgreement";
    /**
     * 债权转让协议
     */
    public static  final  String AGREEMENT_CREDITOR_TRANSFER = URL + "/wap/transfer/appTransferAgreement#?id=";//后面加id
    /**
     * 借款协议
     */
    public static  final  String AGREEMENT_BORROW = URL + "/wap/loan/appLoanAgreement#?id=";//后面加id
    /**
     * 用户登录验证
     */
    public static  final  String LOGIN = URL + "/phone/member/login";

    /**
     * 首页信息
     */
    public static  final  String HOME = URL + "/phone/index/index";


    /**
     * 检测手机号码、邮箱、用户名是否存在
     */
    public static  final  String CHECK = URL + "/phone/member/checkExit";

    /**
     * 用户注销
     */
    public static  final  String LOGINOUT = URL + "/phone/member/loginOut";

    /**
     * 获取用户信息
     */
    public static  final  String INFO = URL + "/phone/member/info";

    /**
     * 获取用资金信息
     */
    public static  final  String ACCOUNT = URL + "/phone/member/account";

    /**
     * 获取用户资金记录
     */
    public static  final  String ACCOUNTLOG = URL + "/phone/member/accountLog";

    /**
     * 获取用户投资列表
     */
    public static  final  String TENDERLIST = URL + "/phone/member/tenderList";

    /**
     * 获取用户收款列表
     */
    public static  final  String COLLECTIONLIST = URL
            + "/phone/member/collectionList";

    /**
     * 获取用户借款列表
     */
    public static  final  String LOANLIST = URL + "/phone/member/loanList";

    /**
     * 获取用户还款明细列表
     */
    public static  final  String REPAYLIST = URL + "/phone/member/repayList";
    /**
     * 获取用户还款操作
     */
    public static  final  String REPAY = URL + "/phone/member/repay";

    /**
     * 获取首页需要相关信息
     */
    public static  final  String INDEX = URL + "/phone/index/index";

    /**
     * 投资列表
     */
    public static  final  String TENDER_INDEX = URL + "/phone/tender/index";
    /**
     * 获取认证信息
     */
    public static  final  String APPROVE = URL + "/phone/member/getApprove";
    /**
     * 立即投资
     */
    public static  final  String TENDER = URL + "/phone/tender/tender";

    /**
     * 获取某个标投资记录
     */
    public static  final  String RECORDLIST = URL + "/phone/tender/recordList";

    /**
     * 债权转让列表
     */
    public static  final  String TRANSFER_INDEX = URL + "/phone/transfer/index";

    /**
     * 债权转让详情
     */
    public static  final  String CREDITER_TRANSFER_DETAILS = URL + "/phone/transfer/transferInfo";
    /**
     * 债权购买详情
     */
    public static  final  String CREDITER_BUY_DETAILS = URL + "/phone/transfer/myTransferBuyInfo";
    /**
     * 购买债权
     */
    public static  final  String BUY = URL + "/phone/transfer/buy";

    /**
     * 借标详情
     */
    public static  final  String DETAIL = URL + "/phone/loan/detail";

    /**
     * 获取版本信息
     */
    public static  final  String VERSION = URL + "/phone/extra/version";
    /**
     * 发送注册短信验证码
     */
    public static  final  String REG_SMS = URL + "/phone/public/sendSms";
    /**
     * 提交手机认证
     */
    public static  final  String APPROVE_PHONE = URL
            + "/phone/approve/approvePhone";
    /**
     * 注册
     */
    public static  final  String REG = URL + "/phone/public/reg";
    /**
     * 常见问题
     */
    public static  final  String ARTICLESLIST = URL
            + "/phone/articles/articlesList";
    /**
     * 公告栏目
     */
    public static  final  String NOTICELIST = URL
            + "/phone/articles/noticeList";

    /**
     * 个人中心首页
     */
    public static  final  String PERSONAL_CENTER_MAIN = URL
            + "/phone/member/memberCenter";
    /**
     * 用户资金详情
     */
    public static  final  String PERSONAL_FUND_DETAIL = URL
            + "/phone/account/memberAccount";
    /**
     * 邮箱检测
     */
    public static  final  String CHECK_EMAIL = URL + "/phone/public/checkEmail";
    /**
     * 手机检测
     */
    public static  final  String CHECK_PHONE = URL + "/phone/public/checkPhone";
    /**
     * 发送邮箱验证码
     */
    public static  final  String EMAIL_SEND_CODE = URL
            + "/phone/approve/sendApproveEmail";
    /**
     * 找回密码发送邮件
     */
    public static  final  String SEND_RECOVER_EMAIL = URL
            + "/phone/member/sendRecoveryEmail";
    /**
     * 邮箱验证
     */
    public static  final  String EMAIL_BIND = URL
            + "/phone/approve/approveEmail";
    /**
     * 我要投资页面
     */
    public static  final  String INVESTDATA = URL + "/phone/tender/investData";
    /**
     * 投资收益
     */
    public static  final  String INVESTINTEREST = URL
            + "/phone/tender/investInterest";
    /**
     * 设置修改登录密码
     */
    public static  final  String EDITPWD = URL + "/phone/member/editPwd";

    /**
     * 判断用户是否设置支付密码
     */
    public static  final  String GETPAYPWD = URL + "/phone/member/getPaypwd";

    /**
     * 设置找回密码新密码
     */
    public static  final  String EIDT_RECOBER_PWD = URL
            + "/phone/member/editRecoverPwd";

    /**
     * 设置支付密码
     */
    public static  final  String EDITPAYPWD = URL + "/phone/member/editPaypwd";
    /**
     * 立即投资
     */
    public static  final  String INVESTMENT_TENDER = URL
            + "/phone/tender/tender";
    /**
     * 我的投资
     */
    public static  final  String MY_INVESTMENT = URL
            + "/phone/tender/myTenderList";
    /**
     * 我的投资详情
     */
    public static  final  String MY_INVESTMENT_DETAIL = URL
            + "/phone/tender/tenderInfo";
    /**
     * 用户交易记录
     */
    public static  final  String TRADERECORD = URL
            + "/phone/account/accountLog";

    /**
     * 充值记录
     */
    public static  final  String RECHARGERECORD = URL
            + "/phone/recharge/rechargelog";

    /**
     * 站内信
     */
    public static  final  String INSIDELETTER = URL
            + "/phone/member/messageLog";
    /**
     * 站内信更新阅读状态
     */
    public static  final  String INSIDELETTER_STATUS = URL
            + "/phone/member/messageView";
    /**
     * 修改手机绑定
     */
    public static  final  String CHANGE_PHONE = URL
            + "/phone/approve/resetPhone";
    /**
     * 修改邮箱绑定
     */
    public static  final  String CHANGE_EMAIL = URL
            + "/phone/approve/resetEmail";
    /**
     * 用户信息
     */
    public static  final  String USER_INFO = URL + "/phone/member/userInfo";
    /**
     * 版本更新
     */
    public static  final  String UPDATE_VERSION = URL
            + "/phone/member/updateVersion";
    /**
     * 债权转让
     */
    public static  final  String CRETIOR_INDEX = URL + "/phone/transfer/index";
    /**
     * 债权转让购买详情
     */
    public static  final  String TransferBuyInfo = URL
            + "/phone/transfer/TransferBuyInfo";
    /**
     * 购买债权详情
     */
    public static  final  String CRETIOR_DETAILS = URL
            + "/phone/transfer/TransferBuyInfo";
    /**
     * 我的债权转让列表
     */
    public static  final  String CREDITER_LIST = URL
            + "/phone/transfer/myTransferList";
    /**
     * 实名认证
     */
    public static  final  String REAL_NAME_AUTH = URL
            + "/phone/approve/approveRealName";
    /**
     * 债权转让
     */
    public static  final  String CREDITER_DETAILS = URL
            + "/phone/transfer/transferInfo";
    /**
     * 绑定银行卡
     */
    public static  final  String BIND_BANK_CARD = URL + "/phone/bank/addBank";
    /**
     * 更换银行卡
     */
    public static  final  String CHANGE_BANK_CARD = URL
            + "/phone/bank/editBank";
    /**
     * 获取银行卡信息
     */
    public static  final  String BANK_CARD_INFO = URL + "/phone/bank/index";
    /**
     * 获取银行卡头信息
     */
    public static  final  String BANK_CARD_NID = URL + "/phone/bank/getBankNid";
    /**
     * 获取银行列表
     */
    public static  final  String BANK_LIST_INFO = URL + "/phone/bank/bankList";
    /**
     * 获取省市信息
     */
    public static  final  String PROVINCE_CITY_INFO = URL
            + "/phone/common/getProvinceCity";
    /**
     * 立即转让
     */
    public static  final  String IMMEDIATE_TRANSFER = URL
            + "/phone/transfer/transferSub";
    /**
     * 撤销转让
     */
    public static  final  String CANCEL_TRANSFER = URL
            + "/phone/transfer/cancel";
    /**
     * 获取取现手续费
     */
    public static  final  String WITHDRAW_FEE = URL
            + "/phone/member/getCashFee";
    /**
     * 提现
     */
    public static  final  String WITHDRAW = URL + "/phone/member/withDraw";
    /**
     * 提现记录
     */
    public static  final  String WITHDRAW_RECORD = URL
            + "/phone/member/withDrawLog";
    /**
     * 推广记录
     */
    public static  final  String PROMOTION_RECORD = URL
            + "/phone/spread/mySpreadLog";
    /**
     * 结算记录
     */
    public static  final  String SETTLEMENT_RECORD = URL
            + "/phone/spread/getSettleLog";
    /**
     * 立即结算
     */
    public static  final  String SETTLEMENT_NOW = URL
            + "/phone/spread/doAccount";
    /**
     * 我的推广
     */
    public static  final  String MY_PROMOTION = URL
            + "/phone/spread/mySpreadAll";
    /**
     * 判断是否是自己的借款标
     */
    public static  final  String IS_MY_LOAN = URL + "/phone/loan/isMyLoan";
    /**
     * 判断是否是自己的债权
     */
    public static  final  String IS_MY_TRANSFER = URL + "/phone/transfer/isMyTransfer";
    /**
     * 托管注册
     */
    public static  final  String TRUST_REGISTER = URL + "/phone/trust/register";
    /**
     * 是否注册托管
     */
    public static  final  String IS_TRUST_REGISTER = URL
            + "/phone/trust/isregister";
    /**
     * 获取充值方式
     */
    public static  final  String RECHARGE_TYPE = URL
            + "/phone/recharge/getList";
    /**
     * 获取充值手续费
     */
    public static  final  String RECHARGE_FEE = URL
            + "/phone/recharge/rechargeFee";
    /**
     * 易宝充值
     */
    public static  final  String YIBAO_RECHARGE = URL + "/phone/trust/recharge";
    /**
     * 易宝提现
     */
    public static  final  String YIBAO_WITHDRAW = URL + "/phone/trust/withdraw";
    /**
     * 易宝绑定银行卡
     */
    public static  final  String YIBAO_BIND_CARD = URL
            + "/phone/trust/bindBankCard";
    /**
     * 易宝解绑银行卡
     */
    public static  final  String YIBAO_UNBIND_CARD = URL
            + "/phone/trustDirect/unBindBankCard";
    /**
     * 易宝投资
     */
    public static  final  String YIBAO_INVEST = URL + "/phone/trust/tender";

    /**
     * 流转标易宝投资
     */
    public static  final  String YIBAO_FLOW_INVEST = URL + "/phone/trust/tenderRoam";
    /**
     *
     * 易宝债权购买JAVA
     */
    public static  final  String YIBAO_BUY_JAVA = URL + "/phone/trust/buyTransfer";
    /**
     * 我的支付账号
     */
    public static  final  String YIBAO_MY_ACCOUNT = URL
            + "/phone/trust/myTrust";

    /**
     * 上传头像
     */
    public static  final  String YIBAO_AVATAR_SUBMIT = URL
            + "/phone/member/avatarSubmit";
    /**
     * 获取协议的内容
     */
    public static  final  String XIE_YI_URL = URL
            + "/phone/loan/loanType";

    /**注册接口*/
    public static  final String  REGISTER=URL+"/phone/public/reg";

    /**
     * 计算器还款方式列表
     */
    public static  final  String GET_REPAY_TYPE_LIST=URL + "/phone/loan/getRepayTypeList";

    /**
     * 计算器计算结果
     */
    public static  final  String CALCULATOR_RESULT =URL + "/phone/common/calculator";


    public static  final  String STATUS_REGISTER_SUCCESS="register_status=success";//注册成功
    public static  final   String STATUS_REGISTER_FAIL="register_status=fail";//注册失败
    public static  final  String STATUS_CLOSE="/wap";

}
