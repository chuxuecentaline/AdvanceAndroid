package com.example.cherish.salehouse_kotlin.activity.customView;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;


import com.example.cherish.salehouse_kotlin.R;
import com.example.baselibrary.base.BaseActivity;

public class ElementAnimalDetailActivity extends BaseActivity {


    private ImageView mIv_icon;
    private AppCompatTextView mTv_content;
    private CollapsingToolbarLayout toolbarLayout;
    private NestedScrollView nestedScrollView;
    private AppBarLayout app_barLayout;
    private Toolbar tool_bar;


    @Override
    public int getContentViewId() {
        return R.layout.activity_element_animal_detail;
    }

    @Override
    protected void findViews() {
        tool_bar = findViewById(R.id.tool_bar);

        setSupportActionBar(tool_bar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("hello");
        mIv_icon = findViewById(R.id.iv_icon);
        mTv_content = findViewById(R.id.tv_content);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        app_barLayout = findViewById(R.id.app_barLayout);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //  mIv_icon.setTransitionName("share_image");//第二个Activity里的头像控件
        //  mTv_content.setTransitionName("share_text");//第二个Activity里的名字控件
        toolbarLayout.setTitle("DesignLibrarySample");
        toolbarLayout.setCollapsedTitleTextColor(Color.GRAY);
        toolbarLayout.setExpandedTitleColor(Color.GRAY);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finishAfterTransition();//不然返回时没有过度动画
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
