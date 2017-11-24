package com.tudoujf.mynotebook;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tudoujf.mynotebook.base.BaseActivity;
import com.tudoujf.mynotebook.data.TextRecordBean;
import com.tudoujf.mynotebook.data.TotalBean;
import com.tudoujf.mynotebook.utils.TimeUtils;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
/**
 * * ==================================================
 * name:            TextRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/11/24
 * description：   文本记录activity
 * history：
 * * ==================================================
 */
public class TextRecordActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_main)
    EditText etMain;
    @BindView(R.id.et_endtime)
    EditText etendTime;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private SQLiteDatabase db;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save://保存信息
                List<TotalBean> listTotal = DataSupport.findAll(TotalBean.class);//当前记录总条数
                long currentId;
                if (listTotal.size() == 0) {//不存在记录时
                    TotalBean totalBean = new TotalBean();
                    totalBean.setTotal(0);
                    totalBean.setMoneyTotal(0);
                    totalBean.save();
                    currentId = 0;
                    listTotal = DataSupport.findAll(TotalBean.class);//重新获取存储的总条数数据
                } else {//存在记录时
                    currentId = listTotal.get(0).getTotal();
                }
                TextRecordBean bean = new TextRecordBean();
                bean.setTitle("二牛");
                bean.setSaveId(currentId + 1);
                if (bean.save()) {
                    listTotal.get(0).setTotal(currentId + 1);
                    listTotal.get(0).save();
                }



                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {
        db = LitePal.getDatabase();
    }

    @Override
    public void initView() {
        List<TextRecordBean> list = DataSupport.findAll(TextRecordBean.class);
        if (list.size() > 0) {
            etTitle.setText(list.get(0).getTitle());
            Log.e("TAG", "initView: -----" + list.get(0).getTitle());
        }

        etendTime.setText(TimeUtils.getNowDateShort());


    }

    @Override
    public void initListener() {
        tvSave.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
