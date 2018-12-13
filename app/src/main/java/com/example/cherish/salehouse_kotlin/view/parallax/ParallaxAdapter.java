package com.example.cherish.salehouse_kotlin.view.parallax;

import android.graphics.drawable.PictureDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 视差 Adapter
 * Created by cherish
 */

public class ParallaxAdapter extends FragmentPagerAdapter {
    int[] layoudIds;
    List<ParallaxFragment> mFragments;

    public ParallaxAdapter(FragmentManager fm, int[] layoudIds) {
        super(fm);
        this.layoudIds = layoudIds;
        mFragments = new ArrayList<>();
        for (int i = 0; i < layoudIds.length; i++) {
            ParallaxFragment parallaxFragment = ParallaxFragment.newInstance(layoudIds[i]);
            mFragments.add(parallaxFragment);
        }

    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return layoudIds.length;
    }

    public List<ParallaxFragment> getChildView() {

        return mFragments;
    }
}
