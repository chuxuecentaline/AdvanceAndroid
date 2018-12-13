package com.example.cherish.salehouse_kotlin.view;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.chart.BaseChartFactory;
import com.example.cherish.salehouse_kotlin.activity.chart.ChartPagerAdapter;
import com.example.cherish.salehouse_kotlin.activity.chart.MPAndroidChartActivity;
import com.example.cherish.salehouse_kotlin.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 成交价格走势图
 * Created by cherish
 */

public class TransactionPriceTrend extends FrameLayout {

    private View mView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private ChartPagerAdapter mAdapter;

    public TransactionPriceTrend(Context context) {
        this(context, null);
    }

    public TransactionPriceTrend(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransactionPriceTrend(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mView = View.inflate(context, R.layout.layout_chart, this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        BindViewUtils.inject(mView);
        ChartPagerAdapter viewpager = new ChartPagerAdapter(MPAndroidChartActivity.mActivity
                .getSupportFragmentManager());
        mViewPager.setAdapter(viewpager);
        //TabLayout加载viewpager
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
     //   mViewPager.setOffscreenPageLimit(1);

    }
}
