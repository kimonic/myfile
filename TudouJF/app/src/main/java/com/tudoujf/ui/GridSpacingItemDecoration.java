package com.tudoujf.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tudoujf.utils.LUtils;

/**
 * * ===============================================================
 * name:             GridSpacingItemDecoration
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/18
 * description：  recyclerview自定义分割线
 * history：
 * *==================================================================
 */


public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    public GridSpacingItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = 0;
            outRect.top = 0;
        } else if (position % 2 == 1) {
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
     * @param c      RecyclerView的画布
     * @param parent RecyclerView
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.drawColor(Color.parseColor("#dddddd"));
        super.onDraw(c, parent, state);
    }


}
