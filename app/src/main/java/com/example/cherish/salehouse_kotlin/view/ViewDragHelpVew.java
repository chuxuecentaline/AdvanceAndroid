package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ListViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * ViewDragHelpVew垂直拖拽的动画
 * Created by cherish
 */

public class ViewDragHelpVew extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mViewMenu, mListView;
    private int mMenuHeight;
    private boolean isIntercept;
    private float mPreY;

    public ViewDragHelpVew(@NonNull Context context) {
        this(context, null);
    }

    public ViewDragHelpVew(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragHelpVew(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, callback);


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count != 2) {
            throw new IllegalThreadStateException("只能包含两个子view");
        }
        mViewMenu = getChildAt(0);
        mListView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mMenuHeight = mViewMenu.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       if(isIntercept){
           return true;
       }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPreY = ev.getY();
                mViewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY =  ev.getY()-mPreY;

                if(currentY>0&&!canChildScrollUp()){
                    return  true;
                }
                break;


        }
      return  super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

            mViewDragHelper.processTouchEvent(event);
            return true;

    }



    /**
     * @return Whether it is possible for the child view of this layout to
     *         scroll up. Override this if the child view is a custom view.
     *         判断是否滚动到了顶部
     */
    public boolean canChildScrollUp() {

        if (mListView instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) mListView, -1);
        }
        return mListView.canScrollVertically(-1);
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //是否允许拖拽
            return child == mListView;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            /**
             * 左右拖拽
             */
            return super.clampViewPositionHorizontal(child, left, dx);
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            /**
             * 上下拖拽
             */
            if (top <= 0) {
                top = 0;
            } else if (top > mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            /**
             * 停止滚动回弹
             */
            if (mListView.getTop() < mMenuHeight / 2) {
                //关闭
                mViewDragHelper.settleCapturedViewAt(0, 0);
                isIntercept=false;
            } else {
                //打开
                mViewDragHelper.settleCapturedViewAt(0, mMenuHeight);
                isIntercept=true;
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
