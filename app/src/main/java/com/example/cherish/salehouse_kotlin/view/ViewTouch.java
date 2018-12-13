package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 分析view的事件
 * Created by cherish
 */

/**
 *
 */

public class ViewTouch extends View {
    public ViewTouch(Context context) {
        this(context,null);
    }

    public ViewTouch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * onTouchEvent 事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("view --->onTouchEvent"+event.getAction());
        return false;
    }

    /**
     * 事件的分发
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("view --->dispatchTouchEvent"+event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
