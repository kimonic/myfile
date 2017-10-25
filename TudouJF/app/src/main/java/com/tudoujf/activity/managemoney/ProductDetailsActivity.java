package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.ProductDetailsActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.bean.databean.InvestDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.IndicatorView;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MHorizontalScrollView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            ProductDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/21
 * description：产品详情界面activity
 * history：
 * ===================================================
 */

public class ProductDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_productdetails)
    MTopBarView mtbProductdetails;
    @BindView(R.id.utv_act_productdetails1)
    UnderlineTextView utv1;
    @BindView(R.id.utv_act_productdetails2)
    UnderlineTextView utv2;
    @BindView(R.id.utv_act_productdetails3)
    UnderlineTextView utv3;
    @BindView(R.id.ll_act_productdetails_xiangmuxiangqing)
    LinearLayout llXiangMuXiangQing;
    @BindView(R.id.ll_act_productdetails_touziliebiao)
    LinearLayout llTouZiLieBiao;
    @BindView(R.id.ll_act_productdetails_changjianwenti)
    LinearLayout llChangJianWenTi;
    @BindView(R.id.tv_act_productdetails_buynow)
    TextView tvBuyNow;
    @BindView(R.id.lv_act_productdetails)
    ListView lvInvestList;
    @BindView(R.id.hsv_act_productdetails)
    MHorizontalScrollView hsvImage;
    @BindView(R.id.iv_act_productdetails)
    IndicatorView indicator;
    @BindView(R.id.tv_act_productdetails_rongziedu)
    TextView tvRongZiEDu;
    @BindView(R.id.tv_act_productdetails_shengyuketou)
    TextView tvShengYuKeTou;
    @BindView(R.id.tv_act_productdetails_huankuanfangshi)
    TextView tvHuanKuanFangShi;
    @BindView(R.id.tv_act_productdetails_jiekuanzhuangtai)
    TextView tvJieKuanZhuangTai;
    @BindView(R.id.tv_act_productdetails_touzirenshu)
    TextView tvTouZiRenShu;
    @BindView(R.id.tv_act_productdetails_jieshushijian)
    TextView tvJieShuShiJian;
    @BindView(R.id.tv_act_productdetails_xiangmumiaoshu)
    TextView tvXiangMuMiaoShu;
    @BindView(R.id.tv_act_productdetails_huankuanbaozhang)
    TextView tvHuanKuanBaoZhang;
    @BindView(R.id.tv_act_productdetails_fengkongyijian)
    TextView tvFengKongYiJian;
    @BindView(R.id.tv_act_productdetails_baozhangjigou)
    TextView tvBaoZhangJiGou;
    @BindView(R.id.tv_act_productdetails_fengxiantishi)
    TextView tvFengXianTiShi;
    @BindView(R.id.tv_act_productdetails_touzizonge)
    TextView tvTouZiZongE;
    @BindView(R.id.tv_act_productdetails_touzirenci)
    TextView tvTouZiRenCi;

    @BindView(R.id.iv_act_productdetails_info)
    InfoView info;
    @BindView(R.id.ll_act_productdetails_image)
    LinearLayout llImage;

    private List<UnderlineTextView> list;
    private List<LinearLayout> listLL;
    private List<InvestDetailsBean.TenderListBean> beanList;
    private int hsvUnShowWidth;
    private String loan_id;
    private IdentityCheckBean identityCheckBean;
    /**
     * 加载进度dialog
     */
    private AlertDialog dialog;
    private InvestDetailsBean bean;
    /**
     * 按钮之前的位置
     */
    private int beforePosition = 0;


    private String loginToken;
    private String status;
    private String hint;
    /**
     * 是否已经登陆,已经登陆了则只检查是否实名,未登陆登陆后需要再次刷新本页面
     */
    private boolean isLogin = false;
    private boolean isLogin1 = false;

    /**
     * 验证请求只执行一次
     */
    private boolean request = true;


    @Override
    public int getLayoutResId() {
        return R.layout.act_productdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.utv_act_productdetails1:
                setUTVStyle(0);
                break;
            case R.id.utv_act_productdetails2:
                setUTVStyle(1);
                break;
            case R.id.utv_act_productdetails3:
                setUTVStyle(2);
                break;
            case R.id.tv_act_productdetails_buynow://立即购买按钮
                if (bean != null && "1".equals(bean.getMember().getSelf_loan())) {
                    ToastUtils.showToast(ProductDetailsActivity.this, R.string.zijibunenggoumaizijidebiaodi);
                } else if (request) {
                    checkLogin();
                } else {
                    enterBuy();
                }


                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();


        if (intent != null) {
            loan_id = intent.getStringExtra("loan_id");
            hint = intent.getStringExtra("hint");
        }

        beanList = new ArrayList<>();
        InvestDetailsBean.TenderListBean bean = new InvestDetailsBean.TenderListBean();
        bean.setMember_name("投标人");
        bean.setAmount("投资金额(元)");
        bean.setAdd_time("投资时间");
        beanList.add(bean);

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbProductdetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbProductdetails.setLayoutParams(params);

        mtbProductdetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        utv1.setUnderlinecolor(R.color.act_productdetails_tvcolor);

        list = new ArrayList<>();
        list.add(utv1);
        list.add(utv2);
        list.add(utv3);

        listLL = new ArrayList<>();
        listLL.add(llXiangMuXiangQing);
        listLL.add(llChangJianWenTi);
        listLL.add(llTouZiLieBiao);


//        checkScroll();
        hsvImage.setListener(new MHorizontalScrollView.ScrollListener() {
            @Override
            public void currentScrollX(int l, int t, int oldl, int oldt) {
                indicator.setScale((float) l / hsvUnShowWidth);//移动距离占未显示距离的比例
            }
        });

    }


    @Override
    public void initListener() {
        utv1.setOnClickListener(this);
        utv2.setOnClickListener(this);
        utv3.setOnClickListener(this);
        tvBuyNow.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        showProgressDialog();
        TreeMap<String, String> map = new TreeMap<>();
//        map.put("loan_id", loan_id);
        //----------------------------临时固定id----------------------------------------------------------------------------------------------------------------
        map.put("loan_id", loan_id);
        Log.e("TAG", "initDataFromInternet: ---------loan_id------------" + loan_id);

        HttpMethods.getInstance().POST(this, Constants.INVESTMENT_DETAILS, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dialog.dismiss();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----理财投资详情接口返回数据--------" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<InvestDetailsBean>() {
                        }.getType(), InvestDetailsBean.class, ProductDetailsActivity.this);
                        if (bean1 != null) {
                            bean = (InvestDetailsBean) bean1;
                            LoadInternetDataToUi();
                            if (isLogin) {
                                enterBuy();
                            }
                        } else {
                            ToastUtils.showToast(ProductDetailsActivity.this, R.string.shujujiazaichucuo);
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {

            if (!(StringUtils.string2Float(bean.getLoan_info().getProgress()) < 100 && StringUtils.string2Float(bean.getLoan_info().getStatus()) <= 3)) {
                tvBuyNow.setBackgroundColor(getResources().getColor(R.color.color_gray));
                tvBuyNow.setText(hint);
            }


            mtbProductdetails.setCenterTitle(bean.getLoan_info().getName());

            tvRongZiEDu.setText((bean.getLoan_info().getAmount() + "元"));
            tvShengYuKeTou.setText((bean.getLoan_info().getLeft_amount() + "元"));
            tvHuanKuanFangShi.setText(bean.getRepay_type().getContents());


            tvJieKuanZhuangTai.setText(bean.getLoan_info().getStatus_name());
            tvTouZiRenShu.setText(bean.getLoan_info().getTender_count());
            tvJieShuShiJian.setText(bean.getLoan_info().getOverdue_time());


            //------------------------------------标的信息详情--------------------------------------
            info.setUnderlineScale1(StringUtils.string2Float(bean.getLoan_info().getProgress()));
            info.setNianHuaShouYi(bean.getLoan_info().getApr() + "%");
            info.setJieKuanQiXian(bean.getLoan_info().getPeriod_name());
            info.setIfNew(!"-1".equals(bean.getLoan_info().getAdditional_status()));
            info.invalidate();
            //------------------------------------标的信息详情--------------------------------------


            //------------------------------------项目详情--------------------------------------
            tvXiangMuMiaoShu.setText(StringUtils.convertRetract(Html.fromHtml(bean.getLoan_info().getProject_contents()).toString()));
            tvHuanKuanBaoZhang.setText(StringUtils.convertRetract(Html.fromHtml(bean.getLoan_info().getRepay_contents()).toString()));
            tvFengKongYiJian.setText(StringUtils.convertRetract(Html.fromHtml(bean.getLoan_info().getContrall_contents()).toString()));
            tvBaoZhangJiGou.setText(StringUtils.convertRetract(Html.fromHtml(bean.getLoan_info().getPromise_contents()).toString()));
            tvFengXianTiShi.setText(StringUtils.convertRetract(Html.fromHtml(bean.getLoan_info().getDenger_contents()).toString()));

            //------------------------------------项目详情--------------------------------------


            //------------------------------------图片列表--------------------------------------
            final List<InvestDetailsBean.AttaListBean> imageList = bean.getAttaList();

            if (imageList != null && imageList.size() > 0) {
                int desity = ScreenSizeUtils.getDensity(this);
                for (int i = 0; i < imageList.size(); i++) {
                    ImageView imageView = new ImageView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(180 * desity, 200 * desity);
                    imageView.setPadding(20 * desity, 0, 20 * desity, 0);
                    imageView.setLayoutParams(params);
                    ImageGlideUtils.loadImageFromUrl(imageView, imageList.get(i).getImgurl());
                    final String url = imageList.get(i).getImgurl();
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), ImageShowActivity.class);
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                    });
                    llImage.addView(imageView);
                }
            }
            //------------------------------------图片列表--------------------------------------


            //------------------------------------投资列表--------------------------------------
            List<InvestDetailsBean.TenderListBean> tenderList = bean.getTender_list();
            if (tenderList != null && tenderList.size() > 0) {
                beanList.addAll(tenderList);
                ProductDetailsActLvAdapter adapter = new ProductDetailsActLvAdapter(this, beanList);
                lvInvestList.setAdapter(adapter);
                HeightUtils.setListviewHeight(lvInvestList);
                tvTouZiZongE.setText((bean.getLoan_info().getCredited_amount() + "元"));
                tvTouZiRenCi.setText((bean.getLoan_info().getTender_count() + "人"));
            }
            //------------------------------------投资列表--------------------------------------

        }

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    /**
     * 设置underllinetextview的样式
     */
    private void setUTVStyle(int i) {

        if (beforePosition != i) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    list.get(j).setUnderlinecolor(R.color.act_productdetails_tvcolor);
                    list.get(j).setTextColor(getResources().getColor(R.color.act_productdetails_tvcolor));
                    list.get(j).invalidate();
                    listLL.get(j).setVisibility(View.VISIBLE);
                } else if (beforePosition == j) {
                    list.get(j).setUnderlinecolor(R.color.color_white);
                    list.get(j).setTextColor(getResources().getColor(R.color.color_black));
                    list.get(j).invalidate();
                    listLL.get(j).setVisibility(View.GONE);
                }
            }
            beforePosition = i;
        }


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hsvUnShowWidth = hsvImage.getChildAt(0).getWidth() - hsvImage.getWidth();
    }

    /**
     * 显示加载进度dialog
     */
    private void showProgressDialog() {
        if (dialog == null) {
            dialog = DialogUtils.showProgreessDialog(this, getString(R.string.zaicidianjijinagtuichugaiyemian));
        } else {
            dialog.show();
        }

    }

    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------

    /**
     * 进入购买页面
     */
    private void enterBuy() {
        Log.e("TAG", "enterBuy: -----进入购买!");

        request = false;
        if (bean != null) {
            if (StringUtils.string2Float(bean.getLoan_info().getProgress()) < 100 && StringUtils.string2Float(bean.getLoan_info().getStatus()) <= 3) {
                Bundle bundle = new Bundle();
                bundle.putString("loan_id", loan_id);
                bundle.putString("is_beginner", bean.getLoan_info().getAdditional_status());
                bundle.putString("time_limit", bean.getLoan_info().getPeriod());
                bundle.putString("has_password", bean.getLoan_info().isPassword_status());
                Log.e("TAG", "enterBuy: --bean.get???Loan_info().getAdditional_status()---" + bean.getLoan_info().getAdditional_status());


                openActivity(AffirmBuyActivity.class, bundle);
            } else if (hint != null) {
                ToastUtils.showToast(this, hint + getResources().getString(R.string.bunenggoumai));
            } else {
                ToastUtils.showToast(this, R.string.weizhicuowu);
            }
        } else {
            ToastUtils.showToast(this, R.string.shujujiazaichucuoqtchcxjr);
        }
    }

    private void checkLogin() {
        loginToken = UserConfig.getInstance().getLoginToken(this);
        if ("".equals(loginToken)) {
            isLogin = false;
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("type", 888);
            startActivityForResult(intent, 888);
        } else {
            isLogin = true;
            checkIdentity();
        }

    }

    private void checkIdentity() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);
        HttpMethods.getInstance().POST(this, Constants.IDENTITY_CHECK, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------请求身份是否实名返回的json数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IdentityCheckBean>() {
                }.getType(), IdentityCheckBean.class, ProductDetailsActivity.this);
                if (bean1 != null) {
                    identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        if (isLogin && !isLogin1) {//之前已登录
                            enterBuy();
                        } else {//之前未登录
                            isLogin = true;
                            initDataFromInternet();
                        }
                    } else {
                        DialogUtils.showHuiFuDialog(ProductDetailsActivity.this);
                    }
                } else {
                    ToastUtils.showToast(ProductDetailsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            isLogin1 = true;
            checkLogin();
        }
    }

    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------


}
