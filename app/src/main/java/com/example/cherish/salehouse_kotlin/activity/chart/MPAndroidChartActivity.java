package com.example.cherish.salehouse_kotlin.activity.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.ChartAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.MultiTypeSupport;
import com.example.cherish.salehouse_kotlin.utils.StatusBarUtils;
import com.example.cherish.salehouse_kotlin.view.TransactionPriceTrend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MPAndroidChartActivity extends BaseActivity {

    public static FragmentActivity mActivity;
    @BindView(R.id.toolBar)
    Toolbar mTooBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private List<String> data = new ArrayList<>();
    private LinearLayoutManager mLayout;


    @Override
    public int getContentViewId() {
        return R.layout.activity_mpandroid_chart;
    }

    @Override
    protected void findViews() {
        new StatusBarUtils().setStatusBarColors(this, Color.parseColor("#FF7B4C"));
        BindViewUtils.inject(this);
        setSupportActionBar(mTooBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mActivity=this;
        constraintLayout.setAlpha(1);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                System.out.println("---->" + totalScrollRange);
                System.out.println("---->" + verticalOffset);
                constraintLayout.setAlpha((float) (1+(verticalOffset /(totalScrollRange*0.8))));

            }
        });
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            data.add(""+i);
        }
        mLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayout);
        ChartAdapter mAdapter = new ChartAdapter(this, data, new MultiTypeSupport() {
            @Override
            public int getLayoutId(Object item, int position) {
                int layoutId;
                if(position==0){
                    layoutId=R.layout.item_chart;
                }else if(position==1){
                    layoutId=R.layout.item_title;
                }else if(position>1&&position<5){
                    layoutId=R.layout.item_area_average;
                }else if(position==5){
                    layoutId=R.layout.item_title_recently;
                }else if(position>5&&position<data.size()-1){
                    layoutId=R.layout.item_deal;
                }else{
                    layoutId=R.layout.item_bottom;
                }
                return layoutId;
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }
}
