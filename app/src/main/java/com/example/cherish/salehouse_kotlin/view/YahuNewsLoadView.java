package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import com.example.cherish.salehouse_kotlin.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 雅虎新闻摘要加载
 * Created by cherish
 * <p>
 * 　  1. 旋转动画，六个小球在不断地旋转
 * 　　3. 扩散动画，当小球移动的最中心就开始扩散
 * 　　2. 位移动画，六个小球往中心点聚合
 */

public class YahuNewsLoadView extends RelativeLayout {

    //点距离中心点的距离
    private int mRadius;
    //偏移量
    private int OFFSET;
    private RelativeLayout.LayoutParams mParams;
    //平六等份的角度
    private int angle = 60;
    private double mSin;
    private double mCos;
    private int mMiddleW;
    private int mMiddleH;


    public YahuNewsLoadView(Context context) {
        this(context, null);
    }

    public YahuNewsLoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YahuNewsLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();

    }

    private void initParams() {
        mRadius = dip2px(80);
        OFFSET = dip2px(20);
        mSin = Math.sin(angle);
        mCos = Math.cos(angle);
        System.out.println("dotView   L:" + mSin + "--------T:" + mCos);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }


    public void initView(Context context) {

        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewA = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW - mRadius - 1.5 * mParams.width);
        mParams.topMargin = (int) (mMiddleH + mParams.height / 1.5);
        dotViewA.changeColor(getResources().getColor(android.R.color.holo_blue_dark));
        addView(dotViewA, mParams);


        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewC = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW - mSin * mRadius + mParams.width / 2);
        mParams.topMargin = (int) (mMiddleH + mParams.height - mCos * mRadius);
        dotViewC.changeColor(getResources().getColor(android.R.color.holo_green_dark));
        addView(dotViewC, mParams);
        System.out.println("dotViewC   L:" + mParams.leftMargin + "--------T:" + mParams.topMargin);

        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewE = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW - 1.5 * mParams.width + mSin * mRadius);
        mParams.topMargin = (int) (mMiddleH + mParams.height - mCos * mRadius);
        dotViewE.changeColor(getResources().getColor(android.R.color.holo_red_dark));
        addView(dotViewE, mParams);
        System.out.println("dotViewE   L:" + mParams.leftMargin + "--------T:" + mParams.topMargin);

        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewB = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW + mRadius + mParams.width / 2);
        mParams.topMargin = (int) (mMiddleH + mParams.height / 1.5);
        dotViewB.changeColor(getResources().getColor(android.R.color.holo_purple));
        addView(dotViewB, mParams);

        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewD = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW - 1.5 * mParams.width + mSin * mRadius);
        mParams.topMargin = (int) (mMiddleH + mCos * mRadius);
        dotViewD.changeColor(getResources().getColor(android.R.color.holo_orange_light));
        addView(dotViewD, mParams);
        System.out.println("dotViewD   L:" + mParams.leftMargin + "--------T:" + mParams.topMargin);

        mParams = new RelativeLayout.LayoutParams(dip2px(30), dip2px(30));
        DotView dotViewF = new DotView(context);
        mParams.leftMargin = (int) (mMiddleW - mSin * mRadius + mParams.width / 2);
        mParams.topMargin = (int) (mMiddleH + mCos * mRadius);
        dotViewF.changeColor(getResources().getColor(android.R.color.holo_red_light));
        addView(dotViewF, mParams);
        System.out.println("dotViewF   L:" + mParams.leftMargin + "--------T:" + mParams.topMargin);
        RotateAnimal();

    }

    class RotationEvaluator implements TypeEvaluator<PointF> {
        private final PointF p1;

        public RotationEvaluator(PointF p1) {
            this.p1 = p1;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            // 二次方贝兹曲线的路径由给定点P0、P1、P2的函数B（t）追踪：
            PointF pointF = new PointF();
            pointF.x = (1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 -
                    fraction) * p1.x + fraction * fraction * endValue.x;
            pointF.y = (1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 -
                    fraction) * p1.y + fraction * fraction * endValue.y;
            return pointF;
        }
    }

    private void RotateAnimal() {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            RelativeLayout.LayoutParams params = (LayoutParams) child.getLayoutParams();



        }

    }

    private void bzeirAnimal(PointF p1, PointF startValue, PointF endValue, final View child) {

        ValueAnimator animator = ObjectAnimator.ofObject(new RotationEvaluator(p1), startValue,
                endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF animatedValue = (PointF) animation.getAnimatedValue();
                child.setX(animatedValue.x);
                child.setY(animatedValue.y);
            }
        });
        animator.setDuration(3000);
        animator.start();
    }

    int mHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeight == 0) {
            mHeight = getMeasuredHeight();
            int width = getMeasuredWidth();
            mMiddleW = width / 2;
            mMiddleH = mHeight / 2;
            System.out.println("---->h" + mHeight + "---->w" + width + "---->r" + mRadius);
            initView(getContext());
        }

    }

    private int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

}
