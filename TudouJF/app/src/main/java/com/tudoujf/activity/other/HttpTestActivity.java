package com.tudoujf.activity.other;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.TimeUtils;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            HttpTestActivity
 * guide:          WelcomeActivity-->GuideActivity--->HttpTestActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description： okhttp测试用activity
 * history：
 * ===================================================
 */

public class HttpTestActivity extends BaseActivity {
    @BindView(R.id.tv_act_httptest1)
    TextView tvActHttptest1;

    @BindView(R.id.tv_act_httptest4)
    TextView tvActHttptest4;
    @BindView(R.id.et_canshu_interface)
    EditText etCanshuInterface;
    @BindView(R.id.et_canshu_key1)
    EditText etCanshuKey1;
    @BindView(R.id.et_canshu_value1)
    EditText etCanshuValue1;
    @BindView(R.id.et_canshu_key2)
    EditText etCanshuKey2;
    @BindView(R.id.et_canshu_value2)
    EditText etCanshuValue2;
    @BindView(R.id.et_canshu_key3)
    EditText etCanshuKey3;
    @BindView(R.id.et_canshu_value3)
    EditText etCanshuValue3;
    @BindView(R.id.et_canshu_key4)
    EditText etCanshuKey4;
    @BindView(R.id.et_canshu_value4)
    EditText etCanshuValue4;
    @BindView(R.id.et_canshu_key5)
    EditText etCanshuKey5;
    @BindView(R.id.et_canshu_value5)
    EditText etCanshuValue5;
    @BindView(R.id.et_canshu_key6)
    EditText etCanshuKey6;
    @BindView(R.id.et_canshu_value6)
    EditText etCanshuValue6;
    @BindView(R.id.clear1)
    TextView clear1;
    @BindView(R.id.clear2)
    TextView clear2;
    @BindView(R.id.clear3)
    TextView clear3;
    @BindView(R.id.clear4)
    TextView clear4;
    @BindView(R.id.clear5)
    TextView clear5;
    @BindView(R.id.clear6)
    TextView clear6;
    @BindView(R.id.clear7)
    TextView clear7;

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        etCanshuKey1.setText("login_token");
        etCanshuKey2.setText("loan_id ");
        etCanshuKey3.setText("page_count");
        etCanshuKey4.setText("page");
        etCanshuKey5.setText("start_time");
        etCanshuKey6.setText("end_time");
        etCanshuValue1.setText("12267");

        etCanshuInterface.setText("/phone/loan/loanInfo");

    }

    @Override
    public void initListener() {
        tvActHttptest4.setOnClickListener(this);
        clear1.setOnClickListener(this);
        clear2.setOnClickListener(this);
        clear3.setOnClickListener(this);
        clear4.setOnClickListener(this);
        clear5.setOnClickListener(this);
        clear6.setOnClickListener(this);
        clear7.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        SensorManager manager= (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors=manager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < sensors.size(); i++) {
            Log.e("TAG", "onStart: ---------------------"+sensors.get(i).getName());
            builder.append(sensors.get(i).getName()).append("\n");
        }

        tvActHttptest1.setText(builder.toString());
    }

    //
    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_httptest;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_httptest4:
                tvActHttptest1.setText("");
                httpSend();
                break;
            case R.id.clear1:
                etCanshuInterface.setText("");
                break;
            case R.id.clear2:
                etCanshuKey1.setText("");
                etCanshuValue1.setText("");
                break;
            case R.id.clear3:
                etCanshuKey2.setText("");
                etCanshuValue2.setText("");
                break;
            case R.id.clear4:
                etCanshuKey3.setText("");
                etCanshuValue3.setText("");
                break;
            case R.id.clear5:
                etCanshuKey4.setText("");
                etCanshuValue4.setText("");
                break;
            case R.id.clear6:
                etCanshuKey5.setText("");
                etCanshuValue5.setText("");
                break;
            case R.id.clear7:
                etCanshuKey6.setText("");
                etCanshuValue6.setText("");
                break;
        }
    }

    private void httpSend() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put(etCanshuKey1.getText().toString().trim(), etCanshuValue1.getText().toString().trim());
        map.put(etCanshuKey2.getText().toString().trim(), etCanshuValue2.getText().toString().trim());
        map.put(etCanshuKey3.getText().toString().trim(), etCanshuValue3.getText().toString().trim());
        map.put(etCanshuKey4.getText().toString().trim(), etCanshuValue4.getText().toString().trim());
        map.put(etCanshuKey5.getText().toString().trim(), etCanshuValue5.getText().toString().trim());
        map.put(etCanshuKey6.getText().toString().trim(), etCanshuValue6.getText().toString().trim());
        String url;

        for (String  s:map.keySet()){
            Log.e("TAG", "httpSend:----"+s+"-------- "+map.get(s));
        }


        url = Constants.URL + etCanshuInterface.getText().toString().trim().replace(" ", "");

        HttpMethods.getInstance().POST(this, url, map, "test", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (!etCanshuInterface.getText().toString().trim().equals("")) {
                    Log.e("TAG", "onSuccess: --------接口测试返回响应------" + response.body());
                    String result="";
                    try {
                        result = StringUtils.getDecodeString(response.body());
                        tvActHttptest1.setText(result);
                    }catch (Exception e){
                        tvActHttptest1.setText(response.body());
                    }

                    if (result.contains("\"code\":200")){
                        FileUtils.saveJsonToSDCard(HttpTestActivity.this,"数据数据"+ TimeUtils.getCurentTimeTotal()+".txt",result);
                    }

                    //   /phone/loan/loanInfo  loan_id,292
                    Log.e("TAG", "onSuccess:-----------接口测试返回结果---------- " + result);
                } else {
                    tvActHttptest1.setText(response.body());
                }


            }

            @Override
            public void onError(Response<String> response) {
                tvActHttptest1.setText(response.message());
                super.onError(response);
            }
        });


    }



}
