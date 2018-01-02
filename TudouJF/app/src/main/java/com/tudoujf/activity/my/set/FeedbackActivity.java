package com.tudoujf.activity.my.set;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            FeedbackActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/13
 * description：  意见反馈activity
 * history：
 * * ====================================================================
 */

public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.mtb_act_feedback)
    MTopBarView mtbActFeedback;
    @BindView(R.id.et_act_feedback_content)
    EditText etContent;
    @BindView(R.id.et_act_feedback_contact)
    EditText etContact;
    @BindView(R.id.tv_act_feedback_submit)
    TextView tvSubmit;

    @Override
    public int getLayoutResId() {
        return R.layout.act_feedback;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_act_feedback_submit://提交
                if ("".equals(etContent.getText().toString()) || etContent.getText().toString().length() > 200) {
                    ToastUtils.showToast(FeedbackActivity.this, R.string.qsrfkyjbqfkyjbncglbz);
                } else if ("".equals(etContact.getText().toString())) {
                    ToastUtils.showToast(FeedbackActivity.this, R.string.qsrndlxfs);
                } else {
                    commitContent(etContent.getText().toString(), etContact.getText().toString());
                }
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFeedback.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFeedback.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActFeedback.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    private void commitContent(String content, String contact) {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("content", content);
        map.put("contact", contact);
        HttpMethods.getInstance().POST(this, Constants.FEEDBACK, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----反馈意见接口返回数据--------------" + result);
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        if (bean != null) {
                            if ("200".equals(bean.getCode())) {
                                ToastUtils.showToast(FeedbackActivity.this, R.string.fankuiyijiantijiaochenggong);
                                closeActivity();
                            } else {
                                ToastUtils.showToast(FeedbackActivity.this, bean.getDescription().toString());
                            }
                        } else {
                            ToastUtils.showToast(FeedbackActivity.this, R.string.fankuiyijiantijiaoshibai);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(FeedbackActivity.this, R.string.fankuiyijiantijiaoshibai);
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
}
