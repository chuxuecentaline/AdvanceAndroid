package com.example.cherish.salehouse_kotlin.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.wheel.AidlActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.AlertDialogActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.AnalysisActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.BuilderFoldNavigationBarActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.BuilderNavigationBarActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.DaoActicity;
import com.example.cherish.salehouse_kotlin.activity.wheel.IocActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.OkHttpActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.OkHttpEngineActicity;
import com.example.cherish.salehouse_kotlin.adapter.BaseRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class NewsFragment extends BaseFragment {
    public static final String TAG = "NEW";
    private RecyclerView mRecyclerView;
    private List<String> datas=new ArrayList<>();
    private NewsAdapter mAdapter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        datas.add("自己动手打造一套IOC注解框架");
        datas.add("Android studio 插件开发");
        datas.add("模板设计模式构建BaseActivity");
        datas.add("Android 热修复-打补丁技术");
        datas.add("Android 热修复-打补丁原来如此简单");
        datas.add("万能的AlertDialog");
        datas.add("Builder设计模式 - 构建整个应用的NavigationBar");
        datas.add("Builder设计模式 - 构建整个应用的 可折叠NavigationBar");
        datas.add("打造OkHttp 网络链式调用");
        datas.add("定制外部存储数据库");
        datas.add("AIDL 跨进程通信");
        datas.add("OkHttp 缓存+断点下载");
        datas.add("三方库源码分析");
    }

    @Override
    protected void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);

    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initComplete() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter(getActivity(), datas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
               switch (position){
                   case 0:
                       startActivity(IocActivity.class);
                       break;
                   case  1:
                       break;
                   case  2:
                       break;
                   case  3:
                       break;
                   case  4:
                       break;
                   case  5:

                       startActivity(AlertDialogActivity.class);
                       break;
                   case  6:
                       startActivity(BuilderNavigationBarActivity.class);
                       break;
                   case 7:
                       startActivity(BuilderFoldNavigationBarActivity.class);
                       break;
                   case 8:
                       startActivity(OkHttpEngineActicity.class);
                       break;
                   case 9:
                       startActivity(DaoActicity.class);
                       break;
                   case 10:
                       startActivity(AidlActivity.class);
                       break;
                   case 11:
                       startActivity(OkHttpActivity.class);
                       break;
                   case 12:
                       startActivity(AnalysisActivity.class);
                       break;
                       default:
                           break;
               }
            }
        });

    }
}
