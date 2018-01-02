package com.tudoujf.activity.common;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.activity.other.RegisterActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            WebActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/18
 * description：(webview+title)组合的activity
 * history：
 * ===================================================
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.mtb_act_web)
    MTopBarView mtbActWeb;
    @BindView(R.id.wv_act_web)
    WebView wvActWeb;

    private  String  url;
    private  String  title;

    @Override
    public int getLayoutResId() {
        return R.layout.act_web;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
//        url="https://www.baidu.com/";
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActWeb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActWeb.setLayoutParams(params);

        mtbActWeb.setCenterTitle(title);


        wvActWeb.setWebViewClient(new MyWebClient());
        WebSettings ws = wvActWeb.getSettings();
        ws.setAllowFileAccess(true);
        ws.setSupportZoom(true);
        ws.setJavaScriptEnabled(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        ws.setBuiltInZoomControls(true);

//        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        ws.setSaveFormData(true);
//        ws.setGeolocationEnabled(true);
        ws.setDomStorageEnabled(true);
//        wvActWeb.requestFocus();
//        wvActWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        wvActWeb.loadUrl(url);
    }

    @Override
    public void initListener() {
        mtbActWeb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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


    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dismissPDialog();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.contains(Constants.XIN_SHOU_FU_LI_ZHU_CE)){
                openActivity(RegisterActivity.class);
                closeActivity();
            }else if (url.contains(Constants.LOGIN_CLICK)){
                openActivity(LoginActivity.class);
                closeActivity();
            }else {
                showPDialog();
            }
            Log.e("TAG", "onPageStarted: ---url--"+url);
        }



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (wvActWeb.canGoBack()){
            wvActWeb.goBack();
        }else {
            closeActivity();
        }

    }

    @Override
    protected void onDestroy() {
        if(wvActWeb!=null){
            wvActWeb.destroy();
        }
        super.onDestroy();
    }
}
