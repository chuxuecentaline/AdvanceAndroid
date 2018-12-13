package com.example.cherish.salehouse_kotlin.view.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 自定义简易Behavior 实现跟随recyclerView 联动动画
 * Created by cherish
 */

public class TranslationBehavior extends FloatingActionButton.Behavior {
    /**
     * 当前是否显示
     */
    private boolean mIsShow = false;
    private LinearLayout mBottomTabView;

    /**
     * @param context
     * @param attrs
     */
    public TranslationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 摆放子view 的时候调用
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int
            layoutDirection) {
        mBottomTabView=parent.findViewById(R.id.bottom_tab_layout);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * 当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     * coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     * 的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     * 这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target,
                                       int nestedScrollAxes, int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll
                (coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, type);
    }

    /**
     * 进行嵌套滚动时被调用
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed        target 已经消费的x方向的距离
     * @param dyConsumed        target 已经消费的y方向的距离
     * @param dxUnconsumed      x 方向剩下的滚动距离
     * @param dyUnconsumed      y 方向剩下的滚动距离
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int
            dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);
        System.out.println("-----------dyConsumed:" + dyConsumed);
        if (dyConsumed < 0) {//向上滚动 显示
            if (mIsShow) {
                child.animate().translationY(0).setDuration(400).start();
                mBottomTabView.animate().translationY(0).setDuration(400).start();
                mIsShow = !mIsShow;
            }

        }
        if (dyConsumed > 0) {//向下滚动 隐藏
            if (!mIsShow) {
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child
                        .getLayoutParams();
                child.animate().translationY(params.bottomMargin + child.getMeasuredHeight())
                        .setDuration(200).start();
                mBottomTabView.animate().translationY(target.getMeasuredHeight()).setDuration(400).start();
                mIsShow=true;
            }
        }

    }

}
