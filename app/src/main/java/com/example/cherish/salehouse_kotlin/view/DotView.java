package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 圆形
 * Created by cherish
 */

public class DotView extends View {

    private int mHeight;
    private int mWidth;
    private Paint mPaint;
    private int mColor = Color.parseColor("#ffffff");

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DotView);
        mColor = array.getColor(R.styleable.DotView_dotColor, mColor);
        array.recycle();

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dip2px(30), dip2px(30));
        if (mHeight == 0) {
            mHeight = getMeasuredHeight();
            mWidth = getMeasuredWidth();
            System.out.println("dot h:" + mHeight + "w:" + mWidth);
        }

    }

    public void changeColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    private int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }
}
