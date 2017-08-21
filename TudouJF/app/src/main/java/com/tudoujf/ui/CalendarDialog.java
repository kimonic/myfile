package com.tudoujf.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.readystatesoftware.chuck.internal.support.DividerItemDecoration;
import com.tudoujf.R;
import com.tudoujf.adapter.CalendarSelDialogRvAdapter;

import butterknife.BindView;

/**
 * * ==================================================
 * name:            BidView+
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：   选择日期view
 * history：
 * * ==================================================
 */

public class CalendarDialog {
    //    @BindView(R.id.tv_dialog_canlendar_starttime)
    private TextView tvStartTime;
    //    @BindView(R.id.tv_dialog_canlendar_endtime)
    private TextView tvEndTime;
    //    @BindView(R.id.tv_dialog_canlendar_previous)
    private TextView tvPrevious;
    //    @BindView(R.id.tv_dialog_canlendar_next)
    private TextView tvNext;
    //    @BindView(R.id.tv_dialog_canlendar_currenttime)
    private TextView tvCurrentTime;
    //    @BindView(R.id.rv_dialog_canlendar)
    private RecyclerView rv;
    /**
     * 自定义展示view
     */
    private View view;
    private Context context;

    public CalendarDialog(Context context) {
        this.context = context;
        initView();
        initRV();
    }


    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_act_integralrecode_calendarsel, null);
        tvStartTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_starttime);
        tvEndTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_endtime);
        tvPrevious = (TextView) view.findViewById(R.id.tv_dialog_canlendar_previous);
        tvNext = (TextView) view.findViewById(R.id.tv_dialog_canlendar_next);
        tvCurrentTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_currenttime);


        rv = (RecyclerView) view.findViewById(R.id.rv_dialog_canlendar);

    }

    private void initRV() {
        GridLayoutManager layout = new GridLayoutManager(context, 7);//网格布局管理器
        //设置布局管理器
        rv.setLayoutManager(layout);
        CalendarSelDialogRvAdapter adapter = new CalendarSelDialogRvAdapter(context, null);//适配器
        //设置adapter
        rv.setAdapter(adapter);
        //设置Item增加、移除动画
        rv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv.addItemDecoration(new DividerGridItemDecoration(context));
        rv.setOverScrollMode(View.OVER_SCROLL_NEVER);//禁止边缘阴影效果

    }

    public void show() {
//        view = LayoutInflater.from(context).inflate(R.layout.dialog_act_integralrecode_calendarsel, null);

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        });
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            ColorDrawable drawable = new ColorDrawable(Color.WHITE);
            window.setBackgroundDrawable(drawable);
//            window.setBackgroundDrawable(new ColorDrawable());


            window.setContentView(view);
            window.setWindowAnimations(R.style.AnimBottom);
        }

    }


    /**分割线绘制*/
    public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;//分割线drawable,可以提替代

        /**首先获取系统分割线*/
        public DividerGridItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        /**绘制分割线*/
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }

        /**
         *
         * @param parent  rv
         * @return  一行的item列数
         */
        private int getSpanCount(RecyclerView parent) {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                spanCount = ((StaggeredGridLayoutManager) layoutManager)
                        .getSpanCount();
            }
            return spanCount;
        }

        /**
         * 绘制水平线
         * @param c  画布
         * @param parent   rv
         */
        public void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            int column_num = getSpanCount(parent);
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                int top = 0;
                int bottom = 0;

                if ((i / column_num) == 0) {
                    //画item最上面的分割线
                    top = 0;
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                    //画item下面的分割线
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                } else {
                    //画item下面的分割线
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);

                }
            }

        }

        /**
         * 绘制垂直线
         * @param c  画布
         * @param parent   rv
         */
        public void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
            int column_num = getSpanCount(parent);
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin;
                int left = 0;
                int right = 0;
                if ((i % column_num) == 0) {
                    //item左边分割线
                    left = 0;
                    right = left + mDivider.getIntrinsicWidth();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                    //item右边分割线
                    left = child.getRight() + params.rightMargin;
                    right = left + mDivider.getIntrinsicWidth();
                } else {
                    left = child.getRight() + params.rightMargin;
                    right = left + mDivider.getIntrinsicWidth();
                }

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

            }
        }

        /**
         * 是最后一列的
         * @param parent     rv
         * @param pos        item位置
         * @param spanCount  列数
         * @param childCount 总个数
         * @return true-----是最后一列的
         */
        private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            if ((pos + 1) % spanCount == 1) {
                return true;
            }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    // 如果是最后一列，则不需要绘制右边
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        /**
         * 是第一列
         * @param parent     rv
         * @param pos        item位置
         * @param spanCount  列数
         * @param childCount 总个数
         * @return true-----是第一列的
         */
        private boolean isFirstColum(RecyclerView parent, int pos, int spanCount, int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            if (layoutManager instanceof GridLayoutManager) {
                // 如果是最后一列，则不需要绘制右边
                Log.e("TAG", "isFirstColum:------------位置----------------- " + pos);
                if ((pos + 1) % spanCount == 1) {
                    Log.e("TAG", "isFirstColum:------------第一列的---------------- " + pos);
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    // 如果是最后一列，则不需要绘制右边
                    if ((pos + 1) % spanCount == 1) {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % (spanCount + 1);
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        /**
         * 是最后一行
         * @param parent     rv
         * @param pos        item位置
         * @param spanCount  列数
         * @param childCount 总个数
         * @return true-----是最后一行的
         */
        private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
//            childCount = childCount - childCount % spanCount;
//            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
//                return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                        return true;
                    // StaggeredGridLayoutManager 且横向滚动
                } else {
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * @param parent     rv
         * @param pos        item位置
         * @param spanCount  列数
         * @param childCount 总个数
         * @return true-----是第一行的
         */
        private boolean isFirstRaw(RecyclerView parent, int pos, int spanCount, int childCount) {

            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            if (layoutManager instanceof GridLayoutManager) {
//            childCount = childCount - childCount % spanCount;
                if (pos < spanCount)
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
//                    childCount = childCount - childCount % spanCount;
                    if (pos < spanCount)// 如果是最后一行，则不需要绘制底部
                        return true;
                    // StaggeredGridLayoutManager 且横向滚动
                }
//                else {
//                    // 如果是最后一行，则不需要绘制底部
//                    if ((pos + 1) % spanCount == 0) {
//                        return true;
//                    }
//                }
            }
            return false;
        }

//        @Override
//        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
//            Log.e("TAG", "getItemOffsets:--------有的位置----------------- " + itemPosition);
//            int spanCount = getSpanCount(parent);//列数
//            int childCount = parent.getAdapter().getItemCount();//item总数
////             如果是最后一行，则不需要绘制底部
//
//            if (itemPosition == 0) {
//                outRect.set(2, 2, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//
//            } else if (isFirstRaw(parent, itemPosition, spanCount, childCount)) {
//
//                outRect.set(0, 2, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//
//                // 如果是最后一列，则不需要绘制右边
//            } else if (isFirstColum(parent, itemPosition, spanCount, childCount)) {
//
//                outRect.set(2, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//
//            } else {
//
//
//                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//            }
//
//
//        }

        /**
         * 替代  getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent)
         * @param outRect   绘制的地方
         * @param view       item
         * @param parent  rv
         * @param state  state
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int itemPosition=parent.getChildAdapterPosition(view);//item的position
            Log.e("TAG", "getItemOffsets:--------有的位置----------------- " + itemPosition);
            int spanCount = getSpanCount(parent);//列数
            int childCount = parent.getAdapter().getItemCount();//item总数
//             如果是最后一行，则不需要绘制底部

            if (itemPosition == 0) {//0的时候绘制左上
                outRect.set(2, 2, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());

            } else if (isFirstRaw(parent, itemPosition, spanCount, childCount)) {//第一行时

                outRect.set(0, 2, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());

                // 如果是最后一列，则不需要绘制右边
            } else if (isFirstColum(parent, itemPosition, spanCount, childCount)) {//第一列时

                outRect.set(2, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());

            } else {//其他部分

                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }
        }
    }


}
