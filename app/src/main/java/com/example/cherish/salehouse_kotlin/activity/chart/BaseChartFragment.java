package com.example.cherish.salehouse_kotlin.activity.chart;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.bean.CharBean;
import com.example.cherish.salehouse_kotlin.frame.BaseFragment;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.jobs.MoveViewJob;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A fragment with a Google +1 button.
 */
public abstract class BaseChartFragment extends BaseFragment {

    CombinedChart mCombinedChart;
    private float mMax;

    /**
     * 取消缓存策略
     *
     * @param isVisibleToUser
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {

        }
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        requestData();
    }

    @Override
    protected void findViews(View view) {
        mCombinedChart = view.findViewById(R.id.combinedChart);
    }

    @Override
    public int layoutResId() {
        return R.layout.base_chart;
    }


    @Override
    protected void initComplete() {
        //是否显示描述
        mCombinedChart.setDescription(null);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        //设置绘制区域的背景色
        mCombinedChart.setDrawGridBackground(false);
        //设置阴影
        mCombinedChart.setDrawBarShadow(false);
        //禁止绘制图表边框的线设置边界
        //  mCombinedChart.setDrawBorders(false);
        //设置横向,和竖向都不可伸缩
        mCombinedChart.setScaleYEnabled(false);
        mCombinedChart.setScaleXEnabled(false);
        //设置放大倍数,横向拉伸两倍
        mCombinedChart.zoom(2, 0, 0, 0);
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE});
        mCombinedChart.setNoDataText("暂无数据");

        setChartLegend(mCombinedChart.getLegend());
        //setting marker
        XYMarkerView mv = new XYMarkerView(getActivity());
        mv.setChartView(mCombinedChart); // For bounds control
        mCombinedChart.setMarker(mv); // Set the marker to the chart
    }


    /**
     * 绑定数据
     */
    public void bindData() {
        if(getActivity()==null){
            return;
        }
        setLeftYAxis(mCombinedChart.getAxisLeft());
        setRightYAxis(mCombinedChart.getAxisRight());
        setXAxis();
        CombinedData data = new CombinedData();
        data.setData(generateLineData());
        data.setData(generateBarData());
        mCombinedChart.setData(data);
        moveViewToX(mMax);
        mCombinedChart.setVisibleXRangeMaximum(6);
        XYMarkerView marker = (XYMarkerView) mCombinedChart.getMarker();
        marker.bindData(getLinData(), getBarData());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCombinedChart.animateY(1500);
            }
        });
    }

    /**
     * 设置图列
     *
     * @param legend
     */
    private void setChartLegend(Legend legend) {
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextColor(Color.parseColor("#999999"));
        legend.setTextSize(12);
        legend.setForm(Legend.LegendForm.CIRCLE);
    }

    /**
     * 设置x 轴
     */
    private void setXAxis() {
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(true);
        xAxis.setLabelCount(12, false);
        xAxis.setGranularity(1f);    //缩放的时候有用，比如放大的时候，我不想把横轴的月份再细分
        xAxis.setTextColor(Color.parseColor("#999999"));
        xAxis.setAxisLineColor(Color.parseColor("#eaeaea"));
        xAxis.setAvoidFirstLastClipping(true);
        final List<String> month = getMonth();
        int size = month.size();
        mMax = size;
        xAxis.setAxisMinimum(1f);
        xAxis.setAxisMaximum(mMax);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return month.get((int) value % month.size());
            }


        });
    }


    /**
     * 设置左边Y轴
     *
     * @return
     */
    private void setLeftYAxis(YAxis leftAxis) {
        //文字颜色
        leftAxis.setTextColor(Color.parseColor("#999999"));
        //是否使用 Y轴网格线条
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridLineWidth(0.5f);
        leftAxis.setGridColor(Color.parseColor("#eaeaea"));
        leftAxis.setLabelCount(5, true);
        //是否绘制轴线
        leftAxis.setDrawAxisLine(false);
        List<String> axis = CacheUtils.getInstance().getData();
        if (axis != null && axis.size() > 0) {
            leftAxis.setAxisMinimum(Float.parseFloat(axis.get(0)));
            leftAxis.setAxisMaximum(Float.parseFloat(axis.get(axis.size() - 1)));
        }

        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat format = new DecimalFormat("#0.0");
                return String.format(Locale.CHINA, "%s万/平", format.format(value));
            }
        });
    }

    /**
     * 设置右边Y轴
     *
     * @return
     */
    @NonNull
    private YAxis setRightYAxis(YAxis rightAxis) {
        List<String> axis = getRightAxis();
        if (axis == null) {
            rightAxis.setEnabled(false);
            return rightAxis;
        }
        //文字颜色
        rightAxis.setTextColor(Color.parseColor("#999999"));
        //是否使用 Y轴网格线条
        rightAxis.setDrawGridLines(false);
        //是否绘制轴线
        rightAxis.setDrawAxisLine(false);
        rightAxis.setLabelCount(5, true);
        rightAxis.setAxisMinimum(Float.parseFloat(axis.get(0)));
        rightAxis.setAxisMaximum(Float.parseFloat(axis.get(axis.size() - 1)));
        rightAxis.setEnabled(true);
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.format(Locale.CHINA, "%s套", String.valueOf((int) value));
            }
        });
        return rightAxis;
    }

    /**
     * 折线图
     *
     * @return
     */
    private LineData generateLineData() {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<>();
        List<CharBean> barData = getLinData();
        if (barData == null) {
            return null;
        }
        for (int i = 0; i < barData.size(); i++) {
            entries.add(new Entry(i + 0.5f, barData.get(i).y));
        }
        LineDataSet set = new LineDataSet(entries, "朝阳均价");
        set.setColor(Color.parseColor("#E2594A"));
        set.setLineWidth(2f);
        set.setCircleColor(Color.parseColor("#D85127"));
        set.setCircleRadius(4f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        //设置曲线值的圆点是实心还是空心
        set.setDrawCircleHole(false);
        //线模式为圆滑曲线（默认折线）
        // set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);
        set.setDrawHighlightIndicators(true);

        d.addDataSet(set);
        d.setHighlightEnabled(true);

        return d;
    }

    /**
     * 柱状图
     *
     * @return
     */
    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        List<CharBean> barData = getBarData();
        if (barData == null) {
            return null;
        }
        for (int i = 0; i < barData.size(); i++) {
            entries1.add(new BarEntry(i + 0.5f, barData.get(i).y));
        }
        BarDataSet set = new BarDataSet(entries1, "成交量");
        set.setColor(Color.parseColor("#999999"));
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setColor(Color.parseColor("#EBEBEB"));
        set.setDrawValues(false);
        BarData d = new BarData(set);
        //设置间距和内容平均分配
        d.setBarWidth(0.5f);
        d.setHighlightEnabled(false);

        return d;
    }

    /**
     * 移动位置
     *
     * @param xValue
     */
    void moveViewToX(float xValue) {
        MoveViewJob moveViewJob = new MoveViewJob(mCombinedChart.getViewPortHandler(), xValue,
                0f, mCombinedChart.getTransformer(YAxis.AxisDependency.LEFT), mCombinedChart);
        mCombinedChart.addViewportJob(moveViewJob);
    }

    /**
     * 请求接口
     */
    protected abstract void requestData();

    /**
     * 折线图数据
     *
     * @return
     */
    protected abstract List<CharBean> getLinData();

    /**
     * 柱状图数据
     *
     * @return
     */
    protected abstract List<CharBean> getBarData();


    /**
     * 万/平数
     *
     * @return
     */
    protected abstract List<String> getLeftAxis();

    /**
     * 套数
     *
     * @return
     */
    public abstract List<String> getRightAxis();

    /**
     * 月份
     *
     * @return
     */
    public abstract List<String> getMonth();

    /**
     * 释放资源
     */
    public void resetChart() {
        if(mCombinedChart!=null){
            if (mCombinedChart.getLineData() != null)
                mCombinedChart.getLineData().clearValues();
            if (mCombinedChart.getBarData() != null)
                mCombinedChart.getBarData().clearValues();
            mCombinedChart.moveViewToX(0);
        }

    }
    @Override
    public void onDestroy() {
        resetChart();
        super.onDestroy();
    }
}
