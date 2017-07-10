package com.tudoujf.activity;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;

/**
 * * ================================================
 * name:            LoginActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：登陆页activity
 * history：
 * ===================================================
 */

public class LoginActivity extends BaseActivity {
    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
/**
 *
 * input.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置密文明显示
 *
 * 密文设置：
 EditText  et = (EditText)findViewById(R.id.et);
 TransformationMethod method =  PasswordTransformationMethod.getInstance();
 et.setTransformationMethod(method);
 明文设置：
 EditText et = (EditText)findViewById(R.id.et);
 HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance();
 et.setTransformatinMethod(method);
 * //监听edittext的输入文本变化
 dialogEdittext.addTextChangedListener(new TextWatcher() {
@Override//s--本次输入之前edittext中已存在的字符串,本次输入开始位置,start--已存在的字符个数,after--本次输入的字符个数
public void beforeTextChanged(CharSequence s, int start, int count, int after) {
Log.e(Constant.TAG, "beforeTextChanged: ---"+s+"----"+start+"----"+after );

}

@Override//s----输入完成之后的字符串,start---本次输入开始的位置,count--本次输入的字符个数,before=1本次有删除,before=0,本次无删除
public void onTextChanged(CharSequence s, int start, int before, int count) {
Log.e(Constant.TAG, "onTextChanged: ---"+s+"----"+start+"----"+before+"-----"+count );

if (count==0){
dialogTextview.setBackgroundResource(R.drawable.textroundbac);
}


}

@Override//s----输入完成之后的字符串
public void afterTextChanged(Editable s) {
Log.e(Constant.TAG, "afterTextChanged: ---"+s);

if (dialogEdittext.getText().toString().length()>0){
dialogTextview.setBackgroundResource(R.drawable.textroundbac1);
}

}
});
 */
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_login;
    }
}
