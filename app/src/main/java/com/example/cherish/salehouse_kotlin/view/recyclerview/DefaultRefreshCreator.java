package com.example.cherish.salehouse_kotlin.view.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 默认样式的头部刷新
 * Created by cherish
 */

public class DefaultRefreshCreator extends RefreshViewCreator {
    //加载数据的imageView
    private View mRefreshIv;
    private ValueAnimator mAnimator;
    private Context mContext;
    private float mProgress;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout
                .layout_refresh_header_view, parent, false);
        mRefreshIv = refreshView.findViewById(R.id.iv);
        mContext=context;
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中不断的旋转图片
        mRefreshIv.setRotation(rotate * 360);
    }

    @Override
    public void onRefreshing(View parent) {
        mAnimator = ObjectAnimator.ofFloat(mRefreshIv, "rotation", mProgress, 720);
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(-1);
        mAnimator.start();
    }

    @Override
    public void onStopRefresh() {
        mAnimator.cancel();
        mRefreshIv.setRotation(0);
        mRefreshIv.clearAnimation();
    }
}
