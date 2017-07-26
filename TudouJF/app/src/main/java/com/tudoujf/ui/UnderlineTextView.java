package com.tudoujf.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.tudoujf.R;

/**
 * * ==================================================
 * name:            UnderlineTextView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   带下划线的textview
 * history：
 * * ==================================================
 */

public class UnderlineTextView extends AppCompatTextView {

    private int underlinecolor= R.color.color_white;
    private Paint underlinePaint;

    public int getUnderlinecolor() {
        return underlinecolor;
    }

    public void setUnderlinecolor(int underlinecolor) {
        this.underlinecolor = underlinecolor;
    }



    public UnderlineTextView(Context context) {
        this(context, null, 0);
    }

    public UnderlineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        underlinePaint =new Paint();
        underlinePaint.setStrokeWidth(6);
        underlinePaint.setAntiAlias(true);
        underlinePaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        underlinePaint.setColor(getResources().getColor(underlinecolor));
        int width=getWidth();
        int height=getHeight();
        canvas.drawLine(0,height-3,width,height-3,underlinePaint);
        canvas.restore();
    }
}
