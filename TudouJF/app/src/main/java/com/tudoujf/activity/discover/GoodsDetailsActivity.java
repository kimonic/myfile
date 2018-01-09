package com.tudoujf.activity.discover;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.GoodsDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.BoderTextView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             GoodsDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/8
 * description：     商品详情activity
 * history：
 * *==================================================================
 */

public class GoodsDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_godsdetails)
    MTopBarView mtb;
    @BindView(R.id.iv_act_godsdetails)
    ImageView iv;
    @BindView(R.id.iv_act_goodsdetails_leftarrow)
    ImageView ivLeft;
    @BindView(R.id.iv_act_goodsdetails_rightarrow)
    ImageView ivRight;
    @BindView(R.id.hsv_act_godsdetails)
    HorizontalScrollView hsv;
    @BindView(R.id.tv_act_godsdetails_nowexchange)
    TextView tvNowExchange;
    @BindView(R.id.tv_act_goodsdetails_title)
    TextView tvTitle;
    @BindView(R.id.tv_act_goodsdetails_content)
    TextView tvContent;
    @BindView(R.id.tv_act_goodsdetails_bewrite)
    TextView tvBewrite;
    @BindView(R.id.tv_act_goodsdetails_num)
    TextView tvNum;
    @BindView(R.id.tv_act_goodsdetails_needintegral)
    TextView tvNeedIntegral;
    @BindView(R.id.tv_act_goodsdetails_myintegral)
    TextView tvMyintegral;
    @BindView(R.id.tv_act_goodsdetails_reduce)
    TextView tvReduce;
    @BindView(R.id.tv_act_goodsdetails_number)
    TextView tvNumber;
    @BindView(R.id.tv_act_goodsdetails_add)
    TextView tvAdd;
    @BindView(R.id.tv_act_goodsdetails_stock)
    TextView tvStock;
    @BindView(R.id.btv_act_godsdetails_btv1)
    BoderTextView btvBtv1;
    @BindView(R.id.btv_act_godsdetails_btv2)
    BoderTextView btvBtv2;
    @BindView(R.id.btv_act_godsdetails_btv3)
    BoderTextView btvBtv3;
    @BindView(R.id.ll_act_goodsdetails_imagesgroup)
    LinearLayout llImagesGroup;
    private String id = "";
    private GoodsDetailsBean bean;
    private List<BoderTextView> list;

    private List<LinearLayout> linearList;

    //当前显示图片的位置
    private int position = 0;
    private boolean flag = true;
    private String integral;

    @Override
    public int getLayoutResId() {
        return R.layout.act_godsdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btv_act_godsdetails_btv1:
                if (bean != null) {
                    tvContent.setText(bean.getName());
                }
                setBtnStyle(0);
                break;
            case R.id.btv_act_godsdetails_btv2:
                if (bean != null) {
                    tvContent.setText(bean.getDetail());
                }
                setBtnStyle(1);

                break;
            case R.id.btv_act_godsdetails_btv3:
                if (bean != null) {
                    tvContent.setText(bean.getUse_description());
                }
                setBtnStyle(2);
                break;
            case R.id.iv_act_goodsdetails_leftarrow:
                if (position == 0) {
                    position = linearList.size() - 1;
                } else {
                    position = position - 1;
                }
                if (bean != null && bean.getImages() != null) {
                    ImageGlideUtils.loadImage(iv, bean.getImages().get(position));
                }
                setBorderColor();

                break;
            case R.id.iv_act_goodsdetails_rightarrow:
                if (position == linearList.size() - 1) {
                    position = 0;
                } else {
                    position = position + 1;
                }
                if (bean != null && bean.getImages() != null) {
                    ImageGlideUtils.loadImage(iv, bean.getImages().get(position));
                }
                setBorderColor();
                break;
            case R.id.tv_act_goodsdetails_add:
                if (bean != null && StringUtils.string2Integer(tvNumber.getText().toString()) < StringUtils.string2Integer(bean.getStock())) {
                    tvNumber.setText(("" + (StringUtils.string2Integer(tvNumber.getText().toString()) + 1)));
                    flag = true;
                } else {
                    if (flag) {
                        ToastUtils.showToast(GoodsDetailsActivity.this, R.string.yichaochukucunshuliang);
                        flag = false;
                    }
                }
                break;
            case R.id.tv_act_goodsdetails_reduce:
                if (StringUtils.string2Integer(tvNumber.getText().toString()) > 1) {
                    tvNumber.setText(("" + (StringUtils.string2Integer(tvNumber.getText().toString()) - 1)));
                }
                break;
            case R.id.tv_act_godsdetails_nowexchange://
                if (StringUtils.string2Integer(integral)>
                        StringUtils.string2Integer(tvNumber.getText().toString())*StringUtils.string2Integer(bean.getCredit())){

                    Intent intent=new Intent(this,GoodsExchangeActivity.class);
                    intent.putExtra("number",tvNumber.getText().toString());
                    intent.putExtra("url",bean.getImages().get(0));
                    intent.putExtra("needintegral",bean.getCredit());
                    intent.putExtra("myintegral",integral);
                    intent.putExtra("lipinhao",bean.getNum());
                    startActivity(intent);

                }else {
                    ToastUtils.showToast(GoodsDetailsActivity.this, R.string.jifenbuzu);
                }
                break;
//                 case R.id.:break;
        }

    }



    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        linearList = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        integral = getIntent().getStringExtra("integral");
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);

        btvBtv1.setDrawBoder(true);
        tvMyintegral.setText(integral);
        list.add(btvBtv1);
        list.add(btvBtv2);
        list.add(btvBtv3);
    }

    @Override
    public void initListener() {

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        btvBtv1.setOnClickListener(this);
        btvBtv2.setOnClickListener(this);
        btvBtv3.setOnClickListener(this);

        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        tvAdd.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        tvNowExchange.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("id", id);
        HttpMethods.getInstance().POST(this, Constants.GOODS_DETAILS, map, "info", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------积分商城商品详情接口请求返回数据-----------------" + result);
                BaseBean baseBean = ParseJson.getJsonResult(response.body(), new TypeToken<GoodsDetailsBean>() {
                        }.getType()
                        , GoodsDetailsBean.class, GoodsDetailsActivity.this);
                if (baseBean != null) {
                    bean = (GoodsDetailsBean) baseBean;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(GoodsDetailsActivity.this, R.string.shujujiazaichucuo);
                }
            }

            @Override
            public void onError(Response<String> response) {
                dismissPDialog();
                ToastUtils.showToast(GoodsDetailsActivity.this, R.string.huoqushangpinxiangqingshibai);
                super.onError(response);
            }
        });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            if (bean.getImages() != null && bean.getImages().size() > 0) {
                ImageGlideUtils.loadImage(iv, bean.getImages().get(0));
                int desity = ScreenSizeUtils.getDensity(this);
                for (int i = 0; i < bean.getImages().size(); i++) {
                    ImageView imageView = new ImageView(this);
                    final LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(60 * desity, 52 * desity);
                    params1.setMargins(0, 0, 10 * desity, 0);
                    params1.gravity = Gravity.CENTER;
                    linearLayout.setLayoutParams(params1);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setGravity(Gravity.CENTER);
                    if (i == 0) {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.global_theme_background_color));
                    } else {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.color_gray3));
                    }


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(56 * desity, 48 * desity);
//                    imageView.setPadding(20 * desity, 0, 20 * desity, 0);

                    imageView.setLayoutParams(params);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageGlideUtils.loadImageFromUrl(imageView, bean.getImages().get(i));
                    imageView.setTag(i);
                    final String url = bean.getImages().get(i);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageGlideUtils.loadImage(iv, url);
                            position = (int) (v.getTag());
                            setBorderColor();
                        }
                    });

                    linearLayout.addView(imageView);
                    linearList.add(linearLayout);
                    llImagesGroup.addView(linearLayout);
                }
            }

            tvTitle.setText(bean.getSummary());
            tvBewrite.setText(bean.getBewrite());
            tvNum.setText(bean.getNum());

            tvNeedIntegral.setText(bean.getCredit());
            tvStock.setText(bean.getStock());
            tvContent.setText(bean.getName());


        }

    }

    private void setBorderColor() {
        for (int j = 0; j < linearList.size(); j++) {
            if (j == position) {
                linearList.get(j).setBackgroundColor(getResources().getColor(R.color.global_theme_background_color));
            } else {
                linearList.get(j).setBackgroundColor(getResources().getColor(R.color.color_gray3));
            }
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

    private void setBtnStyle(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setDrawBoder(true);
                list.get(i).setUnderlinecolor(getResources().getColor(R.color.global_theme_background_color));
                list.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_white));
            } else {
                list.get(i).setDrawBoder(false);
                list.get(i).setTextColor(getResources().getColor(R.color.color_black2));
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_line));
            }
        }
    }
}
