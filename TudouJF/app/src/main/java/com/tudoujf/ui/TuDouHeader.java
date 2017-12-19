package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.tudoujf.R;

/**
 * * ===============================================================
 * name:             TuDouHeader
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/13
 * description：
 * history：
 * *==================================================================
 */

public class TuDouHeader extends LinearLayout implements RefreshHeader {

    private TextView  imageView;
    private TextView textView;
    private View view;

//    private ProgressDrawable  progressDrawable;


    public TuDouHeader(Context context) {
        this(context, null, 0);
    }

    public TuDouHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TuDouHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(21)
    public TuDouHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        setGravity(Gravity.CENTER_HORIZONTAL);
//        this.setOrientation(VERTICAL);
//        imageView = new ImageView(getContext());
//        textView = new TextView(getContext());
//
//        imageView.setImageResource(R.drawable.refreshimage);
//        textView.setText("正在刷新");
//        mProgressDrawable = new ProgressDrawable();
//        mArrowView = new PathsView(context);
//        mProgressView = new ImageView(context);
//        mProgressView.setImageDrawable(mProgressDrawable);

//        progressDrawable=new ProgressDrawable();
        view= LayoutInflater.from(getContext()).inflate(R.layout.header_tudou,null);
        imageView=view.findViewById(R.id.iv_arrow);
        textView=view.findViewById(R.id.tv_description);
        addView(view);
//        addView(imageView);
//        addView(textView);

    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        Log.e("TAG", "onPullingDown: TuDouHeader-----percent"+percent);
        Log.e("TAG", "onPullingDown: TuDouHeader-----offset"+offset);
        Log.e("TAG", "onPullingDown: TuDouHeader-----headerHeight"+headerHeight);
        Log.e("TAG", "onPullingDown: TuDouHeader-----extendHeight"+extendHeight);


    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
        Log.e("TAG", "onReleasing: TuDouHeader-----percent"+percent);
        Log.e("TAG", "onReleasing: TuDouHeader-----offset"+offset);
        Log.e("TAG", "onReleasing: TuDouHeader-----headerHeight"+headerHeight);
        Log.e("TAG", "onReleasing: TuDouHeader-----extendHeight"+extendHeight);
    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {
        Log.e("TAG", "onRefreshReleased:TuDouHeader -----headerHeight"+headerHeight);
        Log.e("TAG", "onRefreshReleased: TuDouHeader-----extendHeight"+extendHeight);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        Log.e("TAG", "onInitialized: TuDouHeader-----height"+height);
        Log.e("TAG", "onInitialized: TuDouHeader-----extendHeight"+extendHeight);
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
        Log.e("TAG", "onHorizontalDrag: TuDouHeader-----percentX"+percentX);
        Log.e("TAG", "onHorizontalDrag: TuDouHeader-----offsetX"+offsetX);
        Log.e("TAG", "onHorizontalDrag: TuDouHeader-----offsetMax"+offsetMax);


    }

    /**
     * 开始动画
     * @param layout
     * @param height
     * @param extendHeight
     */
    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        Log.e("TAG", "onStartAnimator: TuDouHeader-----height"+height);
        Log.e("TAG", "onStartAnimator: TuDouHeader-----extendHeight"+extendHeight);
        RotateAnimation rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(500);//设置动画持续周期
        rotate.setRepeatCount(Animation.INFINITE);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//        rotate.setStartOffset(10);//执行前的等待时间
//        imageView.setAnimation(rotate);
        imageView.startAnimation(rotate);

//        RotateAnimation animation=new RotateAnimation(0,360,30,30);
//        animation.setDuration(3000);
//        imageView.startAnimation(animation);


    }

    /**结束动画*/
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        imageView.clearAnimation();
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        Log.e("TAG", "onStateChanged: TuDouHeader-----newState"+newState);

        switch (newState) {
            case None:
            case PullDownToRefresh:
                textView.setText("下拉开始刷新");
//                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
//                mProgressView.setVisibility(GONE);//隐藏动画
//                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                textView.setText("正在刷新");
//                mProgressView.setVisibility(VISIBLE);//显示加载动画
//                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
                textView.setText("释放立即刷新");
//                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }
}
