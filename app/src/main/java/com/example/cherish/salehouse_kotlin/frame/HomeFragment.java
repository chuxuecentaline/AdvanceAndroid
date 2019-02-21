package com.example.cherish.salehouse_kotlin.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.customView.AlphabeticalIndexActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.BehaviorActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.CardMoveActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ColorTrackTextActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ElementAnimalActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.IndicatorColorTrackTextActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.LoadingAnimalActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.PedometerActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ProgressAnimalActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.QQSlidingMenuActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.QqBubbleActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.QqSpaceStatusBarActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.RecyclerViewActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.SlidingMenuActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.TimeAnimalActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ViewDragHelpActivity;
import com.example.cherish.salehouse_kotlin.activity.customView.ViewTouchActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.BuilderNavigationBarActivity;
import com.example.cherish.salehouse_kotlin.activity.wheel.KotlinLockActivity;
import com.example.cherish.salehouse_kotlin.adapter.ViewAdapter;

import java.util.Arrays;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class HomeFragment extends BaseFragment {
    public static final String TAG = "HOME";
    private RecyclerView mRecyclerview;
    String[] mTitles = new String[]{"计步器", "颜色指示器", "viewPager联动指示器", "仿酷狗侧滑效果", "kotlin九宫格解锁",
            "自定义Behavior", "RecyclerView更全解析之 - 打造通用的下拉刷新上拉加载", "属性动画 - 58同城数据加载动画", ".贝塞尔曲线 - "
            + "QQ消息汽包拖拽", "共享元素动画", "自定义进度条", "时钟", "字母索引View", "view 的事件解析", "自定义柱状图", "贝塞尔曲线 - " +
            "" + "花束直播点赞效果", ".视差动画 - 雅虎新闻摘要加载", "Builder设计模式 - 构建整个应用的NavigationBar",
            "仿QQ6.0侧滑效果", "上下抽屉效果", "仿QQ空间沉浸式效果", "ViewDragHelp 侧滑删除或还原"};
    private Toolbar mTool_bar;
    private ViewAdapter mAdapter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void findViews(View view) {
        mTool_bar = view.findViewById(R.id.tool_bar);
        mRecyclerview = view.findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ViewAdapter(getActivity(), Arrays.asList(mTitles));
        mRecyclerview.setAdapter(mAdapter);
        mTool_bar.setTitle("自定义控件");
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initComplete() {
        mAdapter.setOnItemClickListener(new ViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), PedometerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ColorTrackTextActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), IndicatorColorTrackTextActivity
                                .class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), SlidingMenuActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), KotlinLockActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), BehaviorActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), RecyclerViewActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), LoadingAnimalActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getActivity(), QqBubbleActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(getActivity(), ElementAnimalActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(getActivity(), ProgressAnimalActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), TimeAnimalActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(getActivity(), AlphabeticalIndexActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(getActivity(), ViewTouchActivity.class));
                        break;
                    case 22:
                        startActivity(new Intent(getActivity(), BehaviorActivity.class));
                        break;
                    case 23:
                        startActivity(new Intent(getActivity(), BuilderNavigationBarActivity
                                .class));
                        break;
                    case 18:
                        startActivity(new Intent(getActivity(), QQSlidingMenuActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(getActivity(), ViewDragHelpActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(getActivity(), QqSpaceStatusBarActivity.class));
                        break;
                    case 21:
                        startActivity(new Intent(getActivity(), CardMoveActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
