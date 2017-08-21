package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

/**
 * * ==================================================
 * name:            SignInView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：   签到view
 * history：
 * * ==================================================
 */

public class SignInView extends View {

    private int width;
    private static final float whScale = 0.7573f;
    private static final int BACRESID = R.drawable.view_signin;
    private Bitmap bitmap;
    private Rect rect = new Rect(), rectClick = new Rect();
    /**当前总积分*/
    private String totalIntegrel = "00,000";
    /**签到成功*/
    private String signInSuccess = "签到成功,获得1积分";
    private Paint whitePaint, textPaint;
    /**
     * 是否是点击此处签到
     * true--未签到,false--已签到
     */
    private boolean flagIsSignIn = true;
    /**点击成功后不再响应点击事件
     * false---可点击
     * true--不能点击
     * */
    private boolean flagSignInSuccess = false;
    /**点击事件监听*/
    private ClickEventListener  listener;
    private int currentX;
    private int currentY;

    public boolean isFlagSignInSuccess() {
        return flagSignInSuccess;
    }

    public void setFlagSignInSuccess(boolean flagSignInSuccess) {
        this.flagSignInSuccess = flagSignInSuccess;
    }

    /**当前总积分*/
    public void setTotalIntegrel(String totalIntegrel) {
        this.totalIntegrel = totalIntegrel;
    }
    /**是否已签到*/
    public void setFlagIsSignIn(boolean flag) {
        this.flagIsSignIn = flag;
    }
    /**点击时间监听*/
    public void setListener(ClickEventListener listener) {
        this.listener = listener;
    }

    public SignInView(Context context) {
        this(context, null, 0);
    }

    public SignInView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignInView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public SignInView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        width = ScreenSizeUtils.getScreenWidth(getContext());
        bitmap = BitmapFactory.decodeResource(getResources(), BACRESID);
        whitePaint = initPaint(Color.parseColor("#FFFFFF"));

        textPaint = initPaint(Color.parseColor("#0F9EBE"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        rect.left = 0;
        rect.top = 0;
        rect.right = getWidth();
        rect.bottom = getHeight();
        canvas.drawBitmap(bitmap, null, rect, null);

        rectClick.left = (int) (width * 0.2533f);
        rectClick.top = (int) (width * 0.1533f);
        rectClick.right = (int) (width * 0.7333f);
        rectClick.bottom = (int) (width * 0.6333f);


        whitePaint.setTextSize(width * 0.04f);
        float textX = width * 0.03333f;
        float textY = width * 0.1066f;
        canvas.drawText(getResources().getString(R.string.zongjifen), textX, textY, whitePaint);

        float textX1 = width * 0.2f;
        canvas.drawText(totalIntegrel, textX1, textY, whitePaint);

        if (flagIsSignIn) {

            textPaint.setTextSize(width * 0.06666f);
            float textX2 = width * 0.368f;
            float textY2 = width * 0.35f;
            canvas.drawText(getResources().getString(R.string.dianjicichu), textX2, textY2, textPaint);

            textPaint.setTextSize(width * 0.1066f);
            float textX3 = width * 0.39f;
            float textY3 = width * 0.49f;
            canvas.drawText(getResources().getString(R.string.qiandao), textX3, textY3, textPaint);

        } else {
            textPaint.setTextSize(width * 0.1f);
            float textX2 = width * 0.3466f;
            float textY2 = width * 0.44f;
            canvas.drawText(getResources().getString(R.string.yiqiandao), textX2, textY2, textPaint);

        }


        if (!flagIsSignIn){
            float textX3=width*0.32f;
            float textY3=width*0.71f;
            canvas.drawText(signInSuccess, textX3, textY3, whitePaint);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, (int) (width * whScale));
    }

    private Paint initPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setColor(color);
        return paint;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                 currentX= (int) event.getX();
                 currentY= (int) event.getY();
                if (rectClick.contains(currentX,currentY)&&!flagSignInSuccess){
                    flagIsSignIn=false;
                    if (listener!=null){
                        listener.clickEvent();
                    }


                }

                break;
        }
        return true;
    }

    public interface ClickEventListener{
        void clickEvent();
    }
}
