package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 58同城数据加载动画
 * Created by cherish
 */

public class LoadingView extends LinearLayout {
    private final Context mContext;
    private ImageView mShapeLoadingView;
    private ImageView mIndicationView;
    private float DISTANCE = 0f;
    private String CURRENT_SHAPE = "square";
    private ObjectAnimator mAnimator;


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        DISTANCE = dip2px(80);
        initLayout();
    }

    private void initLayout() {
        View.inflate(mContext, R.layout.load_view, this);
        mShapeLoadingView = findViewById(R.id.shapeLoadingView);
        mIndicationView = findViewById(R.id.indication);

        downAnimal();

    }

    /**
     * 下落动画
     */
    private void downAnimal() {

        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeLoadingView, "translationY",
                0, DISTANCE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIndicationView, "scaleX", 0.8f, 5f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(translationY, scaleX);
        set.setDuration(800);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                changeShape();
                upAnimal();
            }
        });

    }

    /**
     * 更改形状
     */
    private void changeShape() {
        switch (CURRENT_SHAPE) {
            case "round":
                mShapeLoadingView.setImageResource(R.drawable.ic_round);
                break;
            case "rectangle":
                mShapeLoadingView.setImageResource(R.drawable.ic_rectangle);
                break;
            case "square":

                mShapeLoadingView.setImageResource(R.drawable.ic_square);
                break;

        }
    }

    /**
     * 上抛动画
     */
    private void upAnimal() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeLoadingView, "translationY",
                DISTANCE, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIndicationView, "scaleX", 5f, 0.8f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(translationY, scaleX);
        set.setDuration(800);
        set.setInterpolator(new DecelerateInterpolator());

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                downAnimal();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                scaleAnimal();

            }
        });
        set.start();
    }

    /**
     * 旋转动画
     */
    private void scaleAnimal() {
        switch (CURRENT_SHAPE) {
            case "round":
                CURRENT_SHAPE = "rectangle";
                break;
            case "rectangle":
                CURRENT_SHAPE = "square";
                mAnimator = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0,90);
                mAnimator.setDuration(800);
                mAnimator.start();
                break;
            case "square":
                CURRENT_SHAPE = "round";
                mAnimator = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, -60);
                mAnimator.setDuration(800);
                mAnimator.start();
                break;

        }


    }

    /**
     * 把dip 转成像素
     */
    private float dip2px(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources()
                .getDisplayMetrics());
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, INVISIBLE);
        //停止动画
        mShapeLoadingView.clearAnimation();
        mIndicationView.clearAnimation();
        if (mAnimator != null) {
            mAnimator.cancel();

        }

    }
}
