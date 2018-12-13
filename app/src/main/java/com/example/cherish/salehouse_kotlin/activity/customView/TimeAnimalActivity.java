package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

/**
 * 时钟动画
 */
public class TimeAnimalActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_time_animal;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("时钟动画").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
