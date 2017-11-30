package com.tudoujf.activity.my.myaccount;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
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

import com.google.gson.reflect.TypeToken;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.activity.other.DrawGestureActivity;
import com.tudoujf.activity.other.LockActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.MyAccountBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.GlideImageLoaderUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.TreeMap;

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
    @BindView(R.id.tv_act_myaccount_username)
    TextView tvUserName;
    @BindView(R.id.tv_act_myaccount_bankcard_description)
    TextView tvBankcardDescription;
    @BindView(R.id.tv_act_myaccount_huifu_description)
    TextView tvHuifuDescription;
    @BindView(R.id.tv_act_myaccount_phone_description)
    TextView tvPhoneDescription;
    @BindView(R.id.tv_act_myaccount_email_description)
    TextView tvEmailDescription;
    @BindView(R.id.tv_act_myaccount_name_description)
    TextView tvNameDescription;


    @BindView(R.id.iv_act_myaccount_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_act_myaccount_vip)
    ImageView ivVip;
    TextView tvPhotograph;
    TextView tvAlbum;

    private AlertDialog dialog;
    private View view;
    /**
     * 是否已开启手势密码
     */
    private boolean isOpen;
    private AlertDialog.Builder dialogOpen, dialogClose;
    private String userName;

    private MyAccountBean bean;


    private int requestCount = 0;
    private String iconurl;


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
//                Intent intent1 = new Intent(this, ChangePhoneNumberActivity.class);
//                intent1.putExtra("phone",bean.getPhone());
//                startActivity(intent1);

                openActivity(ChangePhoneNumberActivity.class);
                break;
            case R.id.ll_act_myaccount_email://邮箱认证
                openActivity(BindEmailActivity.class);
                break;
            case R.id.ll_act_myaccount_name://实名认证
                openActivity(RealNameAuthenticationHuiFuActivity.class);
                break;
            case R.id.ll_act_myaccount_login://登陆密码
                openActivity(ChangePasswordActivity.class);
                break;
            case R.id.ll_act_myaccount_gesture://手势密码
                if (isOpen) {
                    showCloseDialog();
                } else {
                    showOpenDialog();
                }

//                if (count % 2 == 1) {
//                    tvOC.setBackgroundResource(R.drawable.act_myaccount_open);
//                } else {
//                    tvOC.setBackgroundResource(R.drawable.act_myaccount_close);
//                }
//                count++;
                break;
            case R.id.tv_act_myaccount_signout://退出登陆
                SharedPreferencesUtils.getInstance(MyAccountActivity.this, Constants.USER_CONFIG)
                        .put(Constants.SHARE_LOGINTOKEN, "");
                UserConfig.getInstance().setLoginToken("");
                SharedPreferencesUtils.getInstance(this, "popshow").put("show", true);
                openActivity(HomeActivity.class);
                closeActivity();
                break;
            case R.id.act_myaccount_paishe://拍照
                dialog.dismiss();
                // TODO: 2017/9/14 此处权限申请需真机测试,不同机型对应不同的处理方案

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED && requestCount == 0) {
                    requestCount++;
                    ToastUtils.showToast(this, "需要相机使用权限,否则无法使用此功能,请在应用权限设置中进行授权!");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED && requestCount != 0) {

                    requestCount++;
                    ToastUtils.showToast(this, "需要相机使用权限,否则无法使用此功能,请在应用权限设置中进行授权!");

                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                    startActivity(localIntent);
                    /**
                     * Intent intentFromCapture = newIntent(MediaStore.ACTION_IMAGE_CAPTURE);
                     File file = newFile(Environment.getExternalStorageDirectory().getPath()+"/wood/head/");
                     //是否是文件夹，不是就创建文件夹
                     if (!file.exists()) file.mkdirs();
                     //指定保存路径
                     cameraPath = Environment.getExternalStorageDirectory().getPath()+"/wood/head/" +
                     format.format(new Date()) + ".jpg";
                     File imageFile = new File(cameraPath);
                     //创建一个图片保存的Uri
                     Uri imageFileUri = Uri.fromFile(imageFile);
                     intentFromCapture.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                     //设置MediaStore.EXTRA_OUTPUT的输出路径为imageFileUri
                     intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                     startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);*/


                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(intent, 1);
                    } catch (ActivityNotFoundException e) {
                        ToastUtils.showToast(this, "未能找到相机!");
                    }
                }
                break;
            case R.id.act_myaccount_xiangcexuanqu://相册选取
                dialog.dismiss();
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setImageLoader(new GlideImageLoaderUtils());
                imagePicker.setMultiMode(true);   //多选
                imagePicker.setShowCamera(true);  //显示拍照按钮
                imagePicker.setSelectLimit(1);    //最多选择9张
                imagePicker.setCrop(false);       //不进行裁剪
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, 2);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName = bundle.getString("name");
            iconurl = bundle.getString("iconurl");
        }

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

        //---------------------------------------------手势密码相关---------------------------------
        if (UserConfig.getInstance().getLockPass(this)) {//已开启
            isOpen = true;
            tvOC.setBackgroundResource(R.drawable.act_myaccount_open);
        } else {
            isOpen = false;
            tvOC.setBackgroundResource(R.drawable.act_myaccount_close);
        }
        //---------------------------------------------手势密码相关---------------------------------


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
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.MY_ACCOUNT, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的账户接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyAccountBean>() {
                        }.getType(), MyAccountBean.class, MyAccountActivity.this);
                        if (bean1 != null) {
                            bean = (MyAccountBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(MyAccountActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyAccountActivity.this, R.string.huoquzhanghuxinxishibai);

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            ImageGlideUtils.loadCircularImage(ivIcon, iconurl + "?aa=" + System.currentTimeMillis());
            tvUserName.setText(userName);
            if (bean.getIsVip() == 1) {//是否是vip
                ivVip.setImageResource(R.drawable.frag_my_vipt);
            } else {
                ivVip.setImageResource(R.drawable.frag_my_vip);
            }





            UserConfig.getInstance().setBindPhone(bean.getPhone());

            tvBankcardDescription.setText(bean.getBankName());
            tvHuifuDescription.setText(bean.getTrust_account().getStatus_name());
            tvPhoneDescription.setText(bean.getIs_phone().getStatus_name());
            tvEmailDescription.setText(bean.getIs_email().getStatus_name());
            tvNameDescription.setText(bean.getIs_realname().getStatus_name());


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            Bitmap photo = data.getParcelableExtra("data");//获取拍照图片
            if (photo != null) {
                ivIcon.setImageBitmap(photo);
                String imPath = FileUtils.saveImageToGallery(MyAccountActivity.this, Environment.getExternalStorageDirectory()+"/temp");
                postImage(imPath);
                // TODO: 2017/9/11 将拍照图片设置为头像并上传服务器,无裁剪----------------------------------
            } else {
                ToastUtils.showToast(this, "没有获取到拍照图片!");
            }
        } else if (requestCode == 2 && data != null) {

            ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (imageItems != null && imageItems.size() > 0) {
                postImage(imageItems.get(0).path);
                // TODO: 2017/11/9 是否将头像文件保存到本地???
            } else {
                ToastUtils.showToast(MyAccountActivity.this, R.string.meiyouxuanzhongtupian);
            }

        } else if (requestCode == 111) {//开启手势密码是否成功
            if (data != null && data.getBooleanExtra("result", false)) {
                tvOC.setBackgroundResource(R.drawable.act_myaccount_open);
                isOpen = true;
            }

        } else if (requestCode == 222 && data!=null&&data.getBooleanExtra("result", false)) {//关闭手势密码是否成功
            tvOC.setBackgroundResource(R.drawable.act_myaccount_close);
            isOpen = false;
        }else if (requestCode==222){
            openActivity(HomeActivity.class);
        }


    }


    private void postImage(final String path) {
        showPDialog();
        HttpMethods.getInstance().postFile(this, Constants.POST_FILE, UserConfig.getInstance().getLoginToken(this), path,
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        Log.e("TAG", "onSuccess: ---图片存储路径--" + response.body());
                        if (response.body().contains("200")) {
                            ToastUtils.showToast(MyAccountActivity.this, R.string.tupianshangchuanchenggong);
                            ImageGlideUtils.loadCircularImage(ivIcon, path);
                            new Thread() {
                                @Override
                                public void run() {
                                    String imPath = FileUtils.saveImageToGallery(MyAccountActivity.this, path);
                                    Log.e("TAG", "run: -图片存储路径--imPath--" + imPath);

                                }
                            }.start();
                        } else {
                            ToastUtils.showToast(MyAccountActivity.this, R.string.tupianshangchuanshibai);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyAccountActivity.this, R.string.tupianshangchuanshibai);
                        Log.e("TAG", "onSuccess: ---图片存储路径--" + response.body());
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.e("TAG", "uploadProgress: --图片存储路径-progress--" + progress);

                    }
                });
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

    private void showOpenDialog() {
        if (dialogOpen == null) {
            dialogOpen = DialogUtils.showDialogS(this, R.string.jijiangkaiqishoushimima, R.string.queding, R.string.kaiqishoushimima, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", userName);
                    bundle.putString("type", "open");
                    openActivityForResult(DrawGestureActivity.class, bundle, 111);//打开绘制手势密码界面
                }
            });

        } else {
            dialogOpen.show();
        }

    }

    private void showCloseDialog() {
        if (dialogClose == null) {
            dialogClose = DialogUtils.showDialogS(this, R.string.jijiangguanbishoushimima, R.string.queding, R.string.guanbishoushimima, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", userName);
                    bundle.putString("type", "close");
                    openActivityForResult(LockActivity.class, bundle, 222);//打开手势密码界面
                }
            });
        } else {
            dialogClose.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserConfig.getInstance().setDraw(true);
    }
}


/**

 -------------------------------------------------------------------------------------------------
 onActivityResult（）方法中获取数据
 -------------------------------------------------------------------------------------------------
 /相机的请求编码
 case CAMERA_REQUEST_CODE:
 isCamera =true;
 startPhotoZoom(Uri.fromFile(new File(cameraPath)));
 break;
 //相机、相册的图片再 剪辑完 在这地方上传
 case RESULT_REQUEST_CODE:
 if (isCamera){
 upLoadPictrue(new File(cameraPath));
 } else {
 String path = null;
 if (android.os.Build.VERSION_CODES.KITKAT >= 19) {
 path = new GetPicPath().getPath_above19(getActivity(), uri1);
 }else {
 path = getFilePath_below19(uri1);
 }
 upLoadPictrue(new File(path));
 }
 break;

 */
