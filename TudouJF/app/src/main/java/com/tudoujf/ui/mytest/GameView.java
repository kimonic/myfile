package com.tudoujf.ui.mytest;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ===============================================================
 * name:             GameView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/30
 * description：  自定义游戏控件
 * history：
 * *==================================================================
 */

public class GameView extends View {

    private Rect rect = new Rect();
    private Paint paint;

    private List<RectF> listRect;

    public GameView(Context context) {
        this(context, null, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @TargetApi(21)
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        rect.left = 0;
        rect.top = 0;
        rect.right = 90;
        rect.bottom = 90;
        paint = new Paint();
        paint.setColor(Color.parseColor("#9266F4"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        listRect = new ArrayList<>();

        int width = ScreenSizeUtils.getScreenWidth(getContext());

        float gap = (width - 900) / 11;
        int startHeight = 30;
        float size = width * 0.08333f;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                RectF rectF = new RectF(gap + (size + gap) * j,
                        startHeight+i*(size+gap),
                        (size + gap) * (j+1),
                        startHeight + (size+gap)*(i+1));
                listRect.add(rectF);
            }

        }

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.parseColor("#48AEFA"));

        for (int i = 0; i < listRect.size(); i++) {
            canvas.drawRect(listRect.get(i), paint);
        }

    }
}
