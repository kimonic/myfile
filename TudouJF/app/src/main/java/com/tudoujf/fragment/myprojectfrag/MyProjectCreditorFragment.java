package com.tudoujf.fragment.myprojectfrag;

import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.utils.HeightUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyProjectCreditorFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目--->债权项目列表
 * history：
 * * ====================================================================
 */

public class MyProjectCreditorFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojectcreditor)
    ListView lvTotal;
    @BindView(R.id.srl_frag_myprojectcreditor)
    SmartRefreshLayout srlTotal;

    private List<MyProjectTotalFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectcreditor;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            MyProjectTotalFragBean bean=new MyProjectTotalFragBean();
            bean.setTitle("房产抵押贷款201703270003");
            bean.setTitle1("00,000.00");
            bean.setTitle2("00,000.00");
            bean.setTitle3("20XX-XX-XX");
            bean.setTitle4("待收本息(元)");
            bean.setTitle5("债权价值(元)");
            bean.setTitle6("还款期限");
            bean.setTitle7("转让期数:7/12");
            bean.setTouzijindu(i%10*0.1f);
            bean.setTitle8("00,000.00");
            bean.setType(i%5+1);
            list.add(bean);
        }
    }

    @Override
    public void initView() {

        MyProjectTotalFragLvAdapter  adapter=new MyProjectTotalFragLvAdapter(getActivity(),list);
        lvTotal.setAdapter(adapter);

        HeightUtils.setListviewHeight(lvTotal);

        srlTotal.setPrimaryColorsId(R.color.global_theme_background_color);
        srlTotal.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srlTotal.setRefreshFooter(new BallPulseFooter(getActivity()));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
/**
 PointEvaluator.java

 // 实现TypeEvaluator接口
 public class PointEvaluator implements TypeEvaluator {

 // 复写evaluate（）
 // 在evaluate（）里写入对象动画过渡的逻辑
 @Override
 public Object evaluate(float fraction, Object startValue, Object endValue) {

 // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象
 Point startPoint = (Point) startValue;
 Point endPoint = (Point) endValue;

 // 根据fraction来计算当前动画的x和y的值
 float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
 float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());

 // 将计算后的坐标封装到一个新的Point对象中并返回
 Point point = new Point(x, y);
 return point;
 }

 }
 上面步骤是根据需求自定义TypeEvaluator的实现
 下面将讲解如何通过对 Point 对象进行动画操作，从而实现整个自定义View的动画效果。
 步骤3：将属性动画作用到自定义View当中

 MyView.java

 /**
  * Created by Carson_Ho on 17/4/18.
 *//**
public class MyView extends View {
    // 设置需要用到的变量
    public static final float RADIUS = 70f;// 圆的半径 = 70
    private Point currentPoint;// 当前点坐标
    private Paint mPaint;// 绘图画笔


    // 构造方法(初始化画笔)
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    // 复写onDraw()从而实现绘制逻辑
    // 绘制逻辑:先在初始点画圆,通过监听当前坐标值(currentPoint)的变化,每次变化都调用onDraw()重新绘制圆,从而实现圆的平移动画效果
    @Override
    protected void onDraw(Canvas canvas) {
        // 如果当前点坐标为空(即第一次)
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            // 创建一个点对象(坐标是(70,70))

            // 在该点画一个圆:圆心 = (70,70),半径 = 70
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);


            // (重点关注)将属性动画作用到View中
            // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            Point startPoint = new Point(RADIUS, RADIUS);// 初始点为圆心(70,70)
            Point endPoint = new Point(700, 1000);// 结束点为(700,1000)

            // 步骤2:创建动画对象 & 设置初始值 和 结束值
            ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
            // 参数说明
            // 参数1：TypeEvaluator 类型参数 - 使用自定义的PointEvaluator(实现了TypeEvaluator接口)
            // 参数2：初始动画的对象点
            // 参数3：结束动画的对象点

            // 步骤3：设置动画参数
            anim.setDuration(5000);
            // 设置动画时长

// 步骤3：通过 值 的更新监听器，将改变的对象手动赋值给当前对象
// 此处是将 改变后的坐标值对象 赋给 当前的坐标值对象
            // 设置 值的更新监听器
            // 即每当坐标值（Point对象）更新一次,该方法就会被调用一次
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (Point) animation.getAnimatedValue();
                    // 将每次变化后的坐标值（估值器PointEvaluator中evaluate（）返回的Piont对象值）到当前坐标值对象（currentPoint）
                    // 从而更新当前坐标值（currentPoint）

// 步骤4：每次赋值后就重新绘制，从而实现动画效果
                    invalidate();
                    // 调用invalidate()后,就会刷新View,即才能看到重新绘制的界面,即onDraw()会被重新调用一次
                    // 所以坐标值每改变一次,就会调用onDraw()一次
                }
            });

            anim.start();
            // 启动动画


        } else {
            // 如果坐标值不为0,则画圆
            // 所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果

            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }
    }


}
步骤4：在布局文件加入自定义View空间

        activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        tools:context="scut.carson_ho.valueanimator_ofobject.MainActivity">

<scut.carson_ho.valueanimator_ofobject.MyView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
</RelativeLayout>
        步骤5：在主代码文件设置显示视图

        MainActivity.java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
 */