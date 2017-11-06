package com.tudoujf.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_act_main_fanxian)
    EditText etFanXian;
    @BindView(R.id.et_act_main_touzijine)
    EditText etTouZiJinE;
    @BindView(R.id.et_act_main_nianhuashouyi)
    EditText etNianHuaShouYi;
    @BindView(R.id.et_act_main_touzishijian)
    EditText etTouZiShiJian;
    @BindView(R.id.tv_act_main_zongnianhuashouyi)
    TextView tvZongNianhuaShouYi;
    @BindView(R.id.tv_act_main_calculator)
    TextView tvCalculator;


    @BindView(R.id.et_act_main_tianshu)
    EditText etTianshu;
    @BindView(R.id.tv_act_main_translate)
    TextView tvTranslate;
    @BindView(R.id.et_act_main_tianshu1)
    EditText etTianshu1;
    @BindView(R.id.tv_act_main_translate1)
    TextView tvTranslate1;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        decimalFormat = new DecimalFormat("0.0000");
        initListener();

    }

    private void initListener() {
        tvCalculator.setOnClickListener(this);
        tvTranslate.setOnClickListener(this);
        tvTranslate1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_main_calculator:
                float fanxian = stringTofloat(etFanXian.getText().toString().trim());
                float nianhuashouyi = stringTofloat(etNianHuaShouYi.getText().toString().trim()) / 100;
                float touzishijian = stringTofloat(etTouZiShiJian.getText().toString().trim());
                float touzijine = stringTofloat(etTouZiJinE.getText().toString().trim());

                float temp = (fanxian + touzijine * nianhuashouyi * touzishijian /12)*365/30 / touzishijian/ touzijine * 100;
                tvZongNianhuaShouYi.setText((decimalFormat.format(temp) + "%"));

                break;
            case R.id.tv_act_main_translate:
                etTouZiShiJian.setText((""+stringTofloat(etTianshu.getText().toString().trim())/30));
                etTianshu1.setText("");
                break;
            case R.id.tv_act_main_translate1:
                etTianshu.setText("");
                etTouZiShiJian.setText((""+stringTofloat(etTianshu1.getText().toString().trim())/31));
                break;
        }

    }

    private float stringTofloat(String str) {

        if (str == null||str.equals("")) {
            return 0;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

    }
}