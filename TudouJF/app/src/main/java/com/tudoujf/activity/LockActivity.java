package com.tudoujf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ImageGlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            LockActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：锁屏页activity
 * history：
 * ===================================================
 */
public class LockActivity extends BaseActivity {
    @BindView(R.id.iv_act_lock_icon)
    ImageView ivIcon;

    @Override
    public int getLayoutResId() {
        return R.layout.act_lock;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        ImageGlideUtils.loadCircularImage(ivIcon,R.drawable.act_lock2_icon);
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


}
