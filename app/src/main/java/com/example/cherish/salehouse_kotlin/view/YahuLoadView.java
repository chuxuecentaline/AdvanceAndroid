package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.cherish.salehouse_kotlin.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class YahuLoadView extends View {

    private Paint mPaint;
    private int mRadius;
    private int mSplashColor;
    private int[] mCircleColors;
    private int mCenterX;
    private int mCenterY;
    private float mRotationRadius;
    private int mSmallRadius;
    //绘制水波纹
    private boolean drawWatterRipple = false;
    private int mWidth;
    private int mHeight;
    private Bitmap mBitmap;
    private Canvas mTransCanvas;

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
        mRadius = dip2px(30);
        mSmallRadius = dip2px(10);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mRotationRadius = 180;
        mCircleColors = new int[]{getResources().getColor(android.R.color.holo_blue_light),
                getResources().getColor(android.R.color.holo_green_light), getResources()
                .getColor(android.R.color.darker_gray), getResources().getColor(android.R.color
                .holo_orange_light), getResources().getColor(android.R.color.holo_purple),
                getResources().getColor(android.R.color.holo_red_light)};
        mSplashColor = getResources().getColor(android.R.color.white);

        rotateAnimal();


    }

    /**
     * 聚合动画
     */
    private void polymerization() {

        ValueAnimator rotate = ValueAnimator.ofInt(mRadius, 0);
        rotate.setDuration(1000);
        rotate.setInterpolator(new AnticipateInterpolator(6f));
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //6.mRadius 变小 聚合
                mRadius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                drawWatterRipple = true;
                drawWatterRipple();

            }
        });
        rotate.start();
    }

    /**
     * 去除图层
     */
    private void drawWatterRipple() {
        ValueAnimator rotate = ValueAnimator.ofInt(mRadius, mCenterY * 2);
        rotate.setDuration(1000);
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //6.mRadius 变小 聚合
                mRadius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.start();

    }

    /**
     * 扩散动画
     */
    private void spreadAnimal() {
        ValueAnimator rotate = ValueAnimator.ofInt(mRadius, dip2px(60));
        rotate.setDuration(1000);
        rotate.setInterpolator(new AccelerateInterpolator());
        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //5.mRadius  变大扩散
                mRadius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                polymerization();
            }
        });
        rotate.start();
    }

    /**
     * 旋转动画
     */
    private void rotateAnimal() {

        ValueAnimator rotate = ValueAnimator.ofFloat(0, (float) (2f * Math.PI));
        rotate.setDuration(2000);
        rotate.setInterpolator(new LinearInterpolator());
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
                spreadAnimal();
            }
        });


    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制六个小圆 坐标
        //1. 6 个小圆所在大圆的半径 为 mRadius=40;
        if (!drawWatterRipple) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
            float preAngle = (float) (2 * Math.PI / mCircleColors.length);
            for (int i = 0; i < mCircleColors.length; i++) {
                //2.屏幕的中心点
                //3.每个小圆的中心点 cx cy 半径 mSmallRadius
                //4.旋转角度
                float angle = preAngle * i + mRotationRadius;
                float cx = (float) (Math.cos(angle) * mRadius + mCenterX);
                float cy = (float) (Math.sin(angle) * mRadius + mCenterY);
                mPaint.setColor(mCircleColors[i]);
                canvas.drawCircle(cx, cy, mSmallRadius, mPaint);
            }
        } else {
            //7.遮罩擦除 (加了一个白色的图层)
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mTransCanvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
            canvas.drawBitmap(mBitmap, 0, 0, null);

        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        draw();
    }

    private void draw() {
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mTransCanvas = new Canvas(mBitmap);
        mTransCanvas.drawColor(mSplashColor);
    }

    private int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

}
