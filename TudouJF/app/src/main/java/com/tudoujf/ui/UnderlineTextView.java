package com.tudoujf.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
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
 * description：   带下划线的textview
 * history：
 * * ==================================================
 */

public class UnderlineTextView extends AppCompatTextView {

    private int underlineColor=0;
    private Paint underlinePaint;
    private AttributeSet attrs;

    public int getUnderlinecolor() {
        return underlineColor;
    }

    public void setUnderlinecolor(int underlinecolor) {
        this.underlineColor = underlinecolor;
    }


    public UnderlineTextView(Context context) {
        this(context, null, 0);
    }

    public UnderlineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        initView();
    }

    private void initView() {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.UnderlineTextView);
            underlineColor = a.getColor(R.styleable.UnderlineTextView_underlinecolor, 0);
            a.recycle();
        }




        underlinePaint = new Paint();
        underlinePaint.setStrokeWidth(6);
        underlinePaint.setAntiAlias(true);
        underlinePaint.setStyle(Paint.Style.FILL);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (underlineColor!=0){
            try {
                underlinePaint.setColor(getContext().getResources().getColor(underlineColor));
            }catch (Exception  e){
                underlinePaint.setColor(underlineColor);
            }
        }else {
            underlinePaint.setColor(getResources().getColor(R.color.color_white));
        }
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height - 3, width, height - 3, underlinePaint);
        canvas.restore();
    }
}
