package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.CardAdapter;

public class ViewDragHelpActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_view_drag_help;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("ViewDragHelpVew垂直拖拽的动画").setRightMenu(R.menu
                .navigation_tab).create();
        recyclerView=  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(this));
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
