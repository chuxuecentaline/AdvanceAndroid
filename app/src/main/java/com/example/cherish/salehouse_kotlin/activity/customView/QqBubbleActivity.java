package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

public class QqBubbleActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_qq_bubble;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("QQ消息汽包拖拽").setRightMenu(R.menu
                .navigation_tab).create();

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
