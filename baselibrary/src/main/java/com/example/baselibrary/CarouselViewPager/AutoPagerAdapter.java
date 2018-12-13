package com.example.baselibrary.CarouselViewPager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器
 * Created by cherish
 */

public class AutoPagerAdapter extends PagerAdapter {
    //pagerAdapter 默认缓存3个页面，页面的复用
    ViewPagerViewAdapter mAdapter;
    List<View> mConvertViews;

    public AutoPagerAdapter(ViewPagerViewAdapter adapter) {
        mAdapter = adapter;
        mConvertViews = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int index = position % mAdapter.getCount();
        View view = mAdapter.getView(index, getContainer());
        container.addView(view);
        return view;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView((View) object);
        mConvertViews.add((View) object);


    }

    /**
     * 页面的复用
     * @return
     */
    private View getContainer() {
        for (int i = 0; i < mConvertViews.size(); i++) {
            if(mConvertViews.get(i).getParent()==null){
                return mConvertViews.get(i);
            }
        }
        return null;
    }


}
