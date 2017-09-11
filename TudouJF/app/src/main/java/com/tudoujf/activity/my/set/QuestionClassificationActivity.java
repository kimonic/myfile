package com.tudoujf.activity.my.set;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            QuestionClassificationActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/14
 * description：  帮助中心问题分类activity
 * history：
 * * ====================================================================
 */

public class QuestionClassificationActivity extends BaseActivity {
    @BindView(R.id.mtb_act_questionclassification)
    MTopBarView mtbActQuestionClassification;
    @BindView(R.id.lv_act_questionclassification)
    ListView lvActQuestionClassification;

    private int[]  strResId=new int[]{
            R.string.xinshouzhiyin,
            R.string.chongzhitixian,

            R.string.woyaolicai,
            R.string.woyaorongzi,

            R.string.zhaiquanzhuanrang,
            R.string.wangzhanfeiyong,

            R.string.falvfagui,
            R.string.mingcijieshi
    };

    private int[]  imageResId=new int[]{
            R.drawable.act_questionclassification_xinshouzhiyin,
            R.drawable.act_questionclassification_chongzhitixian,

            R.drawable.act_questionclassification_woyaolicai,
            R.drawable.act_questionclassification_woyaorongzi,

            R.drawable.act_questionclassification_zhaiquanzhuanrang,
            R.drawable.act_questionclassification_wangzhanfeiyong,

            R.drawable.act_questionclassification_falvfagui,
            R.drawable.act_questionclassification_mingcijieshi

    };

    private List<ResouseBean> list;


    @Override
    public int getLayoutResId() {
        return R.layout.act_questionclassification;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();

        for (int i = 0; i < strResId.length; i++) {
            ResouseBean bean=new ResouseBean();
            bean.setImageResId(imageResId[i]);
            bean.setTextResId(strResId[i]);
            list.add(bean);
        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActQuestionClassification.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActQuestionClassification.setLayoutParams(params);

        LVAdapter adapter=new LVAdapter();
        lvActQuestionClassification.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbActQuestionClassification.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        lvActQuestionClassification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(HelpCenterCommonActivity.class);
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


    private class ResouseBean{
        private int  textResId;
        private int imageResId;

        int getTextResId() {
            return textResId;
        }

        void setTextResId(int textResId) {
            this.textResId = textResId;
        }

        int getImageResId() {
            return imageResId;
        }

        void setImageResId(int imageResId) {
            this.imageResId = imageResId;
        }
    }

    private class LVAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView==null){
                view= LayoutInflater.from(QuestionClassificationActivity.this).inflate(R.layout.lvitem_questionclassifition,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.textView= (TextView) view.findViewById(R.id.tv_lvitem_questionclassificationact);
                viewHolder.imageView= (ImageView) view.findViewById(R.id.iv_lvitem_questionclassificationact);

                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }

            viewHolder.textView.setText(list.get(position).getTextResId());
            viewHolder.imageView.setImageResource(list.get(position).getImageResId());
            return view;
        }

        private  class ViewHolder{
            TextView textView;
            ImageView imageView;
        }

    }
}
