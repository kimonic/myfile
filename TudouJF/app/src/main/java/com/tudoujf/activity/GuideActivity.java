package com.tudoujf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tudoujf.R;
import com.tudoujf.adapter.GuideVPAdapter;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.DotView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * * ================================================
 * name:            GuideActivity
 * guide:          WelcomeActivity-->GuideActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：导航页activity
 * history：
 * ===================================================
 */


public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.vp_actguide)
    ViewPager vpActguide;
    @BindView(R.id.dv_actguide)
    DotView dvActguide;

    private int images[]=new int[]{R.drawable.act_guide_page1,
            R.drawable.act_guide_page2,
            R.drawable.act_guide_page3,
            R.drawable.act_guide_page4};
    private List<ImageView>  list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        list=new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        list.get(list.size()-1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GuideActivity.this,HttpTestActivity.class);
                startActivity(intent);
            }
        });

        GuideVPAdapter adapter=new GuideVPAdapter(list);
        vpActguide.setAdapter(adapter);
        vpActguide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                dvActguide.setPosition(position);
                dvActguide.invalidate();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        dvActguide.setViewPager(vpActguide);
        dvActguide.invalidate();

    }
}
