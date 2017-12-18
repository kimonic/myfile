package com.tudoujf.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * * ===============================================================
 * name:             GridSpacingItemDecoration
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/18
 * description：
 * history：
 * *==================================================================
 */


public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;

    public GridSpacingItemDecoration() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setColor(Color.parseColor("#dddddd"));
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 0) {
            outRect.left = 0;
            outRect.right = 3;
            outRect.bottom = 3;
            outRect.top = 0;
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = 3;
            outRect.top = 0;
        }
    }

    /**
     *
     * @param c  RecyclerView的画布
     * @param parent RecyclerView
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        Log.e("TAG", "getItemOffsets: --执行-count--"+count);
        int bottom;
        if (count % 2 == 0) {
            bottom = count/2;
        } else {
            bottom = count/2 + 1;
        }

        int height = parent.getMeasuredHeight();
        int width = parent.getMeasuredWidth();
        int childHeight =height/bottom;

        for (int i = 0; i < bottom; i++) {
            c.drawLine(0, childHeight*(i+1),width,childHeight*(i+1),paint);
        }

        c.drawLine(width/2,0,width/2,height,paint);


        super.onDraw(c, parent, state);
    }
}
