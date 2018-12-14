package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.StatusBarUtils;
import com.example.cherish.salehouse_kotlin.view.parallax.ParallaxViewPager;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.parallax_vp)
    ParallaxViewPager parallax_vp;

    @Override
    public int getContentViewId() {
        return R.layout.custom_tutorial_layout_example;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new StatusBarUtils().setStatusTranslate(this);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        parallax_vp.setLayout(getSupportFragmentManager(), new int[]{R.layout.include_first_page,
                R.layout.fragment_page_second, R.layout.fragment_page_third, R.layout
                .fragment_page_first});


    }
}
