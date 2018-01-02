package com.tudoujf.activity.my.mypopularize;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.imageutils.BitmapUtil;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.UserConfig;
import com.tudoujf.utils.ScreenSizeUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;

/**
 * * ================================================
 * name:            NowRecommendActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/7
 * description：  我的推广页面--->立即推荐activity
 * history：
 * ===================================================
 */

public class NowRecommendActivity extends BaseActivity {
    @BindView(R.id.mtb_act_nowrecommend)
    FrameLayout mtbActNowRecommend;
    @BindView(R.id.tv_act_nowrecommend_close)
    TextView tvClose;
    @BindView(R.id.tv_act_nowrecommend_share)
    TextView tvShare;
    @BindView(R.id.tv_act_nowrecommend_register)
    TextView tvRegister;
    @BindView(R.id.iv_act_nowrecommend_share)
    ImageView ivShare;

    private String share_url;

    @Override
    public int getLayoutResId() {
        return R.layout.act_nowrecommend;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_nowrecommend_close://关闭本页面
                closeActivity();
                break;
            case R.id.tv_act_nowrecommend_share://分享
                share();
                break;
            case R.id.tv_act_nowrecommend_register://立即注册
                break;
            case R.id.iv_act_nowrecommend_share://引导显示
                ivShare.setVisibility(View.GONE);
                break;
        }

    }

    /**
     * 把bitmap画到一个白底的newBitmap上,将newBitmap返回
     *
     * @param bitmap 要绘制的位图
     * @return Bitmap
     */
    public Bitmap drawableBitmapOnWhiteBg(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(getResources().getColor(android.R.color.white));
        Paint paint = new Paint();
        Rect rect=new Rect(0,0,150,150);
        canvas.drawBitmap(bitmap,null,rect,paint);
//        canvas.drawBitmap(bitmap, 0, 0, paint); //将原图使用给定的画笔画到画布上
        return newBitmap;
    }

    /**
     * 友盟分享代码-------------------------------------------------------------------------------
     */
    private void share() {
        Log.e("TAG", "share: 微信是否安装-----" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmap = drawableBitmapOnWhiteBg(bitmap);

        UMImage image = new UMImage(NowRecommendActivity.this, bitmap);//网络图片
//        UMImage image = new UMImage(NowRecommendActivity.this, R.drawable.act_lock_icon);//网络图片
//        new ShareAction(NowRecommendActivity.this).withText("hello").withMedia(image).share();
//        UMWeb web = new UMWeb("https://www.tudoujf.com/");
        UMWeb web = new UMWeb(share_url);
        web.setTitle("土豆金服");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("实名领取28888元体验金!!!");//描述

        new ShareAction(NowRecommendActivity.this)
                .withMedia(web)
                .setDisplayList(
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
//                        SHARE_MEDIA.SINA,
//                        SHARE_MEDIA.SMS,
//                        SHARE_MEDIA.EMAIL,
                        SHARE_MEDIA.MORE
                )
                .setCallback(shareListener)
                .open();


    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(NowRecommendActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(NowRecommendActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(NowRecommendActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    /**
     * 友盟分享代码-------------------------------------------------------------------------------
     */

    @Override
    public void initDataFromIntent() {
        share_url = getIntent().getStringExtra("share_url");
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActNowRecommend.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActNowRecommend.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        tvClose.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivShare.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


    //返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserConfig.getInstance().setDraw(true);
        UMShareAPI.get(this).release();
    }

}
