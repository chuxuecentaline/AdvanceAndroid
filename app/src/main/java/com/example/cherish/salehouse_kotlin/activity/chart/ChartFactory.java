package com.example.cherish.salehouse_kotlin.activity.chart;

/**
 * 抽象工厂方法
 * Created by cherish
 */

public abstract class ChartFactory {
    public abstract <T extends ChartFragment> T createFragment(Class<T> clz);
}
