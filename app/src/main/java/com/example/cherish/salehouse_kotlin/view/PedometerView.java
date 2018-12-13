package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 自定义view 计步器
 * Created by cherish
 */

public class PedometerView extends View {
    /**
     * 起始弧度
     */
    private float mStartAngle = -230;
    /**
     * 扫描弧度
     */
    private float mSweepAngle = 280;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 画笔宽度
     */
    private float mCircleWidth = 20;
    /**
     * 计步器半圆的轨迹
     */
    private RectF mOval;
    /**
     * 字体大小
     */
    private float mTextSize = 60;
    /**
     * 字体颜色
     */
    private int mTextColor;
    /**
     * 文本
     */
    private String mStep = "0";
    /**
     * 进度条画笔
     */
    private Paint mProgressPaint;
    /**
     * 文字画笔
     */
    private Paint mTextPaint;
    /**
     * 最大步数
     */
    private int mMaxStep = 2400;
    /**
     * 进度值
     */
    private int mProgressStep;

    public PedometerView(Context context) {
        this(context, null);
    }

    public PedometerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PedometerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初试化
     */
    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边(空心)，
        mPaint.setStrokeWidth(mCircleWidth);//画笔宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);//取消锯齿
        mProgressPaint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边(空心)，
        mProgressPaint.setStrokeWidth(22);//画笔宽度
        mProgressPaint.setColor(getResources().getColor(R.color.colorAccent));
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);//圆角
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(getResources().getColor(R.color.colorAccent));
        mTextPaint.setTextAlign(Paint.Align.LEFT);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mOval==null){
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            //半径
            int radius = (int) (centerX/ 2 - mCircleWidth / 2);
            mOval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        }

        /**
         * useCenter 是否连接圆心
         */
        canvas.drawArc(mOval, mStartAngle, mSweepAngle, false, mPaint);
        // 计算当前百分比
        float percent = (float) mProgressStep / mMaxStep;
        canvas.drawArc(mOval, mStartAngle, percent * 200, false, mProgressPaint);
        mStep = mProgressStep + "";
        //todo 测量文字的宽高
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(mStep, 0, mStep.length(), textBounds);
        int dx = (getWidth() - textBounds.width()) / 2;
        // 获取文字的Metrics 用来计算基线
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        // 获取文字的宽高
        int fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        // 计算基线到中心点的位置
        int offY = fontTotalHeight / 2 - fontMetrics.bottom;
        // 计算基线位置
        int baseline = getHeight() / 2 + offY;

        canvas.drawText(mStep, dx, baseline, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width= MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height?height:width,width>height?height:width);

    }

    // 设置当前最大步数
    public synchronized void setMaxStep(int maxStep) {
        if (maxStep < 0) {
            throw new IllegalArgumentException("max 不能小于0!");
        }
        this.mMaxStep = maxStep;
    }

    public synchronized int getMaxStep() {
        return mMaxStep;
    }

    // 设置进度
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress 不能小于0!");
        }
        this.mProgressStep = progress;
        if (mProgressStep > mMaxStep) {
            return;
        }
        // 重新刷新绘制 -> onDraw()
        invalidate();
    }

    public synchronized int getProgress() {
        return mProgressStep;
    }

}
