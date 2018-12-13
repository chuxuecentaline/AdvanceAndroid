package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.ViewGroupTouch;


/**
 * View  TouchEvent 事件 源码分析
 */
public class ViewTouchActivity extends BaseActivity {

    private View viewTouch;

    @Override
    public int getContentViewId() {
        return R.layout.activity_view_touch;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("View  TouchEvent 事件 源码分析").setRightMenu(R.menu
                .navigation_tab).create();
         viewTouch = findViewById(R.id.viewTouch);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        final ViewGroupTouch viewGroupTouch = findViewById(R.id.viewGroupTouch);
        viewGroupTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("viewGroup --->onTouch" + event.getAction());
                return false;
            }
        });
       /* viewGroupTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("viewGroup --->onClick");
            }
        });*/

        viewTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("view --->onTouch" + event.getAction());
                return false;
            }
        });
        viewTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("view --->onClick");
            }
        });
    }
    /**
     * 分析 view 源码流程 dispatchTouchEvent()===>onTouch()--->onTouchEvent()===>performClick===>onClick()
     *
     * 1.默认情况下 ：down   dispatchTouchEvent()->onTouch()->onTouchEvent()
     *                move  dispatchTouchEvent()->onTouch-()>onTouchEvent()
     *                up    dispatchTouchEvent()->onTouch()->onTouchEvent()->onClick()
     *
     * 2.onTouch 返回true 已消费事件
     *                down  dispatchTouchEvent()->onTouch()
     *                move dispatchTouchEvent()->onTouch()
     *                up   dispatchTouchEvent()->onTouch()
     *
     * 3.onTouch false  onTouchEvent true
     *                down  dispatchTouchEvent()->onTouch()->onTouchEvent()
     *                move  dispatchTouchEvent()->onTouch(()->onTouchEvent()
     *                up     dispatchTouchEvent()->onTouch(()->onTouchEvent()
     * 4.如果没有 onClick 事件 onTouch false  onTouchEvent false 只会执行down 事件
     *               down  dispatchTouchEvent()->onTouch()->onTouchEvent()
     */


    /**
     * 分析 viewGroup 源码流程 dispatchTouchEvent()--->onInterceptTouchEvent===>onTouch()
     * --->onTouchEvent()===>performClick===>onClick()
     *  触摸view 事件
     * 1.默认情况下 ：子view 无onclick
     *           view     down   ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->view
     *                  dispatchTouchEvent()->view onTouch()->view onTouchEvent()->viewGroup onTouch()->ViewGroup onTouchEvent()
     *           viewGroup
     *                     dispatchTouchEvent()->ViewGroup onInterceptTouchEvent()
     *                  ->ViewGroup onTouch()->ViewGroup onTouchEvent()
     *
     *
     *
     * 2 viewGroup.onInterceptTouchEvent 返回true viewGroup 拦截事件
     *                down   ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->
     *                ViewGroup onTouch-> ViewGroup onTouchEvent
     *
     * 3.viewGroup   onTouch true
     *               down   ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->
     *                ViewGroup onTouch
     *                move   ViewGroup dispatchTouchEvent->ViewGroup onTouch
     *                up   ViewGroup dispatchTouchEvent->ViewGroup onTouch
     *
     *  4.viewGroup   onTouchEvent true 或 子view  没有消费事件
     *               down   ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->
     *                ViewGroup onTouch->ViewGroup onTouchEvent
     *                move   ViewGroup dispatchTouchEvent->ViewGroup onTouch->ViewGroup onTouchEvent
     *                up   ViewGroup dispatchTouchEvent->ViewGroup onTouch->ViewGroup onTouchEvent
     *
     * 5.view onTouch true 子view /or   onTouchEvent true
     *                down  ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->view
     *                 dispatchTouchEvent()->view onTouch() or ->view onTouchEvent()
     *                move  ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->view
     *                 dispatchTouchEvent()->view onTouch()or ->view onTouchEvent()
     *                up   ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->view
     *                 dispatchTouchEvent()->view onTouch()or ->view onTouchEvent()
     *                 Group:
     *                 down  ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->viewGroup onTouch()
     *                  ->ViewGroup onTouchEvent()
     *                 move  ViewGroup dispatchTouchEvent-> ViewGroup
     *                  onTouch()->ViewGroup onTouchEvent()
     *                 up   ViewGroup dispatchTouchEvent-> ViewGroup
     *                 onTouch()->ViewGroup onTouchEvent()->viewGroup onClick
     *
     *
     * 6.如果没有view  onClick 事件 onTouch false  onTouchEvent false 只会执行down 事件
     *               down  ViewGroup dispatchTouchEvent-> ViewGroup onInterceptTouchEvent->view
     *                 dispatchTouchEvent()->view onTouch()->view onTouchEvent-> ViewGroup onTouch()->ViewGroup onTouchEvent()
     *                 move  ViewGroup dispatchTouchEvent->ViewGroup onTouch()->ViewGroup onTouchEvent()
     *                 up  ViewGroup dispatchTouchEvent-> ViewGroup onTouch()->ViewGroup onTouchEvent()->viewGroup onClick
     *
     *
     * 8.如果viewGroup 有onClick  点击事件 其他均为默认值
     *    down  ViewGroup dispatchTouchEvent()->ViewGroup onInterceptTouchEvent()
     *                  ->ViewGroup onTouch()->ViewGroup onTouchEvent()
     *
     *
     */
}
