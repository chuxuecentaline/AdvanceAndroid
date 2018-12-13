package com.example.cherish.salehouse_kotlin.view.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 下拉刷新的RecyclerView的辅助类为了匹配所有效果
 * Created by cherish
 */

public class RefreshRecyclerView extends WrapRecyclerView {
    //下拉刷新的辅助类
    private RefreshViewCreator mRefreshCreator;
    //控件距离手机屏幕的高度值
    private int mFingerDownY;
    //当前是否在拖动的状态
    private boolean mCurrentDrag;
    // 下拉刷新头部的高度
    private int mRefreshViewHeight = 0;
    /**
     * head 布局
     */
    private View mRefreshView;
    // 当前的状态
    private int mCurrentRefreshStatus;
    // 默认状态
    public int REFRESH_STATUS_NORMAL = 0x0011;
    // 下拉刷新状态
    public int REFRESH_STATUS_PULL_DOWN_REFRESH = 0x0022;
    // 松开刷新状态
    public int REFRESH_STATUS_LOOSEN_REFRESHING = 0x0033;
    // 正在刷新状态
    public int REFRESH_STATUS_REFRESHING = 0x0033;
    /**
     * 手指拖拽的
     */
    private float mDragIndex = 0.35f;
    private float mFingerDownX;

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 先处理下拉刷新，同时考虑刷新列表的不同风格样式
     * 所以，我们不能直接添加View,需要利用辅助类
     */
    public void addRefreshViewCreator(RefreshViewCreator refreshCreator) {
        this.mRefreshCreator = refreshCreator;
        addRefreshView();
    }



    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录手指按下的位置，之所以写在dispatchTouchEvent 那是因为如果
                //我们处理了条目点击事件，那么就不会进入onTouchEvent里面，所以只能在这里获取
                mFingerDownY = (int) ev.getRawY();
                mFingerDownX=ev.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentDrag) {
                    restoreRefreshView();

                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 加载头部刷新View
     */
    private void addRefreshView() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null && mRefreshCreator != null) {
            //添加头部的刷新View
            View refreshView = mRefreshCreator.getRefreshView(getContext(), this);
            if (refreshView != null) {
                addHeaderView(refreshView);
                this.mRefreshView = refreshView;
            }
        }
    }

    /**
     * 重置下拉刷新view
     */
    private void restoreRefreshView() {
        MarginLayoutParams params = (MarginLayoutParams) mRefreshView.getLayoutParams();
        int currentTopMargin = params.topMargin;
        int finalTopMargin = -mRefreshViewHeight + 1;
        if (mCurrentRefreshStatus == REFRESH_STATUS_LOOSEN_REFRESHING) {
            finalTopMargin = 0;
            mCurrentRefreshStatus = REFRESH_STATUS_REFRESHING;
            if (mRefreshCreator != null) {
                mRefreshCreator.onRefreshing(mRefreshView);
            }
            if (mListener != null) {
                mListener.onRefresh();
            }
        }
        int distance = currentTopMargin - finalTopMargin;
        //回弹到指定位置
        ValueAnimator animator = ObjectAnimator.ofFloat(currentTopMargin, finalTopMargin)
                .setDuration(distance);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentTopMargin = (float) animation.getAnimatedValue();
                setRefreshViewMarginTop((int) currentTopMargin);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }

    /**
     * 重置top
     *
     * @param currentTopMargin
     */
    private void setRefreshViewMarginTop(int currentTopMargin) {
        MarginLayoutParams params = (MarginLayoutParams) mRefreshView.getLayoutParams();
        if (currentTopMargin < -mRefreshViewHeight + 1) {
            currentTopMargin = -mRefreshViewHeight + 1;
        }
        params.topMargin = currentTopMargin;
        mRefreshView.setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {

            case MotionEvent.ACTION_MOVE:
                float distanceX = e.getRawX() - mFingerDownX;
                float distanceY2 =  e.getRawY() - mFingerDownY;
                //如果实在最顶部才处理，负责不需要处理
                if (canScrollUp() || mCurrentRefreshStatus == REFRESH_STATUS_REFRESHING||Math.abs(distanceX)>Math.abs(distanceY2)) {
                    //如果没有到达最顶端，也就是说还可以向上滚动就神都不处理
                    return super.onTouchEvent(e);
                }
                //解决下拉刷新自动滚动问题
                if (mCurrentDrag) {
                    scrollToPosition(0);
                }
                // 获取手指触摸拖拽的距离
                int distanceY = (int) (distanceY2 * mDragIndex);
                //如果是已经到达头部，并且不断地向下拉，那么不断的改变refreshView的marginTop 的值
                if (distanceY > 0) {
                    int marginTop = distanceY - mRefreshViewHeight;
                    setRefreshViewMarginTop(distanceY);
                    updateRefreshStatus(marginTop);
                    mCurrentDrag = true;
                    return false;
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    /**
     * 判断是不是滚动到了最顶部，这个是从swipeRefreshLayout里面copy过来的源代码
     *
     * @return
     */
    public boolean canScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            return ViewCompat.canScrollVertically(this, -1) || this.getScrollY() > 0;
        } else {
            return ViewCompat.canScrollVertically(this, -1);
        }
    }

    /**
     * 更新刷新的状态
     *
     * @param marginTop
     */
    private void updateRefreshStatus(int marginTop) {
        if (marginTop <= -mRefreshViewHeight) {
            mCurrentRefreshStatus = REFRESH_STATUS_NORMAL;

        } else if (marginTop < 0) {
            mCurrentRefreshStatus = REFRESH_STATUS_PULL_DOWN_REFRESH;
        } else {
            mCurrentRefreshStatus = REFRESH_STATUS_LOOSEN_REFRESHING;
        }
        if (mRefreshCreator != null && mCurrentRefreshStatus != REFRESH_STATUS_NORMAL) {
            mRefreshCreator.onPull(marginTop, mRefreshViewHeight, mCurrentRefreshStatus);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            if (mRefreshView != null && mRefreshViewHeight <= 0) {
                //获取头部刷新View 的高度
                mRefreshViewHeight = mRefreshView.getMeasuredHeight();
                if (mRefreshViewHeight > 0) {
                    //隐藏头部刷新的View marginTop 多留出1px防止无法判断是不是滚动到头部问题
                    setRefreshViewMarginTop(-mRefreshViewHeight + 1);
                }
            }
        }
    }

    /**
     * 停止刷新
     */
    public void onStopRefresh() {
        mCurrentRefreshStatus = REFRESH_STATUS_NORMAL;
        restoreRefreshView();
        if (mRefreshCreator != null) {
            mRefreshCreator.onStopRefresh();
        }
    }

    // 处理刷新回调监听
    private OnRefreshListener mListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }


}
