package com.example.cherish.salehouse_kotlin.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.pattern.BuildActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.FactoryActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.IteratorActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.ObserverActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.PrototypeActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.ProxyActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.ResponsibilityChainActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.SingletonActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.StrategyActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.TemplateActivity;
import com.example.cherish.salehouse_kotlin.activity.pattern.WrapActivity;
import com.example.cherish.salehouse_kotlin.adapter.BaseRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 23 种设计模式
 * Created by cherish
 */

public class MineFragment extends BaseFragment {
    public static final String TAG = "MINE";
    private RecyclerView mRecyclerView;
    List<String> data = new ArrayList<>();
    private NewsAdapter mAdapter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        data.add("单例设计模式");
        data.add("Build设计模式");
        data.add("工厂设计模式");
        data.add("装饰设计模式");
        data.add("模板设计模式");
        data.add("策略设计模式");
        data.add("观察者设计模式");
        data.add("代理设计模式");
        data.add("原型设计模式");
        data.add("迭代器设计模式");
        data.add("责任链设计模式");
        data.add("外观模式");
        data.add("享元模式");
        data.add("中介模式");
        data.add("桥接模式");
        data.add("享元模式");
        data.add("命令模式");
        data.add("备忘录模式");
        data.add("状态模式");

    }

    @Override
    protected void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initComplete() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
                switch (position){
                    case 0:
                        startActivity(SingletonActivity.class);
                        break;
                    case 1:
                        startActivity(BuildActivity.class);
                        break;
                    case 2:
                        startActivity(FactoryActivity.class);
                        break;
                    case 3:
                        startActivity(WrapActivity.class);
                        break;
                    case 4:
                        startActivity(TemplateActivity.class);
                        break;
                    case 5:
                        startActivity(StrategyActivity.class);
                        break;
                    case 6:
                        startActivity(ObserverActivity.class);
                        break;
                    case 7:
                        startActivity(ProxyActivity.class);
                        break;
                    case 8:
                        startActivity(PrototypeActivity.class);
                        break;
                    case 9:
                        startActivity(IteratorActivity.class);
                        break;
                    case 10:
                        startActivity(ResponsibilityChainActivity.class);
                        break;
                    case 11:
                        startActivity(AppearanceActivity.class);
                        break;
                }
            }
        });

    }
}
