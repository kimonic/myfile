package com.tudoujf.utils;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.fragment.HomeFragment;
import com.tudoujf.http.HttpMethods;

import java.util.TreeMap;

/**
 * * ===============================================================
 * name:             DialogShowOrderUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/9/5
 * method:
 * <p>
 * <p>
 * description：  按照顺序弹出dialog
 * history：
 * *==================================================================
 */

public class DialogShowOrderUtils {
    //风险提示,电子印章dialog,生日红包dialog,版本更新dialog,重要通知dialog

    private HomeActivity homeActivity;
    private boolean riskFlag = false;
    private boolean birthdayFlag = false;
    private boolean updateFlag = false;
    //活动提示,重要通知,是否禁用app
    private boolean noticeFlag = false;
    private boolean updateForceFlag = false;

    private String url = "";
    private String name = "";
    private int count = 0;

    private DialogShowOrderUtils() {
    }

    private static class UtilsHolder {
        private static DialogShowOrderUtils INSTANCE = new DialogShowOrderUtils();
    }

    public static DialogShowOrderUtils getInstance() {
        return UtilsHolder.INSTANCE;
    }

    public void setRiskFlag(boolean riskFlag) {
        this.riskFlag = riskFlag;
    }

    public void setBirthdayFlag(boolean birthdayFlag, String name) {
        this.birthdayFlag = birthdayFlag;
        this.name = name;
    }

    public void setUpdateFlag(boolean updateFlag, String url) {
        this.updateFlag = updateFlag;
        this.url = url;
    }

    public void setNoticeFlag(boolean noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public void setUpdateForceFlag(boolean updateForceFlag, String url) {
        this.updateForceFlag = updateForceFlag;
        this.url = url;
    }

    private void showOrderDialog() {
        if (updateForceFlag) {
            DialogUtils.showPromptDialogAll(homeActivity, "提示",
                    "APP必须更新到最新版本,是否前往更新?",
                    new DialogUtils.DialogUtilsClickListener() {
                        @Override
                        public void onClick() {
                            DownloadAppUtils.downloadForWebView(homeActivity
                                    , url);
                            homeActivity.finish();
                        }
                    }, new DialogUtils.DialogUtilsClickListener() {
                        @Override
                        public void onClick() {
                            homeActivity.finish();
                        }
                    });
        }

        if (updateFlag) {
            DialogUtils.showPromptDialogAll(homeActivity, "提示", "发现APP有新版本," +
                            "是否前往更新?",
                    new DialogUtils.DialogUtilsClickListener() {
                        @Override
                        public void onClick() {
                            DownloadAppUtils.downloadForWebView(homeActivity, url);
                            showRiskDialog();
                        }
                    }, new DialogUtils.DialogUtilsClickListener() {
                        @Override
                        public void onClick() {
                            showRiskDialog();
                        }
                    });
        }
    }

    /**
     * 显示风险提示dialog
     */
    private void showRiskDialog() {
        if (riskFlag) {
            DialogUtils.showRiskDialog(homeActivity, new DialogUtils.DialogUtilsClickListener() {
                @Override
                public void onClick() {
                    showBirthdayDialog();
                }
            });
        } else {
            showBirthdayDialog();
        }
    }

    /**
     * 显示生日红包dialog
     */
    private void showBirthdayDialog() {
        if (birthdayFlag) {
            ToastUtils.showToast(homeActivity, name + ",生日快乐!");
            DialogUtils.showPromptDialog(homeActivity, homeActivity.getString(R.string.tishi),
                    name +
                    homeActivity.getString(R.string.shengritishi), new DialogUtils
                    .DialogUtilsClickListener() {
                @Override
                public void onClick() {
                    receiveRedPackage();
                }
            });
        } else {
            showNoticeDialog();
        }
    }

    /**
     * 显示通知dialog
     */
    private void showNoticeDialog() {
        if (noticeFlag) {
            ToastUtils.showToast(homeActivity, "显示重要通知dialog");
        }
    }

    public void setHomeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public void setCount() {
        count++;
        if (count == 3) {
            showOrderDialog();
        }
    }

    /**
     * 领取生日红包
     */
    private void receiveRedPackage() {
        homeActivity.showPDialog();
        TreeMap<String, String> map = new TreeMap<>();

        map.put("login_token", UserConfig.getInstance().getLoginToken(homeActivity));

        HttpMethods.getInstance().POST(homeActivity, Constants.BIRTHDAY_WELFARE, map, "", new
                StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        homeActivity.dismissPDialog();

                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(HomeFragment.class, "logflag--领取生日红包返回的json数据-" + result);

                        if (result != null && result.contains("\"code\":200")) {
                            ToastUtils.showToast(homeActivity, R.string.lingquchenggong);
                        } else {
                            ToastUtils.showToast(homeActivity, R.string.lignqushibai);
                        }
                        showNoticeDialog();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        homeActivity.dismissPDialog();
                        ToastUtils.showToast(homeActivity, R.string.lignqushibai);
                        showNoticeDialog();
                    }
                });
    }
}
