package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
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
     5.贝塞尔曲线应用到属性动画*/ List<Drawable> mDrawables = new ArrayList<>();
    private LayoutParams mParams;
    private int mWidth;
    private int mHeight;
    private Random mRandom;

    private int mDrawableWidth;
    private int mDrawableHeight;

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
        finalSet.start();
        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getBezierValueAnimator(imageView).start();
            }
        });

    }

    private AnimatorSet getAnimatorSet(ImageView imageView) {
        // 缩放

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1.0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
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
        PointF pointF0 = new PointF((mWidth - mDrawableWidth) / 2, mHeight - mDrawableHeight);
        // 结束的位置
        PointF pointF3 = new PointF(mRandom.nextInt(mWidth), 0);
        // 估值器Evaluator,来控制view的行驶路径（不断的修改point.x,point.y）
        BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
        // 属性动画不仅仅改变View的属性，还可以改变自定义的属性
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0, pointF3);
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
                loveIv.setAlpha(0f);
                removeView(loveIv);
            }
        });
        animator.setDuration(3000);
        return animator;
    }

    private PointF getPonitF(int position) {
        PointF pointF = new PointF();
        if (position == 2) {
            pointF.set(mRandom.nextInt(mWidth), mRandom.nextInt(mHeight));
        } else {
            pointF.set(mRandom.nextInt(mWidth), mRandom.nextInt(mHeight));
        }


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
