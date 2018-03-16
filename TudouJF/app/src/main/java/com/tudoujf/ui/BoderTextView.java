package com.tudoujf.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.tudoujf.R;

/**
 * * ==================================================
 * name:            UnderlineTextView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   带边线和下划线的textview
 * history：
 * * ==================================================
 */

public class BoderTextView extends AppCompatTextView {

    private int underlineColor=0;
    private Paint underlinePaint,grayPaint;
    private AttributeSet attrs;
    private boolean  drawBoder=false;


    public void setDrawBoder(boolean drawBoder) {
        this.drawBoder = drawBoder;
    }

    public int getUnderlinecolor() {
        return underlineColor;
    }

    public void setUnderlinecolor(int underlinecolor) {
        this.underlineColor = underlinecolor;
    }


    public BoderTextView(Context context) {
        this(context, null, 0);
    }

    public BoderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        initView();
    }

    private void initView() {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BoderTextView);
            underlineColor = a.getColor(R.styleable.BoderTextView_bunderlinecolor, 0);
            a.recycle();
        }


        grayPaint = new Paint();
        grayPaint.setAntiAlias(true);
        grayPaint.setStyle(Paint.Style.FILL);
        grayPaint.setStrokeWidth(3);
        grayPaint.setAlpha(1);
        grayPaint.setColor(Color.parseColor("#dcdcdc"));
//        grayPaint.setColor(Color.RED);

        underlinePaint = new Paint();
        underlinePaint.setStrokeWidth(3);
        underlinePaint.setAntiAlias(true);
        underlinePaint.setStyle(Paint.Style.FILL);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (drawBoder){
            canvas.save();
            int width = getWidth();
            int height = getHeight();

            if (underlineColor!=0){
                try {
                    underlinePaint.setColor(getContext().getResources().getColor(underlineColor));
                }catch (Exception  e){
                    underlinePaint.setColor(underlineColor);
                    Log.e("UnderlineTextView", "onDraw:---- "+e.getMessage());
                }
            }
            canvas.drawLine(0, height - 3, width, height - 3, underlinePaint);
            canvas.drawLine(0, 3, 0, height-3, grayPaint);
            canvas.drawLine(width, 0, width, height, grayPaint);
            canvas.restore();
        }


    }
}
