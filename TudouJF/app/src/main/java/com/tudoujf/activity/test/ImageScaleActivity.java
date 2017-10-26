package com.tudoujf.activity.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tudoujf.R;
import com.tudoujf.utils.BitmapUtils;
import com.tudoujf.utils.ScreenSizeUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * * ===============================================================
 * name:             ImageScaleActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/9/29
 * description：   手势缩放移动图片
 * history：
 * *==================================================================
 */

public class ImageScaleActivity extends Activity {


    ImageView iv;
    Button btn, btn1;
    private Bitmap bitmap;
    private int count = 0;
    private int count1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_imagescale);

        Log.e("TAG", "onClick: ---hello--111");
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnTouchListener(new TouchListener());

        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.act_account_bankcard);
        try {
        @SuppressWarnings("ResourceType")InputStream in = getResources().openRawResource(R.drawable.act_account_bankcard);
        //获得图片的宽、高
        BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
        tmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, tmpOptions);
        int width = tmpOptions.outWidth;
        int height = tmpOptions.outHeight;

        //设置显示图片的中心区域
        BitmapRegionDecoder bitmapRegionDecoder = null;

            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(in, false);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100), options);
        iv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        bitmap=BitmapUtils.getReduceBitmap(bitmap,30000,900);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: ---hello--222");



                if (count1 == 0) {
                    //xml中默认设置为center,为实现缩放拖动,故应设置为ImageView.ScaleType.MATRIX
                    iv.setScaleType(ImageView.ScaleType.MATRIX);
                    count1++;
                }
                /**
                 */
                //matrix错切
//                Matrix matrix = new Matrix();
//                int bw = bitmap.getWidth();
//                int bh = bitmap.getHeight();
//                float[] src = {0,0, 0, bh,bw,bh};
//                float[] dst = {0, 0, 200, bh, bw + 200, bh};
//                matrix.setPolyToPoly(src, 0, dst, 0, 3);
                //透旋转
//                Matrix matrix = new Matrix();
//                matrix.set(iv.getImageMatrix());
//                PointF pointF=getCenter(matrix);
//                int bw = ((BitmapDrawable)(iv.getDrawable())).getBitmap().getWidth();
//                int bh =((BitmapDrawable)(iv.getDrawable())).getBitmap().getHeight();
//                float[] src = {bw / 2, bh / 2, bw, 0};
//                float[] dst = {bw / 2, bh / 2, bw / 2 + bh / 2, bh / 2 + bw / 2};
////                matrix.postRotate(count++*90,bw/2,bw/2);
//                matrix.postRotate(count++*90, ScreenSizeUtils.getScreenWidth(ImageScaleActivity.this)/2,
//                        ScreenSizeUtils.getScreenHeight(ImageScaleActivity.this)/2);

//                //通过改变bitmap四个角的坐标点对bitmap进行处理可拉伸为不规则四边形
                Matrix matrix = new Matrix();//matrix
                matrix.set(iv.getImageMatrix());
                int bw = bitmap.getWidth();//位图宽度
                int bh = bitmap.getHeight();//位图高度
                float[] src = {0, 0, 0, bh, bw, bh, bw, 0};//坐标系为控件的Android坐标系,原图片四角坐标点,此处可对应1-4个坐标点,为改变前的坐标点的位置
                int DX = 100;//偏移量
                float[] dst = {0 + DX, -50, 0, bh - 50, bw, bh + 200, bw - DX, 0};//对应src坐标点的个数,为改变后的坐标点位置,要改变的图片四角坐标点
                matrix.setPolyToPoly(src, 0, dst, 0, 4);//执行改变,改变四个点的位置,最后一个参数即为要改变的点的个数与src,dsc的坐标个数相对应
//
//                matrix.setScale(0.03f,1);
////                iv.setRotation(count++*90);
                iv.setImageMatrix(matrix);//设置新的matrix
                iv.setImageBitmap(bitmap);
//                canvas.drawBitmap(bitmap, matrix, paint);
            }
        });

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

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (count1 == 0) {
                //xml中默认设置为center,为实现缩放拖动,故应设置为ImageView.ScaleType.MATRIX
                iv.setScaleType(ImageView.ScaleType.MATRIX);
                count1++;
            }

            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 记录ImageView当前的移动位置
                    currentMatrix.set(iv.getImageMatrix());
                    startPoint.set(event.getX(), event.getY());
                    matrix.set(currentMatrix);
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    // 拖拉图片
                    if (mode == MODE_DRAG) {
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                        // 在没有移动之前的位置上进行移动
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                    }
                    // 放大缩小图片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 结束距离
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数

                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale, midPoint.x, midPoint.y);
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
                    /** 计算两个手指间的距离 */
                    startDis = distance(event);
                    /** 计算两个手指间的中间点 */
                    if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        midPoint = mid(event);
                        //记录当前ImageView的缩放倍数
                        currentMatrix.set(iv.getImageMatrix());
                    }
                    break;
            }
            iv.setImageMatrix(matrix);
            return true;
        }

        /**
         * 计算两个手指间的距离
         */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /**
         * 计算两个手指间的中间点
         */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

    }
}
