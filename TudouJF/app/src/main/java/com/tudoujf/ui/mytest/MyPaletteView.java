package com.tudoujf.ui.mytest;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tudoujf.R;

/**
 * * ===============================================================
 * name:             MyPaletteView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/12
 * description：
 * history：
 * *==================================================================
 */

public class MyPaletteView extends View {
    public MyPaletteView(Context context) {
        this(context, null, 0);
    }

    public MyPaletteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPaletteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public MyPaletteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private Paint paint;
    private Path path;
    private Path path1;

    private void initView() {
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        paint = new Paint();
//        paint.setColor(Color.parseColor("#9EEA6A"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setTextSize(200);

        //现行颜色渐变,当paint设置了shader后,paint.setcolor()设置的颜色将失效
        //参数1,2---颜色渐变的起始点
        //参数3,4---颜色渐变的结束点
        //参数5,起始颜色
        //参数6,结束颜色
        //参数7,tile：端点范围之外的着色规则，类型是 TileMode。TileMode 一共有 3 个值可选：
        // CLAMP, MIRROR 和 REPEAT。CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；
        // MIRROR 是镜像模式；REPEAT 是重复模式。具体的看一下例子就明白。
//        Shader shader = new LinearGradient(540, 760, 540, 1160, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);


//        辐射渐变很好理解，就是从中心向周围辐射状的渐变。参数含义同现行渐变

//        Shader shader = new RadialGradient(540, 960, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

//        又是一个渐变。「扫描渐变」这个翻译我也不知道精确不精确。大概是这样：
//        参数1,2扫描的中心点坐标

//        Shader shader = new SweepGradient(540, 960, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));


//        用 Bitmap 来着色（终于不是渐变了）。其实也就是用 Bitmap 的像素来作为图形或文字的填充
//        当图片大小不足以充满图形时,使用CLAMP模式可能只看到一部分
        //使用该方法绘制文本时,可以使文字绘制出现多彩效果
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);


//        // 第一个 Shader：头像的 Bitmap
//        //使用图片时,默认是从0,0点开始绘制图片
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.test);
//        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//// 第二个 Shader：从上到下的线性渐变（由透明到黑色）
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.act_lock_icon);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//// ComposeShader：结合两个 Shader,使用该方法时需要关闭硬件加速,否则无显示
//        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN);

        //颜色过滤器
        //LightingColorFilter 是用来模拟简单的光照效果的。
//        LightingColorFilter 的构造方法是 LightingColorFilter(int mul, int add) ，
//        参数里的 mul 和 add 都是和颜色值格式相同的 int 值，其中 mul 用来和目标像素相乘，add 用来和目标像素相加：
//        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x220033);


//        PorterDuffColorFilter 的作用是使用一个指定的颜色和一种指定的 PorterDuff.Mode 来与绘制对象进行合成。
//        它的构造方法是 PorterDuffColorFilter(int color, PorterDuff.Mode mode) 其中的 color 参数是指定的颜色，
//        mode 参数是指定的 Mode。同样也是 PorterDuff.Mode ，不过和 ComposeShader 不同的是，PorterDuffColorFilter
//        作为一个 ColorFilter，只能指定一种颜色作为源，而不是一个 Bitmap。
        ColorFilter lightingColorFilter = new PorterDuffColorFilter(0x888888, PorterDuff.Mode.SRC_ATOP);

        paint.setColorFilter(lightingColorFilter);
        paint.setFilterBitmap(true);
        paint.setShader(shader);

        //模糊效果
//        构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中， radius 参数是模糊的范围， style 是模糊的类型。一共有四种：
//
//        NORMAL: 内外都模糊绘制
//        SOLID: 内部正常绘制，外部模糊
//        INNER: 内部模糊，外部不绘制
//        OUTER: 内部不绘制，外部模糊（什么鬼？）
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));

//        浮雕效果的 MaskFilter。
//        构造方法 EmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius) 的参数里，
//        direction 是一个 3 个元素的数组，指定了光源的方向； ambient 是环境光的强度，数值范围是 0 到 1；
//        specular 是炫光的系数； blurRadius 是应用光线的范围。
        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 0, 0}, 0.9f, 80, 10));

        PathEffect pathEffect = new CornerPathEffect(20);
        paint.setPathEffect(pathEffect);


        path=new Path();
        path1=new Path();
        path.moveTo(0,1820);
        path.lineTo(200,1620);
        path.lineTo(400,1820);
        path.lineTo(600,1620);
        path.lineTo(800,1820);
        path.lineTo(1000,1620);

        path1.moveTo(0,0);
        path1.lineTo(30,30);
        path1.lineTo(0,30);
        path1.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawCircle(500, 500, 500, paint);
//        //阴影效果必须关闭硬件加速
//        paint.setShadowLayer(30, 0, 0, Color.RED);
        canvas.drawText("土豆金服",0,1160,paint);

        paint.reset();
        paint.setColor(Color.parseColor("#9EEA6A"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        //拐角为弧形
//        PathEffect pathEffect = new CornerPathEffect(80);
        //轮廓抖动,变乱
//        PathEffect pathEffect = new DiscretePathEffect(20, 5);

        //虚线绘制线条
//        PathEffect pathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);

        PathEffect pathEffect1 = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);

        //用path绘制的图形绘制线条
        PathEffect pathEffect2 = new CornerPathEffect(80);

//        PathEffect pathEffect = new SumPathEffect(pathEffect1,pathEffect2);
        PathEffect pathEffect = new ComposePathEffect(pathEffect1,pathEffect2);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path,paint);

        canvas.drawPath(path,paint);


    }
}
