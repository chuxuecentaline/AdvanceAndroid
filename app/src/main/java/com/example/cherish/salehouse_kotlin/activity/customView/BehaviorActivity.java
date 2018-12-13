package com.example.cherish.salehouse_kotlin.activity.customView;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.CardAdapter;
import com.example.baselibrary.base.BaseActivity;

/**
 * 自定义Behavior
 */
public class BehaviorActivity extends BaseActivity {

    private RecyclerView recycler_view;
    private LinearLayoutManager mLayout;

    @Override
    public int getContentViewId() {
        return R.layout.activity_behavior;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle(R.string.title).setRightMenu(R.menu
                .navigation_tab).create();
        recycler_view = findViewById(R.id.recycler_view);
        mLayout = new LinearLayoutManager(this);
        mLayout.isAutoMeasureEnabled();
        recycler_view.setLayoutManager(mLayout);
        recycler_view.setAdapter(new CardAdapter(this));

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

}
