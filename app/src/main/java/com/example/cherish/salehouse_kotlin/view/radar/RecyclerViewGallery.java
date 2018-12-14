package com.example.cherish.salehouse_kotlin.view.radar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.cherish.salehouse_kotlin.adapter.RadarScanAdapter;

/**
 * Created by cherish
 */

public class RecyclerViewGallery extends RecyclerView {
    private int mItemWidth;
    private int mScrollX;
    private double mCurrentPosition;
    private double mMathPosition;
    private int mDivide;
    RadarScanAdapter mRadarScanAdapter;

    public RecyclerViewGallery(Context context) {
        this(context,null);
    }

    public RecyclerViewGallery(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecyclerViewGallery(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
      addItemDecoration(new com.example.cherish.salehouse_kotlin.view.radar.ItemDecoration(getContext(),getResources().getColor(android.R.color.transparent)));
      setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    public void setAdapter(RadarScanAdapter adapter) {
        super.setAdapter(adapter);
        mRadarScanAdapter=adapter;
        mRadarScanAdapter.setMiddleItem(1);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        //第二种实现方法
        mScrollX+=dx;
        if(mItemWidth!=0){
            mCurrentPosition = mScrollX*1d / mItemWidth;
            System.out.println("mScrollX___________"+mScrollX);
            System.out.println("mCurrentPosition___________"+mCurrentPosition);
            mMathPosition = Math.round(mCurrentPosition +0.1);
            System.out.println("mMathPosition___________"+mMathPosition);
        }


    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if(state==RecyclerView.SCROLL_STATE_IDLE){
            mRadarScanAdapter.setMiddleItem(getScrollPosition()+1);
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(getScrollPosition(), 0);

        }

    }


    /**
     * 获取滑动值, 滑动偏移 / 每个格子宽度
     */
    private int getScrollPosition() {
        return (int) (((double) computeHorizontalScrollOffset() / (double)
                mItemWidth) + 0.2f);
    }

    public void setParams(int itemWidth, int divide) {
        mItemWidth=itemWidth;
        mDivide=divide;
    }
}
