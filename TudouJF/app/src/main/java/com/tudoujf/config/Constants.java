package com.tudoujf.config;

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
    /**
     * 固定URL
     */
    // TODO: 2018/1/17 正式环境与测试环境切换
//    public static final String URL = "http://m.test.tudoujf.com";
    public static final String URL = "http://mob.tudoujf.com";

    /**
     * 活动专区接口
     */
    public static final String HUO_DONG_ZHUAN_QU = URL + "/phone/activity/list";
    /**
     * 签到接口
     */
    public static final String SIGN_IN = URL + "/phone/sign/index";
    /**
     * 上传签到接口
     */
    public static final String SIGN_IN_SAVE = URL + "/phone/sign/save";
    /**
     * 消息列表接口
     */
    public static final String MESSAGE_LIST = URL + "/phone/message/list";
    /**
     * 积分记录表接口
     */
    public static final String INTEGRAL_LIST = URL + "/phone/sign/list";
    /**
     * 标记消息是否已读接口
     */
    public static final String MESSAGE_READ = URL + "/phone/message/read";
    /**
     * 我的体验金接口
     */
    public static final String MY_EXPERIENCE = URL + "/phone/loan/myExperience";
    /**
     * 新手体验标详情
     */
    public static final String MY_EXPERIENCE_LOAN_INFO = URL + "/phone/loan/loanInfo";
    /**
     * 理财--投资列表
     */
    public static final String INVESTMENT_LIST = URL + "/phone/loan/list";
    /**
     * 理财--债权列表
     */
    public static final String CREDITOR_TRANSFER_LIST = URL + "/phone/transfer/list";
    /**
     * 投资列表详情接口
     */
    public static final String INVESTMENT_DETAILS = URL + "/phone/loan/loanInfo";
    /**
     * 获取首页标的详情对应标的id接口
     */
    public static final String HOME_DETAILS_ID = URL + "/phone/loan/getLoanidByProduct";
    /**
     * 债权详情接口
     */
    public static final String CREDITOR_DETAILS = URL + "/phone/transfer/transferInfo";
    /**
     * 债权购买接口
     */
    public static final String CREDITOR_BUY = URL + "/phone/transfer/TransferBuyInfo";
    /**
     * 是否实名验证接口
     */
    public static final String IDENTITY_CHECK = URL + "/phone/system/isApprove";
    /**
     * 确认购买债权接口
     */
    public static final String AFFIRM_BUY_CREDITOR_RIGHTS = URL + "/phone/trust/buyTransfer";
    /**
     * 确认购买标的接口
     */
    public static final String AFFIRM_BUY_INVEST = URL + "/phone/trust/tender";

    /**
     * 用户名
     */
    public static final String SHARE_USERNAME = "username";
    /**
     * 用户信息保存文件名
     */
    public static final String USER_CONFIG = "userconfig";
    /**
     * 产品点击购买接口
     */
    public static final String PRODUCE_BUY = URL + "/phone/loan/loansimpleinfo";
    /**
     * 获取可用红包接口
     */
    public static final String RED_BAG = URL + "/phone/tender/getRedBag";
    /**
     * 获取可用加息券接口
     */
    public static final String JIA_XI_QUAN = URL + "/phone/tender/getCoupon";
    /**
     * 汇付充值接口
     */
    public static final String RECHARGE = URL + "/phone/trust/recharge";
    /**
     * 充值记录
     */
    public static final String RECHARG_ERECORD = URL + "/phone/recharge/rechargelog";
    /**
     * 提现接口
     */
    public static final String WITHDRAW = URL + "/phone/bank/withdrawCash";
    /**
     * 提现记录接口
     */
    public static final String WITHDRAW_RECORD = URL + "/phone/bank/withdrawLog";
    /**
     * 汇付提现接口
     */
    public static final String HUIFU_WITHDRAW = URL + "/phone/trust/withdraw";

    /**
     * 个人中心首页
     */
    public static final String PERSONAL_CENTER_MAIN = URL + "/phone/member/memberCenter";
    /**
     * 资金详情接口
     */
    public static final String FUND_DETAILS = URL + "/phone/account/memberAccount";
    /**
     * 交易记录接口
     */
    public static final String TRADERECORD = URL + "/phone/account/accountLog";

    /**
     * 我的的福利红包列表接口
     */
    public static final String BOUNTY = URL + "/phone/bounty/list";

    /**
     * 我的的福利加息券列表接口
     */
    public static final String COUPOM = URL + "/phone/coupon/list";
    /**
     * 我的福利返现券列表接口
     */
    public static final String CASH_BACK = URL + "/phone/cashback/list";
    /**
     * 我的福利返现券列表接口
     */
    public static final String FAN_XIAN_QUAN = URL + "/phone/tender/getCashback";

    /**
     * 我的账户接口
     */
    public static final String MY_ACCOUNT = URL + "/phone/member/myAccountdata";

    /**
     * 获取绑定的银行卡列表信息
     */
    public static final String BANK_CARD_INFO = URL + "/phone/bank/index";

    /**
     * 绑定银行卡,跳转汇付
     */
    public static final String YIBAO_BIND_CARD = URL + "/phone/trust/bindBankCard";

    /**
     * 设置修改登录密码
     */
    public static final String EDITPWD = URL + "/phone/member/editPwd";

    /**
     * 验证元手机号获取的验证码是否正确,更改绑定手机时使用
     */
    public static final String VERIFY_CODE = URL + "/phone/public/verifyCode";


    /**
     * 修改手机绑定接口
     */
    public static final String CHANGE_PHONE = URL + "/phone/approve/resetPhone";
    /**
     * 上传头像图片接口
     */
    public static final String POST_FILE = URL + "/phone/member/avatarSubmit";


    /**
     * 我的投资项目
     */
    public static final String MY_INVESTMENT = URL + "/phone/tender/myTenderList";
    /**
     * 我的债权项目
     */
    public static final String MY_CREDITOR = URL + "/phone/transfer/myTransferList";
    /**
     * 我的推广首页接口
     */
    public static final String MY_POPULARIZE = URL + "/phone/spread/spreadRewardData";


    /**
     * 我的投资详情
     */
    public static final String MY_INVESTMENT_DETAIL = URL + "/phone/tender/tenderInfo";

    /**
     * 债权购买详情
     */
    public static final String CREDITER_BUY_DETAILS = URL + "/phone/transfer/myTransferBuyInfo";

    /**
     * 债权转让详情
     */
    public static final String CREDITER_TRANSFER_DETAILS = URL + "/phone/transfer/myTransferDetail";

    /**
     * 债权转让请求接口
     */
    public static final String IMMEDIATE_TRANSFER = URL + "/phone/transfer/transferSub";

    /**
     * 我的推广成功邀请请求接口
     */
    public static final String SUCCEED_INVITATION = URL + "/phone/spread/mySpread";

    /**
     * 申请VIP接口
     */
    public static final String VIP_APPLY = URL + "/phone/trust/vipApply";

    /**
     * 申请VIP接口
     */
    public static final String VIP_LOG = URL + "/phone/member/viplog";

    /**
     * 我的推广-->推广记录-->接口
     */
    public static final String RECOMMEND_LOG = URL + "/phone/spread/spreadLog";


    /**
     * 结算记录接口
     */
    public static final String SETTLEMENT_RECORD = URL + "/phone/spread/getSettleLog";

    /**
     * 结算记录--立即结算接口
     */
    public static final String SETTLEMENT_NOW = URL + "/phone/spread/doAccount";

    /**
     * 推广奖励总额
     */
    public static final String SUM_MONEY = URL + "/phone/spread/sumMoney";

    /**
     * 推广奖励总额
     */
    public static final String MY_EARNINGS = URL + "/phone/tender/getTenderRecover";
    /**
     * 帮助中心接口
     */
    public static final String HELP_CENTER = URL + "/phone/articles/helpCenter";

    /**
     * 意见反馈接口
     */
    public static final String FEEDBACK = URL + "/phone/articles/suggestion";

    /**
     * 意见反馈接口
     */
    public static final String DISCOVER = URL + "/phone/activity/view";

    /**
     * 体验金确认投资接口
     */
    public static final String EXPERIENCE_INVEST = URL + "/phone/tender/experienceInvest";

    /**
     * 体验金确认投资接口
     */
    public static final String EXPERIENCE_TENDERLIST = URL + "/phone/experience/tenderList";

    /**
     * 好礼兑换接口
     */
    public static final String REDEEM_CODE = URL + "/phone/activity/redeemCode";
    /**
     * 积分商城首页接口
     */
    public static final String INTEGRAL_SHOP = URL + "/phone/mall/index";

    /**
     * 积分商城加载更多商品接口
     */
    public static final String INTEGRAL_SHOP_MORE = URL + "/phone/mall/goodList";

    /**
     * 积分商城积分排行榜接口
     */
    public static final String INTEGRAL_RANKING_LIST = URL + "/phone/mall/creditRanking";
    /**
     * 积分商城热门兑换接口
     */
    public static final String HOT_GOODS = URL + "/phone/mall/hotGoods";
    /**
     * 积分商城获取商品类别接口
     */
    public static final String GOODS_TYPES = URL + "/phone/mall/goodTypes";

    /**
     * 积分商城获取积分类别接口
     */
    public static final String POINT_RANK = URL + "/phone/mall/pointRank";
    /**
     * 积分商城商品详情接口
     */
    public static final String GOODS_DETAILS = URL + "/phone/mall/showGoodDetail";
    /**
     * 积分商城提交订单接口
     */
    public static final String SUBMIT_ORDER = URL + "/phone/mall/submitOrder";

    /**
     * 积分商城兑换记录接口
     */
    public static final String EXCHANGE_LOG = URL + "/phone/mall/exchangeLog";

    /**
     * 债权转让撤销
     */
    public static final String CANCEL_TRANSFER = URL + "/phone/transfer/cancel";

    /**
     * 债权转让撤销
     */
    public static final String DAI_HOU_GUAN_LI = URL + "/phone/loan/managementAfter";

    /**
     * 检查版本更新
     */
    public static final String NEW_VERSION = URL + "/phone/member/updateVersion";
    /**
     * 风险测评
     */
    public static final String RISK_QUESTION = URL + "/wap/member/riskAssessment/questions?type=app&login_token=";

    /**
     * 创建电子签章信息
     */
    public static final String SINATURE_INIT = URL + "/phone/seal/memberInfo";
    /**
     * 确认创建电子签章
     */
    public static final String SINATURE_AFFIRM = URL + "/phone/seal/createSeal";
    /**
     * 电子签章创建协议
     */
//    public static final String SINATURE_AGREEMENT = URL + "/phone/seal/agreement";
    public static final String SINATURE_AGREEMENT = URL + "/phone/seal/agreement?login_token=";

    /**获取图形验证码*/
    public static final String IMAGE_CODE = URL + "/phone/common/public/verifyType/";


    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------


    /**
     * 公司简介
     */
    public static final String GONG_SI_JIAN_JIE = URL + "/wap/content/companyProfile?style=app";
    /**
     * 机构信息
     */
    public static final String JI_GOU_XIN_XI = URL + "/wap/content/orgInfo?style=app";
    /**
     * 主要人员
     */
    public static final String ZHU_YAO_REN_YUAN = URL + "/wap/content/founder?style=app";
    /**
     * 荣誉资质
     */
    public static final String RONG_YU_ZI_ZHI = URL + "/wap/content/honor?style=app";
    /**
     * 安全措施
     */
    public static final String AN_QUAN_CUO_SHI = URL + "/wap/content/safety?style=app";
    /**
     * 土豆大事记
     */
    public static final String TU_DOU_DA_SHI_JI = URL + "/wap/content/event?style=app";
    /**
     * 运营模式
     */
    public static final String YUN_YIGN_MO_SHI = URL + "/wap/content/operationModel?style=app";
    /**
     * 组织架构
     */
    public static final String ZU_ZHI_JIA_GOU = URL + "/wap/content/org?style=app";
    /**
     * 合作伙伴
     */
    public static final String HE_ZUO_HUO_BAN = URL + "/wap/content/partner?style=app";
    /**
     * 土豆视角
     */
    public static final String TU_DOU_SHI_JIAO = URL + "/wap/content/article/tdsj?style=app";
    /**
     * 媒体报道
     */
    public static final String MEI_TI_BAO_DAO = URL + "/wap/content/article/mtbd?style=app";
    /**
     * 土豆公益
     */
    public static final String TU_DOU_GONG_YI = URL + "/wap/content/article/tdgy?style=app";
    /**
     * 平台数据
     */
    public static final String PING_TAI_SHU_JU = URL + "/wap/content/platformData?style=app";
    /**
     * 联系我们
     */
    public static final String LIAN_XI_WO_MEN = URL + "/wap/content/contactUs?style=app";

    /**
     * 网站公告
     */
    public static final String WANG_ZHAN_GONG_GAO = URL + "/phone/content/notice";

    /**
     * 注册协议
     */
    public static final String ZHU_CE_XIE_YI = URL + "/phone/agreement/getone?id=1";
    /**
     * 投资协议
     */
    public static final String TOU_ZI_XIE_YI = URL + "/phone/agreement/loanProtocol";
    /**
     * 投资协议
     */
    public static final String LOAN_AGREEMENT = URL + "/wap/loan/appLoanAgreement#";
    /**
     * 新手福利
     */
    public static final String XIN_SHOU_FU_LI = URL + "/phone/welfare/newUser";

    /**
     * 积分说明
     */
    public static final String JI_FEN_SHUO_MING = URL + "/phone/articles/pointDirection";
    /**
     * 新手福利注册链接
     */
    public static final String XIN_SHOU_FU_LI_ZHU_CE = "/wap/system/register2";
    /**
     * web页中的登录链接关键字
     */
    public static final String LOGIN_CLICK = "/wap/system/reglogin";

    /**
     * 债权协议链接
     */
    public static final String ZHAI_QUAN_XIE_YI = "http://mob.tudoujf.com/wap/transfer/appTransferAgreement#?id=";

//http://mob.tudoujf.com/wap/system/register2?originate=app   新手福利注册链接
//    ------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------

    /**
     * 托管注册
     */
    public static final String REAL_NAME_AUTHENTICATION = URL + "/phone/trust/register";
    public static final String TRUST_REGISTER = URL + "/phone/trust/register";

    public static final String SHARE_LOGINTOKEN = "loginToken";
    public static final String SHARE_USERID = "userid";
    public static final String SHARE_ISLOGIN = "islogin";
    public static final String SHARE_USERKEY = "userkey";


    /**
     * 用户登录验证
     */
    public static final String LOGIN = URL + "/phone/member/login";

    /**
     * 领取生日红包
     */
    public static final String BIRTHDAY_WELFARE = URL + "/phone/member/birthdayWelfare";


    /**
     * 首页信息
     */
    public static final String HOME = URL + "/phone/index/index";

    /**
     * 检测手机号码、邮箱、用户名是否存在
     */
    public static final String CHECK = URL + "/phone/member/checkExit";

    /**
     * 发送注册短信验证码
     */
    public static final String REG_SMS = URL + "/phone/public/sendSms";


    /**
     * 网站服务协议
     */
    public static final String AGREEMENT_WEBSITE = URL + "/wap/index/appAgreement";
    /**
     * 债权转让协议
     */
    public static final String AGREEMENT_CREDITOR_TRANSFER = URL + "/wap/transfer/appTransferAgreement#?id=";//后面加id
    /**
     * 借款协议
     */
    public static final String AGREEMENT_BORROW = URL + "/wap/loan/appLoanAgreement#?id=";//后面加id


    /**
     * 用户注销
     */
    public static final String LOGINOUT = URL + "/phone/member/loginOut";

    /**
     * 获取用户信息
     */
    public static final String INFO = URL + "/phone/member/info";

    /**
     * 获取用资金信息
     */
    public static final String ACCOUNT = URL + "/phone/member/account";

    /**
     * 获取用户资金记录
     */
    public static final String ACCOUNTLOG = URL + "/phone/member/accountLog";

    /**
     * 获取用户投资列表
     */
    public static final String TENDERLIST = URL + "/phone/member/tenderList";

    /**
     * 获取用户收款列表
     */
    public static final String COLLECTIONLIST = URL
            + "/phone/member/collectionList";

    /**
     * 获取用户借款列表
     */
    public static final String LOANLIST = URL + "/phone/member/loanList";

    /**
     * 获取用户还款明细列表
     */
    public static final String REPAYLIST = URL + "/phone/member/repayList";
    /**
     * 获取用户还款操作
     */
    public static final String REPAY = URL + "/phone/member/repay";

    /**
     * 获取首页需要相关信息
     */
    public static final String INDEX = URL + "/phone/index/index";

    /**
     * 投资列表
     */
    public static final String TENDER_INDEX = URL + "/phone/tender/index";
    /**
     * 获取认证信息
     */
    public static final String APPROVE = URL + "/phone/member/getApprove";
    /**
     * 立即投资
     */
    public static final String TENDER = URL + "/phone/tender/tender";

    /**
     * 获取某个标投资记录
     */
    public static final String RECORDLIST = URL + "/phone/tender/recordList";

    /**
     * 债权转让列表
     */
    public static final String TRANSFER_INDEX = URL + "/phone/transfer/index";

//    /**
//     * 债权转让详情
//     */
//    public static final String CREDITER_TRANSFER_DETAILS = URL + "/phone/transfer/transferInfo";

    /**
     * 购买债权
     */
    public static final String BUY = URL + "/phone/transfer/buy";

    /**
     * 借标详情
     */
    public static final String DETAIL = URL + "/phone/loan/detail";

    /**
     * 获取版本信息
     */
    public static final String VERSION = URL + "/phone/extra/version";

    /**
     * 提交手机认证
     */
    public static final String APPROVE_PHONE = URL
            + "/phone/approve/approvePhone";
    /**
     * 注册
     */
    public static final String REG = URL + "/phone/public/reg";
    /**
     * 常见问题
     */
    public static final String ARTICLESLIST = URL
            + "/phone/articles/articlesList";
    /**
     * 公告栏目
     */
    public static final String NOTICELIST = URL
            + "/phone/articles/noticeList";


    /**
     * 用户资金详情
     */
    public static final String PERSONAL_FUND_DETAIL = URL
            + "/phone/account/memberAccount";
    /**
     * 邮箱检测
     */
    public static final String CHECK_EMAIL = URL + "/phone/public/checkEmail";
    /**
     * 手机检测
     */
    public static final String CHECK_PHONE = URL + "/phone/public/checkPhone";
    /**
     * 发送邮箱验证码
     */
    public static final String EMAIL_SEND_CODE = URL
            + "/phone/approve/sendApproveEmail";
    /**
     * 找回密码发送邮件
     */
    public static final String SEND_RECOVER_EMAIL = URL
            + "/phone/member/sendRecoveryEmail";
    /**
     * 邮箱验证
     */
    public static final String EMAIL_BIND = URL
            + "/phone/approve/approveEmail";
    /**
     * 我要投资页面
     */
    public static final String INVESTDATA = URL + "/phone/tender/investData";
    /**
     * 投资收益
     */
    public static final String INVESTINTEREST = URL
            + "/phone/tender/investInterest";

    /**
     * 判断用户是否设置支付密码
     */
    public static final String GETPAYPWD = URL + "/phone/member/getPaypwd";

    /**
     * 设置找回密码新密码
     */
    public static final String EIDT_RECOBER_PWD = URL
            + "/phone/member/editRecoverPwd";

    /**
     * 设置支付密码
     */
    public static final String EDITPAYPWD = URL + "/phone/member/editPaypwd";
    /**
     * 立即投资
     */
    public static final String INVESTMENT_TENDER = URL
            + "/phone/tender/tender";


    /**
     * 站内信
     */
    public static final String INSIDELETTER = URL
            + "/phone/member/messageLog";
    /**
     * 站内信更新阅读状态
     */
    public static final String INSIDELETTER_STATUS = URL
            + "/phone/member/messageView";

    /**
     * 修改邮箱绑定
     */
    public static final String CHANGE_EMAIL = URL
            + "/phone/approve/resetEmail";
    /**
     * 用户信息
     */
    public static final String USER_INFO = URL + "/phone/member/userInfo";
    /**
     * 版本更新
     */
    public static final String UPDATE_VERSION = URL
            + "/phone/member/updateVersion";
    /**
     * 债权转让
     */
    public static final String CRETIOR_INDEX = URL + "/phone/transfer/index";
    /**
     * 债权转让购买详情
     */
    public static final String TransferBuyInfo = URL
            + "/phone/transfer/TransferBuyInfo";
    /**
     * 购买债权详情
     */
    public static final String CRETIOR_DETAILS = URL
            + "/phone/transfer/TransferBuyInfo";
    /**
     * 我的债权转让列表
     */
    public static final String CREDITER_LIST = URL
            + "/phone/transfer/myTransferList";
    /**
     * 实名认证
     */
    public static final String REAL_NAME_AUTH = URL
            + "/phone/approve/approveRealName";
    /**
     * 债权转让
     */
    public static final String CREDITER_DETAILS = URL
            + "/phone/transfer/transferInfo";
    /**
     * 绑定银行卡
     */
    public static final String BIND_BANK_CARD = URL + "/phone/bank/addBank";
    /**
     * 更换银行卡
     */
    public static final String CHANGE_BANK_CARD = URL
            + "/phone/bank/editBank";
    /**
     * 获取银行卡头信息
     */
    public static final String BANK_CARD_NID = URL + "/phone/bank/getBankNid";
    /**
     * 获取银行列表
     */
    public static final String BANK_LIST_INFO = URL + "/phone/bank/bankList";
    /**
     * 获取省市信息
     */
    public static final String PROVINCE_CITY_INFO = URL
            + "/phone/common/getProvinceCity";


    /**
     * 获取取现手续费
     */
    public static final String WITHDRAW_FEE = URL
            + "/phone/member/getCashFee";
//    /**
//     * 提现
//     */
//    public static final String WITHDRAW = URL + "/phone/member/withDraw";
//    /**
//     * 提现记录
//     */
//    public static final String WITHDRAW_RECORD = URL
//            + "/phone/member/withDrawLog";
    /**
     * 推广记录
     */
    public static final String PROMOTION_RECORD = URL
            + "/phone/spread/mySpreadLog";


    /**
     * 我的推广
     */
    public static final String MY_PROMOTION = URL
            + "/phone/spread/mySpreadAll";
    /**
     * 判断是否是自己的借款标
     */
    public static final String IS_MY_LOAN = URL + "/phone/loan/isMyLoan";
    /**
     * 判断是否是自己的债权
     */
    public static final String IS_MY_TRANSFER = URL + "/phone/transfer/isMyTransfer";

    /**
     * 是否注册托管
     */
    public static final String IS_TRUST_REGISTER = URL
            + "/phone/trust/isregister";
    /**
     * 获取充值方式
     */
    public static final String RECHARGE_TYPE = URL
            + "/phone/recharge/getList";
    /**
     * 获取充值手续费
     */
    public static final String RECHARGE_FEE = URL
            + "/phone/recharge/rechargeFee";


    /**
     * 易宝解绑银行卡
     */
    public static final String YIBAO_UNBIND_CARD = URL
            + "/phone/trustDirect/unBindBankCard";
    /**
     * 易宝投资
     */
    public static final String YIBAO_INVEST = URL + "/phone/trust/tender";

    /**
     * 流转标易宝投资
     */
    public static final String YIBAO_FLOW_INVEST = URL + "/phone/trust/tenderRoam";
    /**
     * 易宝债权购买JAVA
     */
    public static final String YIBAO_BUY_JAVA = URL + "/phone/trust/buyTransfer";
    /**
     * 我的支付账号
     */
    public static final String YIBAO_MY_ACCOUNT = URL
            + "/phone/trust/myTrust";

    /**
     * 上传头像
     */
    public static final String YIBAO_AVATAR_SUBMIT = URL
            + "/phone/member/avatarSubmit";
    /**
     * 获取协议的内容
     */
    public static final String XIE_YI_URL = URL
            + "/phone/loan/loanType";

    /**
     * 注册接口
     */
    public static final String REGISTER = URL + "/phone/public/reg";

    /**
     * 计算器还款方式列表
     */
    public static final String GET_REPAY_TYPE_LIST = URL + "/phone/loan/getRepayTypeList";

    /**
     * 计算器计算结果
     */
    public static final String CALCULATOR_RESULT = URL + "/phone/common/calculator";


    public static final String STATUS_REGISTER_SUCCESS = "register_status=success";//注册成功
    public static final String STATUS_REGISTER_FAIL = "register_status=fail";//注册失败
    public static final String STATUS_CLOSE = "/wap";

}
