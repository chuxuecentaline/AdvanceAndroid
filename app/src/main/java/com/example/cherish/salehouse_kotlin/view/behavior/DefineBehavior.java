package com.example.cherish.salehouse_kotlin.view.behavior;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class DefineBehavior extends CoordinatorLayout.Behavior {
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                               @NonNull View target, int dxConsumed, int dyConsumed, int
                                           dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);
    }
}
