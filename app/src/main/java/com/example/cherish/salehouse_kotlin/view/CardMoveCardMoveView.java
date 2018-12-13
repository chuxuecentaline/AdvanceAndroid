package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 卡片移除
 * Created by cherish
 * 1.
 */

public class CardMoveCardMoveView extends FrameLayout {

    ViewDragHelper mViewDragHelper;
    private int mWidth;
    private int mHeight;
    private float mStartY;
    private float mStartX;
    private float mMoveX;
    private float mMoveY;
    private float mRotateL;
    private int mLeftMargin;
    private int mTopMargin;

    public CardMoveCardMoveView(@NonNull Context context) {
        this(context, null);
    }

    public CardMoveCardMoveView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardMoveCardMoveView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, callBack);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count == 0) {
            throw new IllegalThreadStateException("至少包含一个子view");
        }


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View child = getChildAt(0);
            mWidth = child.getMeasuredWidth();
            mHeight = child.getMeasuredHeight();
            mLeftMargin = (getMeasuredWidth() - mWidth) / 2;
            mTopMargin = (getMeasuredHeight() - mHeight) / 2;

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX()- mStartX;
                mMoveY = event.getY() - mStartY;

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {
        /**
         *  //是否允许拖拽
         * @param child
         * @param pointerId
         * @return
         */
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }


        /**
         * 垂直方向的偏移
         * @param child
         * @param top
         * @param dy
         * @return
         */
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        /**
         * 水平方向的偏移
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            //dx 向右>0 向左< 0
            //1. 向左倾斜最大值0-45度
            mRotateL = (float) (mMoveX * 0.1);
            if (mMoveX > 0) {

                if (Math.abs(mRotateL) >= 60) {
                    mRotateL = 60;
                    mMoveX = 0;
                    removeView(child);
                    invalidate();
                } else if (Math.abs(mRotateL) < 60 && Math.abs(mRotateL) >= 0) {
                    child.setTranslationX(mMoveX);
                    child.setRotation(mRotateL);
                }


            } else if (mMoveX < 0) {
                if (Math.abs(mRotateL) >= 60) {
                    mRotateL = 60;
                    mMoveX = 0;
                    mRotateL = 60;
                    removeView(child);
                    invalidate();
                } else if (Math.abs(mRotateL) < 60) {
                    child.setTranslationX(mMoveX);
                    child.setRotation(mRotateL);
                }

            }


            return left;
        }

        /**
         * 手指松开
         */
        @Override
        public void onViewReleased(@NonNull final View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            System.out.println("xvel:" + xvel);
            System.out.println("yvel:" + yvel);
            /**
             * 停止滚动回弹
             */
            if (Math.abs(mRotateL) < 30) {
                releasedChild.setRotation(0);
                releasedChild.setTranslationX(0);
                mViewDragHelper.settleCapturedViewAt(mLeftMargin, mTopMargin);

            } else if (Math.abs(mRotateL) > 30) {
                //超过 30 度 继续
                if (mRotateL < 0) {
                    releasedChild.animate().translationX(-mWidth + mMoveX).setDuration(500).start();
                    //-x - 60 自动
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRotateL, -60f);
                    valueAnimator.setDuration(500);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float values = (float) animation.getAnimatedValue();
                            releasedChild.setRotation(values);

                        }
                    });
                    valueAnimator.start();
                    valueAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            removeView(releasedChild);
                        }
                    });

                } else {
                    releasedChild.animate().translationX(mWidth - mMoveX).setDuration(500).start();
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRotateL, 60f);
                    valueAnimator.setDuration(500);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float values = (float) animation.getAnimatedValue();
                            releasedChild.setRotation(values);
                        }
                    });
                    valueAnimator.start();
                    valueAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            removeView(releasedChild);
                        }
                    });
                }


            }
            invalidate();

        }
    };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }

    }

}
