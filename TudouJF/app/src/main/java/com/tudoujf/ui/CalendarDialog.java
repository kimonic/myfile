package com.tudoujf.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.CalendarSelDialogRvAdapter;
import com.tudoujf.bean.databean.CalendarBean;
import com.tudoujf.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ==================================================
 * name:            CalendarDialog
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：   选择日期view
 * history：
 * * ==================================================
 */

public class CalendarDialog implements View.OnClickListener {
    private TextView tvStartTime, tvEndTime, tvPrevious, tvNext, tvCurrentTime;
    private RecyclerView rv;
    /**
     * 自定义展示view
     */
    private View view;
    private Context context;
    /**
     * 日历数据源
     */
    private List<CalendarBean> list;
    /**
     * rv适配器
     */
    private CalendarSelDialogRvAdapter adapter;
    /**
     * 系统当前年份
     */
    private int presentYear;
    /**
     * 系统当前月份
     */
    private int presentMonth;

    private int beforeClickPosition = -1;
    /**
     * 当前点击按钮标识
     */
    private int buttonFlag = 1;
    /**
     * dialog dismiss监听
     */
    private OnCalendarDialogDismissListener listener;
    private AlertDialog dialog;


    public OnCalendarDialogDismissListener getListener() {
        return listener;
    }

    public void setListener(OnCalendarDialogDismissListener listener) {
        this.listener = listener;
    }

    public CalendarDialog(Context context) {
        this.context = context;
        initView();
        initRV();
        initListener();
    }

    private void initListener() {
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_canlendar_starttime://开始日期
                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_left);
                buttonFlag = 1;
                break;
            case R.id.tv_dialog_canlendar_endtime://结束日期
                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_right);
                buttonFlag = 2;
                break;
            case R.id.tv_dialog_canlendar_previous://上一个月
                presentYear = TimeUtils.getYearOfLastMonth(presentYear, presentMonth);
                presentMonth = TimeUtils.getLastMonth(presentMonth);
                tvCurrentTime.setText(TimeUtils.getCurrentFirstOfTheMonteh(presentYear, presentMonth));
                initList(presentYear, presentMonth);
                break;
            case R.id.tv_dialog_canlendar_next://下一个月
                presentYear = TimeUtils.getYearOfNextMonth(presentYear, presentMonth);
                presentMonth = TimeUtils.getNextMonth(presentMonth);
                tvCurrentTime.setText(TimeUtils.getCurrentFirstOfTheMonteh(presentYear, presentMonth));
                initList(presentYear, presentMonth);
                break;
        }

    }
    /**初始化控件*/
    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_act_integralrecode_calendarsel, null);
        tvStartTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_starttime);
        tvEndTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_endtime);
        tvPrevious = (TextView) view.findViewById(R.id.tv_dialog_canlendar_previous);
        tvNext = (TextView) view.findViewById(R.id.tv_dialog_canlendar_next);
        tvCurrentTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_currenttime);



        rv = (RecyclerView) view.findViewById(R.id.rv_dialog_canlendar);

        tvStartTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        tvEndTime.setText(TimeUtils.getNowDateShort());

    }

    /**
     * 初始化rv
     */
    private void initRV() {
        presentYear = TimeUtils.getCurrentYear();
        presentMonth = TimeUtils.getCurrentMonthInt();
        tvCurrentTime.setText(TimeUtils.getCurrentFirstOfTheMonteh(presentYear, presentMonth));
        initList(presentYear, presentMonth);
        GridLayoutManager layout = new GridLayoutManager(context, 7);//网格布局管理器
        //设置布局管理器
        rv.setLayoutManager(layout);
        //设置Item增加、移除动画
        rv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv.addItemDecoration(new DividerGridItemDecoration(context));
        rv.setOverScrollMode(View.OVER_SCROLL_NEVER);//禁止边缘阴影效果
        adapter.setListener(new CalendarSelDialogRvAdapter.RVItemListener() {
            @Override
            public void itemClick(View v) {
                if (beforeClickPosition != (int) v.getTag() && beforeClickPosition != -1) {
                    list.get(beforeClickPosition).setFlag(list.get(beforeClickPosition).getDefaultFlag());
                }
                list.get((Integer) v.getTag()).setFlag(3);
                if (buttonFlag == 1) {
                    tvStartTime.setText(list.get((Integer) v.getTag()).getDateStr());
                } else {
                    tvEndTime.setText(list.get((Integer) v.getTag()).getDateStr());
                    beforeClickPosition=-1;
                    dialog.dismiss();
                }
                adapter.notifyDataSetChanged();
                beforeClickPosition = (int) v.getTag();
            }
        });

    }

    /**
     * 初始化日历数据源
     */
    private void initList(int currentYear, int currentMonth) {
        if (list == null) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }
        //获取当前月有多少天
        int currentDayOfMonth = TimeUtils.getDayOfMonth(currentYear, currentMonth);
        //当前月的上一个月是几月
        int lastMonth = TimeUtils.getLastMonth(currentMonth);
        //上一个月所在年份
        int lastYear = TimeUtils.getYearOfLastMonth(currentYear, currentMonth);
        //当前月的下一个月是几月
        int nextMonth = TimeUtils.getNextMonth(currentMonth);
        //下一个月所在的年份
        int nextYear = TimeUtils.getYearOfNextMonth(currentYear, currentMonth);


        //获取上个月有多少天
        int lastDayOfMonth = TimeUtils.getDayOfMonth(lastYear, lastMonth);
        //获取1号是星期几
        int oneOfWeekCurrentMonth = TimeUtils.getCurrentDayOfWeekTheFirstOfMonteh(currentYear, currentMonth);

        //上一个月的日期
        for (int i = 0; i < oneOfWeekCurrentMonth - 1; i++) {
            CalendarBean bean = new CalendarBean();
            bean.setDay(lastDayOfMonth + i - (oneOfWeekCurrentMonth - 2));
            bean.setFlag(1);
            bean.setDefaultFlag(1);
            bean.setDateStr(lastYear, lastMonth, lastDayOfMonth + i - (oneOfWeekCurrentMonth - 2));
            list.add(bean);
        }
        //当前月的日期
        for (int i = 0; i < currentDayOfMonth; i++) {
            CalendarBean bean = new CalendarBean();
            bean.setDay(i + 1);
            bean.setFlag(2);
            bean.setDefaultFlag(2);
            bean.setDateStr(currentYear, currentMonth, i + 1);
            list.add(bean);
        }
        //下一个月的日期
        if (list.size() % 7 != 0) {
            int itemCount = 7 - list.size() % 7;
            for (int i = 0; i < itemCount; i++) {
                CalendarBean bean = new CalendarBean();
                bean.setDay(i + 1);
                bean.setFlag(1);
                bean.setDefaultFlag(1);
                bean.setDateStr(nextYear, nextMonth, i + 1);
                list.add(bean);
            }
        }
        if (adapter == null) {
            adapter = new CalendarSelDialogRvAdapter(context, list);//适配器
            //设置adapter
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 显示当前控件
     */
    public void show() {
        dialog = new AlertDialog.Builder(context).create();
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
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (listener != null) {
                    listener.onDismiss(tvStartTime.getText().toString(), tvEndTime.getText().toString());
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
            window.setContentView(view);
            window.setWindowAnimations(R.style.AnimBottom);
        }
    }

    public void showDialog(){
        if (dialog==null){
            show();
        }else {
            dialog.show();
        }
    }

    public interface OnCalendarDialogDismissListener {
        void onDismiss(String sTime, String eTime);
    }


    /**
     * 分割线绘制
     */
    private class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;//分割线drawable,可以提替代

        /**
         * 首先获取系统分割线
         */
        public DividerGridItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        /**
         * 绘制分割线
         */
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }

        /**
         * @param parent rv
         * @return 一行的item列数
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
         *
         * @param c      画布
         * @param parent rv
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
         *
         * @param c      画布
         * @param parent rv
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
         * 是第一列
         *
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
                if ((pos + 1) % spanCount == 1) {
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
         * @param parent     rv
         * @param pos        item位置
         * @param spanCount  列数
         * @param childCount 总个数
         * @return true-----是第一行的
         */
        private boolean isFirstRaw(RecyclerView parent, int pos, int spanCount, int childCount) {

            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            if (layoutManager instanceof GridLayoutManager) {
                if (pos < spanCount)
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if (pos < spanCount)
                        return true;
                }
//
            }
            return false;
        }


        /**
         * 替代  getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent)
         *
         * @param outRect 绘制的地方
         * @param view    item
         * @param parent  rv
         * @param state   state
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int itemPosition = parent.getChildAdapterPosition(view);//item的position
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
