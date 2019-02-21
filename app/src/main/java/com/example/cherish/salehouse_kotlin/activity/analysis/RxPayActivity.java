package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

public class RxPayActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_rx_pay;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("源码分析").setRightMenu(R.menu.navigation_tab)
                .create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {


    }
}
