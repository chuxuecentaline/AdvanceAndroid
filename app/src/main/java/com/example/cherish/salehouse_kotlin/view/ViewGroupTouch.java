package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 分析view的事件
 * Created by cherish
 */

/**
 *
 */

public class ViewGroupTouch extends LinearLayout {
    public ViewGroupTouch(Context context) {
        super(context);
    }

    public ViewGroupTouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * onTouchEvent 事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("ViewGroup --->onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }

    /**
     * 事件的分发
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("ViewGroup --->dispatchTouchEvent"+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    /**
     * 事件拦截
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("ViewGroup --->onInterceptTouchEvent"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }
}
