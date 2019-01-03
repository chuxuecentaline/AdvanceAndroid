package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.CommonRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.RadarScanAdapter;
import com.example.cherish.salehouse_kotlin.utils.DensityUtils;
import com.example.cherish.salehouse_kotlin.utils.StatusBarUtils;
import com.example.cherish.salehouse_kotlin.view.radar.ItemDecoration;
import com.example.cherish.salehouse_kotlin.view.radar.RadarScanView;
import com.example.cherish.salehouse_kotlin.view.radar.RecyclerViewGallery;

import java.util.ArrayList;
import java.util.List;

public class RadarScanActivity extends BaseActivity {

    @BindView(R.id.radarView)
    RadarScanView radarView;
    @BindView(R.id.recyclerView)
    RecyclerViewGallery recyclerView;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    List<String> data=new ArrayList<>();
    private RadarScanAdapter mAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_radar_scann;
    }

    @Override
    protected void findViews() {
     //   new NormalNavigationBar.Build(this).setTitle("微信QQ附近好友雷达扫描").setRightMenu(R.menu.navigation_tab).create();
        new StatusBarUtils().setStatusTranslate(this);
        BindViewUtils.inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        final int divide = new DensityUtils().dip2px(this, 10);
        data.add("-1");
        for (int i = 0; i < 10; i++) {
            data.add(i+"");
        }
        data.add("-1");
        mAdapter = new RadarScanAdapter(this, data);
        recyclerView.setAdapter(mAdapter);

        constraintLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = constraintLayout.getMeasuredWidth();
                int itemWidth =(width  - 4 * divide) / 3;
                mAdapter.setItemWidth(itemWidth);
                recyclerView.setParams(itemWidth,divide);
            }
        });


    }
}
