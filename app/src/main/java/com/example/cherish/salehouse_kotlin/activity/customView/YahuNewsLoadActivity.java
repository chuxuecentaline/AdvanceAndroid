package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.YahuNewsLoadView;

public class YahuNewsLoadActivity extends BaseActivity {



    @Override
    public int getContentViewId() {
        return R.layout.activity_yahu_news_load;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("雅虎新闻摘要加载").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
