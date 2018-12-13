package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.MyPagerAdapter;
import com.example.cherish.salehouse_kotlin.view.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 指示器 变色
 */
public class IndicatorColorTrackTextActivity extends BaseActivity {
    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    private List<ColorTrackTextView> mIndicators;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    List<TextView> mTextViews = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_indicator_color_track_text;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("指示器 变色").setRightMenu(R.menu
                .navigation_tab).create();
        mLinearLayout = findViewById(R.id.linearLayout);
        mViewPager = findViewById(R.id.viewPager);
        mIndicators = new ArrayList<>();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViewPager();
        initIndicator();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            TextView view = new TextView(this);
            view.setText(items[i]);
            view.setGravity(Gravity.CENTER);
            view.setPadding(0, 10, 0, 10);
            view.setTextSize(24);
            view.setLayoutParams(params);
            mTextViews.add(view);
        }
        mAdapter = new MyPagerAdapter(mTextViews);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                System.out.println("----" + position);
                System.out.println("----" + positionOffset);
                try {
                    ColorTrackTextView leftView = mIndicators.get(position);
                    leftView.setDirection(ColorTrackTextView.Direction.DIRECTION_RIGHT);
                    setCurrentItem(leftView, 1-positionOffset);
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.DIRECTION_LEFT);
                    setCurrentItem(right, positionOffset);
                } catch (Exception e) {

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < items.length; i++) {
            ColorTrackTextView trackTextView = new ColorTrackTextView(this);
            trackTextView.setText(items[i]);
            params.weight = 1;
            trackTextView.setPadding(0, 15, 0, 15);
            trackTextView.setTextSize(14);
            trackTextView.setLayoutParams(params);
            mIndicators.add(trackTextView);
            mLinearLayout.addView(trackTextView);
        }
        ColorTrackTextView leftView = mIndicators.get(0);
        leftView.setDirection(ColorTrackTextView.Direction.DIRECTION_RIGHT);
        setCurrentItem(leftView, 1);

    }

    /**
     * 设置当前文本的颜色
     *
     * @param trackTextView
     */
    private void setCurrentItem(ColorTrackTextView trackTextView, float progress) {
        trackTextView.setCurrentProgress(progress);
    }
}
