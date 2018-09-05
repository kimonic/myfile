package com.tudoujf.activity.my.set;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.other.PreviewActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.NewVersionBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.AppInfoUtils;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.DownloadAppUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            SetActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/13
 * description：  设置activity
 * history：
 * * ====================================================================
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.tv_act_set_title)
    TextView tvActSetTitle;
    @BindView(R.id.tv_act_set_versionname)
    TextView tvVersionName;
    @BindView(R.id.ll_act_set_kefudianhua)
    LinearLayout llActSetPhone;
    @BindView(R.id.ll_act_set_help)
    LinearLayout llActSetHelp;
    @BindView(R.id.ll_act_set_feedback)
    LinearLayout llActSetFeedback;
    @BindView(R.id.ll_act_set_checkupdate)
    LinearLayout llActSetCheckUpdate;
    //
// TODO: 2018/8/15  打开测试界面按钮
    @BindView(R.id.test)
    TextView openPreview;

    private View view;
    private AlertDialog dialog;
    private int count = 0;


    @Override
    public int getLayoutResId() {
        return R.layout.act_set;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_set_kefudianhua://客服电话
                if (dialog == null) {
                    dialog = showCustomDialog(view);
                } else {
                    dialog.show();
                }
                break;
            case R.id.ll_act_set_help://帮助中心
                openActivity(QuestionClassificationActivity.class);
                break;
            case R.id.ll_act_set_feedback://意见反馈
                openActivity(FeedbackActivity.class);
                break;
            case R.id.ll_act_set_checkupdate://检查更新
                checkNew();
                break;
            //打开测试界面
            // TODO: 2018/8/15 打开测试界面
            case R.id.test:
                count++;
                if (count == 10) {
                    openActivity(PreviewActivity.class);
                }
                break;
        }

    }

    private void checkNew() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("phone_type", "1");
        HttpMethods.getInstance().POST(this, Constants.NEW_VERSION, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(SetActivity.class, "logflag-检查版本更新接口返回数据--" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<NewVersionBean>() {
                        }.getType(), NewVersionBean.class, SetActivity.this);
                        if (bean1 != null) {
                            NewVersionBean bean = (NewVersionBean) bean1;

                            String versionName = AppInfoUtils.getVersionName(SetActivity.this);

                            final String url;
                            if (bean.getUrl() != null) {
                                url = bean.getUrl();
                            } else {
                                url = "";
                            }
                            if (versionName.equals(bean.getNew_version())) {
                                DialogUtils.showPromptDialog(SetActivity.this, "提示", "当前已是最新版本!", null);
                            } else {
                                DialogUtils.showPromptDialog(SetActivity.this, "提示", "发现APP有新版本,是否前往更新?",
                                        new DialogUtils.DialogUtilsClickListener() {
                                            @Override
                                            public void onClick() {
                                                DownloadAppUtils.downloadForWebView(SetActivity.this, url);
                                            }
                                        });
                            }
                        } else {
                            ToastUtils.showToast(SetActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(SetActivity.this, R.string.jianchagengxinshiabai);
                    }
                });
    }

    @Override
    public void initDataFromIntent() {

    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvActSetTitle.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        tvActSetTitle.setLayoutParams(params);

        tvVersionName.setText(("土豆金服Ver" + AppInfoUtils.getVersionName(this)));

        view = LayoutInflater.from(this).inflate(R.layout.dialog_phone, null);
        TextView tvCall1 = view.findViewById(R.id.tv_call1);
        TextView tvCall2 = view.findViewById(R.id.tv_call2);
        tvCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        tvCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(getResources().getString(R.string.phone)));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            ToastUtils.showToast(SetActivity.this, R.string.chucuola);
        }
    }

    @Override
    public void initListener() {
        llActSetPhone.setOnClickListener(this);
        llActSetCheckUpdate.setOnClickListener(this);
        llActSetHelp.setOnClickListener(this);
        llActSetFeedback.setOnClickListener(this);
        //20180528打开测试界面
        // TODO: 2018/8/15  打开测试界面按钮
        openPreview.setOnClickListener(this);
        //-------------------------待删除-----------------------------------------------
        llActSetCheckUpdate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        //-------------------------待删除-----------------------------------------------
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


    public AlertDialog showCustomDialog(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
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



