package com.tudoujf.activity.my.myaccount;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyAccountActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  我的账户activity
 * history：
 * * ====================================================================
 */

public class MyAccountActivity extends BaseActivity {
    @BindView(R.id.mtb_act_myaccount)
    MTopBarView mtbActMyAccount;
    @BindView(R.id.ll_act_myaccount_icon)
    LinearLayout llIcon;
    @BindView(R.id.ll_act_myaccount_bankcard)
    LinearLayout llBankCard;
    @BindView(R.id.ll_act_myaccount_huifu)
    LinearLayout llHuiFu;
    @BindView(R.id.ll_act_myaccount_phone)
    LinearLayout llPhone;
    @BindView(R.id.ll_act_myaccount_email)
    LinearLayout llEmail;
    @BindView(R.id.ll_act_myaccount_name)
    LinearLayout llName;
    @BindView(R.id.ll_act_myaccount_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_act_myaccount_gesture)
    LinearLayout llGesture;
    @BindView(R.id.tv_act_myaccount_signout)
    TextView tvSignOut;
    @BindView(R.id.tv_act_myaccount_oc)
    TextView tvOC;
    @BindView(R.id.iv_act_myaccount_icon)
    ImageView ivIcon;
    TextView tvPhotograph;
    TextView tvAlbum;

    private int count = 0;
    private AlertDialog dialog;
    private View view;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myaccount;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_myaccount_icon://更改头像
                if (dialog == null) {
                    dialog = showCustomDialog(view);
                } else {
                    dialog.show();
                }
                break;
            case R.id.ll_act_myaccount_bankcard://银行卡管理
                openActivity(BankCardManageActivity.class);
                break;
            case R.id.ll_act_myaccount_huifu://我的汇付账号
                break;
            case R.id.ll_act_myaccount_phone://手机绑定
                openActivity(ChangePhoneNumberActivity.class);
                break;
            case R.id.ll_act_myaccount_email://邮箱认证
                openActivity(BindEmailActivity.class);
                break;
            case R.id.ll_act_myaccount_name://实名认证
                break;
            case R.id.ll_act_myaccount_login://登陆密码
                openActivity(ChangePasswordActivity.class);
                break;
            case R.id.ll_act_myaccount_gesture://手势密码
                if (count % 2 == 1) {
                    tvOC.setBackgroundResource(R.drawable.act_myaccount_open);
                } else {
                    tvOC.setBackgroundResource(R.drawable.act_myaccount_close);
                }
                count++;
                break;
            case R.id.tv_act_myaccount_signout://退出登陆
                break;
            case R.id.act_myaccount_paishe://拍照
                dialog.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
            case R.id.act_myaccount_xiangcexuanqu://相册选取
                dialog.dismiss();
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, 2);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActMyAccount.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActMyAccount.setLayoutParams(params);


        view = LayoutInflater.from(this).inflate(R.layout.dialog_act_myaccount, null);
        tvPhotograph = (TextView) view.findViewById(R.id.act_myaccount_paishe);
        tvAlbum = (TextView) view.findViewById(R.id.act_myaccount_xiangcexuanqu);


    }

    @Override
    public void initListener() {
        mtbActMyAccount.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        llIcon.setOnClickListener(this);
        llBankCard.setOnClickListener(this);
        llHuiFu.setOnClickListener(this);
        llPhone.setOnClickListener(this);

        llEmail.setOnClickListener(this);
        llName.setOnClickListener(this);
        llLogin.setOnClickListener(this);
        llGesture.setOnClickListener(this);

        tvSignOut.setOnClickListener(this);
        tvPhotograph.setOnClickListener(this);
        tvAlbum.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult:相册???--- " + data);
        if (requestCode == 1) {
            Bitmap photo = data.getParcelableExtra("data");//获取拍照图片
            Log.e("TAG", "onActivityResult: --------------------" + photo);
            if (photo != null) {
                ivIcon.setImageBitmap(photo);
                // TODO: 2017/9/11 将拍照图片设置为头像并上传服务器,无裁剪
            } else {
                ToastUtils.showToast(this, "没有获取到拍照图片!");
            }
        } else if (requestCode == 2&&data!=null) {

            Log.e("TAG", "onActivityResult:相册111 " + data.getData());
            Log.e("TAG", "onActivityResult:相册 222" + data.getData().toString());

            ContentResolver resolver = getContentResolver();
            try {
                InputStream inputStream = resolver.openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivIcon.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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

    /**
     * 显示提示dialog
     *
     * @return alertdialog(v7)
     */
    public AlertDialog showCustomDialog(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);
            window.setBackgroundDrawable(drawable);
            window.setContentView(view);
        }

        return dialog;
    }

}
