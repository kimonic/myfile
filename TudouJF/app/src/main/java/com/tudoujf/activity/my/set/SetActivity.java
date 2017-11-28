package com.tudoujf.activity.my.set;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.other.PreviewActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.ll_act_set_kefudianhua)
    LinearLayout llActSetPhone;
    @BindView(R.id.ll_act_set_help)
    LinearLayout llActSetHelp;
    @BindView(R.id.ll_act_set_feedback)
    LinearLayout llActSetFeedback;
    @BindView(R.id.ll_act_set_checkupdate)
    LinearLayout llActSetCheckUpdate;
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
                count++;
                if (count == 10) {
                    openActivity(PreviewActivity.class);
                }
//                ToastUtils.showToast(SetActivity.this, R.string.jijiangkaiqijingqingqidai);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvActSetTitle.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        tvActSetTitle.setLayoutParams(params);

        view = LayoutInflater.from(this).inflate(R.layout.dialog_phone, null);

    }

    @Override
    public void initListener() {
        llActSetPhone.setOnClickListener(this);
        llActSetCheckUpdate.setOnClickListener(this);
        llActSetHelp.setOnClickListener(this);
        llActSetFeedback.setOnClickListener(this);
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



