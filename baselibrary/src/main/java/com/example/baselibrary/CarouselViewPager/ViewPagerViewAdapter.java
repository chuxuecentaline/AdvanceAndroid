package com.example.baselibrary.CarouselViewPager;

import android.view.View;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public interface ViewPagerViewAdapter {
    /**
     * 条目
     * @return
     */
    public int getCount();

    /**
     * 布局
     * @param position
     * @return
     */
    public View getView(int position,View container);

    /**
     * 宽度/高度
     * @return
     */
    public float  percentage();

}
