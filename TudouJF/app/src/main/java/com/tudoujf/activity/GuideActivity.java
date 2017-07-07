package com.tudoujf.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tudoujf.R;
import com.tudoujf.ui.DotView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            GuideActivity
 * guide:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        for (int i = 0; i <images.length; i++) {
            ImageView imageView=new ImageView(this);
//            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            ViewGroup.LayoutParams params= imageView.getLayoutParams();
            Log.e("TAG", "initView:----11----- "+imageView.getLayoutParams() );
            params.width=1080;
            params.height=1920;

            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            vpActguide.addView(imageView);
            Log.e("TAG", "initView:----11----- "+imageView.getWidth() );
            Log.e("TAG", "initView:------22--- "+imageView.getHeight() );
        }

        Log.e("TAG", "initView:--------- "+vpActguide.getChildCount() );
        dvActguide.setViewPager(vpActguide);
        dvActguide.invalidate();

    }
}
