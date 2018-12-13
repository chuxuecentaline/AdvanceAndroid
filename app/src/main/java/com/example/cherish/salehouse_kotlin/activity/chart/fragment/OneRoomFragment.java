package com.example.cherish.salehouse_kotlin.activity.chart.fragment;

import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpUtils;
import com.example.cherish.salehouse_kotlin.activity.chart.BaseChartFragment;
import com.example.cherish.salehouse_kotlin.activity.chart.collection.CollectionsUtils;
import com.example.cherish.salehouse_kotlin.bean.CharBean;
import com.example.cherish.salehouse_kotlin.bean.ChartJson;
import com.example.cherish.salehouse_kotlin.utils.HttpApi;
import com.google.gson.Gson;
import com.example.cherish.salehouse_kotlin.bean.ChartJson.ResultBean.*;
import com.example.cherish.salehouse_kotlin.bean.ChartJson.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 一室
 * Created by cherish
 */

public class OneRoomFragment extends BaseChartFragment {
    List<CharBean> mLinData = new LinkedList<>();
    List<CharBean> mBarData = new ArrayList<>();
    List<String> mLeftAxis = new ArrayList<>();
    List<String> mRightAxis = new ArrayList<>();
    List<String> mMonth = new ArrayList<>();

    @Override
    protected void requestData() {
        HttpUtils.with(getActivity()).url(HttpApi.one).get().execute(new EngineCallBack() {

            private String mYear;

            @Override
            public void onPreExecute() {
                //获取系统时间的年份
                Calendar calendar = Calendar.getInstance();  //获取当前时间，作为图标的名字
                mYear = calendar.get(Calendar.YEAR) + "";
            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ChartJson chartJson = gson.fromJson(result, ChartJson.class);
                ResultBean chartJsonResult = chartJson.getResult();
                //成交均价走势
                List<GScopeDealAvgPriceHistoryBean> gScopeDealAvgPriceHistory = chartJsonResult
                        .getGScopeDealAvgPriceHistory();
                //按照时间进行排序
                Collections.sort(gScopeDealAvgPriceHistory, new CollectionsUtils<GScopeDealAvgPriceHistoryBean>());
                for (int i = 0; i < gScopeDealAvgPriceHistory.size(); i++) {
                    GScopeDealAvgPriceHistoryBean bean = gScopeDealAvgPriceHistory.get(i);
                    double avgPrice = bean.getDealAvgPrice() / 10000;
                    mLeftAxis.add(String.valueOf(avgPrice));
                    System.out.println("year:" + bean.getDataYear() + "month" + bean.getDataMonth
                            () + "avg" + avgPrice);
                    CharBean charBean = new CharBean();

                    if (mYear.equals(String.valueOf(bean.getDataYear()))) {
                        mMonth.add(bean.getDataMonth() + "月");
                        charBean.x = bean.getDataMonth();
                    } else {
                        mMonth.add(bean.getDataMonth() + "月" + "-" + String.valueOf(bean
                                .getDataYear()).substring(2, 4));
                        StringBuilder sb = new StringBuilder();
                        sb.append(bean.getDataMonth());
                        sb.append(bean.getDataYear()).substring(2, 4);
                        charBean.x = Integer.parseInt(sb.toString());
                    }

                    charBean.y = (float) avgPrice;
                    mLinData.add(charBean);

                }

                //均价走势
                Collections.sort(mLeftAxis);
                double minAvg = Double.parseDouble(mLeftAxis.get(0));
                if (minAvg - 0.5 > 0) {
                    minAvg = minAvg - 0.5;
                } else {
                    minAvg = 0;
                }

                double maxAvg = Double.parseDouble(mLeftAxis.get(mLeftAxis.size() - 1)) + 0.5;
                //增长幅度
                double averageDeal = (maxAvg - minAvg) / 4;
                mLeftAxis.clear();
                DecimalFormat format = new DecimalFormat("#0.0");
                for (int i = 0; i < 5; i++) {
                    double value = minAvg + i * averageDeal;
                    mLeftAxis.add(format.format(value));
                }


                //成交量 套数
                List<GScopeDealNumHistoryBean> gScopeDealNumHistory = chartJsonResult
                        .getGScopeDealNumHistory();
                List<Integer> data = new ArrayList<>();
                for (GScopeDealNumHistoryBean gScopeDealNumHistoryBean : gScopeDealNumHistory) {
                    data.add(gScopeDealNumHistoryBean.getSaleDealNumber());

                    CharBean charBean = new CharBean();
                    if (mYear.equals(String.valueOf(gScopeDealNumHistoryBean.getDataYear()))) {

                        charBean.x = gScopeDealNumHistoryBean.getDataMonth();
                    } else {

                        StringBuilder sb = new StringBuilder();
                        sb.append(gScopeDealNumHistoryBean.getDataMonth());
                        sb.append(gScopeDealNumHistoryBean.getDataYear()).substring(2, 4);
                        charBean.x = Integer.parseInt(sb.toString());
                    }

                    charBean.y = (float) gScopeDealNumHistoryBean.getSaleDealNumber();
                    mBarData.add(charBean);

                }

                int min = Collections.min(data);
                if (min - 40 > 0) {
                    min = min - 40;
                } else {
                    min = 0;
                }
                int max = Collections.max(data) + 40;
                //增长幅度
                int average = (max - min) / 4;
                for (int i = 0; i < 5; i++) {
                    mRightAxis.add(String.valueOf(min + i * average));
                }

                bindData();


            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }


    @Override
    protected List<CharBean> getLinData() {
        return mLinData;
    }

    @Override
    protected List<CharBean> getBarData() {
        return null;
    }

    @Override
    protected List<String> getLeftAxis() {
        return null;
    }

    @Override
    public List<String> getRightAxis() {
        return null;
    }

    @Override
    public List<String> getMonth() {
        return mMonth;
    }
}
