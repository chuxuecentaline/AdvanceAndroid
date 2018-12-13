package com.example.baselibrary.CarouselViewPager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 改变切换速率
 * Created by cherish
 */

public class BannerScroller extends Scroller {
private int mScrollDuration=850;

    public BannerScroller(Context context) {
        super(context);
    }

    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    public int getScrollDuration() {
        return mScrollDuration;
    }

    public void setScrollDuration(int scrollDuration) {
        mScrollDuration = scrollDuration;
    }
}
