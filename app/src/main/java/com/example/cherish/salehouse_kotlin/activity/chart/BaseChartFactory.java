package com.example.cherish.salehouse_kotlin.activity.chart;

/**
 * Created by cherish
 */

public class BaseChartFactory extends ChartFactory {

    @Override
    public <T extends ChartFragment> T createFragment(Class<T> clz) {
        ChartFragment mFragment = null;
        try {
            mFragment = (ChartFragment) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) mFragment;
    }
}
