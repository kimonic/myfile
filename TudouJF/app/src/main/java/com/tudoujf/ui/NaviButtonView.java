package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * * ================================================
 * name:            NaviButtonView
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/10
 * description：  底部导航条自定义控件
 * history：
 * ===================================================
 */

public class NaviButtonView extends LinearLayout implements View.OnClickListener {
    /**按钮数量*/
    private int count=4;
    /**未选中资源图片id*/
    private int imagsUnSel[]=new int[]{R.drawable.act_home_icon1,
            R.drawable.act_home_icon2,
            R.drawable.act_home_icon3,
            R.drawable.act_home_icon4,
    };
    /**选中资源图片id*/
    private int imagsSel[]=new int[]{R.drawable.act_home_icon1_check,
            R.drawable.act_home_icon2_check,
            R.drawable.act_home_icon3_check,
            R.drawable.act_home_icon4_check,
    };
    /**文本数组*/
    private int text[]=new int[]{
            R.string.homepage,
            R.string.licai,
            R.string.more,
            R.string.my,
    };
    /**按钮集合*/
    private List<View> list;
    private List<ImageView> listImag;
    private List<TextView> listText;

    /**选中时文本颜色*/
    private int colorSel=Color.BLUE ;
    private int colorUnSel=Color.BLACK;

    /**tag*/
    private static final  String  VIEWTAG="navibuttonview";

    /**关联viewpager*/
    private ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    setSelStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public NaviButtonView(Context context) {
        this(context, null, 0);
    }

    public NaviButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NaviButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(23)
    public NaviButtonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        listImag=new ArrayList<>();
        listText=new ArrayList<>();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        for (int i = 0; i < count; i++) {
            View view=inflater.inflate(R.layout.view_navibutton,null);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight=1;
            view.setLayoutParams(params);
            view.setTag(VIEWTAG+i);
            this.addView(view);
            view.setOnClickListener(this);
            list.add(view);
            ImageView imageView=view.findViewById(R.id.iv_navibutton);
            listImag.add(imageView);
            TextView textView=view.findViewById(R.id.tv_navibutton);
            listText.add(textView);
            if (i==0){
                imageView.setImageResource(imagsSel[i]);
                textView.setTextColor(colorSel);
            }else {
                imageView.setImageResource(imagsUnSel[i]);
                textView.setTextColor(colorUnSel);
            }
            textView.setText(text[i]);

        }
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < count; i++) {
            if ((VIEWTAG+i).equals(view.getTag())){
                    setSelStyle(i);
                if (viewPager!=null){
                    viewPager.setCurrentItem(i);
                }
                break;
            }
        }
    }
    /**设置选中时的状态*/
    private void setSelStyle(int position) {
        for (int i = 0; i < count; i++) {
            if (position==i){
                listImag.get(i).setImageResource(imagsSel[i]);
                listText.get(i).setTextColor(colorSel);
            }else {
                listImag.get(i).setImageResource(imagsUnSel[i]);
                listText.get(i).setTextColor(colorUnSel);
            }
        }
    }
}
