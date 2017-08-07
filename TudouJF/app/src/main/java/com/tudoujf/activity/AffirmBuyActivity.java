package com.tudoujf.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.PasswordView;
import com.tudoujf.utils.KeyboardChangeListener;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            AffirmBuyActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：确认购买activity
 * history：
 * ===================================================
 */

public class AffirmBuyActivity extends BaseActivity {
    @BindView(R.id.mtb_act_affirmbuy)
    MTopBarView mtbAffirmbuy;
    @BindView(R.id.ll_act_affirmbuy_redpackage)
    LinearLayout llRedpackage;
    @BindView(R.id.ll_act_affirmbuy_jiaxiquan)
    LinearLayout llJiaxiquan;
    @BindView(R.id.ll_act_affirmbuy_fanxianquan)
    LinearLayout llFanxianquan;
    @BindView(R.id.tv_act_affirmbuy_affirmbuy)
    TextView tvAffirmbuy;
    @BindView(R.id.et_act_affirm_touzijine)
    EditText etTouZiJinE;

    @Override
    public int getLayoutResId() {
        return R.layout.act_affirmbuy;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_affirmbuy_fanxianquan://跳转返现券页面
                openActivity(FanXianQuanSelActivity.class);
                break;
            case R.id.ll_act_affirmbuy_jiaxiquan://跳转加息券界面
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 2);
                openActivity(RedPackageActivity.class, bundle1);
                break;
            case R.id.ll_act_affirmbuy_redpackage://跳转红包界面
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                openActivity(RedPackageActivity.class, bundle);
                break;
            case R.id.tv_act_affirmbuy_affirmbuy://确认购买按钮
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbAffirmbuy.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbAffirmbuy.setLayoutParams(params);

        mtbAffirmbuy.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


        Log.e("TAG1212", "isShow = -----------------------");

        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.e("TAG1212", "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
            }
        });
        etTouZiJinE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
// 可捕捉右下角的Return按钮,监听回车按钮
//添加抛出收起事件代码
                Log.e("TAG1212", "???????????????????????????????????????");
                return false;
            }
        });
//        etTouZiJinE.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.e("TAG1212", "onKey: -----------------------------------");
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    return true;
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void initListener() {
        llFanxianquan.setOnClickListener(this);
        llJiaxiquan.setOnClickListener(this);
        llRedpackage.setOnClickListener(this);
        tvAffirmbuy.setOnClickListener(this);


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
