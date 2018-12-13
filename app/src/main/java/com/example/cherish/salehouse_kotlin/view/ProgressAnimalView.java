package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.LocalSocketAddress;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 进度条
 * Created by cherish
 */

public class ProgressAnimalView extends View {
    private int radius;
    private int progressColor;
    private int defaultColor;
    private Paint mOriginPaint, mProgressPaint, mTextPaint;
    private int mStrokeWidth = 10;
    private float startAngle = 0;//开始角度
    private float sweepAngle = 360;//扫描弧度
    private float mCurrentProgress = 1;//当前的进度值
    private float mMaxProgress = 100;//最大的进度值
    private float mStepAngle;//弧度比例值
    private int mSize = 16;
    private int left;
    private int top;


    public ProgressAnimalView(Context context) {
        this(context, null);
    }

    public ProgressAnimalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressAnimalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressAnimalStyle);
        defaultColor = array.getColor(R.styleable.ProgressAnimalStyle_default_color, defaultColor);
        progressColor = array.getColor(R.styleable.ProgressAnimalStyle_progress_color,
                progressColor);
        radius = array.getDimensionPixelSize(R.styleable.ProgressAnimalStyle_radius, radius);
        mStrokeWidth = array.getDimensionPixelSize(R.styleable
                .ProgressAnimalStyle_progress_stroke_width, mStrokeWidth);
        array.recycle();
        init();
    }

    private void init() {
        mStrokeWidth = dip2px(mStrokeWidth);
        mSize = dip2px(mSize);
        /**
         * 圆环
         */
        mOriginPaint = new Paint();
        mOriginPaint.setStrokeCap(Paint.Cap.ROUND);//圆角
        mOriginPaint.setColor(defaultColor);
        mOriginPaint.setAntiAlias(true);
        mOriginPaint.setDither(true);
        mOriginPaint.setStrokeWidth(mStrokeWidth);
        mOriginPaint.setStyle(Paint.Style.STROKE);
        //进度 圆弧
        mProgressPaint = new Paint();
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);//圆角
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setDither(true);
        mProgressPaint.setStrokeWidth(mStrokeWidth);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        //文本
        mTextPaint = new Paint();
        mTextPaint.setColor(progressColor);
        mTextPaint.setTextSize(mSize);
        mOriginPaint.setAntiAlias(true);
        mOriginPaint.setDither(true);
    }

    /**
     * 把dip 转成像素
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources()
                .getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆的中心点 cx ,xy
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        canvas.drawCircle(cx, cy, radius, mOriginPaint);
        left = getWidth() / 2 - radius;
        top = getHeight() / 2 - radius;
        RectF oval = new RectF(left, top, left + 2 * radius, top + 2 * radius);
        mStepAngle = mCurrentProgress / mMaxProgress;
        canvas.drawArc(oval, startAngle, mStepAngle * sweepAngle, false, mProgressPaint);
        String tips = (int) mCurrentProgress + "%";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(tips, 0, tips.length(), rect);
        float dx = getWidth() / 2 - rect.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseline = getHeight() / 2 + dy;
        canvas.drawText(tips, dx, baseline, mTextPaint);
    }

    /**
     * 设置进度数
     *
     * @param currentProgress
     */
    public synchronized void setCurrentProgress(float currentProgress) {
        mCurrentProgress = currentProgress;
        invalidate();
    }
}
