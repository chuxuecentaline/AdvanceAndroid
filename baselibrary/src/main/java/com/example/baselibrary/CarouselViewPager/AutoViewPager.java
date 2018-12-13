package com.example.baselibrary.CarouselViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.reflect.Field;


/**
 * 自动轮播图
 * Created by cherish
 * 在RecyclerView中使用ViewPager时，会出现两个诡异的bug：
 * <p>
 * 1.RecyclerView滚动上去，直至ViewPager看不见，再滚动下来，ViewPager下一次切换没有动画
 * <p>
 * 2.初次加载显示页面的时候，如果已经设置了viewpager到recyclerview中，从服务器拿到数据后进行刷新，notify，也会导致
 * 同样的问题，viewpager第一次切换没有动画
 * <p>
 * 原因:
 * <p>
 * ViewPager里有一个私有变量mFirstLayout，它是表示是不是第一次显示布局，如果是true，则使用无动画的方式显示当前item，
 * 如果是false，则使用动画方式显示当前item。
 */

public class AutoViewPager extends ViewPager {
    // 2.实现自动轮播 - 发送消息的msgWhat
    private final int SCROLL_MSG = 0x0011;

    // 2.实现自动轮播 - 页面切换间隔时间
    private int mCutDownTime = 1500;
    private BannerScroller mScroller;
    private Activity mActivity;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setCurrentItem(getCurrentItem() + 1);
            startScroll();

        }
    };

    public AutoViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        try {
            // 3.改变ViewPager切换的速率
            // 3.1 duration 持续的时间  局部变量
            // 3.2.改变 mScroller private 通过反射设置
            Field field = ViewPager.class.getDeclaredField("mScroller");
            // 设置参数  第一个object当前属性在哪个类  第二个参数代表要设置的值
            mScroller = new BannerScroller(context);
            // 设置为强制改变private
            field.setAccessible(true);
            field.set(this, mScroller);
            mActivity = (Activity) context;

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void setAdapter(@Nullable ViewPagerViewAdapter adapter) {

        setAdapter(new AutoPagerAdapter(adapter));
        // 管理Activity的生命周期
        mActivity.getApplication().registerActivityLifecycleCallbacks(callbacks);
        startScroll();


    }

    /**
     * 开启广告轮播图
     */
    public synchronized void startScroll() {
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);
    }

    /**
     * 2.销毁Handler停止发送  解决内存泄漏
     */
    @Override
    protected void onDetachedFromWindow() {
        onReset();
        mActivity.getApplication().registerActivityLifecycleCallbacks(callbacks);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        if (mHandler== null) {
            mHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    setCurrentItem(getCurrentItem() + 1);
                    startScroll();
                }
            };
        }
        startScroll();
        super.onAttachedToWindow();
       try {
            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
            mFirstLayout.setAccessible(true);
            mFirstLayout.set(this, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    /**
     * 释放资源
     */
    private void onReset() {
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;
    }

    ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityResumed(Activity activity) {
            super.onActivityResumed(activity);
            if (activity == getContext()) {//判断是否是当前的Activity
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);
            }

        }

        @Override
        public void onActivityPaused(Activity activity) {
            super.onActivityPaused(activity);
            if (activity == getContext()) {//判断是否是当前的Activity
                mHandler.removeMessages(SCROLL_MSG);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHandler == null) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, 0);
                break;

        }
        return true;
    }
}
