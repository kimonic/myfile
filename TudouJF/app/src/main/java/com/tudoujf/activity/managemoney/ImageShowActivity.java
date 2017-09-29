package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ImageGlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ===============================================================
 * name:             ImageShowActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/9/28
 * description：   展示图片的activity,可以自由缩放,拖动
 * history：
 * *==================================================================
 */

public class ImageShowActivity extends BaseActivity {
    @BindView(R.id.iv_act_imageshow)
    ImageView ivActImageshow;
    private String url = "";

    @Override
    public int getLayoutResId() {
        return R.layout.act_imageshow;
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }

    }

    @Override
    public void initView() {
        ImageGlideUtils.loadImageFromUrl(ivActImageshow, url);

    }

    @Override
    public void initListener() {
        ivActImageshow.setOnTouchListener(new TouchListener());


    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    private final class TouchListener implements View.OnTouchListener {

        /**
         * 记录是拖拉照片模式还是放大缩小照片模式
         */
        private int mode = 0;// 初始状态
        /**
         * 拖拉照片模式
         */
        private static final int MODE_DRAG = 1;
        /**
         * 放大缩小照片模式
         */
        private static final int MODE_ZOOM = 2;

        /**
         * 用于记录开始时候的坐标位置
         */
        private PointF startPoint = new PointF();
        /**
         * 用于记录拖拉图片移动的坐标位置
         */
        private Matrix matrix = new Matrix();
        /**
         * 用于记录图片要进行拖拉时候的坐标位置
         */
        private Matrix currentMatrix = new Matrix();

        /**
         * 两个手指的开始距离
         */
        private float startDis;
        /**
         * 两个手指的中间点
         */
        private PointF midPoint;

        private int count = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
//            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */

            if (count == 0) {
                //xml中默认设置为center,为实现缩放拖动,故应设置为ImageView.ScaleType.MATRIX
                ivActImageshow.setScaleType(ImageView.ScaleType.MATRIX);
                count++;
            }

            switch (event.getAction() & MotionEvent.ACTION_MASK) {//多点触控必须加& MotionEvent.ACTION_MASK
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 记录ImageView当前的移动位置
                    currentMatrix.set(ivActImageshow.getImageMatrix());//获得imageview的matrix
                    startPoint.set(event.getX(), event.getY());//imageview的当前坐标点
                    matrix.set(currentMatrix);//此处不设置则会造成图片从中间移动到左上角
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    if (mode == MODE_DRAG) {// 拖拉图片模式
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        float dy = event.getY() - startPoint.y; // 得到y轴的移动距离
                        // 在没有移动之前的位置上进行移动
                        //此处的设置如果移动到ACTION_DOWN中,则会造成拖动时位置错乱,原因如果此处不重设则是相对于当前位置
                        //那么移动距离的计算方式就是错误的,故应重设为相对起始位置
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);//移动指定的距离

                    } else if (mode == MODE_ZOOM) {// 放大缩小图片
                        float endDis = distance(event);// 结束距离--两个手指之间的距离
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数

                            if (scale > 0.1f && scale < 10) {
                                PointF p = getCenter(matrix);
                                matrix.set(currentMatrix);
//                                matrix.postScale(scale, scale, p.x, p.y);
                                matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                            }
                        }
                    }

                    break;
                // 手指离开屏幕
                case MotionEvent.ACTION_UP:
                    // 当触点离开屏幕，但是屏幕上还有触点(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
//                    currentMatrix.set(iv.getImageMatrix());
//                    startPoint.set(event.getX(), event.getY());
                    break;
                // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = MODE_ZOOM;
//                    midPoint= new PointF((iv.getLeft()+iv.getRight())/2f,(iv.getTop()+iv.getBottom())/2f);
//                    /** 计算两个手指间的距离 */
                    startDis = distance(event);
//                    /** 计算两个手指间的中间点 */
                    if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        midPoint = mid(event);
                        //记录当前ImageView的缩放倍数
                        currentMatrix.set(ivActImageshow.getImageMatrix());
                    }
                    break;
            }
            ivActImageshow.setImageMatrix(matrix);
            return true;
        }

        /**
         * 计算两个手指间的距离
         */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
//            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /**
         * 计算两个手指间的中间点坐标
         */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

        /**
         * 获取实际图像的中心点坐标
         *
         * @param matrix 变化后的矩阵
         * @ param rectF 矩形对象
         */
        private PointF getCenter(Matrix matrix) {
            RectF rectF = new RectF();
            matrix.mapRect(rectF);
            //其实在此处就可以获得中心! wtf
            float centerX = rectF.centerX();
            float centerY = rectF.centerY();
            return new PointF(centerX, centerY);
        }

    }

}
