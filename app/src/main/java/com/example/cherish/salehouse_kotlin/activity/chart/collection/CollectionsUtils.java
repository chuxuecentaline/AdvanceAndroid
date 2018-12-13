package com.example.cherish.salehouse_kotlin.activity.chart.collection;

import com.example.cherish.salehouse_kotlin.bean.ChartJson;
import com.example.cherish.salehouse_kotlin.bean.ChartJson.ResultBean.*;

import java.util.Comparator;

/**
 * 按时间进行排序
 * Created by cherish
 */

public class CollectionsUtils<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        int type = 0;
        if (o1 instanceof GScopeDealAvgPriceHistoryBean && o2 instanceof
                GScopeDealAvgPriceHistoryBean) {

            if (((GScopeDealAvgPriceHistoryBean) o1).getDataYear() < (
                    (GScopeDealAvgPriceHistoryBean) o2).getDataYear()) {
                type = -1;
            } else if (((GScopeDealAvgPriceHistoryBean) o1).getDataMonth() < (
                    (GScopeDealAvgPriceHistoryBean) o2).getDataMonth()) {
                type = -1;
            } else {
                type = 1;
            }
        } else if (o1 instanceof GScopeDealNumHistoryBean && o2 instanceof
                GScopeDealNumHistoryBean) {
            if (((GScopeDealNumHistoryBean) o1).getDataYear() < ((GScopeDealNumHistoryBean) o2)
                    .getDataYear()) {
                type = -1;
            } else if (((GScopeDealNumHistoryBean) o1).getDataMonth() < (
                    (GScopeDealNumHistoryBean) o2).getDataMonth()) {
                type = -1;
            } else {
                type = 1;
            }

        }

        return type;
    }
}
