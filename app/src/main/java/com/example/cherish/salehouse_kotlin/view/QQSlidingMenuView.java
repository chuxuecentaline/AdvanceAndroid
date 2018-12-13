package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 仿QQ 侧滑菜单
 * Created by cherish
 */

public class QQSlidingMenuView extends HorizontalScrollView {
    private int rightMargin;
    private View vv_menu, vv_content;
    private LinearLayout.LayoutParams mParams;
    private int width_menu;
    private boolean mMenuIsOpen;//标识菜单打开关闭的状态
    private int mScreen_width;
    private GestureDetector mGestureDetector;
    private boolean mIsIntercept;//是否拦截

    public QQSlidingMenuView(Context context) {
        this(context, null);
    }

    public QQSlidingMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQSlidingMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        rightMargin = typedArray.getDimensionPixelSize(R.styleable.SlidingMenu_rightMargin,
                rightMargin);
        typedArray.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreen_width = displayMetrics.widthPixels;
        mGestureDetector = new GestureDetector(context, listener);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup view = (ViewGroup) getChildAt(0);
        vv_menu = view.getChildAt(0);
        vv_content = view.getChildAt(1);
        //获取屏幕的宽度

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.width = mScreen_width;
        vv_content.setLayoutParams(params);
        mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        width_menu = mScreen_width - rightMargin;
        mParams.width = width_menu;
        vv_menu.setLayoutParams(mParams);

    }

    /**
     * 事件拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIsIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mMenuIsOpen && ev.getX() > width_menu) {//展开
                    smoothScrollTo(width_menu, 0);
                    mIsIntercept = true;
                    return true;

                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mGestureDetector.onTouchEvent(ev) || mIsIntercept) {
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:

                if (width_menu / 2 > getScrollX()) {
                    mMenuIsOpen = true;
                } else {
                    mMenuIsOpen = false;
                }
                toggleMenu();

                return true;
        }
        return super.onTouchEvent(ev);
    }

    private void toggleMenu() {
        if (mMenuIsOpen) {
            smoothScrollTo(0, 0);
        } else {
            smoothScrollTo(width_menu, 0);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(width_menu, 0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float step = (float) l / width_menu;
        //展开 left 由大到小 //折叠 left 由小到大
        float scaleX = (float) (step * 0.3 + 0.7);
        vv_content.setAlpha(scaleX);

        //alpha 0.7-1.0
        vv_menu.setTranslationX((float) (l * 0.7));


    }

    GestureDetector.OnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(velocityX) - Math.abs(velocityY) > 20) {
                if (mMenuIsOpen) {
                    if (velocityX < 0) {//向左快速滑动
                        mMenuIsOpen = false;
                    }

                } else {
                    if (velocityX > 0) {//向右快速滑动
                        mMenuIsOpen = true;
                    }

                }
                toggleMenu();
                return true;
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

}
