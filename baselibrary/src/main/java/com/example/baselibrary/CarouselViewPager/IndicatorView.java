package com.example.baselibrary.CarouselViewPager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.baselibrary.R;

/**
 * 自定义指示器
 * Created by cherish
 */

public class IndicatorView extends View {
    /**
     * 右边距
     */
    private float mMarginRight;
    /**
     * 居中 :左 中 右
     */
    private int mGravity = 0;
    /**
     * 半径
     */
    private float mRadius = 4;
    /**
     * 选中颜色
     */
    private int mColor = Color.parseColor("#ff0000");
    /**
     * 默认颜色
     */
    private int mDefaultColor = Color.parseColor("#ffffff");
    private Paint mPaint;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRadius = dip2px(4);
        mMarginRight = dip2px(16);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        mDefaultColor = array.getColor(R.styleable.IndicatorView_default_color, mDefaultColor);
        mColor = array.getColor(R.styleable.IndicatorView_color, mColor);
        mRadius = array.getDimension(R.styleable.IndicatorView_radius, mRadius);
        mGravity = array.getInt(R.styleable.IndicatorView_gravity, mGravity);
        mMarginRight = array.getDimension(R.styleable.IndicatorView_margin_right, mMarginRight);
        array.recycle();
        initPaint();
    }

    public float dip2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mDefaultColor);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(8, getHeight() / 2, mRadius, mPaint);
    }


    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }


}
