package com.example.cherish.salehouse_kotlin.activity.chart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cherish.salehouse_kotlin.activity.chart.fragment.AllFragment;
import com.example.cherish.salehouse_kotlin.activity.chart.fragment.OneRoomFragment;
import com.example.cherish.salehouse_kotlin.activity.chart.fragment.ThreeRoomFragment;
import com.example.cherish.salehouse_kotlin.activity.chart.fragment.TwoRoomFragment;

/**
 * 图表
 * Created by cherish
 */

public class ChartPagerAdapter extends FragmentPagerAdapter {


    public ChartPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllFragment();
            case 1:
                return new OneRoomFragment();
            case 2:
                return new TwoRoomFragment();
            default:
                return new ThreeRoomFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "全部";
            case 1:
                return "一室";
            case 2:
                return "二室";
            default:
                return "三室";
        }
    }

}
