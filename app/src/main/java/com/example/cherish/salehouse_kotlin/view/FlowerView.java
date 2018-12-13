package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cherish.salehouse_kotlin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 贝塞尔曲线 - 花束直播点赞效果
 * Created by cherish
 */

public class FlowerView extends RelativeLayout {
    /* 1.自定义View的一些基础；
     2.随机数的使用；
     3.插补器的使用；
     4.属性动画的一些高级用法
     5.贝塞尔曲线应用到属性动画*/
    List<Drawable> mDrawables = new ArrayList<>();
    private LayoutParams mParams;
    private int mWidth;
    private int mHeight;
    private Random mRandom;

    private int mDrawableWidth;
    private int mDrawableHeight;
    private Interpolator[] mInterpolators;

    public FlowerView(Context context) {
        this(context, null);
    }

    public FlowerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        for (int i = 0; i < 10; i++) {
            int id = context.getResources().getIdentifier("ic_thumb_up" + i, "drawable", context
                    .getPackageName());
            Drawable drawable = getContext().getResources().getDrawable(id);
            mDrawables.add(drawable);
        }

        initInterpolator();
        mParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // 父容器水平居中
        mParams.addRule(CENTER_HORIZONTAL, TRUE);
        // 父容器的底部
        mParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        mRandom = new Random();
    }

    /**
     * 开始
     */
    public void addLove() {

        Drawable drawable = mDrawables.get(mRandom.nextInt(mDrawables.size()-1));
        if (mDrawableWidth == 0) {
            mDrawableWidth = drawable.getIntrinsicWidth();
            mDrawableHeight = drawable.getIntrinsicHeight();
        }
        final ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(mParams);
        imageView.setImageDrawable(drawable);
        addView(imageView);

        // 最终的属性动画集合
        AnimatorSet finalSet = getAnimatorSet(imageView);
       // finalSet.playSequentially();
        finalSet.start();
        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

               getBezierValueAnimator(imageView).start();
            }
        });

    }

    /**
     * 初始化几种插补器
     */
    private void initInterpolator() {
        mInterpolators = new Interpolator[4];
        mInterpolators[0] = new LinearInterpolator();// 线性
        mInterpolators[1] = new AccelerateDecelerateInterpolator();// 先加速后减速
        mInterpolators[2] = new AccelerateInterpolator();// 加速
        mInterpolators[3] = new DecelerateInterpolator();// 减速
    }



    private AnimatorSet getAnimatorSet(ImageView imageView) {
        // 缩放

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1.0f);
        scaleX.setDuration(1000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1.0f);
        scaleX.setDuration(1000);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);
        scaleX.setDuration(1000);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY, alpha);

        return set;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mWidth == 0) {
            mWidth = getWidth();
            mHeight = getHeight();
        }


    }

    public void start() {
        for (int i = 0; i < mRandom.nextInt(15); i++) {
            addLove();
        }

    }

    /**
     * 贝塞尔曲线动画(核心，不断的修改ImageView的坐标ponintF(x,y) )
     */
    private ValueAnimator getBezierValueAnimator(final ImageView loveIv) {
        // 曲线的两个顶点
        PointF pointF2 = getPonitF(2);
        PointF pointF1 = getPonitF(1);
        // 起点位置
        PointF pointF0 = new PointF((mWidth-mDrawableWidth) / 2, mHeight - mDrawableHeight);
        // 结束的位置
        PointF pointF3 = new PointF(mRandom.nextInt(mWidth-mDrawableWidth), 0);
        // 估值器Evaluator,来控制view的行驶路径（不断的修改point.x,point.y）
        BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
        // 属性动画不仅仅改变View的属性，还可以改变自定义的属性
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0, pointF3);
        animator.setInterpolator(mInterpolators[mRandom.nextInt(mInterpolators.length)]);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF animatedValue = (PointF) animation.getAnimatedValue();
                loveIv.setX(animatedValue.x);
                loveIv.setY(animatedValue.y);
                loveIv.setAlpha(1 - animation.getAnimatedFraction() + 0.1f);//得到百分比

            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                removeView(loveIv);
            }
        });
        animator.setDuration(3000);
        return animator;
    }

    private PointF getPonitF(int position) {
        PointF pointF = new PointF();
        pointF.set(mRandom.nextInt(mWidth-mDrawableWidth), mRandom.nextInt(mHeight/2+(position-1)*mHeight/2));
        return pointF;
    }


    // 贝塞尔估值器
    public class BezierEvaluator implements TypeEvaluator<PointF> {
        private PointF point1, point2;

        public BezierEvaluator(PointF pointF1, PointF pointF2) {
            this.point1 = pointF1;
            this.point2 = pointF2;
        }

        @Override
        public PointF evaluate(float t, PointF point0, PointF point3) {
            // t百分比， 0~1
            PointF point = new PointF();
            point.x = point0.x * (1 - t) * (1 - t) * (1 - t) //
                    + 3 * point1.x * t * (1 - t) * (1 - t)//
                    + 3 * point2.x * t * t * (1 - t)//
                    + point3.x * t * t * t;//

            point.y = point0.y * (1 - t) * (1 - t) * (1 - t) //
                    + 3 * point1.y * t * (1 - t) * (1 - t)//
                    + 3 * point2.y * t * t * (1 - t)//
                    + point3.y * t * t * t;//
            // 套用上面的公式把点返回
            return point;
        }

    }


}
