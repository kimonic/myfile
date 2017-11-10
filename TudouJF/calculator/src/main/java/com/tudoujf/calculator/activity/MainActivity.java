package com.tudoujf.calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tudoujf.calculator.R;

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
    @BindView(R.id.tv_act_main_changeyueshu)
    TextView tvChangeYueShu;
    @BindView(R.id.tv_act_main_changeunit)
    TextView tvChangeUnit;


    @BindView(R.id.et_act_main_tianshu)
    EditText etTianshu;
    @BindView(R.id.tv_act_main_translate)
    TextView tvTranslate;
    @BindView(R.id.et_act_main_tianshu1)
    EditText etTianshu1;
    @BindView(R.id.tv_act_main_translate1)
    TextView tvTranslate1;
    private DecimalFormat decimalFormat;

    /**
     * 一年中月的计算方式
     */
    private float yueShu = 12;

    private int count = 1;
    private int count1 = 0;

    /**
     * 当前单位
     */
    private int unit = 10000;


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
        tvChangeYueShu.setOnClickListener(this);
        tvChangeUnit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_main_calculator:
                float fanxian = stringTofloat(etFanXian.getText().toString().trim());
                float nianhuashouyi = stringTofloat(etNianHuaShouYi.getText().toString().trim()) / 100;
                float touzishijian = stringTofloat(etTouZiShiJian.getText().toString().trim());
                float touzijine = stringTofloat(etTouZiJinE.getText().toString().trim()) * unit;

                float temp = (fanxian + touzijine * nianhuashouyi * touzishijian / 12) * yueShu / touzishijian / touzijine * 100;
                Log.e("TAG", "onClick: -temp----" + temp);
                Log.e("TAG", "onClick: ---yueShu--" + yueShu);
                Log.e("TAG", "onClick: ---yueShu--" + yueShu);
                tvZongNianhuaShouYi.setText((decimalFormat.format(temp) + "%"));

                break;
            case R.id.tv_act_main_translate:
                etTouZiShiJian.setText(("" + stringTofloat(etTianshu.getText().toString().trim()) / 30));
                etTianshu1.setText("");
                break;
            case R.id.tv_act_main_translate1:
                etTianshu.setText("");
                etTouZiShiJian.setText(("" + stringTofloat(etTianshu1.getText().toString().trim()) / 31));
                break;
            case R.id.tv_act_main_changeyueshu://改变月的计算方式
                if (count % 2 == 0) {
                    yueShu = 12;
                    tvChangeYueShu.setText(R.string.dangqianyinianyi);
                } else {
                    yueShu = 365f / 30;
                    tvChangeYueShu.setText(R.string.dangqianyinianyi1);
                }
                count++;
                break;

            case R.id.tv_act_main_changeunit:
                if (count1 % 3 == 0) {
                    unit = 1;
                    tvChangeUnit.setText(R.string.touzijine1);

                } else if (count1 % 3 == 1) {
                    unit = 1000;
                    tvChangeUnit.setText(R.string.touzijine);

                } else if (count1 % 3 == 2) {
                    unit = 10000;
                    tvChangeUnit.setText(R.string.touzijine2);

                }
                count1++;
                break;
        }

    }

    private float stringTofloat(String str) {

        if (str == null || str.equals("")) {
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