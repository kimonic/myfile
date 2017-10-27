package com.tudoujf.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.adapter.GuideVPAdapter;
import com.tudoujf.config.Constants;
import com.tudoujf.ui.DotView;
import com.tudoujf.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private float x;

    private int images[] = new int[]{R.drawable.act_guide_page1,
            R.drawable.act_guide_page2,
            R.drawable.act_guide_page3,
            R.drawable.act_guide_page4};
    private List<ImageView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        ButterKnife.bind(this);
        initView();

        SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).put("isguide",true);

    }

    private void initView() {

        list = new ArrayList<>();
        for (int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        list.get(list.size() - 1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(GuideActivity.this,HttpTestActivity.class);
                Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        GuideVPAdapter adapter = new GuideVPAdapter(list);
        vpActguide.setAdapter(adapter);
        vpActguide.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {

                dvActguide.setPosition(position);
                dvActguide.invalidate();

            }


        });

        vpActguide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        if (vpActguide.getCurrentItem()==3&&x - motionEvent.getX() > 50) {
                            Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });


        dvActguide.setViewPager(vpActguide);
        dvActguide.invalidate();

    }
}
