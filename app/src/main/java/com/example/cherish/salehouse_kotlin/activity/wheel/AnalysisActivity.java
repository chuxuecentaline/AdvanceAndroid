package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.analysis.MVCActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.MVPActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.MVVMActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.RetrofitActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.RxJavaAnalysisActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.RxLoginActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.RxPayActivity;
import com.example.cherish.salehouse_kotlin.activity.frame.aidl.AidlFrameActivity;
import com.example.cherish.salehouse_kotlin.adapter.BaseRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    List<String> data = new ArrayList<>();
    private NewsAdapter mAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_analysis;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("源码分析").setRightMenu(R.menu
                .navigation_tab).create();
        data.add("RxJava 源码分析");
        data.add("Retrofit 源码分析");
        data.add("RxLogin");
        data.add("RxPay");
        data.add("aidl 框架");
        data.add("MVC");
        data.add("MVP");
        data.add("MVVM");
        mRecyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
                switch (position){
                    case 0:
                        startActivity(AnalysisActivity.this,RxJavaAnalysisActivity.class);
                        break;
                    case 1:
                        startActivity(AnalysisActivity.this,RetrofitActivity.class);
                        break;
                    case 2:
                        startActivity(AnalysisActivity.this,RxLoginActivity.class);
                        break;
                    case 3:
                        startActivity(AnalysisActivity.this,RxPayActivity.class);
                        break;
                    case 4:
                        startActivity(AnalysisActivity.this,AidlFrameActivity.class);
                        break;
                    case 5:
                        startActivity(AnalysisActivity.this,MVCActivity.class);
                        break;
                    case 6:
                        startActivity(AnalysisActivity.this,MVPActivity.class);
                        break;
                    case 7:
                        startActivity(AnalysisActivity.this,MVVMActivity.class);
                        break;
                }
            }
        });

    }
}
