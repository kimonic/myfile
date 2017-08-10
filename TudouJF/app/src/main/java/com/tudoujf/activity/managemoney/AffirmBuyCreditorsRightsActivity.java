package com.tudoujf.activity.managemoney;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.PasswordView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            AffirmBuyCreditorsRightsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：债权详情,确认购买activity
 * history：
 * ===================================================
 */

public class AffirmBuyCreditorsRightsActivity extends BaseActivity {


    @BindView(R.id.mtb_act_affirmbuycreditorsright)
    MTopBarView mtbAffirmBuyCreditorsRight;
    @BindView(R.id.tv_act_affirmbuycreditors_affirmbuyright)
    TextView tvAffirmBuyCreditorsRight;

    @Override
    public int getLayoutResId() {
        return R.layout.act_affirmbuycreditorsright;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_act_affirmbuycreditors_affirmbuyright://确认购买按钮
                showPasswordDialog();
                break;
        }
    }

    private void showPasswordDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_act_affirmbuy, null);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        final PopupWindow pop = new PopupWindow(view, ScreenSizeUtils.getScreenWidth(this) - 180, 500);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点
        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置


        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

        final PasswordView passwordView = (PasswordView) view.findViewById(R.id.pv_act_affirmbuy_dialog);
        TextView textView = (TextView) view.findViewById(R.id.tv_act_affirmbuy_dialog);
        passwordView.setBtnText(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        textView.setClickable(false);


    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbAffirmBuyCreditorsRight.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbAffirmBuyCreditorsRight.setLayoutParams(params);

        mtbAffirmBuyCreditorsRight.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


    }

    @Override
    public void initListener() {
        tvAffirmBuyCreditorsRight.setOnClickListener(this);


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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
