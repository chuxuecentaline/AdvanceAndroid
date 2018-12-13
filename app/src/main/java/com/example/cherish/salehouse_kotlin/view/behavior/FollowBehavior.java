package com.example.cherish.salehouse_kotlin.view.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class FollowBehavior extends CoordinatorLayout.Behavior<View> {

    private int mChildMeasuredHeight;

    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            View child, @NonNull View directTargetChild, @NonNull View target, int
            nestedScrollAxes, int type) {
        if (mChildMeasuredHeight == 0) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            mChildMeasuredHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
        }
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                               @NonNull View target, int dxConsumed, int dyConsumed, int
                                           dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0) {

            child.animate().translationY(mChildMeasuredHeight).setDuration(100).start();
        } else   if (dyConsumed < 0){
            child.animate().translationY(0).setDuration(100).start();
        }


    }

}
