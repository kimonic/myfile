package com.tudoujf.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tudoujf.utils.utils.ScreenUtil;

public class MainActivity extends AppCompatActivity {

    private EditText etTest;
    private LinearLayout  root;
    private SmartRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);
        etTest=findViewById(R.id.et_test);
        root=findViewById(R.id.root);
//        srl=findViewById(R.id.srl_frag_my);
        etTest.setVisibility(View.GONE);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            Log.e("TAG", "onCreate:packageName-----" + pi.packageName);
            Log.e("TAG", "onCreate: versionCode-----" + pi.versionCode);
            Log.e("TAG", "onCreate:versionName -----" + pi.versionName);
            Log.e("TAG", "onCreate: baseRevisionCode-----" + pi.baseRevisionCode);
            Log.e("TAG", "onCreate: sharedUserId-----" + pi.sharedUserId);
            Log.e("TAG", "onCreate: sharedUserLabel-----" + pi.sharedUserLabel);
            Log.e("TAG", "onCreate: firstInstallTime-----" + pi.firstInstallTime);
            Log.e("TAG", "onCreate: lastUpdateTime-----" + pi.lastUpdateTime);
            Log.e("TAG", "onCreate: overlayTarget-----" + pi.toString());
            Log.e("TAG", "onCreate: -----" + pi.packageName);
            Log.e("TAG", "onCreate: -----" + pi.packageName);
            Log.e("TAG", "onCreate: -----" + pi.packageName);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initView();

    }

    private void initView() {
        ScreenUtil screenUtil=new ScreenUtil(this);

//        srl.setPrimaryColorsId(R.color.global_theme_background_color);
//        srl.setRefreshHeader(new TuDouHeader(this));
//        srl.setEnableLoadmore(false);
        Log.e("TAG", "onOpen: ---输入法已打开--"+screenUtil.getAccurateScreenDpi()[0]);
        Log.e("TAG", "onOpen: ---输入法已打开--"+screenUtil.getAccurateScreenDpi()[1]);


        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                canScrollDown(root,event);
                return false;
            }
        });


        screenUtil.observeInputlayout(root, new ScreenUtil.OnInputActionListener() {
            @Override
            public void onOpen(int  inputHeight) {
                Log.e("TAG", "onOpen: ---输入法已打开--");
            }

            @Override
            public void onClose() {
                Log.e("TAG", "onOpen: ---输入法已关闭--");
            }
        });
    }


    /**将viewgroup中的点击坐标点(viewgroup左上点为(0,0))转化为相对于view的坐标点(view的左上点为(0,0))*/
    public  void transformPointToViewLocal(ViewGroup group, View child, float[] point) {
        Log.e("TAG", "transformPointToViewLocal: --point[0]---"+point[0]);
        Log.e("TAG", "transformPointToViewLocal: --point[1]---"+point[1]);
        Log.e("TAG", "transformPointToViewLocal: --group.getScrollX()---"+group.getScrollX());
        Log.e("TAG", "transformPointToViewLocal: --group.getScrollY()---"+group.getScrollY());
        Log.e("TAG", "transformPointToViewLocal: --child.getLeft()---"+child.getLeft());
        Log.e("TAG", "transformPointToViewLocal: --child.getTop()---"+child.getTop());


        point[0] += group.getScrollX() - child.getLeft();//viewgroup相对于屏幕左边的位置减去child相对于viewgroup左边的位置
        point[1] += group.getScrollY() - child.getTop();

        Log.e("TAG", "transformPointToViewLocal: ---point[0]22--"+point[0]);
        Log.e("TAG", "transformPointToViewLocal: --point[1]-22--"+point[1]);
    }

    public  boolean isTransformedTouchPointInView(ViewGroup group, View child, float x, float y, PointF outLocalPoint) {
        if (child.getVisibility() != View.VISIBLE) {//控件不显示
            return false;
        }
        final float[] point = new float[2];
        point[0] = x;
        point[1] = y;
        transformPointToViewLocal(group, child, point);
        final boolean isInView = pointInView(child, point[0], point[1], 0);
        if (isInView && outLocalPoint != null) {
            outLocalPoint.set(point[0] - x, point[1] - y);
        }
        return isInView;
    }


    public  boolean canScrollDown(View targetView, MotionEvent event) {
        if (canScrollDown(targetView) && targetView.getVisibility() == View.VISIBLE) {//控件显示并且可下滑
            return true;
        }
        if (targetView instanceof ViewGroup && event != null) {//targetview 是viewgroup并且event!=null
            ViewGroup viewGroup = (ViewGroup) targetView;
            final int childCount = viewGroup.getChildCount();//viewgroup中的子view的个数
            PointF point = new PointF();
            for (int i = 0; i < childCount; i++) {//遍历子view
                View child = viewGroup.getChildAt(i);
                if (isTransformedTouchPointInView(viewGroup, child, event.getX(), event.getY(), point)) {
                    event = MotionEvent.obtain(event);
                    event.offsetLocation(point.x, point.y);
                    return canScrollDown(child, event);
                }
            }
        }
        return false;
    }

    public  boolean pointInView(View view, float localX, float localY, float slop) {
        final float left = /*Math.max(view.getPaddingLeft(), 0)*/ -slop;
        final float top = /*Math.max(view.getPaddingTop(), 0)*/ -slop;
        final float width = view.getWidth()/* - Math.max(view.getPaddingLeft(), 0) - Math.max(view.getPaddingRight(), 0)*/;
        final float height = view.getHeight()/* - Math.max(view.getPaddingTop(), 0) - Math.max(view.getPaddingBottom(), 0)*/;
        return localX >= left && localY >= top && localX < ((width) + slop) &&
                localY < ((height) + slop);
    }

    /**
     * 判断目标view是否可以向下滚动
     */
    public static boolean canScrollDown(View targetView) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (targetView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) targetView;
                return absListView.getChildCount() > 0
                        && (absListView.getLastVisiblePosition() < absListView.getChildCount() - 1
                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getPaddingBottom());
            } else {
                return targetView.getScrollY() < 0;
            }
        } else {
            return targetView.canScrollVertically(1);
        }
    }

}
