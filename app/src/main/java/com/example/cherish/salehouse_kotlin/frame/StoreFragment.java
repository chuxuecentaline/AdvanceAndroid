package com.example.cherish.salehouse_kotlin.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.chart.MPAndroidChartActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.AudioPlayActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.CanvasCicleDrawableActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.FlowerActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ShakeAnimalTextViewActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.TreeSetActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.YahuNewsLoadActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.AutoViewPagerActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.DroidPlugin360Activity;
import com.example.cherish.salehouse_kotlin.activity.wheel.RadarScanActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.ReflexActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.SplashActivity;
import com.example.cherish.salehouse_kotlin.adapter.BaseRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class StoreFragment extends BaseFragment {
    public static final String TAG = "STORE";
    private RecyclerView mRecyclerView;
    List<String> data = new ArrayList<>();
    private NewsAdapter mAdapter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        data.add("MPAndroidChart 使用");
        data.add("自定义轮播图");
        data.add("音频play");
        data.add("canvas 绘制圆形 drawable");
        data.add("花束直播点赞");
        data.add("雅虎新闻摘要加载");
        data.add("酷狗引导页");
        data.add("微信QQ附近好友雷达扫描");
        data.add("360 DroidPlugin 插件打开本地未安装apk 的页面");
        data.add("treeSet 列数相同获取每列的最大值");
        data.add("java 反射基础+注解");
    }

    @Override
    protected void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);


    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void initComplete() {
        for (int i = 0; i < 10; i++) {
            data.add(i + "");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
                switch (position){
                    case 0:
                        startActivity(MPAndroidChartActivity.class);
                        break;
                    case 1:
                        startActivity(AutoViewPagerActivity.class);
                        break;
                    case 2:
                        startActivity(AudioPlayActivity.class);
                        break;
                    case 3:
                        startActivity(CanvasCicleDrawableActivity.class);
                        break;
                    case 4:
                        startActivity(FlowerActivity.class);
                        break;
                    case 5:
                        startActivity(YahuNewsLoadActivity.class);
                        break;
                    case 6:
                        startActivity(SplashActivity.class);
                        break;
                    case 7:
                        startActivity(RadarScanActivity.class);
                        break;
                    case 8:
                        startActivity(DroidPlugin360Activity.class);
                        break;
                    case 9:
                        startActivity(TreeSetActivity.class);
                        break;
                    case 10:
                        startActivity(ReflexActivity.class);
                        break;
                    case 100:
                        startActivity(ShakeAnimalTextViewActivity.class);
                        break;
                }
            }
        });
    }
}
