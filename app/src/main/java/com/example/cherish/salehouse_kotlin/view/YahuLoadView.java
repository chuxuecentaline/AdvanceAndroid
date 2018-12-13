package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.RotateAnimation;

import com.example.cherish.salehouse_kotlin.R;

import java.util.List;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class YahuLoadView extends View {

    private Paint mPaint;
    private int mRadius;
    private int mSplashColor;
    private int[] mCircleColors;
    private float mCurrentRotationAngle = 0;
    private int mCenterX;
    private int mCenterY;
    private float mRotationRadius;

    public YahuLoadView(Context context) {
        this(context, null);
    }

    public YahuLoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YahuLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mRadius = dip2px(20);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mRotationRadius=180;
        mCircleColors = new int[]{getResources().getColor(android.R.color.holo_blue_light),
                getResources().getColor(android.R.color.holo_green_light), getResources()
                .getColor(android.R.color.darker_gray), getResources().getColor(android.R.color
                .holo_orange_light), getResources().getColor(android.R.color.holo_purple),
                getResources().getColor(android.R.color.holo_red_light)};

        rotateAnimal();



    }

    /**
     * 聚合动画
     */
    private void polymerization() {

        ValueAnimator rotate = ValueAnimator.ofFloat(360,0);
        rotate.setDuration(2000);
        rotate.start();
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotationRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
    }

    /**
     * 扩散动画
     */
    private void spreadAnimal() {

        ValueAnimator rotate = ValueAnimator.ofFloat(mRotationRadius, 360);
        rotate.setDuration(2000);
        rotate.start();
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotationRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                polymerization();
            }
        });
    }

    /**
     * 旋转动画
     */
    private void rotateAnimal() {
        ValueAnimator rotate = ValueAnimator.ofFloat(0,2);
        rotate.setDuration(2000);
        rotate.start();
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentRotationAngle= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                spreadAnimal();
            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(mSplashColor);
        // 绘制六个小圆 坐标
        float preAngle = (float) (2 * Math.PI / mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            mPaint.setColor(mCircleColors[i]);
            // 初始角度 + 当前旋转的角度
            double angle = i * preAngle + mCurrentRotationAngle;
            float cx = (float) (mCenterX + mRotationRadius * Math.cos(angle));
            float cy = (float) (mCenterY + mRotationRadius * Math.sin(angle));
            canvas.drawCircle(cx, cy, mRadius, mPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mCenterX = width / 2;
        mCenterY = height / 2;
    }

    private int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

}
