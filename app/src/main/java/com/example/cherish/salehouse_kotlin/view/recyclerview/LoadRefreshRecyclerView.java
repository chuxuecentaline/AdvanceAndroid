package com.example.cherish.salehouse_kotlin.view.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 下拉刷新上拉加载更多的RecyclerView
 * Created by cherish
 */

public class LoadRefreshRecyclerView extends RefreshRecyclerView {
    private LoadMoreViewCreator mLoadMoreViewCreator;
    private View mLoadView;
    /**
     * 手指按下的点距离屏幕的高度
     */
    private float mFingerDownY;
    /**
     * 正在加载
     */
    private boolean mCurrentDrag;

    // 当前的状态
    private int mCurrentLoadStatus;
    // 默认状态
    public int LOAD_STATUS_NORMAL = 0x0011;
    // 上拉加载更多状态
    public static int LOAD_STATUS_PULL_DOWN_REFRESH = 0x0022;
    // 松开加载更多状态
    public static int LOAD_STATUS_LOOSEN_LOADING = 0x0033;
    // 正在加载更多状态
    public int LOAD_STATUS_LOADING = 0x0044;
    // 上拉加载更多头部的高度
    private int mLoadViewHeight;
    //手指拖拽的阻力指数
    private float mDragIndex = 0.35f;
    private float mFingerDownX;


    public LoadRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 先处理加载等多，同时考虑刷新列表的不同风格样式
     * 所以，我们不能直接添加View,需要利用辅助类
     */
    public void addLoadViewCreator(LoadMoreViewCreator loadMoreViewCreator) {
        this.mLoadMoreViewCreator = loadMoreViewCreator;
        addLoadView();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addLoadView();
    }

    /**
     * 加载更多布局
     */
    private void addLoadView() {
        Adapter adapter = getAdapter();
        if (adapter != null && mLoadMoreViewCreator != null) {
            View loadView = mLoadMoreViewCreator.getLoadView(getContext(), this);
            if (loadView != null) {
                addFooterView(loadView);
                this.mLoadView = loadView;
            }

        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFingerDownY = e.getRawY();
                mFingerDownX=  e.getRawX();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentDrag) {
                    restoreLoadView();
                }
               break;
        }
        return super.dispatchTouchEvent(e);
    }

    private void restoreLoadView() {
        final MarginLayoutParams layoutParams = (MarginLayoutParams) mLoadView.getLayoutParams();
        float bottomMargin = layoutParams.bottomMargin;
        float finalHeight = 0;
        if (mCurrentLoadStatus == LOAD_STATUS_LOOSEN_LOADING) {
            mCurrentLoadStatus = LOAD_STATUS_LOADING;
            finalHeight = 0;
            if (mLoadMoreViewCreator != null) {
                mLoadMoreViewCreator.onLoading();
            }
        }
        int duration = (int) (bottomMargin - finalHeight);
        ValueAnimator animator = ObjectAnimator.ofFloat(bottomMargin, finalHeight).setDuration
                (duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setLoadViewMarginBottom((int) value);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float distanceX = e.getRawX() - mFingerDownX;
                float distanceY2 =  e.getRawY() - mFingerDownY;
                if (canScrollDown() || mCurrentLoadStatus == LOAD_STATUS_LOADING||Math.abs(distanceX)>Math.abs(distanceY2)) {
                    return super.onTouchEvent(e);
                }
                if(mLoadViewHeight==0&&mLoadView!=null){
                    mLoadViewHeight = mLoadView.getMeasuredHeight();
                }
                // 解决上拉加载更多自动滚动问题
                if (mCurrentDrag) {
                    scrollToPosition(getAdapter().getItemCount() - 1);
                }

                //滑动的距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY) * mDragIndex);
                if (distanceY < 0) {
                    setLoadViewMarginBottom(-distanceY);
                    updateLoadStatus(-distanceY);
                    mCurrentDrag = true;
                    return true;
                }

                break;

        }
        return super.onTouchEvent(e);
    }

    /**
     * 更新加载更多的状态
     *
     * @param marginBottom
     */
    private void updateLoadStatus(int marginBottom) {
        if (marginBottom <= 0) {
            mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        } else if (marginBottom < mLoadViewHeight) {
            mCurrentLoadStatus = LOAD_STATUS_PULL_DOWN_REFRESH;

        } else {
            mCurrentLoadStatus = LOAD_STATUS_LOOSEN_LOADING;
        }
        if (mLoadMoreViewCreator != null) {
            mLoadMoreViewCreator.onPull(marginBottom, mLoadViewHeight, mCurrentLoadStatus);
        }

    }

    /**
     * @param marginBottom
     */
    private void setLoadViewMarginBottom(int marginBottom) {
        MarginLayoutParams params = (MarginLayoutParams) mLoadView.getLayoutParams();
        if (marginBottom <0) {
            marginBottom = 0;
        }
        params.bottomMargin = marginBottom;
        mLoadView.setLayoutParams(params);

    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 判断是不是滚动到了最顶部，这个是从SwipeRefreshLayout里面copy过来的源代码
     */
    public boolean canScrollDown() {
        return ViewCompat.canScrollVertically(this, 1);
    }

    /**
     * 停止加载更多
     */
    public void onStopLoad() {
        mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        restoreLoadView();
        if (mLoadMoreViewCreator != null) {
            mLoadMoreViewCreator.onStopLoad();
        }
    }


}
