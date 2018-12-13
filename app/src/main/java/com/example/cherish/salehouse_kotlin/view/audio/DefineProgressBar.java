package com.example.cherish.salehouse_kotlin.view.audio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


/**
 * 进度条
 * Created by cherish
 */

public class DefineProgressBar extends View {
    /**
     * 默认画笔
     */
    private Paint mDefaultPaint;

    /**
     * 进度画笔
     *
     * @param context
     */
    private Paint mProgressPaint;
    /**
     * 画笔的宽度
     */
    private float STROKEWIDTH = 0;
    /**
     * 宽度 、高度
     */
    private int mWidth;
    private int mHeight;
    /**
     * 偏移量
     */
    private float OFFSET = 0f;
    /**
     * 当前的进度
     */
    private float mCurrentProgress = 0;
    /**
     * 半径
     */
    private float mRadius;
    /**
     * 是否支持拖拽
     */
    private boolean enableDrag = true;


    public DefineProgressBar(Context context) {
        this(context, null);
    }

    public DefineProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefineProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        STROKEWIDTH = dip2px(4);
        mRadius = dip2px(4);
        OFFSET = dip2px(2);
        mHeight = (int) dip2px(20);
        init();
    }


    private void init() {
        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setDither(true);
        mDefaultPaint.setColor(Color.parseColor("#ffffff"));
        mDefaultPaint.setStrokeWidth(STROKEWIDTH);
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setDither(true);
        mProgressPaint.setColor(Color.parseColor("#E95533"));
        mProgressPaint.setStrokeWidth(STROKEWIDTH);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mWidth == 0) {
            mWidth = getMeasuredWidth();
            System.out.println("width:" + mWidth);
        }

        setMeasuredDimension(widthMeasureSpec, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mDefaultPaint);
        canvas.drawLine(0, mHeight / 2, mCurrentProgress * mWidth, mHeight / 2, mProgressPaint);
        //圆的中心点 cx ,xy
        float cx = mCurrentProgress * mWidth;
        float endcx = 0;
        if (endcx == 0) {
            endcx = mWidth - mRadius;
        }
        float cy = getHeight() / 2;
        System.out.println("pro----" + cx);
        if (cx >= 0 && cx < endcx) {
            canvas.drawCircle(cx + mRadius , cy, mRadius, mProgressPaint);
        } else if (cx > endcx) {
            canvas.drawCircle(endcx, cy, mRadius, mProgressPaint);

        }

    }

    /**
     * 把dip 转成像素
     */
    private float dip2px(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources()
                .getDisplayMetrics());
    }

    /**
     * 设置进度数
     *
     * @param currentProgress
     */
    public synchronized void setCurrentProgress(float currentProgress) {
        if (currentProgress < 0) {
            throw new IllegalArgumentException("progress 不能小于0!");
        }
        this.mCurrentProgress = currentProgress;
        if (mCurrentProgress > 1) {
            return;
        }
        // 重新刷新绘制 -> onDraw()
        invalidate();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enableDrag) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    float x = event.getX();
                    if (x > 0) {
                        System.out.println("---------:" + x);
                        setCurrentProgress(x / mWidth);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                   float moveX = event.getX();
                    if (moveX > 0) {
                        System.out.println("---------:" + moveX);
                        setCurrentProgress(moveX / mWidth);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    ObserverManager.getInstance().notifyObserver(event.getX() / mWidth);
                    break;
            }

        }
        return true;
    }

    public void setEnableDrag(boolean enableDrag) {
        this.enableDrag = enableDrag;
    }


    public void release() {

    }
}
