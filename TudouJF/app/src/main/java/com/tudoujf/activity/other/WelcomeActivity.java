package com.tudoujf.activity.other;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.MD5Utils;
import com.tudoujf.utils.PermissionUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            WelcomeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/7
 * description：  欢迎页activity
 * history：
 * ===================================================
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.iv_act_welcome)
    ImageView ivActWelcome;
    /**
     * 是否有锁屏密码
     */
    private boolean isLock;
    /**
     * 是否是第一次打开app
     */
    private boolean isgudie;
    /**
     * 是否有权限未授予
     */
    private boolean permissionGranted;
    private List<String> list;
    /**
     * 从应用详情返回
     */
    private boolean detailsBack = false;


    @Override
    public int getLayoutResId() {
        return R.layout.act_welcome;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initDataFromIntent() {
        isgudie = SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).getBoolean("isguide", false);
        isLock = UserConfig.getInstance().getLockPass(this);


    }

    @Override
    public void initView() {
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivActWelcome.getLayoutParams();
//        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
//        ivActWelcome.setLayoutParams(params);


        //-------------------------------检查权限授予情况-------------------------------------------
        if (!isgudie) {
            list = new ArrayList<>();
            list = PermissionUtils.checkAPPNeedPermission(this);
            permissionGranted = list.size() > 0;
        }
        //-------------------------------检查权限授予情况-------------------------------------------

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (permissionGranted) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(WelcomeActivity.this);
                    dialog.setMessage(R.string.qxjc)
                            .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    PermissionUtils.requestPerssions(WelcomeActivity.this, 111, list);
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    start();
                                }
                            }).show();
                } else {
                    start();
                }

            }
        }, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionUtils.OnRequestPermissionsResultCallbacks() {
            @Override
            public void requestResult(int requestCode, List<String> perms) {
                if (perms.size() > 0) {
//-----------------------------------------待删除-------------------------------------------------------
//                    for (int i = 0; i < perms.size(); i++) {
//                        ToastUtils.showToast(WelcomeActivity.this, perms.get(i));
//                    }

//-----------------------------------------待删除-------------------------------------------------------

                    AlertDialog.Builder dialog = new AlertDialog.Builder(WelcomeActivity.this);
                    dialog.setMessage(R.string.bufenquanxianshouquanshibai)
                            .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    openDetails();
                                    detailsBack = true;
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    start();
                                }
                            }).show();
                } else {
                    start();
                }
            }
        });


    }

    /**
     * 开启应用详情界面
     */
    private void openDetails() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
    }

    /**
     * 启动引导页,手势密码页或者主页的逻辑判断
     */
    private void start() {
        if (!isgudie) {//第一次打开
            Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
            startActivity(intent);
        } else if (isLock) {//有手势密码
            String loginToken = UserConfig.getInstance().getLoginToken(WelcomeActivity.this);
            String name = MD5Utils.md5(loginToken);
            long start = SharedPreferencesUtils.getInstance(WelcomeActivity.this,
                    Constants.USER_CONFIG).getLong(name + "startlocktime", 0);
            int surplus;
            if (System.currentTimeMillis() - start < 300000) {//锁定时间未满
                surplus = 300 - (int) ((System.currentTimeMillis() - start) / 1000);
            } else {
                surplus = 0;
            }
            Intent intent = new Intent(WelcomeActivity.this, LockActivity.class);
            intent.putExtra("surplus", surplus);
            startActivity(intent);

        } else {//无手势密码
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        WelcomeActivity.this.finish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.welcome_statusbar_color);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (detailsBack) {
            start();
        }

    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
