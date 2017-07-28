package com.tudoujf.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tudoujf.R;

/**
 * * ================================================
 * name:            PasswordView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/25
 * description：  密码输入自定义view
 * history：
 * ===================================================
 */

public class PasswordView extends AppCompatEditText implements TextWatcher {

    private Paint borderPaint,contentPaint,backPaint;
    private String content;

    private int textCount=0;

    private TextView  btnText;

    public void setBtnText(TextView btnText) {
        this.btnText = btnText;
    }

    public PasswordView(Context context) {
        this(context, null, 0);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        borderPaint=initPaint("#D4D4D2");
        borderPaint.setStrokeWidth(5f);
        contentPaint=initPaint("#000000");
        backPaint=initPaint("#F0F0E8");
        this.addTextChangedListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();
        int height=getHeight();
        canvas.drawColor(Color.parseColor("#F0F0E8"));

        canvas.drawLine(0,0,width,0,borderPaint);
        canvas.drawLine(width,0,width,height-5,borderPaint);
        canvas.drawLine(0,height-5,width,height-5,borderPaint);
        canvas.drawLine(0,0,0,height-5,borderPaint);


        float  contentWidth=(width-7*5)/6f;
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(5*(i+1)+contentWidth*(i+1),0,5*(i+1)+contentWidth*(i+1),height,borderPaint);
        }

        int radius=(height-10)/4;

        for (int i = 0; i < textCount; i++) {
            if (i>5){
                break;
            }
            canvas.drawCircle((i+1)*5+contentWidth/2+contentWidth*i,height/2,radius,contentPaint);
        }




    }


    private  Paint initPaint(String color){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        textCount=s.length();
        if (textCount>6){
            content=s.subSequence(0,6).toString();
            this.setText(content);
            this.setSelection(content.length());
        }else {
            content=s.toString();
        }
        if (content.length()==6&&btnText!=null){
            btnText.setClickable(true);
            btnText.setBackgroundResource(R.drawable.xshape_roundrect_mborderblue);
        }else if (btnText!=null){
            btnText.setClickable(false);
            btnText.setBackgroundResource(R.drawable.xshape_roundrect_mbordergray);
        }
        invalidate();
    }

    /**获取密码*/
    public String getContent(){
        return content==null?"":content;
    }
}
