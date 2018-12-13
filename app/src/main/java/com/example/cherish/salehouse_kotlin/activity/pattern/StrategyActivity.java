package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

public class StrategyActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_strategy;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("策略设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
